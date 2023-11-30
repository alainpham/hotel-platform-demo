package net.alainpham.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.alainpham.model.Notification;

@RestController
public class NotificationService {
    
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("notify")
    public Notification notify(@RequestBody Notification notification) throws JmsException, JsonProcessingException{
        
        jmsTemplate.convertAndSend("sms", objectMapper.writeValueAsString(notification) );
        jmsTemplate.convertAndSend("email", objectMapper.writeValueAsString(notification) );

        return notification;
    }
}
