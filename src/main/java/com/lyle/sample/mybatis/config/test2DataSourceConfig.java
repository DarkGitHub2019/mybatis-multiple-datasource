package com.lyle.sample.mybatis.config;

import com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yangchenguang
 * @DateTime 2020/11/25 18:09
 */
@Configuration
@MapperScan(basePackages = "com.lyle.sample.mybatis.mapper.test2",sqlSessionTemplateRef = "test2SqlSessionTemplate")
public class test2DataSourceConfig {

    @Bean("test2Datasource")
    DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.setJdbcUrl("jdbc:mysql://edgclearlove.top:3306/test2?characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=5");
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    @Bean("test2SqlSessionFactory")
    SqlSessionFactory sqlSessionFactory(@Qualifier("test2Datasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("test2SqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory);
        return  sqlSessionTemplate;
    }
}
