package com.qastand.audit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "audit_events")
public class AuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_type", nullable = false)
    private String eventType;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String role;
    @Column(name = "event_time", nullable = false)
    private Instant eventTime;
    @Column(name = "received_at", nullable = false)
    private Instant receivedAt;

    protected AuditEvent() {
    }

    public AuditEvent(String eventType, Long userId, String email, String role, Instant eventTime) {
        this.eventType = eventType;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.eventTime = eventTime;
        this.receivedAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getEventType() { return eventType; }
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public Instant getEventTime() { return eventTime; }
    public Instant getReceivedAt() { return receivedAt; }
}
