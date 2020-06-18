package com.zhengcs.seata.multi.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/17 10:50 上午
 **/
@Configuration
@MapperScan(basePackages = "com.zhengcs.seata.multi.datasource.mapper.account",
sqlSessionTemplateRef = "accountSqlSessionTemplate")
public class DBAccountConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.account")
    public DataSource accountDataSource(){
        return new DruidDataSource();
    }


    @Bean
    public SqlSessionFactory accountSqlSessionFactory(@Qualifier("accountDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/account/*.xml"));
        //factoryBean.setConfigLocation(new ClassPathResource("mybatis-configuration.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate accountSqlSessionTemplate(@Qualifier("accountSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /*@Bean
    public DataSourceTransactionManager accountTransactionManager(@Qualifier("accountDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/
}
