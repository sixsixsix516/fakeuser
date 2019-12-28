package com.sixsixsix516.fakeuser.spider;

import com.sixsixsix516.fakeuser.FakeuserApplication;
import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.pipeline.OicqPipeline;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * http://www.oicq88.com/ 的爬虫
 */
public class OicqSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");

    @Override
    public void process(Page page) {
        Selectable url = page.getUrl();

        Html html = page.getHtml();
        // 1.处理首页
        if (SpiderConstant.IOCQHOME.equals(url.get())) {
            // 所有类别的链接
            List<String> catagoryLinks = html.xpath("//div[@class='hd-tab']").links().all();
            page.addTargetRequests(catagoryLinks);
        }
        // 2.处理不同类别
        else if (url.regex("http://www.oicq88.com/.*/").match()) {
            // 2.1 获取当前页的数据
            List<String> all = html.xpath("//div[@class='listfix']//p/text()").all();
            FakeuserApplication.name.addAll(all);
            // 2.2 添加下一页

        } else {
            // 发生错误
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new OicqSpider())
                .addUrl(SpiderConstant.IOCQHOME)
                .addPipeline(new OicqPipeline())
                .thread(1)
                .run();
    }

}
