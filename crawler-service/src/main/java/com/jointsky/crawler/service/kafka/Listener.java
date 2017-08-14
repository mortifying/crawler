package com.jointsky.crawler.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;

/**
 * Author  zhangxiong
 * Date    17-8-2 上午10:43
 */
public class Listener {

    @KafkaListener(topics = {"test_topic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("consumer test_topic: " + message);
        }
    }

}
