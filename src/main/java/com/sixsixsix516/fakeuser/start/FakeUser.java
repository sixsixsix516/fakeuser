package com.sixsixsix516.fakeuser.start;

import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.model.UserBase;
import com.sixsixsix516.fakeuser.sdk.BaiduDwz;
import com.sixsixsix516.fakeuser.spider.HuiyiSpider;
import com.sixsixsix516.fakeuser.spider.OicqSpider;
import com.sixsixsix516.fakeuser.spider.QQSpider;
import com.sixsixsix516.fakeuser.wrapper.*;
import lombok.Data;
import lombok.experimental.Accessors;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 头像
     */
    public StringWrapper headurl;

    /**
     * 真实姓名
     */
    private RealnameWrapper realname;

    /**
     * 性别
     */
    private SexWrapper sex;

    /**
     * 用户编号
     */
    private UserNoWrapper userNo;

    /**
     * 用户生日
     */
    private BirthdayWrapper birthday;

    /**
     * 个性签名
     */
    public StringWrapper signature ;


    public FakeUser() {
        // 默认获取100条数据
        this(100);
    }

    public FakeUser(Integer num) {
        this.num = num;
        username = new StringWrapper();
        headurl =  new StringWrapper();
        realname = new RealnameWrapper();
        sex = new SexWrapper();
        userNo = new UserNoWrapper();
        birthday = new BirthdayWrapper();
        signature = new StringWrapper();

        username.setNum(num);
        headurl.setNum(num);
    }


    /**
     * 启动填充数据
     */
    private void start() {

        // 1.用户昵称爬虫
        OicqSpider oicqSpider = new OicqSpider(username);
        Spider spider1 = Spider.create(oicqSpider);
        oicqSpider.setSpider(spider1);
        username.start(spider1, SpiderConstant.IOCQHOME);

        // 2.头像爬虫
        HuiyiSpider huiyiSpider = new HuiyiSpider(headurl);
        Spider spider2 = Spider.create(huiyiSpider);
        huiyiSpider.setSpider(spider2);
        headurl.start(spider2, SpiderConstant.HUIYIHOME);

        // 3.用户真实姓名填充
        realname.start(num);

        // 4.性别填充
        sex.start(num);

        // 5.用户编号
        userNo.start(num);

        // 6. 生日
        birthday.start(num);


        // 2.头像爬虫
        QQSpider qqSpider = new QQSpider(signature);
        Spider spider3 = Spider.create(qqSpider);
        qqSpider.setSpider(spider3);
        signature.start(spider3, SpiderConstant.QQ_SHUO_SHUO);
    }


    public List<UserBase> get() {
        // 开始填充数据
        start();

        // ---------------------- 获取数据 ------------------------- //

        // 昵称
        List<String> nikenameList = username.getStringDataList();
        // 头像
        List<String> headurlList = headurl.getStringDataList();
        // 真实用户名
        List<String> realnameList = realname.getRealnameList();
        // 性别
        List<Integer> sexList = sex.getSexList();
        // 用户编号
        List<Long> userNoList = userNo.getUserNoList();
        // 用户出生日期
        List<Date> dateList = birthday.getDateList();
        // 签名
        List<String> signatureList = signature.getStringDataList();



        //  组装数据
        List<UserBase> result = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            UserBase userBase = new UserBase();
            userBase.setNikename(nikenameList.get(i));
            userBase.setHeadUrl(BaiduDwz.createShortUrl(headurlList.get(i)));
            userBase.setRealname(realnameList.get(i));
            userBase.setSex(sexList.get(i));
            userBase.setUserNo(userNoList.get(i));
            userBase.setBirthday(dateList.get(i));
            userBase.setPersonalizedSignature(signatureList.get(i));

            result.add(userBase);
        }

        return result;
    }

}
