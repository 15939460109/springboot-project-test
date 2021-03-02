package com.czg.bean;

import lombok.Data;

@Data
public class GraphBarBean implements Comparable<GraphBarBean> {

    private String province;
    private int confirm;
    private int increase;

    @Override
    public int compareTo(GraphBarBean o) {
        return o.getIncrease() - this.getIncrease();
    }
}
