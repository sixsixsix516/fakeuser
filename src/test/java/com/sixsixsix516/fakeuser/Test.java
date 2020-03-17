package com.sixsixsix516.fakeuser;

import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.spider.QQSpider;
import com.sixsixsix516.fakeuser.wrapper.StringWrapper;
import us.codecraft.webmagic.Spider;

/**
 * @author sun 2020/3/17 10:45
 */
public class Test {

    public static void main(String[] args) {

        StringWrapper signature = new StringWrapper();

        QQSpider qqSpider = new QQSpider(signature);
        Spider spider3 = Spider.create(qqSpider);
        qqSpider.setSpider(spider3);
        signature.start(spider3, SpiderConstant.QQ_SHUO_SHUO);

    }
}
