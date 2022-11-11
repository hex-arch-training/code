package hexarch.dms.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "hexarch.dms",
})
@EnableJpaRepositories(basePackages = {
        "hexarch.dms",
})
@EntityScan(basePackages = {
        "hexarch.dms",
})
public class DmsWebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DmsWebApiApplication.class, args);
    }
}
