package com.yangcy.ehcache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class SimpleTest {
    public static void main(String[] args) throws InterruptedException {
        URL url = SimpleTest.class.getClassLoader().getResource("ehcache.xml");

        CacheManager manager = CacheManager.create(url); 
            
        //创建Cache对象
        Cache cache = manager.getCache("lt.ecache");

        //cache缓存名称
        System.out.println("cache name: " + cache.getName());
        
        Element e = new Element("aa", "aa", 10, 10);
        System.err.println(e.isLifespanSet());
        cache.put(e);
        System.out.println(cache.get("aa"));
        Thread.sleep(15*1000);
        System.out.println(cache.get("aa"));//如果这个时候，期待cache是否过期。但是实际的情况是。ehcache依然能获取到相关数据.
          
        System.out.println("size: " + cache.getSize());       
        manager.shutdown();
    }

}