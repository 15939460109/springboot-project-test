package com.czg.handler;

import com.czg.bean.DataBean;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class JsoupHandler {

    public static String htmlStr = "<html><head></head><body><p id=\"test\">hello html</p></body></html>";

    public static String urlStr = "https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline";

    public static void main(String[] args) {
        Document document = Jsoup.parse(htmlStr);

        // 通过id属性获取
//        Element element = document.getElementById("test");
//        System.out.println(element);
//        System.out.println(element.text());

        // 通过tag获取
//        Elements elements = document.getElementsByTag("p");
//        System.out.println(elements);
//        System.out.println(elements.get(0));

        // 通过正则表达式获取
        Element element = document.selectFirst("body p");
        Elements elements = document.select("body p");
        System.out.println(element);
        System.out.println(elements);
    }

    public static ArrayList<DataBean> getData() {
        ArrayList<DataBean> result = new ArrayList<>();
        try {
            Document document = Jsoup.connect(urlStr).get();
            Element element = document.getElementById("getAreaStat");
            // System.out.println(element);
            String data = element.data();
            // 截取字符串
            String subData = data.substring(data.indexOf("["), data.lastIndexOf("]") + 1);
            System.out.println(subData);
            // 解析json数据
            Gson gson = new Gson();
            ArrayList list = gson.fromJson(subData, ArrayList.class);
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map)list.get(i);
                String area = (String)map.get("provinceName");
                double nowConfirm = (Double)map.get("currentConfirmedCount");
                double confirm = (Double)map.get("confirmedCount");
                double heal = (Double)map.get("curedCount");
                double dead = (Double)map.get("deadCount");

                DataBean bean = new DataBean(area, (int)nowConfirm, (int)confirm, (int)heal, (int)dead);
                result.add(bean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
