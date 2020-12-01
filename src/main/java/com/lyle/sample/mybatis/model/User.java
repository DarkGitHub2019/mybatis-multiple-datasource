package com.lyle.sample.mybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * User 实体类
 *
 *@author lyle
 *@DateTime 2020/11/26 10:09
 *
 */
@Data
@TableName("user")
public class User {

    @TableId(type=IdType.AUTO)
    Long id;
    String name;
    Timestamp create_time;
    Timestamp update_time;
}
