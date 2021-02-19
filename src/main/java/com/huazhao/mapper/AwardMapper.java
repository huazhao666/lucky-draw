package com.huazhao.mapper;

import com.huazhao.base.BaseMapper;
import com.huazhao.model.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AwardMapper extends BaseMapper<Award> {
    List<Award> selectBySettingId(Integer id);

}