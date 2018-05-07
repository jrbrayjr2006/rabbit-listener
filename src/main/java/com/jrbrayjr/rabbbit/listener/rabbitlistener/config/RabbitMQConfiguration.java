package com.jrbrayjr.rabbbit.listener.rabbitlistener.config;

import com.jrbrayjr.rabbbit.listener.rabbitlistener.listeners.BrayRabbitMQMessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    private static final String TEST_QUEUE_NAME = "TestQueue";

    @Value(value = "localhost")
    String hostname;

    @Bean
    public Queue testQueue() {
        return new Queue(TEST_QUEUE_NAME, true);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new CachingConnectionFactory(hostname);
        ((CachingConnectionFactory) connectionFactory).setUsername("guest");
        ((CachingConnectionFactory) connectionFactory).setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(testQueue());
        simpleMessageListenerContainer.setMessageListener(new BrayRabbitMQMessageListener());
        return simpleMessageListenerContainer;
    }
}
