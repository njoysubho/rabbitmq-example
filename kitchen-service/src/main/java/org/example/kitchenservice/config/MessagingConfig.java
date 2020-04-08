package org.example.kitchenservice.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("rabbit");
        cachingConnectionFactory.setPassword("rabbit");
        Connection connection = cachingConnectionFactory.createConnection();
        return cachingConnectionFactory;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(QueuePropertyConfiguration queuePropertyConfiguration) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory());
        simpleMessageListenerContainer.setQueueNames(queuePropertyConfiguration.names.toArray(new String[0]));
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter());
        return simpleMessageListenerContainer;
    }

    @Bean
    public MessageDispatcher messageDispatcher(){
        return new MessageDispatcher();
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(messageDispatcher());
        messageListenerAdapter.setDefaultListenerMethod("onMessage");
        return messageListenerAdapter;
    }

}
