package com.yangcy;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class EhcacheConfigMain {
	public static void main(String[] args) {
		CacheConfiguration cacheConfig = new CacheConfiguration("lt.ecache", 50)
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU) // 设置调度算法
                .overflowToDisk(false) // 设置是否缓存到硬盘
                .eternal(false) // 设置是否过期
                .timeToLiveSeconds(60) // 对象存活时间
                .timeToIdleSeconds(30) // 调度设置最大不活动时间
                .diskPersistent(false) // 是否在磁盘上持久化。指重启jvm后，数据是否有效。默认为false。
                .diskExpiryThreadIntervalSeconds(0);// 设置对象检测线程运行时间间隔
        Configuration config = new Configuration();
        config.addCache(cacheConfig);
        CacheManager manager = CacheManager.create(config);
        
        // 创建Cache对象
        Cache cache = manager.getCache("lt.ecache");
        
        //将对象放入缓存    
        Element element = new Element("hello", "world");
        Element element2 = new Element("aaa", "111");
        Element element3 = new Element("bbb", "222");
        Element element4 = new Element("bbb", "222");
        cache.put(element);
        cache.put(element2);
        cache.put(element3);
        cache.put(element4);//key相同时会被覆盖
        
		cache.getKeys().forEach(v->System.out.println(v.toString()));	
	}
}
