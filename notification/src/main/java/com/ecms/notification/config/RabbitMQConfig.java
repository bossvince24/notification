package com.ecms.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${spring.rabbitmq.exchange.name}")
	public String exchangeName;
	
	@Value("${spring.rabbitmq.queue.name}")
	public String queueName;
	
	@Value("${spring.rabbitmq.dlq.exchange}")
	public String deadLetterExchange;
	
	@Value("${spring.rabbitmq.dlq.name}")
	public String deadLetterQueueName;
	
	@Value("${spring.rabbitmq.dlq.routing-key}")
	public String deadLetterRoutingKey;
	

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(exchangeName);
	}
	
//	@Bean
//	public DirectExchange mainExchange() {
//		return new DirectExchange(exchangeName);
//	}
	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(deadLetterExchange);
	}
		
	@Bean
	public Queue mainQueue() {
		return QueueBuilder.durable(queueName)
				.withArgument("x-dead-letter-exchange", deadLetterExchange)
				.withArgument("x-dead-letter-routing-key", deadLetterRoutingKey)
				.build();
	}
	
	@Bean
	public Binding mainBinding(Queue mainQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(mainQueue).to(fanoutExchange);
	}
	
//	@Bean
//	public Binding mainBinding(Queue mainQueue, DirectExchange directExchange) {
//		return BindingBuilder.bind(mainQueue).to(directExchange).with("event-main");
//	}
	
	@Bean
	public Queue deadLetterQueue() {
		return QueueBuilder.durable(deadLetterQueueName).build();
	}
	
	@Bean
	public Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(deadLetterQueue).to(directExchange).with(deadLetterRoutingKey);
	}	
}
