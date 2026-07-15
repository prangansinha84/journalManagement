package net.engineeringdigest.journalApp.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

public class KafkaConsumerService {
    @KafkaListener(topics = "journal-events", groupId = "journal-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
