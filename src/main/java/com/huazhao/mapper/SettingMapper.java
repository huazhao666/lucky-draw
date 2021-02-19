package com.huazhao.mapper;

import com.huazhao.base.BaseMapper;
import com.huazhao.model.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SettingMapper extends BaseMapper<Setting> {
    Setting selectByUserId(Integer id);

    int update(@Param("batchNumber") Integer batchNumber, @Param("userId") Integer userId);

    Integer queryIdByUserId(Integer userId);

}