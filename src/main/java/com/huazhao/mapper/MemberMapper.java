package com.huazhao.mapper;

import com.huazhao.base.BaseMapper;
import com.huazhao.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    List<Member> selectBySettingId(Integer id);

}