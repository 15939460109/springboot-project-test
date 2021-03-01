package com.czg.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {

    public static String doGet(String urlStr) {
        String result = null;

        // 提供了闭合的httpClient对象
        CloseableHttpClient httpClient = null;
        // 也提供了闭合的相应对象
        CloseableHttpResponse httpResponse = null;

        try {
            // 使用默认创建方式
            httpClient = HttpClients.createDefault();

            // 创建一个HttpGet，传入url
            HttpGet httpGet = new HttpGet(urlStr);
            // 设置请求头的方式
            httpGet.setHeader("Accept", "application/json");

            // 设置请求参数
            // connectTimeout   连接时间
            // connectionRequestTimeout     共享连接池获取连接超时时间
            // socketTimeout    数据读取时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)
                    .setConnectionRequestTimeout(35000)
                    .setSocketTimeout(60000).build();
            httpGet.setConfig(requestConfig);

            // 执行请求，返回结果
            httpResponse = httpClient.execute(httpGet);
            // 从请求结果中获取数据
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        String str = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=chinaDayList,chinaDayAddList,nowConfirmStatis,provinceCompare";
        String result = doGet(str);
        System.out.println(result);
    }
}
