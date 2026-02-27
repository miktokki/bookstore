package bookstore.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/api/books**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/delete/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())

                .formLogin(formlogin -> formlogin
                        .defaultSuccessUrl("/books", true)
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable()); // not for production, just for development
        return http.build();
    }

}
