package com.example.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

public class HelloClasseLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Object object= new DecryptClass().findClass("Hello").newInstance();
            Method method = object.getClass().getMethod("hello");
            method.invoke(object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name){
        String path ="D://Week_00//JAVA-000//Week_01//Hello.xlass";
        byte[] result = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
            byte[] data = new byte[bis.available()];
            bis.read(data);
            bis.close();
            for(int i = 0; i< data.length; i++){
                //解密
                data[i] =(byte)( 255-data[i]);
            }
            return  defineClass(name,data,0,data.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
