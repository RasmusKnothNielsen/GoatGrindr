package edu.kea.group.goatsite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM goats where username =?")
                // Query to get the authorization from the database
                .authoritiesByUsernameQuery("SELECT goats.username AS username, authorizations.role AS authority " +
                        "FROM goats JOIN authorizations on goats.id=authorizations.goat_id WHERE goats.username = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login").permitAll()
                        // Når vi logger ind, hvilken side skal vi så havne på?
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                .and()
                    .logout()
                        .logoutSuccessUrl("/login?logout").permitAll();
    }

    // PasswordEncoder Bean is needed to hash the password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}