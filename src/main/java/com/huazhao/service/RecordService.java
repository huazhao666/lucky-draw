package com.huazhao.service;

import com.huazhao.mapper.RecordMapper;
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
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;

    //批量插入操作
    public int add(List<Integer> memberIds, Integer awardId) {

        return recordMapper.batchInsert(memberIds,awardId);
    }



    public int deleteByAwardId(Integer id) {

        return recordMapper.deleteByAwardId(id);
    }

    public int deleteByMemberId(Integer id) {
        return recordMapper.deleteByMemberId(id);
    }

    public int deleteByUserId(Integer id) {
        return recordMapper.deleteByUserId(id);
    }
}
