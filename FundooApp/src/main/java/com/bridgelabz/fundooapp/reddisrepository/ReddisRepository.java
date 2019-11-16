package com.bridgelabz.fundooapp.reddisrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooapp.model.UserInformation;

@Repository
public class ReddisRepository {
	
//	@Autowired
//	private RedisTemplate<String, UserInformation> redisTemplate;
//
//    private HashOperations<String, Long, UserInformation> hashOperations;
//
//
//    public ReddisRepository(RedisTemplate<String, UserInformation> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//
//        hashOperations = redisTemplate.opsForHash();
//    }
//	 
//	 public void save(UserInformation user) {
//		 System.out.println("user"+ "  "+user);
//	        hashOperations.put("USER", user.getUserId(), user);
//	    }

}
