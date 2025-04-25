package com.ecms.notification.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeadLetterListener {
	
//	@RabbitListener(queues = "${spring.rabbitmq.dlq.name}")
//	@ConditionalOnProperty(name = "rabbitmq.dlq.listener.enabled", havingValue = "true")
	public void receiveDeadLetter(String mesage) {
		
		log.info("Received from DLQ: {}", mesage);
	}
}
