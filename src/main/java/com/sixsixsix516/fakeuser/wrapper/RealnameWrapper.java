package com.sixsixsix516.fakeuser.wrapper;

import com.sixsixsix516.fakeuser.condition.Condition;
import com.sixsixsix516.fakeuser.wrapper.realname.Constant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 真实姓名包装器
 *
 * @author sun 2020/1/26 14:57
 */
@Data
public class RealnameWrapper {

    Random random = new Random();

    /**
     * 存储所有生成的真实姓名
     */
    private List<String> data;

    private String[] firstName = Constant.firstName;
    private String[] lastName = Constant.lastName;

    /**
     * 条件列表
     */
    private List<Condition> conditionList;

    public RealnameWrapper() {
        conditionList = new ArrayList<>();
        data = new ArrayList<>();

    }

    public List<String> getRealnameList(Integer num) {
        for (int i = 0; i < num; i++) {
            int firstNameRandomNum = random.nextInt(firstName.length - 1);
            // 获取姓
            String realname = firstName[firstNameRandomNum];
            // 过滤条件便利
            for (int i1 = 0; i1 < conditionList.size(); i1++) {
                Condition condition = conditionList.get(i1);
                realname = (String) condition.modify(realname);
            }
            // 设置长度
            int lastNameLenth = random.nextInt(2) + 1;
            for (int i2 = 0; i2 < lastNameLenth; i2++) {
                int lastNameRandomNum = random.nextInt(lastName.length - 1);
                realname += lastName[lastNameRandomNum];
            }

            data.add(realname);
        }
        return data;
    }

}
