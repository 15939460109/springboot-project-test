package com.czg.handler;

import com.czg.bean.DataBean;
import com.czg.service.DataService;
import com.czg.util.HttpURLConnectionUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DataHandler {

    @Autowired
    private DataService dataService;
//    private static String testStr = "{\"name\":\"独钓寒江雪\"}";

    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    public static void main(String[] args) throws IOException {
//        Gson gson = new Gson();
//        Map result = gson.fromJson(testStr, Map.class);
//        System.out.println(result);

        List<DataBean> result = getData();
        System.out.println(result);
    }

    // 服务器启动时更新数据到数据库
    @PostConstruct
    public void saveData() {
        try {
            List<DataBean> data = getData();
            // 清空表格数据
            dataService.remove(null);
            // 更新数据
            dataService.saveBatch(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 配置定时执行的注解  支持cron表达式
    // 每分钟执行一次  更改可参考cron表达式生成网站
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void updateData() {
        System.out.println("更新数据");
        saveData();
    }

    public static List<DataBean> getData() throws IOException {
//        // 文件输入流
//        File file = new File("temp1_1.txt");
//        FileReader fr = new FileReader(file);
//        // 缓存数组
//        char[] cBuf = new char[1024];
//        // 每次读取长度
//        int cRead = 0;
//        // 每次读取后拼接到字符串后面
//        StringBuilder builder = new StringBuilder();
//        while ((cRead = fr.read(cBuf)) > 0) {
//            builder.append(new String(cBuf, 0, cRead));
//        }
//        // 关闭流
//        fr.close();

//        System.out.println(builder.toString());

        // 实时获取数据
        String respJson = HttpURLConnectionUtil.doGet(urlStr);

        Gson gson = new Gson();
        // 将json数据包装成map
//        Map map = gson.fromJson(builder.toString(), Map.class);
        Map originalMap = gson.fromJson(respJson, Map.class);
        String data = (String)originalMap.get("data");

        Map map = gson.fromJson(data, Map.class);

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

            DataBean bean = new DataBean();
            bean.setArea(name);
            bean.setNowConfirm((int)nowConfirm);
            bean.setConfirm((int)confirm);
            bean.setDead((int)dead);
            bean.setHeal((int)heal);

            result.add(bean);
        }
        return result;
    }
}
