package com.lyle.sample.mybatis;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Map;

@SpringBootTest
class MybatisMultipleDatasourceApplicationTests {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Test
    void contextLoads() throws SQLException {
    }

}
