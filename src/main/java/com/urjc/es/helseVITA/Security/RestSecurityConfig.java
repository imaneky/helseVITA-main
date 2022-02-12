package com.urjc.es.helseVITA.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String adminstr = "ADMIN";
    @Autowired
    public UserRepositoryAuthenticationProvider userRepoAuthProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**");
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/login").authenticated()
                //Patients
                .antMatchers(HttpMethod.GET, "/api/patients/{id}/**").hasRole(adminstr)
                .antMatchers(HttpMethod.GET, "/api/patients/**").hasRole(adminstr)
                .antMatchers(HttpMethod.PATCH, "/api/patients/{id}**").hasRole(adminstr)
                .antMatchers(HttpMethod.POST, "/api/patients/**").hasRole(adminstr)
                .antMatchers(HttpMethod.PUT, "/api/patients/{id}**").hasRole(adminstr)
                //HealthPersonnels
                .antMatchers(HttpMethod.DELETE, "/api/healthPersonnels/{id}/**").hasRole(adminstr)
                .antMatchers(HttpMethod.GET, "/api/healthPersonnels/{id}/**").hasRole(adminstr)
                .antMatchers(HttpMethod.GET, "/api/healthPersonnels/**").hasRole(adminstr)
                .antMatchers(HttpMethod.PATCH, "/api/healthPersonnels/{id}/**").hasRole(adminstr)
                .antMatchers(HttpMethod.POST, "/api/healthPersonnels/**").hasRole(adminstr)
                .antMatchers(HttpMethod.PUT, "/api/healthPersonnels/{id}/**").hasRole(adminstr)
                .antMatchers(HttpMethod.DELETE, "/api/healthPersonnels/{id}/**").hasRole(adminstr)
                //Appointments
                .antMatchers(HttpMethod.GET, "/api/appointments/{id}/**").hasRole(adminstr)
                .antMatchers(HttpMethod.GET, "/api/appointments/**").hasRole(adminstr)
                .antMatchers(HttpMethod.PATCH, "/api/appointments/{id}/**").hasRole(adminstr)
                .antMatchers(HttpMethod.POST, "/api/appointments/**").hasRole(adminstr)
                .antMatchers(HttpMethod.PUT, "/api/appointments/{id}/**").hasRole(adminstr)
                .antMatchers(HttpMethod.DELETE, "/api/appointments/{id}/**").hasRole(adminstr)
            .anyRequest().permitAll();
        http.csrf().disable();
        http.httpBasic();
        http.logout().logoutSuccessHandler((rq, rs, a) -> { });
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userRepoAuthProvider);
    }

}