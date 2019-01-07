package com.yangcy.memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;
 
import net.spy.memcached.CASValue;
import net.spy.memcached.CASResponse;
import net.spy.memcached.MemcachedClient;
 
public class MemcachedGets {
   public static void main(String[] args) {
   
      try{
   
         // 连接本地的 Memcached 服务
         MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
         System.out.println("Connection to server sucessful.");
 
         // 添加数据
         Future fo = mcc.set("runoob", 900, "Free Education");
 
         // 输出执行 set 方法后的状态
         System.out.println("set status:" + fo.get());
            
         // 从缓存中获取键为 runoob 的值
         System.out.println("runoob value in cache - " + mcc.get("runoob"));
 
         // 通过 gets 方法获取 CAS token（令牌）
         CASValue casValue = mcc.gets("runoob");
 
         // 输出 CAS token（令牌） 值
         System.out.println("CAS value in cache - " + casValue);
 
         // 关闭连接
         mcc.shutdown();
         
      }catch(Exception ex) {
         System.out.println(ex.getMessage());
      }
   }
}