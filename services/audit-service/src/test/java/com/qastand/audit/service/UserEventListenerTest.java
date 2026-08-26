package com.qastand.audit.service;

import com.qastand.audit.entity.AuditEvent;
import com.qastand.audit.event.UserEvent;
import com.qastand.audit.repository.AuditEventRepository;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserEventListenerTest {

    @Test
    void storesReceivedUserEvent() {
        AuditEventRepository repository = mock(AuditEventRepository.class);
        UserEventListener listener = new UserEventListener(repository);

        listener.receive(new UserEvent("USER_UPDATED", 7L, "user@example.com", "ADMIN", Instant.EPOCH));

        verify(repository).save(argThat(event ->
                event.getUserId().equals(7L)
                        && event.getEventType().equals("USER_UPDATED")
                        && event.getRole().equals("ADMIN")
        ));
    }
}
