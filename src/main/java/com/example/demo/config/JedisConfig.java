package com.example.demo.config;

import com.example.demo.entity.RedisProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisConfig Redis配置类
 * @Description TODO
 * @Author 陈杰
 * @Date 2019/6/6 00069:52
 * @Version 1.0
 **/
@Configuration
public class JedisConfig {
    @Autowired
    private RedisProperties redisProperties;

    /**
     * 创建连接池并且封装成一个Bean
     * @param config
     * @return
     */
    @Bean(name= "jedisPool")
    @Autowired
    public JedisPool jedisPool(GenericObjectPoolConfig config){
        return new JedisPool(config,redisProperties.getHost(),Integer.parseInt(redisProperties.getPort()),
                Integer.parseInt(redisProperties.getTimeout()),null);
    }



    /**
     * jedis的基本配置
     * @return
     */
    @Bean(name= "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig (){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(Integer.parseInt(redisProperties.getMaxIdle()));
        config.setMaxIdle(Integer.parseInt(redisProperties.getMinIdle()));
        config.setMaxWaitMillis(Long.parseLong(redisProperties.getMaxWait()));
        config.setTestOnBorrow(true);
        return config;
    }


}
