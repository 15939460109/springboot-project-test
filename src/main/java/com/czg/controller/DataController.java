package com.czg.controller;

import com.czg.bean.DataBean;
import com.czg.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}