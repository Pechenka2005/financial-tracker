package sirius.tinkoff.financialTracker.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("sirius.tinkoff.financialTracker.repository")
public class RepositoryConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       @Value("${spring.jpa.properties.hibernate.dialect}") String dialect) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        Properties properties = new Properties();
        properties.put("jpaDialect", dialect);
        entityManagerFactoryBean.setJpaProperties(properties);
        entityManagerFactoryBean.setPersistenceUnitName("persistence");
        entityManagerFactoryBean.setPackagesToScan("sirius.tinkoff.financialTracker.models");

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean factoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factoryBean.getObject());
        return transactionManager;
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource,
                                     ResourceLoader resourceLoader,
                                     @Value("${liquibase.contexts:local}") String contexts,
                                     @Value("${jdbc.schema:}") String jdbcSchema) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/db.changelog-master.xml");
        liquibase.setContexts(contexts);
        liquibase.setDataSource(dataSource);
        liquibase.setResourceLoader(resourceLoader);
        if (jdbcSchema.isEmpty()) {
            liquibase.setDefaultSchema("public");
        } else {
            Properties props = System.getProperties();
            props.setProperty("liquibase.databaseChangeLogTableName",
                    "DATABASECHANGELOG_" + jdbcSchema.toUpperCase());
            props.setProperty("liquibase.databaseChangeLogLockTableName",
                    "DATABASECHANGELOGLOCK_" + jdbcSchema.toUpperCase());
            liquibase.setDefaultSchema(jdbcSchema);
        }
        return liquibase;
    }

}
