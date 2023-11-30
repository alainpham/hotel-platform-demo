package net.alainpham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);

    @JmsListener(destination = "sms")
    public void receiveMessage(String message) {
        logger.info("Received message: " + message);
        logger.info("Notification Sent per sms");
    }
}