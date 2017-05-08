package br.com.tcc.chamada.conf;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataSourceConfig {
	@Bean
	public Statistics statistics(EntityManagerFactory emf) {
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		return sessionFactory.getStatistics();
	}

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
	}
}
