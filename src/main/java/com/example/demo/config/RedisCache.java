package com.example.demo.config;

import java.lang.annotation.*;
/**
 * 1.RetentionPolicy.SOURCE 这种类型的Annotations只在源代码级别保留,编译时就会被忽略
 * 2.RetentionPolicy.CLASS 这种类型的Annotations编译时被保留,在class文件中存在,但JVM将会忽略
 * 3.RetentionPolicy.RUNTIME 这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用.
 *@ClassName RedisCache 用来标识查询数据库的方法
 *@Author 陈杰
 *@Date 2019/6/6 000615:57
 *@Version 1.0
**/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
}
