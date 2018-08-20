package egcom.yaf.packy;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "egcom.yaf.repo")
@EntityScan(basePackages = "egcom.yaf.entity")
@ComponentScan(basePackages = "egcom.yaf.service")
public class MainConfiguration {
}
