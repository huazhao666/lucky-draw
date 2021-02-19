package com.huazhao.service;

import com.huazhao.mapper.AwardMapper;
import com.huazhao.mapper.SettingMapper;
import com.huazhao.model.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-02
 * Time : 12:09
 */
@Service
public class AwardService {
    @Autowired
    private AwardMapper awardMapper;

    @Autowired
    private SettingMapper settingMapper;

    public List<Award> queryByUserId(Integer id) {

        return awardMapper.selectBySettingId(id);
    }

    public int add(Award award, Integer userId) {
        //通过userId寻找settingId;可以调用以后的selectByUserId:
        Integer settingId = settingMapper.queryIdByUserId(userId);
        //设置award中settId属性；
        award.setSettingId(settingId);
        //插入一条award数据/对象；
        return awardMapper.insertSelective(award);

    }
    public int update(Award award) {

        return awardMapper.updateByPrimaryKeySelective(award);
    }

    public int delete(Integer id) {
        return awardMapper.deleteByPrimaryKey(id);
    }
}
