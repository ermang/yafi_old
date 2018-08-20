package egcom.yafi.packy;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "egcom.yafi.repo")
@EntityScan(basePackages = "egcom.yafi.entity")
@ComponentScan(basePackages = "egcom.yafi.service")
public class MainConfiguration {
}
