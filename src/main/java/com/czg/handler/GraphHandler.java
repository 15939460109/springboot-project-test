package com.czg.handler;

import com.czg.bean.GraphBean;
import com.czg.util.HttpClientUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphHandler {

    public static String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=chinaDayList,chinaDayAddList,nowConfirmStatis,provinceCompare";

    public static List<GraphBean> getGraphData() {
        List<GraphBean> result = new ArrayList<>();
        // 获取请求数据
        String respJson = HttpClientUtil.doGet(url);

        // 将json字符串转化成Map集合
        Gson gson = new Gson();
        Map subMap = gson.fromJson(respJson, Map.class);

        Map dataMap = (Map)subMap.get("data");
        List chinaDayList = (List)dataMap.get("chinaDayList");
        for (int i = 0; i < chinaDayList.size(); i++) {
            Map beanMap = (Map)chinaDayList.get(i);
            String date = (String)beanMap.get("date");
            double nowConfirm = (Double)beanMap.get("nowConfirm");

            GraphBean bean = new GraphBean();
            bean.setDate(date);
            bean.setNowConfirm((int)nowConfirm);

            result.add(bean);
        }
        return result;
    }

    public static void main(String[] args) {
        List<GraphBean> result = getGraphData();
        System.out.println(result);
    }
}
