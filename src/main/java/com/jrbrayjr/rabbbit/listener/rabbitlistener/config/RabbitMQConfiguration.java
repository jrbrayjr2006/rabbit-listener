package com.jrbrayjr.rabbbit.listener.rabbitlistener.config;

import com.jrbrayjr.rabbbit.listener.rabbitlistener.listeners.BrayRabbitMQMessageListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
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

    private static final String TEST_EXCHANGE_NAME = "tempExchange.topic";

    private static final String TEST_ROUTING_KEY_NAME = "tempRoutingKey";

    @Value(value = "localhost")
    String hostname;

    @Bean
    public Queue testQueue() {
        return new Queue(TEST_QUEUE_NAME, true);
    }

    @Bean
    public Exchange tempExchange() {
        Exchange tempExchange = ExchangeBuilder.topicExchange(TEST_EXCHANGE_NAME).durable(true).build();
        return tempExchange;
    }

    @Bean
    public Binding testBinding() {
        Binding binding = new Binding(TEST_QUEUE_NAME, Binding.DestinationType.QUEUE, TEST_EXCHANGE_NAME, TEST_ROUTING_KEY_NAME, null);
        return binding;
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
