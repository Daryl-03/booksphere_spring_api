package dev.richryl.booksphere.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        System.err.println("WebSecurityConfig.securityFilterChain");
        http.authorizeHttpRequests(
                authz -> authz
                        .anyRequest().permitAll()
                        );

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        http.addFilterBefore(
                new FirebaseAuthenticationFilter(
                        new FirebaseAuthenticationService()
                ),
                BasicAuthenticationFilter.class
        );
        return http.build();
    }

}
