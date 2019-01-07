package com.yangcy;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcy.ehcache.manager.EhcacheService;

import redis.clients.jedis.JedisPool;

@ContextConfiguration(locations = {"classpath:spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestCase extends AbstractJUnit4SpringContextTests{
	@Autowired
    public EhcacheService ehcacheService;
	@Autowired
	public JedisPool jedisPool;

}
