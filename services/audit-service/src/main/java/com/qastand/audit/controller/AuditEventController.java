package com.qastand.audit.controller;

import com.qastand.audit.entity.AuditEvent;
import com.qastand.audit.repository.AuditEventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit/events")
public class AuditEventController {

    private final AuditEventRepository repository;

    public AuditEventController(AuditEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<AuditEvent> getEvents() {
        return repository.findAllByOrderByReceivedAtDesc();
    }
}
