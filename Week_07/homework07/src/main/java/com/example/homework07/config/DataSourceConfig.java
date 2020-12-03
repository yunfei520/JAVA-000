package com.example.homework07.config;

import com.example.homework07.dynamicdatasource.DataSourceEnum;
import com.example.homework07.dynamicdatasource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.example.homework07.repository")
public class DataSourceConfig {
    /**
     * 主库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource primaryDb() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 从库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource replicaDb() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 主从动态配置
     */
    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("primaryDb") DataSource masterDataSource, @Autowired(required = false) @Qualifier("replicaDb") DataSource slaveDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER, masterDataSource);
        if (slaveDataSource != null) {
            targetDataSources.put(DataSourceEnum.SLAVE1, slaveDataSource);
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        bean.setDataSource(dynamicDataSource);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "dataSourceTx")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dynamicDataSource);
        return dataSourceTransactionManager;
    }
}
