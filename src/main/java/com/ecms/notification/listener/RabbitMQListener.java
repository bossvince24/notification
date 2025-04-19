package com.ecms.notification.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQListener {
	
	@RabbitListener(queues = "${spring.rabbitmq.queue.name}")
	public void receiveMessage(String message) {
		
		if (message.contains("fails")) {
			throw new RuntimeException("Simulated processing error");
		}
		log.info("Received Message from RabbitMQ Queue: {}", message);
	}
}
