package com.bridgelabz.fundooapp.configure;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundooapp.responses.MailObject;

@Component
public class RabbitMQSender {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("rmq.rube.exchange")
	private String exchange;
	
	@Value("rube.key")
	private String routingkey;	
	
	public void send(MailObject message) {
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
		

	    
	}

}
