package com.yangcy.jedis;

import org.junit.Test;

import com.yangcy.SpringTestCase;

import redis.clients.jedis.Jedis;

public class JedisTest extends SpringTestCase{
	
	@Test
	public void test() {
		System.out.println("开始===");
		Jedis jedis = jedisPool.getResource();
		String val = jedis.get("yangcy");
		System.out.println(val);
		System.out.println(jedis.time());
		System.out.println(System.currentTimeMillis());
		jedis.expireAt("yangcy", System.currentTimeMillis()/1000+60);
		
		
		
		jedis.close();
	}
	
	
	
}