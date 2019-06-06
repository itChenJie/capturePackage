package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis工具类用来从Redis里get值和set值
 * @ClassName RedisCache
 * @Description TODO
 * @Author 陈杰
 * @Date 2019/6/6 000610:41
 * @Version 1.0
 **/
@Component("redisCache")
public class RedisCache {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 得到连接池
     * @return
     */
    private JedisPool getJedisPool(){
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool){
        this.jedisPool = jedisPool;
    }

    /**
     * 从Redis缓存获取数据
     * @param redisKey
     * @return
     */
    public Object getDataFromRedis(String redisKey){
        //获取连接资源
        Jedis jedis = jedisPool.getResource();
        byte[] byteArray = jedis.get(redisKey.getBytes());

        if(byteArray != null){
            //获取反序列化对象
            return SerializeUtil.unSerialize(byteArray);
        }
        return null;
    }

    /**
     * 保存数据到Redis
     * @param redisKey
     */
    public String saveDataToRedis(String redisKey,Object obj){
        //获取序列化对象
        byte[] bytes = SerializeUtil.serialize(obj);

        Jedis jedis = jedisPool.getResource();

        String code = jedis.set(redisKey.getBytes(), bytes);

        return code;
    }

}
