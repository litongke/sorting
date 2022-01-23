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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.chufinfo.sorting.mapper.second", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DataSourceSecondConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.second")
    public DataSource db2DataSource() {
//        return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean
    public MybatisPlusProperties mybatisPlusProperties2(){
        return new MybatisPlusProperties();
    }

    @Bean
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource, @Qualifier("mybatisPlusProperties2") MybatisPlusProperties mybatisPlusProperties) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(mybatisPlusProperties.getConfiguration());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/second/*.xml"));
        SqlSessionFactory factory = bean.getObject();
//        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory;
    }

    @Bean
    public DataSourceTransactionManager db2TransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
