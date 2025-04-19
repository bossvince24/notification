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

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(exchangeName);
	}
	
//	@Bean
//	public FanoutExchange deadLetterFanoutExchange() {
//		return new FanoutExchange("event-dlx");
//	}
	
	@Bean
	public DirectExchange deadLetterExchange() {
		return new DirectExchange("event-dlx");
	}
	
	@Bean
	public Queue mainQueue() {
		return QueueBuilder.durable(queueName)
				.withArgument("x-dead-letter-exchange", "event-dlx")
				.withArgument("x-dead-letter-routing-key", queueName + ".dlq")
				.build();
	}
	
	@Bean
	public Queue deadLetterQueue() {
		return QueueBuilder.durable(queueName + ".dlq").build();
	}
	
//	@Bean
//	public Queue queue() {
//		return new Queue(queueName);
//	}
	
//	@Bean
//	public Binding binding(Queue queue, FanoutExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange);
//	}
	
	@Bean
	public Binding mainBinding() {
		return BindingBuilder.bind(mainQueue()).to(fanoutExchange());
	}
	
	@Bean
	public Binding dlqBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(queueName + ".dlq");
	}
}
