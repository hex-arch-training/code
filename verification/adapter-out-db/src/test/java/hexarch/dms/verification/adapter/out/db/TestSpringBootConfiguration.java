package hexarch.dms.verification.adapter.out.db;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = TestSpringBootConfiguration.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = TestSpringBootConfiguration.BASE_PACKAGE)
@EntityScan(basePackages = TestSpringBootConfiguration.BASE_PACKAGE)
public class TestSpringBootConfiguration {
    public static final String BASE_PACKAGE = "hexarch.dms.verification";
}
