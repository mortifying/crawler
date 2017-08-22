package com.jointsky.crawler.entity.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "province_month_data")
public class ProvinceMonthData {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 省级行政区划编码
     */
    @Column(name = "region_id")
    private String regionId;

    /**
     * 行业分类
     */
    @Column(name = "bc_id")
    private String bcId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 值(亿千瓦时)
     */
    private BigDecimal value;

    /**
     * 精度值(亿千瓦时)
     */
    @Column(name = "str_value")
    private String strValue;

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

    public ProvinceMonthData(String id, String regionId, String bcId, Integer year, Integer month, BigDecimal value, String strValue, Date addTime, Date updateTime) {
        this.id = id;
        this.regionId = regionId;
        this.bcId = bcId;
        this.year = year;
        this.month = month;
        this.value = value;
        this.strValue = strValue;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public ProvinceMonthData() {
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
     * 获取省级行政区划编码
     *
     * @return region_id - 省级行政区划编码
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * 设置省级行政区划编码
     *
     * @param regionId 省级行政区划编码
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    /**
     * 获取行业分类
     *
     * @return bc_id - 行业分类
     */
    public String getBcId() {
        return bcId;
    }

    /**
     * 设置行业分类
     *
     * @param bcId 行业分类
     */
    public void setBcId(String bcId) {
        this.bcId = bcId == null ? null : bcId.trim();
    }

    /**
     * 获取年份
     *
     * @return year - 年份
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 设置年份
     *
     * @param year 年份
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 获取月份
     *
     * @return month - 月份
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * 设置月份
     *
     * @param month 月份
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * 获取值(亿千瓦时)
     *
     * @return value - 值(亿千瓦时)
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * 设置值(亿千瓦时)
     *
     * @param value 值(亿千瓦时)
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * 获取精度值(亿千瓦时)
     *
     * @return str_value - 精度值(亿千瓦时)
     */
    public String getStrValue() {
        return strValue;
    }

    /**
     * 设置精度值(亿千瓦时)
     *
     * @param strValue 精度值(亿千瓦时)
     */
    public void setStrValue(String strValue) {
        this.strValue = strValue == null ? null : strValue.trim();
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