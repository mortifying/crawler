package com.jointsky.crawler.entity.model;

import java.io.Serializable;

/**
 * Author  zhangxiong
 * Date    17-8-17 下午3:23
 */
public class Param implements Serializable {

    private String wdcode;
    private String valuecode;


    public String getWdcode() {
        return wdcode;
    }

    public void setWdcode(String wdcode) {
        this.wdcode = wdcode;
    }

    public String getValuecode() {
        return valuecode;
    }

    public void setValuecode(String valuecode) {
        this.valuecode = valuecode;
    }
}
