package com.sixsixsix516.fakeuser.start;

import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.model.UserBase;
import com.sixsixsix516.fakeuser.pipeline.OicqPipeline;
import com.sixsixsix516.fakeuser.spider.OicqSpider;
import com.sixsixsix516.fakeuser.wrapper.UsernameWrapper;
import lombok.Data;
import lombok.experimental.Accessors;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sun 2019/12/29 21:34
 */
@Accessors(chain = true)
@Data
public class FakeUser {

    /**
     * 生成的数量
     */
    private Integer num;


    /**
     * 头像列表
     */
    private List<String> headUrlList;

    public UsernameWrapper username;


    public FakeUser() {
        // 默认获取100条数据
        num = 100;
        username = new UsernameWrapper();
    }


    private void check() {


    }


    /**
     * 填充指定数量的用户名
     *
     * @param num
     * @return
     */
    private List<String> fillUsername(Integer num) {
        OicqSpider oicqSpider = new OicqSpider(num, username);
        Spider spider = Spider.create(oicqSpider);
        oicqSpider.setSpider(spider);

        spider.addUrl(SpiderConstant.IOCQHOME)
                .addPipeline(new OicqPipeline())
                .thread(1)
                .run();

        return username.getNikenameList();
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
        List<UserBase> result = new ArrayList<>(num);
        List<String> usernameList = fillUsername(num);

        for (int i = 0; i < num; i++) {
            UserBase userBase = new UserBase();
            userBase.setNikename(usernameList.get(i));
            result.add(userBase);
        }

        return result;
    }
}
