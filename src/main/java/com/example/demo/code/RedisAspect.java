package com.example.demo.code;

import com.example.demo.util.RedisCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisAspect
 * AOP实现Redis缓存处理
 * @Author 陈杰
 * @Date 2019/6/6 000613:59
 * @Version 1.0
 **/
@Component
@Aspect
public class RedisAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisAspect.class);
    @Autowired
    private RedisCache redisCache;

    /**
     * 拦截所有元注解RedisCache注解的方法
     */
    @Pointcut("@annotation(com.example.demo.config.RedisCache)")
    public void pointcutMethod(){

    }
    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     * @param joinPoint
     * @return
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint){
        //前置：从Redis里获取缓存
        //先获取目标方法参数
        long startTime = System.currentTimeMillis();
        String tesxtName = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            tesxtName  = String.valueOf(args[0]);
        }
        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];
        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();
        //redis中key格式：    tesxtName :章节名称
        String redisKey = tesxtName  + ":" + className + "." + methodName;
        Object obj = redisCache.getDataFromRedis(redisKey);
        if(obj!=null){
            System.out.println("**********从Redis中查到了数据**********");
            System.out.println("Redis的KEY值:"+redisKey);
            System.out.println("REDIS的VALUE值:"+obj.toString());
            return obj;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Redis缓存AOP处理所用时间:"+(endTime-startTime));
        System.out.println("**********没有从Redis查到数据**********");
        try{
            obj = joinPoint.proceed();
        }catch(Throwable e){
            e.printStackTrace();
        }
        System.out.println("**********开始从MySQL查询数据**********");
        //后置：将数据库查到的数据保存到Redis
        String code = redisCache.saveDataToRedis(redisKey,obj);
        if(code.equals("OK")){
            System.out.println("**********数据成功保存到Redis缓存!!!**********");
            System.out.println("Redis的KEY值:"+redisKey);
            System.out.println("REDIS的VALUE值:"+obj.toString());
        }
        return obj;
    }
}
