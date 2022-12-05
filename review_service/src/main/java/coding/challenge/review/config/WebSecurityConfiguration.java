package coding.challenge.review.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("java:S1874")
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .antMatchers(HttpMethod.GET).permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(
            @Value("${app.editor.login}") String pocUserName,
            @Value("${app.editor.password}") String pocPassword
    ) {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username(pocUserName)
                .password(pocPassword)
                .roles("EDITOR")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
