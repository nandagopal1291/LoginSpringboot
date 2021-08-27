package com.spring.login;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
@KafkaListener(topics="products", groupId="group_id")
public class KafkaConsumer {
	public static String prodlist="";	
	
	@KafkaHandler
	public void consumeFromTopic(String message) {
		System.out.println("Consummed message "+message);
		prodlist= message;
	}
	
	public static String getProdlist(){
		return prodlist;
	}


}
