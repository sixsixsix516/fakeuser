package com.sixsixsix516.fakeuser.wrapper;

import com.sixsixsix516.fakeuser.condition.Condition;
import com.sixsixsix516.fakeuser.condition.IntegerCodition;
import com.sixsixsix516.fakeuser.condition.StringCondition;
import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.spider.OicqSpider;
import lombok.Data;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sun 2019/12/29 22:57
 */
@Data
public class StringWrapper {

    /**
     * 生成的数据数量
     */
    private Integer num;

    /**
     * 网名列表, 存储生成的数据
     */
    private List<String> stringDataList;

    /**
     * 用户名的条件列表
     */
    private List<Condition> conditionList;

    public StringWrapper() {
        conditionList = new ArrayList<>(0);
    }

    public void setNum(Integer num) {
        this.num = num;
        this.stringDataList = new ArrayList<>(num);
    }

    public IntegerCodition addIntegerCodition() {
        IntegerCodition integerCodition = new IntegerCodition();
        integerCodition.setStringWrapper(this);
        conditionList.add(integerCodition);
        return integerCodition;
    }

    public StringCondition addStringCondition() {
        StringCondition stringCondition = new StringCondition();
        stringCondition.setStringWrapper(this);
        conditionList.add(stringCondition);
        return stringCondition;
    }

    public boolean check(Object data) {
        for (int i1 = 0, len = conditionList.size(); i1 < len; i1++) {
            Condition condition = conditionList.get(i1);
            if (!condition.check(data)) {
                // 失败
                return false;
            }
        }
        return true;
    }

    /**
     * 填充指定数量的用户名
     */
    public void start(Spider spider, String url) {
        spider.addUrl(url)
                .thread(1)
                .run();
    }


}
