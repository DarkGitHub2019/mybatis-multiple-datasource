package com.lyle.sample.mybatis.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test_employee")
public class Employee {

    Long id;
    Integer age;
    Long deptId;
    String name;
}
