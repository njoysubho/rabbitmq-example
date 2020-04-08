package org.example.kitchenservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MessageUtil {
    private final QueuePropertyConfiguration queuePropertyConfiguration;
    private final ApplicationContext context;
    @PostConstruct
    public void init(){
        queuePropertyConfiguration.names
                .stream()
                .forEach(this::createQueue);
    }

    private void createQueue(String name) {
        Queue queue = new Queue(name);
        Binding binding= BindingBuilder.bind(queue).to(new TopicExchange("order-topic")).with("order.created");
        ((ConfigurableApplicationContext)context).getBeanFactory().registerSingleton(name,binding);
    }

}
