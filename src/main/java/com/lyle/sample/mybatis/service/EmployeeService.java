package com.lyle.sample.mybatis.service;

import com.lyle.sample.mybatis.mapper.test1.EmplyeeMapper;
import com.lyle.sample.mybatis.model.Employee;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工 Service
 *
 *@author lyle
 *@DateTime 2020/12/1 14:35
 *
 */
@Service
public class EmployeeService {

    @Resource
    EmplyeeMapper emplyeeMapper;

    /**
     * 根据部门id查询员工信息
     *
     * @param deptId 部门信息
     *
     * @return 员工信息列表
     */
    public List<Employee> queryEmployeeByDeptId(Long deptId) {
        return emplyeeMapper.queryEmployeeByDeptId(deptId);
    }
}
