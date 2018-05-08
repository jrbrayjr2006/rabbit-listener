package com.jrbrayjr.rabbbit.listener.rabbitlistener.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {

    /**
     * Declaring a new Queue object
     * @return
     */
    @Bean
    public Queue fitnessQueue() {
        return new Queue("fitnessQueue", false);
    }

    /**
     * Use <code>QueueBuilder</code> to create the queue
     * @return
     */
    @Bean
    public Queue fitnessPlusQueue() {
        return QueueBuilder.durable("fitnessPlusQueue").exclusive().autoDelete().build();
    }

}
