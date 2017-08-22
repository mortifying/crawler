package com.jointsky.crawler.entity.model;

import java.io.Serializable;

/**
 * Author  zhangxiong
 * Date    17-8-17 上午10:22
 */
public class Params implements Serializable {

    private String id;
    private String dbcode;
    private String wdcode;
    private String m;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDbcode() {
        return dbcode;
    }

    public void setDbcode(String dbcode) {
        this.dbcode = dbcode;
    }

    public String getWdcode() {
        return wdcode;
    }

    public void setWdcode(String wdcode) {
        this.wdcode = wdcode;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
