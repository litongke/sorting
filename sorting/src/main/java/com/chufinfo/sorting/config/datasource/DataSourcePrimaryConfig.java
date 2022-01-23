package com.chufinfo.sorting.config.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.chufinfo.sorting.mapper.primary", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class DataSourcePrimaryConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.primary")
    @Primary
    public DataSource db1DataSource() {
//        return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public MybatisPlusProperties mybatisPlusProperties1(){
        return new MybatisPlusProperties();
    }

    @Bean
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource, @Qualifier("mybatisPlusProperties1") MybatisPlusProperties mybatisPlusProperties) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(mybatisPlusProperties.getConfiguration());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/primary/**/*.xml"));
        SqlSessionFactory factory = bean.getObject();
        return factory;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager db1TransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}