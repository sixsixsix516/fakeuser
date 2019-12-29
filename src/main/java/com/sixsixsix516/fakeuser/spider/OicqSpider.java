package com.sixsixsix516.fakeuser.spider;

import com.sixsixsix516.fakeuser.FakeuserApplication;
import com.sixsixsix516.fakeuser.condition.IntegerCodition;
import com.sixsixsix516.fakeuser.condition.StringCondition;
import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.pipeline.OicqPipeline;
import com.sixsixsix516.fakeuser.wrapper.UsernameWrapper;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;


import java.util.ArrayList;
import java.util.List;

/**
 * 用于爬取网名
 * <p>
 * http://www.oicq88.com/ 的爬虫
 */
@Data
public class OicqSpider implements PageProcessor {

    private Integer count;
    private List<String> data;
    private Spider spider;

    private String format;

    private StringCondition usernameCondition;

    public OicqSpider(Integer count, UsernameWrapper usernameCondition) {
        this.count = count;
        this.usernameCondition = usernameCondition;
        this.format = usernameCondition.getFormat();
        usernameCondition.setNikenameList(new ArrayList<>(count));
        data = usernameCondition.getNikenameList();
    }

    /**
     * 处理生成条件
     *
     * @param stringCondition
     */
    private void handlerStringCondition(StringCondition stringCondition) {
        format = stringCondition.getFormat();
    }

    /**
     * 检验数据是否合法
     *
     * @return
     */
    public boolean check(String username) {
        IntegerCodition length = usernameCondition.getLength();
        if (length != null) {
            // 当条件有值的时候才判断
            if (length.getEq() != null) {
                // 如果eq有值则判断eq
                return length.getEq() == username.length() ? true : false;
            }
            if (length.getGt() != null) {
                if (length.getGt() >= username.length()) {
                    return false;
                }
            }
            if (length.getLt() != null) {
                if (length.getLt() <= username.length()) {
                    return false;
                }
            }
        }
        return true;
    }


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
            // 2.2 添加下一页
            for (int i = 0, len = all.size(); i < len; i++) {
                String username = all.get(i);
                if (data.size() < count) {
                    // 当数据合法时才使用
                    if (check(username)) {
                        data.add(String.format(format, username));
                    }
                } else {
                    spider.stop();
                }
            }

        } else {
            // 发生错误
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


}
