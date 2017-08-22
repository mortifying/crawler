package com.jointsky.crawler.entity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "business_category")
public class BusinessCategory {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 行业分类id
     */
    private String bid;

    /**
     * db code
     */
    private String dbcode;

    /**
     * 是否为父目录，1:true,0:false
     */
    @Column(name = "is_parent")
    private String isParent;

    /**
     * 名称
     */
    private String name;

    /**
     * 父目录ID
     */
    private String pid;

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

    private String description;

    public BusinessCategory(String id, String bid, String dbcode, String isParent, String name, String pid, Date addTime, Date updateTime, String desc) {
        this.id = id;
        this.bid = bid;
        this.dbcode = dbcode;
        this.isParent = isParent;
        this.name = name;
        this.pid = pid;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.description = desc;
    }

    public BusinessCategory() {
        super();
    }

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取行业分类id
     *
     * @return bid - 行业分类id
     */
    public String getBid() {
        return bid;
    }

    /**
     * 设置行业分类id
     *
     * @param bid 行业分类id
     */
    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    /**
     * 获取db code
     *
     * @return dbcode - db code
     */
    public String getDbcode() {
        return dbcode;
    }

    /**
     * 设置db code
     *
     * @param dbcode db code
     */
    public void setDbcode(String dbcode) {
        this.dbcode = dbcode == null ? null : dbcode.trim();
    }

    /**
     * 获取是否为父目录，1:true,0:false
     *
     * @return is_parent - 是否为父目录，1:true,0:false
     */
    public String getIsParent() {
        return isParent;
    }

    /**
     * 设置是否为父目录，1:true,0:false
     *
     * @param isParent 是否为父目录，1:true,0:false
     */
    public void setIsParent(String isParent) {
        this.isParent = isParent == null ? null : isParent.trim();
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父目录ID
     *
     * @return pid - 父目录ID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置父目录ID
     *
     * @param pid 父目录ID
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
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

    /**
     * @return desc
     */
    public String getDesc() {
        return description;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.description = desc == null ? null : desc.trim();
    }
}