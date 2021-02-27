package com.czg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czg.bean.DataBean;
import com.czg.handler.DataHandler;
import com.czg.handler.JsoupHandler;
import com.czg.mapper.DataMapper;
import com.czg.service.DataService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, DataBean> implements DataService {

//    @Override
//    public List<DataBean> list() {
//        List<DataBean> result = null;
//
//        try {
//            result = DataHandler.getData();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public List<DataBean> listById(int id) {
//        if (id == 2) {
//            return JsoupHandler.getData();
//        }
//        return list();
//    }

}
