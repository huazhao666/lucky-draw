package com.huazhao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 中奖记录
 */
@Getter
@Setter
@ToString
public class Record {
    
    private Integer id;

    /**
     * 中奖人员id
     */
    private Integer memberId;

    /**
     * 中奖奖项id
     */
    private Integer awardId;

    /**
     * 创建时间
     */
    private Date createTime;
}