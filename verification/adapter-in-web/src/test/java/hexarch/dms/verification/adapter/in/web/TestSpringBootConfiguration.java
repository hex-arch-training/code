package hexarch.dms.verification.adapter.in.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = TestSpringBootConfiguration.BASE_PACKAGE)
public class TestSpringBootConfiguration {
    public static final String BASE_PACKAGE = "hexarch.dms.verification";
}
