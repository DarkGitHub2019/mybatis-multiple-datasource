package com.lyle.sample.mybatis.mapper.test1;

import com.lyle.sample.mybatis.model.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工 Mapper
 *
 *@author lyle
 *@DateTime 2020/12/1 14:38
 *
 */
@Mapper
public interface EmplyeeMapper {

    /**
     * 根据部门id查询员工信息列表
     *
     * @param deptId 部门id
     *
     * @return 员工信息列表
     */
    List<Employee> queryEmployeeByDeptId(Long deptId);
}
