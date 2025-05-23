package com.example.learningspringsecurity.seurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Recommended for production
import org.springframework.security.crypto.password.PasswordEncoder; // Use this for custom encoders
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines the security filter chain for HTTP requests.
     *
     * @param http The HttpSecurity object to configure.
     * @return The built SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Allow H2 console in an iframe
            )
                // 1. Disable CSRF for REST APIs, as it's typically handled differently
                //    (e.g., JWTs, tokens) or not needed if stateless and clients don't use sessions.
             
        
        .csrf(csrf -> csrf.disable())

                // 2. Configure authorization rules for different request paths.
                .authorizeHttpRequests(auth -> auth
                        // Allow specific endpoints to be accessed without authentication.
                        // Your /save endpoint is public.
                        .requestMatchers("/save").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(toH2Console()).permitAll() // Uses Spring Boot's utility for H2 console path

                        // Assuming /todos and /users/** are also meant to be public as per your controller.
                        .requestMatchers("/todos").permitAll()
                        .requestMatchers("/users/**").permitAll() // Example: /users/{title}/todo

                        // All other requests require authentication.
                        // If you have other API endpoints, they will require credentials.
                        .anyRequest().authenticated()
                )

                // 3. Enable HTTP Basic Authentication.
                //    This allows clients (like Postman) to send a username and password
                //    in the Authorization header.
                .httpBasic(withDefaults()); // Uses a default BasicAuthenticationEntryPoint

                // 4. Optionally configure session management (uncomment if needed for stateless APIs).
                //    For typical REST APIs using tokens, stateless session management is common.
                // .sessionManagement(session ->
                //     session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    /**
     * Defines in-memory users for testing purposes.
     * In a real application, you would typically integrate with a database
     * (e.g., using JdbcUserDetailsManager or a custom UserDetailsService).
     *
     * @return An InMemoryUserDetailsManager with a test user.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Define a test user
        UserDetails testUser = User.builder()
            .username("roka") // Test username
            // Encode the password using the BCryptPasswordEncoder bean
            .password(passwordEncoder.encode("123")) // Test password
            .roles("USER") // Assign roles to the user
            .build();

        UserDetails adminUser = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("adminpass"))
            .roles("ADMIN", "USER") // Admin has multiple roles
            .build();

        return new InMemoryUserDetailsManager(testUser, adminUser);
    }

    /**
     * Defines the password encoder to be used by Spring Security.
     * BCrypt is a strong, widely recommended hashing algorithm for passwords.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt is recommended for production
        return new BCryptPasswordEncoder();
    }
}