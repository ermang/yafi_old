package egcom.yaf.test;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@EnableJpaRepositories(basePackages = "egcom.yaf.repo")
@EntityScan(basePackages = "egcom.yaf.entity")
@ComponentScan(basePackages = "egcom.yaf.service")
public class TestConfig2 {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl("jdbc:derby:memory:testdb;create=true");
        dataSource.setUsername("app");

        return dataSource;
    }
}
