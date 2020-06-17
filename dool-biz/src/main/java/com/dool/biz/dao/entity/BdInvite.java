package com.dool.biz.dao.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
* Created by Mybatis Generator on 2020/04/17
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BdInvite {
    private Integer id;

    private String code;

    private Byte status;

    private Date createTime;

    private String createUserId;

    private String createUserName;

    private Date updateTime;

    private String updateUserId;
}