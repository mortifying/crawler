package com.jointsky.crawler.spider.process;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Set;

/**
 * Author  zhangxiong
 * Date    17-8-14 上午10:56
 */
public class DataStats implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(0).setTimeOut(3000);

    //用来存储cookie信息
    private Set<Cookie> cookies;

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return null;
    }


    //使用 selenium 来模拟用户的登录获取cookie信息
    public void login() {
        WebDriver driver = new ChromeDriver();
        driver.get("data.stats.gov.cn/index.htm?isLogin=0");

        driver.findElement(By.id("username")).clear();

        //在******中填你的用户名
        driver.findElement(By.id("username")).sendKeys("zhangxiongcolin@126.com");

        driver.findElement(By.id("keyp")).clear();
        //在*******填你密码
        driver.findElement(By.id("keyp")).sendKeys("zhangxiong12*");

        //模拟点击登录按钮
        driver.findElement(By.id("submitBtn")).click();

        //获取cookie信息
        cookies = driver.manage().getCookies();
        driver.close();

        System.out.println("login success");
    }


    public static void main(String[] args){
        DataStats dataStats = new DataStats();

        //调用selenium，进行模拟登录
        dataStats.login();
        /*Spider.create(miai)
                .addUrl("http://www.jiayuan.com/164830633")
                .run();*/
    }


}
