CREATE TABLE audit_events
(
    id            BIGSERIAL PRIMARY KEY,
    event_type    VARCHAR(50) NOT NULL,
    user_id       BIGINT NOT NULL,
    email         VARCHAR(255) NOT NULL,
    role          VARCHAR(50) NOT NULL,
    event_time    TIMESTAMP WITH TIME ZONE NOT NULL,
    received_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);
