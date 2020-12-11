## Mybatis 多数据源配置

##### 一、背景描述

  由于未使用微服务架构或者架构设计无法避免需要在同一个服务中连接两个或以上的Mysql数据库时，我们就需要为该服务配置多数据源

##### 二、相关技术

springboot、mybatis、mybatis-plus、mysql

##### 三、配置方式

###### 1.依赖引入

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    <exclusions>
        <exclusion>
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!--mybatis-plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.0</version>
</dependency>
```

###### 2.在application.yml文件中添加数据源配置信息

```
datasource:
  test1:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://你的主机:3306/数据库1?characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=5
    username: 用户名
    password: 密码
  test2:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://你的主机:3306/数据库2?characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=5
    username: 用户名
    password: 密码
```

###### 3.添加DO、Mapper.java、Mapper.xml文件

  此处比较简单 略   注意：建议将多个库的mapper文件放在对应库的包下面，否则需要在扫描mapper时挨个指定

4.添加数据源配置

注意：下面的代码中我直接是指定了连接信息，实际使用时建议不要这样，应该从上面的application.yml文件中读取

```java
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
 * @author lyle
 * @DateTime 2020/11/25 18:09
 */
@Configuration
@MapperScan(basePackages = "com.lyle.sample.mybatis.mapper.test1",sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class test1DataSourceConfig {

    @Bean("test1Datasource")
    DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername("用户名");
        hikariConfig.setPassword("密码");
        hikariConfig.setJdbcUrl("jdbc:mysql://你的主机:3306/数据库1?characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=5");
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    @Bean("test1SqlSessionFactory")
    SqlSessionFactory sqlSessionFactory(@Qualifier("test1Datasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("test1SqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory);
        return  sqlSessionTemplate;
    }
}
```

```java
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
 * @author lyle
 * @DateTime 2020/11/25 18:09
 */
@Configuration
@MapperScan(basePackages = "com.lyle.sample.mybatis.mapper.test2",sqlSessionTemplateRef = "test2SqlSessionTemplate")
public class test2DataSourceConfig {

    @Bean("test2Datasource")
    DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername("用户名");
        hikariConfig.setPassword("密码");
        hikariConfig.setJdbcUrl("jdbc:mysql://你的主机:3306/数据库2?characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=5");
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
```

**多数据源的配置重点就在上面的配置，首先需要逐层装配数据源和sqlSessionFactory、sqlSessionTemplate，然后mapper包扫描的时候只扫描各自库对应包下面的mapper，并指定注入指定的sqlSessionTemplate**

##### 四、测试

略

##### 五、总结

  其实多数据源配置并没有什么难点，只要你逐层注入bean，然后注意mapper的扫描包范围和使用的sqlSessionTemplate就可以了