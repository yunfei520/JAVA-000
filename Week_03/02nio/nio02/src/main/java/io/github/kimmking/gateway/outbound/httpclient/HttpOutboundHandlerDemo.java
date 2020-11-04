package io.github.kimmking.gateway.outbound.httpclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;

public class HttpOutboundHandlerDemo {
    private static Logger logger = LoggerFactory.getLogger(HttpOutboundHandlerDemo.class);
    private CloseableHttpClient httpclient;
    private ExecutorService proxyService;
    private String backendUrl;

    public HttpOutboundHandlerDemo(String backendUrl) {
        this.backendUrl = backendUrl;
        httpclient = HttpClients.createDefault();
    }


    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullRequest.uri();
        doGetMethod(fullRequest, ctx, url);
    }

    // get请求
    public String doGetMethod(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        //    System.out.println("URL"+url);
        //    System.out.println("inbound"+inbound);
        //    System.out.println("ctx"+ctx);
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
        } finally {
            // 关闭流
            try {
                in.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
