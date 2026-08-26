package com.qastand.audit.service;

import com.qastand.audit.entity.AuditEvent;
import com.qastand.audit.event.UserEvent;
import com.qastand.audit.repository.AuditEventRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventListener {

    private final AuditEventRepository repository;

    public UserEventListener(AuditEventRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${qa.events.queue}")
    public void receive(UserEvent event) {
        repository.save(new AuditEvent(
                event.eventType(),
                event.userId(),
                event.email(),
                event.role(),
                event.timestamp()
        ));
    }
}
