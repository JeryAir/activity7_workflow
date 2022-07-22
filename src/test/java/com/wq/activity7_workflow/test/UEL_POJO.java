package com.wq.activity7_workflow.test;

import java.io.Serializable;

public class UEL_POJO implements Serializable {

    private String zhixingren;
    private String pay;

    public UEL_POJO() {
    }

    public UEL_POJO(String zhixingren) {
        this.zhixingren = zhixingren;
    }

    public String getZhixingren() {
        return zhixingren;
    }

    public void setZhixingren(String zhixingren) {
        this.zhixingren = zhixingren;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
