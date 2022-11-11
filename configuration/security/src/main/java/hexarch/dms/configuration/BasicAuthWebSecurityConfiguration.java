package hexarch.dms.configuration;

import hexarch.dms.verification.application.port.out.GetSecurityContextPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class BasicAuthWebSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var users = IntStream.rangeClosed(0, 10)
                .boxed()
                .map(id -> User
                        .withUsername("user" + id)
                        .password("{noop}password")
                        .roles("USER")
                        .build()
                ).collect(Collectors.toList());
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public GetSecurityContextPort getSecurityContextPort() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return new hexarch.dms.verification.domain.User(authentication.getName());
        };
    }
}
