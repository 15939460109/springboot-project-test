package com.czg.controller;

import com.czg.bean.DataBean;
import com.czg.bean.GraphBean;
import com.czg.handler.GraphHandler;
import com.czg.service.DataService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String list(Model model) {
        List<DataBean> list = dataService.list();
        model.addAttribute("dataList", list);
        return "list";
    }

//    @GetMapping("/list/{id}")
//    public String list(Model model, @PathVariable String id) {
//        List<DataBean> list = dataService.listById(Integer.parseInt(id));
//        model.addAttribute("dataList", list);
//        return "list";
//    }

    @GetMapping("/graph")
    public String graph(Model model) {
        List<GraphBean> list = GraphHandler.getGraphData();

        // 前端需要x、y轴的数据，所以这里做一下拆分
        List<String> dateList = new ArrayList<>();
        List<Integer> nowConfirmList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            GraphBean bean = list.get(i);
            dateList.add(bean.getDate());
            nowConfirmList.add(bean.getNowConfirm());
        }

        // 将数据放入model
        model.addAttribute("dateList", new Gson().toJson(dateList));
        model.addAttribute("nowConfirmList", new Gson().toJson(nowConfirmList));
        return "graph";
    }
}
