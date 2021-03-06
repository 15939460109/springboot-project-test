package com.czg.bean;

import lombok.Data;

@Data
public class GraphPieBean implements Comparable<GraphPieBean> {

    private String name;
    private int value;

    @Override
    public int compareTo(GraphPieBean o) {
        return this.getValue() - o.getValue();
    }
}
