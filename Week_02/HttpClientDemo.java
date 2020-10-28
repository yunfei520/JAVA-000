package com.example.demo;


import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.io.InputStream;

public class HttpClientDemo {

    public static void main(String[] args) {
        doGetMethod("http://localhost:8808/test");
    }
    // get请求
    public static String doGetMethod(String url){
        // 获取httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet hg = new HttpGet(url);
        // 设置参数
        hg.addHeader("Content-Type", "text/html;charset=utf-8");

        String result = null;
        InputStream in = null;
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(hg);
            in = response.getEntity().getContent();
            result = IOUtils.toString(in);
            System.out.println("响应状态码：" + response.getStatusLine());
            System.out.println("返回内容：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭流
            try {
                in.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result ;
    }

}
