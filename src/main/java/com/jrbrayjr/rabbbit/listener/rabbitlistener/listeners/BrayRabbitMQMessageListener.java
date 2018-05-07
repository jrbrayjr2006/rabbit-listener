package com.jrbrayjr.rabbbit.listener.rabbitlistener.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class BrayRabbitMQMessageListener implements MessageListener {

    Logger LOG = LoggerFactory.getLogger(BrayRabbitMQMessageListener.class);

    @Override
    public void onMessage(Message message) {
        LOG.debug("Entered onMessage(Message)...");
        byte[] messageBody = message.getBody();
        String decrypedString = new String(messageBody);
        LOG.info("The message body is:  " + decrypedString);
    }
}
