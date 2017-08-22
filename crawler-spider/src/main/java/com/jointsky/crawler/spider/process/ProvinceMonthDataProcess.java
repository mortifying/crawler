package com.jointsky.crawler.spider.process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jointsky.crawler.entity.model.BusinessCategory;
import com.jointsky.crawler.entity.model.ProvinceMonthData;
import com.jointsky.crawler.quartz.common.utils.IDGenerator;
import com.jointsky.crawler.spider.utils.UserAgentUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-8-17 下午2:34
 */
public class ProvinceMonthDataProcess implements PageProcessor {

    @Override
    public void process(Page page) {
        String rawText = page.getRawText();

        List<BusinessCategory> businessCategoryList = new ArrayList<>();
        String wdnJson = JSON.parseObject(JSON.parseObject(rawText).getString("returndata")).getString("wdnodes");
        JSONArray nodes = JSON.parseArray(JSON.parseObject(JSON.parseArray(wdnJson).get(0).toString()).getString("nodes"));
        for (int index = 0; index < nodes.size(); index++) {
            BusinessCategory bc = new BusinessCategory();
            JSONObject jsonObject = nodes.getJSONObject(index);
            String cname = jsonObject.getString("cname");
            String code = jsonObject.getString("code");
            String desc = jsonObject.getString("memo");
            String unit = jsonObject.getString("unit");
            bc.setId(IDGenerator.getUUID(cname + code));
            bc.setBid(code);
            bc.setIsParent("0");
            bc.setDbcode("");
            bc.setPid(code.substring(0, code.length() - 3));
            bc.setDesc(desc + "(" + unit + ")");
            businessCategoryList.add(bc);
        }

        page.putField("bc_result", businessCategoryList);

        List<ProvinceMonthData> provinceMonthDataList = new ArrayList<>();
        String resJson = JSON.parseObject(JSON.parseObject(rawText).getString("returndata")).getString("datanodes");
        JSONArray jsonArray = JSON.parseArray(resJson);
        for (int index = 0; index < jsonArray.size(); index++) {
            ProvinceMonthData pmd = new ProvinceMonthData();
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            //zb.A140301_reg.110000_sj.201212
            //zb.A03010601_reg.610000_sj.201201
            String code = jsonObject.getString("code");
            System.out.println("===================code:" + code);
            String datas[] = code.split("_");
            //区域编码
            String regionId = datas[1].split("\\.")[1];
            String bcId = datas[0].split("\\.")[1];
            String year = datas[2].split("\\.")[1].substring(0, 4);
            String month = datas[2].split("\\.")[1].substring(4, 6).replace("0", "");

            pmd.setId(IDGenerator.getUUID(code));
            pmd.setRegionId(regionId);
            pmd.setBcId(bcId);

            pmd.setYear(Integer.parseInt(year));
            pmd.setMonth(Integer.parseInt(month));
            String data = jsonObject.getString("data");
            JSONObject dataObj = JSON.parseObject(data);
            String strData = dataObj.getString("data");
            pmd.setStrValue(strData);
            BigDecimal dData = dataObj.getBigDecimal("strdata");
            pmd.setValue(dData);
            provinceMonthDataList.add(pmd);
            System.out.println("regionId:" + regionId + ",bcId:" + bcId + ",year:" + year + ",month:" + month + ",data:" + dData + ",strData:" + strData);
        }
        page.putField("pmd_result", provinceMonthDataList);
    }

    @Override
    public Site getSite() {
        return Site.me().setTimeOut(6000).setRetryTimes(3)
                .setSleepTime(1000).setCharset("UTF-8").addHeader("Accept-Encoding", "/")
                .setUserAgent(UserAgentUtils.radomUserAgent());
    }

}
