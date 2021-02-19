package com.huazhao.mapper;

import com.huazhao.base.BaseMapper;
import com.huazhao.model.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    int batchInsert(@Param("memberIds") List<Integer> memberIds,
                    @Param("awardId") Integer awardId);


    int deleteByAwardId(Integer id);

    int deleteByMemberId(Integer id);

    int deleteByUserId(Integer id);
}