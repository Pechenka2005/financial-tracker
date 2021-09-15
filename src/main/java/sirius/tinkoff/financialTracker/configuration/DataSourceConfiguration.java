package sirius.tinkoff.financialTracker.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
    public DataSource getDataSource() {
        var dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://34.116.131.138:5432/sirius8_db");
        dataSourceBuilder.username("sirius8");
        dataSourceBuilder.password("Fvc2ds");
        return dataSourceBuilder.build();
    }
}
