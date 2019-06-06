package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redis配置参数实体类
 * @ClassName RedisProperties
 * @Author 陈杰
 * @Date 2019/6/6 00069:24
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String database;

    private String host;

    private String port;

    private String password;

    private String maxActive;

    private String maxWait;

    private String maxIdle;

    private String minIdle;

    private String timeout;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaxActive() {
        return maxActive;
    }
    @Value("${spring.redis.jedis.pool.max-active}")
    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxWait() {
        return maxWait;
    }
    @Value("${spring.redis.jedis.pool.max-wait}")
    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public String getMaxIdle() {
        return maxIdle;
    }
    @Value("${spring.redis.jedis.pool.max-idle}")
    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMinIdle() {
        return minIdle;
    }
    @Value("${spring.redis.jedis.pool.min-idle}")
    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getTimeout() {
        return timeout;
    }
    @Value("${spring.redis.jedis.timeout}")
    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "RedisProperties{" +
                "database='" + database + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", password='" + password + '\'' +
                ", maxActive='" + maxActive + '\'' +
                ", maxWait='" + maxWait + '\'' +
                ", maxIdle='" + maxIdle + '\'' +
                ", minIdle='" + minIdle + '\'' +
                ", timeout='" + timeout + '\'' +
                '}';
    }
}
