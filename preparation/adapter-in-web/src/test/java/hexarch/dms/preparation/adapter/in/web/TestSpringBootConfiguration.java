package hexarch.dms.preparation.adapter.in.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = TestSpringBootConfiguration.BASE_PACKAGE)
public class TestSpringBootConfiguration {
    public static final String BASE_PACKAGE = "hexarch.dms.preparation";
}
