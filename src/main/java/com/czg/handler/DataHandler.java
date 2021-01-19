package com.czg.handler;

import com.czg.bean.DataBean;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataHandler {

    private static String testStr = "{\"name\":\"独钓寒江雪\"}";

    public static void main(String[] args) throws IOException {
//        Gson gson = new Gson();
//        Map result = gson.fromJson(testStr, Map.class);
//        System.out.println(result);

        List<DataBean> result = readData();
        System.out.println(result);
    }

    public static List<DataBean> readData() throws IOException {
        // 文件输入流
        File file = new File("temp.txt");
        FileReader fr = new FileReader(file);
        // 缓存数组
        char[] cBuf = new char[1024];
        // 每次读取长度
        int cRead = 0;
        // 每次读取后拼接到字符串后面
        StringBuilder builder = new StringBuilder();
        while ((cRead = fr.read(cBuf)) > 0) {
            builder.append(new String(cBuf, 0, cRead));
        }
        // 关闭流
        fr.close();

//        System.out.println(builder.toString());

        Gson gson = new Gson();
        // 将json数据包装成map
        Map map = gson.fromJson(builder.toString(), Map.class);

        ArrayList areaList = (ArrayList)map.get("areaTree");
        Map dataMap = (Map)areaList.get(0);
        ArrayList childrenList = (ArrayList)dataMap.get("children");

        // 遍历转化
        List<DataBean> result = new ArrayList<>();

        for (int i = 0; i < childrenList.size(); i ++) {
            Map tem = (Map)childrenList.get(i);
            String name = (String)tem.get("name");

            Map totalMap = (Map)tem.get("total");
            double nowConfirm = (Double)totalMap.get("nowConfirm");
            double confirm = (Double)totalMap.get("confirm");
            double heal = (Double)totalMap.get("heal");
            double dead = (Double)totalMap.get("dead");

            DataBean bean = new DataBean(name, (int)nowConfirm, (int)confirm, (int)heal, (int)dead);
            result.add(bean);
        }
        return result;
    }
}
