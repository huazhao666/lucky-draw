package com.huazhao.service;

import com.huazhao.mapper.MemberMapper;
import com.huazhao.mapper.SettingMapper;
import com.huazhao.model.Member;
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
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private SettingMapper settingMapper;


    public List<Member> queryByUserId(Integer id) {

        return memberMapper.selectBySettingId(id);
    }

    public int add(Member member, Integer userId) {
        Integer settingId = settingMapper.queryIdByUserId(userId);
        //设置member中settId属性；
        member.setSettingId(settingId);
        //插入一条member数据/对象；
        return memberMapper.insertSelective(member);
    }

    public int update(Member member) {
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    public Object delete(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

}
