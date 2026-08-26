package com.qastand.audit.controller;

import com.qastand.audit.entity.AuditEvent;
import com.qastand.audit.repository.AuditEventRepository;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuditEventControllerTest {

    @Test
    void returnsStoredEvents() {
        AuditEventRepository repository = mock(AuditEventRepository.class);
        AuditEvent event = new AuditEvent("USER_CREATED", 1L, "test@email.com", "ADMIN", Instant.EPOCH);
        when(repository.findAllByOrderByReceivedAtDesc()).thenReturn(List.of(event));

        assertThat(new AuditEventController(repository).getEvents()).containsExactly(event);
    }
}
