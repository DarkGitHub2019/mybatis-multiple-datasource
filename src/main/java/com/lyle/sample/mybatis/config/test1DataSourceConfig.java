package com.lyle.sample.mybatis.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * test1数据库数据连接配置
 *
 *@author lyle
 *@DateTime 2020/11/25 18:10
 *
 */
@Configuration
public class test1DataSourceConfig {

    @Bean("mySqlSessionTemplate")
    public SqlSessionTemplate mySqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
