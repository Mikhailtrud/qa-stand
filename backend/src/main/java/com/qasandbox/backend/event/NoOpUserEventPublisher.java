package com.qasandbox.backend.event;

import com.qasandbox.backend.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "qa.events.enabled", havingValue = "false", matchIfMissing = true)
public class NoOpUserEventPublisher implements UserEventPublisher {

    @Override
    public void publish(String eventType, User user) {
        // Integrations are opt-in so isolated backend/mobile environments remain usable.
    }
}
