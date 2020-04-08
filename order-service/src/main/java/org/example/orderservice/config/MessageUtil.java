package org.example.orderservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MessageUtil {
    private final TopicConfigProperties topicConfigProperties;
    private final ApplicationContext context;
    @PostConstruct
    public void init() {
        topicConfigProperties.names.stream()
                .forEach(this::createTopic);
    }

    private void createTopic(String name) {
        TopicExchange topicExchange=ExchangeBuilder.topicExchange(name).build();
        ((ConfigurableApplicationContext)context).getBeanFactory().registerSingleton(name,topicExchange);
    }
}
