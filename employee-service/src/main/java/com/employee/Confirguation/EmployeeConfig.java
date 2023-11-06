package com.employee.Confirguation;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "secondEntityManagerFactoryBean",
basePackages = {"com.lcwd.db.postgres.repository"},
transactionManagerRef = "secondTransactionManager" )

public class EmployeeConfig {
	   @Autowired
		private Environment enviroment;
		
		@Bean("secondDataSource")
		@Primary
		public DataSource dataSource() {
			DriverManagerDataSource dataSource= new DriverManagerDataSource();
			dataSource.setUrl(enviroment.getProperty("second.datasource.url"));
			dataSource.setDriverClassName(enviroment.getProperty("second.datasource.driver-class-name"));
			dataSource.setSchema(enviroment.getProperty("second.datasource.username"));
			dataSource.setPassword(enviroment.getProperty("second.datasource.password"));
			return dataSource;
			
		}
		
		@Bean(name= "secondEntityManagerFactoryBean")
		@Primary
		public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
			LocalContainerEntityManagerFactoryBean bean= new LocalContainerEntityManagerFactoryBean();
			bean.setDataSource(dataSource());
			
			JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
			Map<String, Object> props = new HashMap<>();
			props.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
			props.put("hibernate.show_sql", "true");
			props.put("hibernate.hbm2ddl.auto", "update");
			
			bean.setJpaPropertyMap(props);
			bean.setPackagesToScan("com.lcwd.postgres.entities");
			return bean;
		}
		
		@Primary
		@Bean(name="secondTransactionManager")
		public PlatformTransactionManager transactionManager() {
			JpaTransactionManager manager = new JpaTransactionManager();
			manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
			return manager;
			
		}

}
