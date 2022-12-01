package com.example.springboot2022novc.tengxunapi;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpUtils {

    @Bean
    public String getData() throws IOException {
        //请求参数设置
        RequestConfig requestConfig = RequestConfig.custom()
                //读取目标服务器数据超时时间
                .setSocketTimeout(10000)
                //连接目标服务器超时时间
                .setConnectTimeout(10000)
                //从连接池获取连接的超时时间
                .setConnectionRequestTimeout(10000)
                .build();

        CloseableHttpClient httpClient = null;
        HttpGet request = null;
        CloseableHttpResponse response = null;
        try {
            //创建HttpClient
            httpClient = HttpClients.createDefault();
            //使用url构建get请求
            request = new HttpGet("https://c.m.163.com/ug/api/wuhan/app/data/list-total");
            //填充请求设置
            request.setConfig(requestConfig);
            //发送请求，得到响应
            response = httpClient.execute(request);

            //获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //状态码200 正常
            if (statusCode == 200) {
                //解析响应数据
                HttpEntity entity = response.getEntity();
                //字符串格式数据
                String string = EntityUtils.toString(entity, "UTF-8");
//               System.out.println("字符串格式：" + string);
                return string;

            } else {
                throw new HttpResponseException(statusCode, "响应异常");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (request != null) {
                request.releaseConnection();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }

    }

}