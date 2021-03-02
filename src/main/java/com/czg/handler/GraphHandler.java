package com.czg.handler;

import com.czg.bean.GraphBarBean;
import com.czg.bean.GraphAddBean;
import com.czg.bean.GraphBean;
import com.czg.bean.GraphPieBean;
import com.czg.util.HttpClientUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GraphHandler {

    public static String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=chinaDayList,chinaDayAddList,nowConfirmStatis,provinceCompare";
    public static String url2 = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=asymptomaticProvince";

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

    public static List<GraphAddBean> getGraphAddData() {
        List<GraphAddBean> result = new ArrayList<>();
        // 获取请求数据
        String respJson = HttpClientUtil.doGet(url);

        // 将json字符串转化成Map集合
        Gson gson = new Gson();
        Map subMap = gson.fromJson(respJson, Map.class);

        Map dataMap = (Map)subMap.get("data");
        List chinaDayAddList = (List)dataMap.get("chinaDayAddList");
        for (int i = 0; i < chinaDayAddList.size(); i++) {
            Map beanMap = (Map)chinaDayAddList.get(i);
            String date = (String)beanMap.get("date");
            double confirm = (Double)beanMap.get("confirm");
            double suspect = (Double)beanMap.get("suspect");

            GraphAddBean bean = new GraphAddBean();
            bean.setDate(date);
            bean.setConfirm((int)confirm);
            bean.setSuspect((int)suspect);
            result.add(bean);
        }
        return result;
    }

    public static List<GraphBarBean> getAsymptomaticData() {
        List<GraphBarBean> result = new ArrayList<>();

        String respJson = HttpClientUtil.doGet(url2);
        Gson gson = new Gson();
        Map subMap = gson.fromJson(respJson, Map.class);
        Map dataMap = (Map)subMap.get("data");
        Map map = (Map)dataMap.get("asymptomaticProvince");
        List list = (List)map.get("confirm");
        for (int i = 0; i < list.size(); i++) {
            Map tempMap = (Map)list.get(i);
            String province = (String)tempMap.get("province");
            double increase = (Double)tempMap.get("increase");
            double confirm = (Double)tempMap.get("confirm");

            GraphBarBean bean = new GraphBarBean();
            bean.setProvince(province);
            bean.setConfirm((int)confirm);
            bean.setIncrease((int)increase);
            result.add(bean);
        }

        return result;
    }

    public static List<GraphBarBean> asymptomaticAddData() {
        List<GraphBarBean> result = getAsymptomaticData();
        System.out.println(result);
        Collections.sort(result);
        return result;
    }

    public static List<GraphBarBean> asymptomaticData() {
        List<GraphBarBean> result = new ArrayList<>();

        String respJson = HttpClientUtil.doGet(url2);
        Gson gson = new Gson();
        Map subMap = gson.fromJson(respJson, Map.class);
        Map dataMap = (Map)subMap.get("data");
        Map map = (Map)dataMap.get("asymptomaticProvince");
        List list = (List)map.get("confirm");
        for (int i = 0; i < list.size(); i++) {
            Map tempMap = (Map)list.get(i);
            String province = (String)tempMap.get("province");
            double confirm = (Double)tempMap.get("confirm");

            GraphBarBean bean = new GraphBarBean();
            bean.setProvince(province);
            bean.setConfirm((int)confirm);
            result.add(bean);
        }

        return result;
    }

    public static List<GraphPieBean> getGraphPieData() {
        List<GraphPieBean> result = new ArrayList<>();

        String respStr = HttpClientUtil.doGet(url);
        Gson gson = new Gson();
        Map subMap = gson.fromJson(respStr, Map.class);
        Map dataMap = (Map)subMap.get("data");
        Map map = (Map)dataMap.get("nowConfirmStatis");

        for (Object o : map.keySet()) {
            String name = (String)o;
            switch (name) {
                case "gat":
                    name = "港澳台病例";
                    break;
                case "import":
                    name = "境外输出病例";
                    break;
                case "province":
                    name = "31省本土病例";
                    break;
            }

            double value = (Double)map.get(o);
            name += ":" + (int)value + "例";

            GraphPieBean bean = new GraphPieBean();
            bean.setName(name);
            bean.setValue((int)value);

            result.add(bean);
        }

        // 排序
        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {
//        List<GraphBean> result = getGraphData();
//        System.out.println(result);

//        List<AsymptomaticBean> result = asymptomaticData();
//        System.out.println(result);

        List<GraphPieBean> result = getGraphPieData();
        System.out.println(result);
    }
}
