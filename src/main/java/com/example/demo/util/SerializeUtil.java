package com.example.demo.util;

import java.io.*;

/**
 * @ClassName SerializeUtil 序列化工具类
 * @Author 陈杰
 * @Date 2019/6/6 000613:56
 * @Version 1.0
 **/
public class SerializeUtil {

    /**
     * 序列化对象
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj){
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] byteArray = baos.toByteArray();
            return byteArray;

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化对象
     * @param byteArray
     * @return
     */
    public static Object unSerialize(byte[] byteArray){
        ByteArrayInputStream bais = null;
        try {
            //反序列化为对象
            bais = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
