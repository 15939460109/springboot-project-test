package com.czg.bean;

import lombok.Data;

@Data
public class AsymptomaticBean implements Comparable<AsymptomaticBean> {

    private String province;
    private int confirm;
    private int increase;

    @Override
    public int compareTo(AsymptomaticBean o) {
        return o.getIncrease() - this.getIncrease();
    }
}
