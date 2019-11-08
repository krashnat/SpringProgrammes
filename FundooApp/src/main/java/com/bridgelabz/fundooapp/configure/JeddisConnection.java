package com.bridgelabz.fundooapp.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.bridgelabz.fundooapp.model.UserInformation;

@Configuration
public class JeddisConnection {
	
	@ Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String,UserInformation> redisTemplet(){
		RedisTemplate<String, UserInformation> redisTemplete=new RedisTemplate<>();
		redisTemplete.setConnectionFactory(jedisConnectionFactory());
		return redisTemplete;
		
	}
	

}
