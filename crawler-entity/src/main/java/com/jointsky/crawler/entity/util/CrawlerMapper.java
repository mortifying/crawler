package com.jointsky.crawler.entity.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Author  zhangxiong
 * Date    17-7-25 下午5:16
 */
public interface CrawlerMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
