package com.lyle.sample.mybatis;

import com.lyle.sample.mybatis.model.Employee;
import com.lyle.sample.mybatis.service.EmployeeService;
import com.lyle.sample.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisMultipleDatasourceApplicationTests {


    @Resource
    UserService userService;

    @Resource
    EmployeeService employeeService;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(userService.queryUserCount());

        List<Employee> employees = employeeService.queryEmployeeByDeptId(1300997152714772481L);
        employees.forEach(System.out::println);
    }

}
