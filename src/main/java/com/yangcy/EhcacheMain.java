package com.yangcy;

import java.io.InputStream;
import java.net.URL;

import com.yangcy.bean.Dog;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheMain {

	public static void main(String[] args) {
		InputStream in = EhcacheMain.class.getClassLoader().getResourceAsStream("ehcache.xml");
		URL url = EhcacheMain.class.getClassLoader().getResource("ehcache.xml");
		URL url2 = EhcacheMain.class.getResource("/ehcache.xml");
		String path = System.getProperty("/ehcache.xml");
		
		System.err.println(in);
		System.err.println(url);
		System.err.println(url2);
		System.err.println(path);

		// 1. 创建缓存管理器
		CacheManager cacheManager = CacheManager.create(EhcacheMain.class.getResource("/ehcache.xml"));

		// 2. 获取缓存对象
		Cache cache = cacheManager.getCache("HelloWorldCache");

		// 3. 创建元素
		Element element = new Element("key1", "value1");

		// 4. 将元素添加到缓存
		cache.put(element);

		// 5. 获取缓存
		Element value = cache.get("key1");
		System.out.println(value);
		System.out.println(value.getObjectValue());

		// 6. 删除元素
		cache.remove("key1");

		Dog dog = new Dog((short) 3, "taidi", 20.0F);
		Element element2 = new Element("taidi", dog);
		cache.put(element2);
		Element value2 = cache.get("taidi");
		Dog dog2 = (Dog) value2.getObjectValue();
		System.out.println(dog2);

		System.out.println(cache.getSize());

		// 7. 刷新缓存
		cache.flush();

		// 8. 关闭缓存管理器
		cacheManager.shutdown();
	}

}
