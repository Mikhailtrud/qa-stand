package com.qasandbox.backend.service;

import com.qasandbox.backend.cache.UserCache;
import com.qasandbox.backend.client.ExternalProfileClient;
import com.qasandbox.backend.dto.user.CreateUserRequest;
import com.qasandbox.backend.dto.user.UpdateUserRequest;
import com.qasandbox.backend.dto.user.UserResponse;
import com.qasandbox.backend.entity.User;
import com.qasandbox.backend.entity.enums.UserRole;
import com.qasandbox.backend.event.UserEventPublisher;
import com.qasandbox.backend.exception.UserAlreadyExistsException;
import com.qasandbox.backend.mapper.UserMapper;
import com.qasandbox.backend.repository.UserRepository;
import com.qasandbox.backend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private final UserRepository repository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final UserMapper mapper = new UserMapper();
    private final UserEventPublisher publisher = mock(UserEventPublisher.class);
    private final UserCache cache = mock(UserCache.class);
    private final ExternalProfileClient externalClient = mock(ExternalProfileClient.class);
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(repository, passwordEncoder, mapper, publisher, cache, externalClient);
    }

    @Test
    void createsUserWithEncodedPasswordAndPublishesEvent() {
        CreateUserRequest request = new CreateUserRequest("User", "user@email.com", "password1", UserRole.USER);
        when(repository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.password())).thenReturn("encoded");
        when(repository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(10L);
            return user;
        });

        UserResponse response = service.createUser(request);

        assertThat(response.id()).isEqualTo(10L);
        verify(repository).save(argThat(user -> user.getPassword().equals("encoded")));
        verify(publisher).publish(eq("USER_CREATED"), any(User.class));
    }

    @Test
    void updateWithBlankPasswordKeepsExistingHashAndInvalidatesCache() {
        User user = user(3L, "old@email.com", "existing-hash");
        UpdateUserRequest request = new UpdateUserRequest("Updated", "new@email.com", UserRole.ADMIN, "");
        when(repository.findById(3L)).thenReturn(Optional.of(user));
        when(repository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(repository.save(user)).thenReturn(user);

        service.updateUser(3L, request);

        assertThat(user.getPassword()).isEqualTo("existing-hash");
        verify(passwordEncoder, never()).encode(anyString());
        verify(cache).evict(3L);
        verify(publisher).publish("USER_UPDATED", user);
    }

    @Test
    void updateEncodesExplicitPassword() {
        User user = user(3L, "old@email.com", "old-hash");
        UpdateUserRequest request = new UpdateUserRequest("Updated", "new@email.com", UserRole.USER, "newpass1");
        when(repository.findById(3L)).thenReturn(Optional.of(user));
        when(repository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode("newpass1")).thenReturn("new-hash");
        when(repository.save(user)).thenReturn(user);

        service.updateUser(3L, request);

        assertThat(user.getPassword()).isEqualTo("new-hash");
    }

    @Test
    void updateRejectsEmailOwnedByAnotherUser() {
        User user = user(3L, "old@email.com", "hash");
        User other = user(4L, "taken@email.com", "hash");
        UpdateUserRequest request = new UpdateUserRequest("Updated", "taken@email.com", UserRole.USER, null);
        when(repository.findById(3L)).thenReturn(Optional.of(user));
        when(repository.findByEmail(request.email())).thenReturn(Optional.of(other));

        assertThatThrownBy(() -> service.updateUser(3L, request))
                .isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    void getByIdUsesCachedPasswordFreeResponse() {
        UserResponse cached = new UserResponse(3L, "cached@email.com", "Cached", UserRole.USER);
        when(cache.get(3L)).thenReturn(Optional.of(cached));

        assertThat(service.getUserById(3L)).isSameAs(cached);
        verifyNoInteractions(repository);
    }

    @Test
    void deleteInvalidatesCache() {
        when(repository.existsById(3L)).thenReturn(true);

        service.deleteUser(3L);

        verify(repository).deleteById(3L);
        verify(cache).evict(3L);
    }

    private User user(Long id, String email, String password) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setName("User");
        user.setRole(UserRole.USER);
        user.setPassword(password);
        return user;
    }
}
