package com.bridgelabz.fundooapp.reddisrepository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooapp.model.NoteInformation;

@Repository
public class ReddisRepository {
    private static final String KEY = "notes";


	
	
	private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, Object> hashOperations;


    public ReddisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }
	 
	 public void save(NoteInformation note) {
		 System.out.println("note"+ "  "+note);
	        hashOperations.put("note", note.getId(), note);
	    }

}
