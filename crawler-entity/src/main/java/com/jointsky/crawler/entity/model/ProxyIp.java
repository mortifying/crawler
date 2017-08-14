package com.jointsky.crawler.entity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "proxy_ip")
public class ProxyIp {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 类型
     */
    private String type;

    /**
     * 地址
     */
    private String address;

    /**
     * 使用
     */
    private Byte used;

    /**
     * 其他
     */
    private String other;

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

    public ProxyIp(String id, String ip, Integer port, String type, String address, Byte used, String other, Date addTime, Date updateTime) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.type = type;
        this.address = address;
        this.used = used;
        this.other = other;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public ProxyIp() {
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
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取端口
     *
     * @return port - 端口
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置端口
     *
     * @param port 端口
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取使用
     *
     * @return used - 使用
     */
    public Byte getUsed() {
        return used;
    }

    /**
     * 设置使用
     *
     * @param used 使用
     */
    public void setUsed(Byte used) {
        this.used = used;
    }

    /**
     * 获取其他
     *
     * @return other - 其他
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置其他
     *
     * @param other 其他
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
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