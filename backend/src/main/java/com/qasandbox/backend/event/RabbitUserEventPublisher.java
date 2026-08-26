package com.qasandbox.backend.event;

import com.qasandbox.backend.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ConditionalOnProperty(name = "qa.events.enabled", havingValue = "true")
public class RabbitUserEventPublisher implements UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final String exchange;
    private final String routingKey;

    public RabbitUserEventPublisher(
            RabbitTemplate rabbitTemplate,
            ObjectMapper objectMapper,
            @Value("${qa.events.exchange}") String exchange,
            @Value("${qa.events.routing-key}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public void publish(String eventType, User user) {
        UserEvent event = new UserEvent(eventType, user.getId(), user.getEmail(), user.getRole(), Instant.now());
        try {
            MessageProperties properties = new MessageProperties();
            properties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            rabbitTemplate.send(exchange, routingKey, new Message(objectMapper.writeValueAsBytes(event), properties));
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Could not serialize user event", exception);
        }
    }
}
