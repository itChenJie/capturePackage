package com.example.demo.service.impl;

import com.example.demo.service.RedisServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import com.alibaba.fastjson.JSON;
/**
 * @ClassName RedisServerImpl
 * @Description TODO
 * @Author 陈杰
 * @Date 2019/6/5 000518:07
 * @Version 1.0
 **/
public class RedisServerImpl implements RedisServer {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public <T> void put(String key, T obj) {
        redisTemplate.opsForValue().set(key,JSON.toJSONString(obj));
    }

    @Override
    public <T> void put(String key, T obj, int timeout) {
        put(key,obj,timeout,TimeUnit.MINUTES);
    }

    @Override
    public <T> void put(String key, T obj, int timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(obj),timeout,unit);
    }

    @Override
    public <T> T get(String key, Class<T> cls) {
        return JSON.parseObject(JSON.toJSONString(redisTemplate.opsForValue().get(key)), cls);
    }

    @Override
    public <E, T extends Collection<E>> T get(String key, Class<E> cls, Class<T> collectionCls) {
        return null;
    }

    @Override
    public <T> T putIfAbsent(String key, Class<T> cls, Supplier<T> supplier) {
        return null;
    }

    @Override
    public <T> T putIfAbsent(String key, Class<T> cls, Supplier<T> supplier, int timeout) {
        return null;
    }

    @Override
    public <E, T extends Collection<E>> T putIfAbsent(String key, Class<E> cls, Class<T> collectionCls, Supplier<T> supplier) {
        return null;
    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public void del(String key) {

    }

    @Override
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return false;
    }

    @Override
    public boolean expire(String key, long timeout) {
        return false;
    }

    @Override
    public void put(String key, String value) {

    }

    @Override
    public void put(String key, String value, int timeout) {

    }

    @Override
    public void put(String key, String value, int timeout, TimeUnit unit) {

    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void putHash(String key, Map<Object, Object> m) {

    }

    @Override
    public Map<Object, Object> getHash(String key) {
        return null;
    }
}
