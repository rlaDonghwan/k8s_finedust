package inhatc.k8sProject.particulateMatter.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "inhatc.k8sProject.particulateMatter.repository.jeju",
        entityManagerFactoryRef = "jejuEntityManagerFactory",
        transactionManagerRef = "jejuTransactionManager"
)
public class  JejuDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.jeju.datasource")
    public DataSource jejuDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jejuEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean jejuEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("jejuDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("inhatc.k8sProject.particulateMatter.domain.jeju")
                .persistenceUnit("jeju")
                .properties(hibernateProperties())
                .build();
    }

    @Bean(name = "jejuTransactionManager")
    public PlatformTransactionManager jejuTransactionManager(
            @Qualifier("jejuEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, Object> hibernateProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }
}
