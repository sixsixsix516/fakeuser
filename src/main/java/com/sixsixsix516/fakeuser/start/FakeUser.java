package com.sixsixsix516.fakeuser.start;

import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.model.UserBase;
import com.sixsixsix516.fakeuser.pipeline.OicqPipeline;
import com.sixsixsix516.fakeuser.spider.OicqSpider;
import lombok.Builder;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * @author sun 2019/12/29 21:34
 */
@Builder
public class FakeUser {

    /**
     * 生成的数量
     */
    private Integer num;

    /**
     * 网名列表
     */
    private List<String> usernameList;

    /**
     * 头像列表
     */
    private List<String> headUrlList;


    private void check() {
        if (num == null) {
            // 默认获取100条数据
            num = 100;
        }

    }


    /**
     * 填充指定数量的用户名
     *
     * @param num
     * @return
     */
    private List<String> fillUsername(Integer num) {
        OicqSpider oicqSpider = new OicqSpider(num);
        Spider spider = Spider.create(oicqSpider);
        oicqSpider.setSpider(spider);

        spider.addUrl(SpiderConstant.IOCQHOME)
                .addPipeline(new OicqPipeline())
                .thread(1)
                .run();


        return oicqSpider.getData();
    }

    /**
     * 填充指定数量的头像
     *
     * @return
     */
    private List<String> fillHeadUrlList(Integer num) {
        return null;
    }


    public List get() {
        check();
        usernameList =  fillUsername(num);
        return usernameList;
    }
}