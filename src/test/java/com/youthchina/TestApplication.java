package com.youthchina;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

/**
 * Created by Tommy on 2017/5/12.
 */
@Configuration
public class TestApplication {
    @Autowired
    private Environment env;

    @Bean(name = "testDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(env.getProperty("spring.datasource.url") + "?useUnicode=yes&characterEncoding=UTF-8&sessionVariables=FOREIGN_KEY_CHECKS=0");
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setSchema("youthchina");
        return ds;
    }

    @Bean("mysqlTestSource")
    public IDatabaseConnection databaseConnection(@Qualifier("testDataSource") DataSource dataSource) throws SQLException, DatabaseUnitException {
        return new MySqlConnection(dataSource.getConnection(), "youthchina");
    }

}
