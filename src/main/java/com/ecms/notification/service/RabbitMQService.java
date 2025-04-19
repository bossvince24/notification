package com.ecms.notification.service;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecms.notification.config.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private RabbitMQConfig rabbitMQConfig;
	
	public void sendMessage(String message) {
		log.info("Sending Message to the RabbitMQ Exchange: {}", message);
		
		HashMap<String, String> mapAuditLog = new HashMap<>();
		mapAuditLog.put("Username", message);
		mapAuditLog.put("TransactionDate", LocalDateTime.now().toString());
		mapAuditLog.put("Type", "Validate Api Key");
		
		String requestMessage = "";
		try {
			requestMessage = new ObjectMapper().writeValueAsString(mapAuditLog);
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			log.error(e.getMessage());
			e.printStackTrace();
		}
		rabbitTemplate.convertAndSend(rabbitMQConfig.exchangeName, "", requestMessage);
	}

}
