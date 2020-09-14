package xyz.prmusic.config.spring;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import xyz.prmusic.config.mybatis.JdbcConfig;
import xyz.prmusic.config.redis.RedisConfig;

import javax.sql.DataSource;
import java.util.Properties;

@Import({RedisConfig.class})
@Configuration
@ComponentScan("xyz.prmusic")
@MapperScan("xyz.prmusic.mapper")
@EnableTransactionManagement
@EnableWebSecurity
public class MainConfig extends AbstractSecurityWebApplicationInitializer{

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource(JdbcConfig jdbcConfig) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(jdbcConfig.getDriver());
        hikariDataSource.setJdbcUrl(jdbcConfig.getUrl());
        hikariDataSource.setUsername(jdbcConfig.getUsername());
        hikariDataSource.setPassword(jdbcConfig.getPassword());
        hikariDataSource.setReadOnly(jdbcConfig.isReadOnly());
        hikariDataSource.setConnectionTimeout(jdbcConfig.getConnectionTimeout());
        hikariDataSource.setIdleTimeout(jdbcConfig.getIdleTimeout());
        hikariDataSource.setMaxLifetime(jdbcConfig.getMaxLifetime());
        hikariDataSource.setMaximumPoolSize(jdbcConfig.getMaximumPoolSize());
        return hikariDataSource;
    }

    //配置mybatis工厂
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //注入数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置实体类的位置
        sqlSessionFactoryBean.setTypeAliasesPackage("xyz.prmusic.entity,xyz.prmusic.mapper");
        //配置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties props = new Properties();
        props.setProperty("offsetAsPageNum", "true");
        props.setProperty("rowBoundsWithCount", "true");
        props.setProperty("pageSizeZero", "true");
        props.setProperty("reasonable", "true");
        props.setProperty("params","pageNum=start;pageSize=limit;");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        pageInterceptor.setProperties(props);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setCacheEnabled(true);
        sqlSessionFactory.getConfiguration().setLazyLoadingEnabled(true);
        sqlSessionFactory.getConfiguration().setAggressiveLazyLoading(true);
        sqlSessionFactory.getConfiguration().setUseGeneratedKeys(true);
        return sqlSessionFactory;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("xyz.prmusic.mapper");
        return mapperScannerConfigurer;
    }


    @Bean
    public TransactionManager transactionManager(@Autowired DataSource dataSource){
        TransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }
}
