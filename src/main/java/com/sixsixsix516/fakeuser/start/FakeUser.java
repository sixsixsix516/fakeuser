package com.sixsixsix516.fakeuser.start;

import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.model.UserBase;
import com.sixsixsix516.fakeuser.sdk.BaiduDwz;
import com.sixsixsix516.fakeuser.spider.HuiyiSpider;
import com.sixsixsix516.fakeuser.spider.OicqSpider;
import com.sixsixsix516.fakeuser.wrapper.StringWrapper;
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
     * 用户字段区
     */
    public StringWrapper username;

    public StringWrapper headurl;


    public FakeUser() {
        // 默认获取100条数据
        num = 100;
        username = new StringWrapper();
        headurl =  new StringWrapper();
    }

    public FakeUser setNum(Integer num) {
        this.num = num;
        username.setNum(num);
        headurl.setNum(num);
        return this;
    }


    /**
     * 启动填充数据
     */
    public void start() {

        OicqSpider oicqSpider = new OicqSpider(username);
        Spider spider1 = Spider.create(oicqSpider);
        oicqSpider.setSpider(spider1);
        username.start(spider1, SpiderConstant.IOCQHOME);


        HuiyiSpider huiyiSpider = new HuiyiSpider(headurl);
        Spider spider2 = Spider.create(huiyiSpider);
        huiyiSpider.setSpider(spider2);
        username.start(spider2, SpiderConstant.HUIYIHOME);

    }


    public List<UserBase> get() {
        // 最后返回的数据
        List<UserBase> result = new ArrayList<>(num);
        start();
        List<String> nikenameList = username.getStringDataList();
        List<String> headurlList = headurl.getStringDataList();

        for (int i = 0; i < num; i++) {
            UserBase userBase = new UserBase();
            userBase.setNikename(nikenameList.get(i));
            userBase.setHeadUrl(BaiduDwz.createShortUrl(headurlList.get(i)));
            result.add(userBase);
        }

        return result;
    }

}
