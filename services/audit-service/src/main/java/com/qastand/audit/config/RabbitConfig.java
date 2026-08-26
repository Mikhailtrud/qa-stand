package com.qastand.audit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    TopicExchange userEventsExchange(@Value("${qa.events.exchange}") String name) {
        return new TopicExchange(name, true, false);
    }

    @Bean
    Queue userEventsQueue(@Value("${qa.events.queue}") String name) {
        return new Queue(name, true);
    }

    @Bean
    Binding userEventsBinding(
            Queue userEventsQueue,
            TopicExchange userEventsExchange,
            @Value("${qa.events.routing-key}") String routingKey
    ) {
        return BindingBuilder.bind(userEventsQueue).to(userEventsExchange).with(routingKey);
    }

    @Bean
    MessageConverter rabbitMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
