package com.spring.login;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyTopicConsumer {

    private final static List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "products", groupId = "group_id")
    public void listen(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }

    public static List<String> getMessages() {
    	System.out.println(messages);
        return messages;
    }

}