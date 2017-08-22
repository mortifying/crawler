package com.jointsky.crawler.quartz.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.utils.HttpConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author  zhangxiong
 * Date    17-8-16 上午10:44
 */
public class HttpUtils {

    /**
     * {"id":"zb","dbCode":"hgyd","wdcode":"zb"}
     *
     * @param url
     * @param jsonParams
     */
    public static Request getRequest(String url, String jsonParams) {
        //设置POST请求
        Request request = new Request(url);
        //只有POST请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.POST);

        //设置POST参数
        List<NameValuePair> nvs = new ArrayList<>();
        Map<String, String> jsons = JSON.parseObject(jsonParams, Map.class);

        for (Map.Entry<String, String> json : jsons.entrySet()) {
            if (json.getKey().equals("id") || json.getKey().equals("dbcode") || json.getKey().equals("wdcode")) {
                nvs.add(new BasicNameValuePair(json.getKey(), json.getValue()));
            }
        }
        nvs.add(new BasicNameValuePair("m", "getTree"));

        //转换为键值对数组
        NameValuePair[] values = nvs.toArray(new NameValuePair[]{});

        //将键值对数组添加到map中
        Map<String, Object> params = new HashMap<>();
        //key必须是：nameValuePair
        params.put("nameValuePair", values);

        //设置request参数
        request.setExtras(params);

        return request;
    }


    public static void main(String args[]) throws ParseException {
        String code = "zb.A03010601_reg.610000_sj.201201";
        String datas[] = code.split("_");
        //区域编码
        System.out.println(datas[1]);
        String regionId = datas[1].split("\\.")[1];
        String bcId = datas[0].split("\\.")[1];
        String year = datas[2].split("\\.")[1].substring(0, 4);
        String month = datas[2].split("\\.")[1].substring(4, 6).replace("0", "");
        System.out.println(regionId + "," + bcId + "," + year + "," + month);


        DateFormat dateFormat2 = new SimpleDateFormat("YYYY");
        Date myDate2 = dateFormat2.parse(year);

        System.out.println(myDate2);
    }

}
