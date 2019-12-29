package com.sixsixsix516.fakeuser.start;

import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.model.UserBase;
import com.sixsixsix516.fakeuser.pipeline.OicqPipeline;
import com.sixsixsix516.fakeuser.spider.OicqSpider;
import lombok.Builder;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
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

    public UserBase userbase;

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
        usernameList = oicqSpider.getData();
        return usernameList;
    }

    /**
     * 填充指定数量的头像
     *
     * @return
     */
    private List<String> fillHeadUrlList(Integer num) {
        return null;
    }


    public List<UserBase> get() {
        check();
        List<UserBase> result =  new ArrayList<>(num);
        fillUsername(num);
        for (int i = 0; i < num; i++) {
            UserBase userBase = new UserBase();
            userBase.setNikename(usernameList.get(i));
            result.add(userBase);
        }

        return result;
    }
}
