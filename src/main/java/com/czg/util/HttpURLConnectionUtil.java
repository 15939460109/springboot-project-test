package com.czg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 用java模拟发送http网络请求，并获取json字符串
// java原生的使用方式
public class HttpURLConnectionUtil {

    public static String doGet(String urlStr) {
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            // 通过打开一个远程连接  需要强转类型
            conn = (HttpURLConnection)url.openConnection();

            // 设置请求方式  GET POST
            conn.setRequestMethod("GET");

            // 设置连接时间和读取时间
            // 连接时间:发送请求端连接到url地址端需要的时间(受距离长短、网络速度影响)
            // 读取时间:指连接成功后获取数据的时间(受到数据量和服务器处理速度的影响)
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(60000);

            // 可以指定接收json数据     服务端的key值为content-type
            conn.setRequestProperty("Accept", "application/json");

            // 发送请求
            conn.connect();

            // 成功处理
            if (conn.getResponseCode() != 200) {
                // TODO 此处应该添加异常处理
                return "error code";
            }

            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String str = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
        String result = doGet(str);
        System.out.println(result);
    }
}
