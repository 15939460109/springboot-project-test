package com.czg.service.impl;

import com.czg.bean.DataBean;
import com.czg.handler.DataHandler;
import com.czg.service.DataService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public List<DataBean> list() {
        List<DataBean> result = null;

        try {
            result = DataHandler.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
