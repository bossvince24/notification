<<<<<<< HEAD
//package com.ecms.notification.listener;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class DeadLetterListener {
//	
//	@RabbitListener(queues = "${spring.rabbitmq.queue.name}.dlq")
//	public void receiveDeadLetter(String message) {
//		
//		log.info("Message sent to DLQ: {}", message);
//	}
//}
=======
//package com.ecms.notification.listener;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class DeadLetterListener {
//	
//	@RabbitListener(queues = "${spring.rabbitmq.queue.name}.dlq")
//	public void receiveDeadLetter(String message) {
//		
//		log.info("Message sent to DLQ: {}", message);
//	}
//}
>>>>>>> 613eb21 (add dlq)
