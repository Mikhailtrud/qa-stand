package com.qasandbox.backend.event;

import com.qasandbox.backend.entity.User;

public interface UserEventPublisher {

    void publish(String eventType, User user);
}
