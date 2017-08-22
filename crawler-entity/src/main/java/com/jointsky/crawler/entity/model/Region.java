package com.jointsky.crawler.entity.model;

import java.util.Date;
import javax.persistence.*;

public class Region {
    /**
     * 区域id
     */
    @Id
    private String id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public Region(String id, String name, Date addTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public Region() {
        super();
    }

    /**
     * 获取区域id
     *
     * @return id - 区域id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置区域id
     *
     * @param id 区域id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取区域名称
     *
     * @return name - 区域名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置区域名称
     *
     * @param name 区域名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}