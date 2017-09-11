package org.net.plat4j.sr.core.ebsRedis;

import net.plat4j.core.spring.BeanFactory;

import org.net.plat4j.common.utils.AppConfigure;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class EbsRedis {
	private static ShardedJedisPool pool = BeanFactory.getBean("shardedJedisPool");
	private static String EFFECTIVE_TIME="ebs.redis.effective.time";
	
	public static ShardedJedis  getJedis() {
		if(pool == null){
			pool = BeanFactory.getBean("shardedJedisPool");
		}
		return pool.getResource();
	}
	
	/**
	 * 向redis 中保存值，无时间限制
	 * @param key
	 * @param val
	 */
	public static void setValue(String key,String val){
		ShardedJedis jedis = getJedis();
		jedis.set(key, val);
		jedis.close();
	}
	
	/**
	 * 向redis中保存值，并设置有效时间
	 * @param key
	 * @param val
	 */
	public static void setValueNx(String key,String val){
		ShardedJedis jedis = getJedis();
		jedis.setex(key, AppConfigure.getInt(EFFECTIVE_TIME), val);
		jedis.close();
	}
	
	/**
	 * 通过key 获取值
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		ShardedJedis jedis = getJedis();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	
	/**
	 * 通过key删除redis中的值
	 * @param key
	 */
	public static void delValue(String key){
		ShardedJedis jedis = getJedis();
		jedis.del(key);
		jedis.close();
	}
}
