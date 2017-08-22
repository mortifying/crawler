package com.jointsky.crawler.entity.mapper;

import com.jointsky.crawler.entity.model.BusinessCategory;
import com.jointsky.crawler.entity.util.CrawlerMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BusinessCategoryMapper extends CrawlerMapper<BusinessCategory> {
    @Select("select * from business_category where is_parent=#{hasParent}")
    List<BusinessCategory> findBusinessCategoryByHasParent(@Param("hasParent") String hasParent);
}