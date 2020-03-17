package com.sixsixsix516.fakeuser.spider;

import com.sixsixsix516.fakeuser.condition.Condition;
import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.wrapper.StringWrapper;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author sun 2020/3/17 10:36
 */
public class QQSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");


    /**
     * 生成数据的数量
     */
    private Integer count;

    /**
     * 当前spider对象 用于停止爬虫
     */
    private Spider spider;

    /**
     * 用户名的条件对象
     */
    private StringWrapper signatureCondition;


    public QQSpider(StringWrapper signatureCondition) {
        this.signatureCondition = signatureCondition;
        this.count = signatureCondition.getNum();
    }


    @Override
    public void process(Page page) {
        Selectable url = page.getUrl();
        Html html = page.getHtml();
        signatureCondition.setStringDataList(html.xpath("//ul[@id='list1']//p//text()").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void setSpider(Spider spider3) {
        this.spider = spider3;
    }
}
