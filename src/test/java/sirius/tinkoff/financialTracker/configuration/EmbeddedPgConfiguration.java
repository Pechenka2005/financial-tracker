package sirius.tinkoff.financialTracker.configuration;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.Duration;

@Configuration
public class EmbeddedPgConfiguration {

    @Bean
    public EmbeddedPostgres embeddedPostgres() throws IOException {
        return EmbeddedPostgres.builder().setPGStartupWait(Duration.ofSeconds(120)).start();
    }

    @Bean
    public DataSource embeddedDataSource(EmbeddedPostgres pg) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:" + pg.getPort() + "/postgres");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("postgres");

        return new HikariDataSource(hikariConfig);
    }
}