package com.czg.controller;

import com.czg.bean.*;
import com.czg.handler.GraphHandler;
import com.czg.service.DataService;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

        Gson gson = new Gson();
        // 将数据放入model
        model.addAttribute("dateList", gson.toJson(dateList));
        model.addAttribute("nowConfirmList", gson.toJson(nowConfirmList));
        return "graph";
    }

    @GetMapping("/graphAdd")
    public String graphAdd(Model model) {
        List<GraphAddBean> list = GraphHandler.getGraphAddData();

        List<String> dateList = new ArrayList<>();
        List<Integer> confirmList = new ArrayList<>();
        List<Integer> suspectList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
             GraphAddBean bean = list.get(i);
             dateList.add(bean.getDate());
             confirmList.add(bean.getConfirm());
             suspectList.add(bean.getSuspect());
        }

        Gson gson = new Gson();
        model.addAttribute("dateList", gson.toJson(dateList));
        model.addAttribute("confirmList", gson.toJson(confirmList));
        model.addAttribute("suspectList", gson.toJson(suspectList));
        return "graphAdd";
    }

    @GetMapping("/asymptomatic")
    public String asymptomatic(Model model) {
        List<AsymptomaticBean> list = GraphHandler.asymptomaticData();
        // 需要排名前十的数据
        List<String> provinceList = new ArrayList<>();
        List<Integer> confirmList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AsymptomaticBean bean = list.get(i);
             if (bean.getConfirm() > 0) {
                 provinceList.add(bean.getProvince());
                 confirmList.add(bean.getConfirm());
             }
        }

        Gson gson = new Gson();
        model.addAttribute("provinceList", gson.toJson(provinceList));
        model.addAttribute("confirmList", gson.toJson(confirmList));
        return "asymptomatic";
    }

    @GetMapping("/asymptomaticAdd")
    public String asymptomaticAdd(Model model) {
        List<AsymptomaticBean> list = GraphHandler.asymptomaticAddData();
        // 需要排名前十的数据
        List<String> provinceList = new ArrayList<>();
        List<Integer> increaseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AsymptomaticBean bean = list.get(i);
            if (bean.getIncrease() > 0) {
                provinceList.add(bean.getProvince());
                increaseList.add(bean.getIncrease());
            }
        }

        Gson gson = new Gson();
        model.addAttribute("provinceList", gson.toJson(provinceList));
        model.addAttribute("increaseList", gson.toJson(increaseList));
        return "asymptomaticAdd";
    }

    @GetMapping("/graphPie")
    public String graphPie(Model model) {
        List<GraphPieBean> list = GraphHandler.getGraphPieData();
        model.addAttribute("list", new Gson().toJson(list));
        return "graphPie";
    }
}
