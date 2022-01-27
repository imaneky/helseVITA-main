package com.urjc.es.helseVITA.Security;

import com.urjc.es.helseVITA.Enums.EnumRolUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public RepositoryUserDetailsService userDetailsService;


    public EnumRolUsers rol;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()

                .antMatchers("/index", "/login", "/", "/loginError", "/logout", "/exito", "/exito-contacto",

                        "/contact-us", "/faq", "/myHelsevita", "/search-center", "/work-with-us", "/error", "/insurance", "/politica")   //Aquí se ponen las rutas que se permiten a ese rol (Anónimo en este caso)

                .permitAll()

                //privadas

                .antMatchers("/mostrarPacientes", "/mostrarCitas", "/loginExito", "/indexAuth", "/nuevaCita", "/areaPaciente", "/areaSanitario", "/cualDoctor", "/citaAgregada", "/appointmentNotFound","/appointmentAlreadyExist/**", "/appointment").hasAnyRole("ADMIN")

                .antMatchers("/indexAuth", "/loginExito").hasAnyRole("PATIENT")
                .antMatchers("/areaSanitario", "/indexAuth", "/loginExito", "/mostrarPacientes").hasAnyRole("HEALTHPERSONNEL")


                .antMatchers("/appointmentAlreadyExist/**", "/appointment", "/appointmentNotFound", "/citaAgregada", "/cualDoctor", "/areaPaciente",
                        "/mostrarCitas", "/nuevaCita").hasAnyRole("PATIENT") //Páginas permitidas para Paciente


                .antMatchers(  "/asignarNuevoPaciente", "/asignarNuevoSanitario",
                        "/buscarPaciente", "/buscarSanitario",  "/areaAdmin", "/crearPaciente", "/crearSanitario",  "/user-not-found",
                        "/mostrarSanitario", "/userAlreadyExists", "/admin/**", "/mostrar/**").hasAnyRole("ADMIN") //Páginas permitidas para Admin

                 //ADMIN AND HEALTH PERSONNEL
                .antMatchers("/preguntasSinContestar", "/preguntasSinContestar/**", "/contestarPregunta/**").hasAnyRole("ADMIN", "HEALTHPERSONNEL")


                .antMatchers("/myProfile").hasAnyRole("ADMIN", "HEALTHPERSONNEL", "PATIENT");


        http
                .formLogin()
                .loginPage("/login") //Ruta login
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/loginExitoso") //Página a la que nos lleva al hacer el login
                .failureUrl("/loginError")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")//Url para deslogearse
                .logoutSuccessUrl("/performLogout") //Url de la zona pública
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .headers().frameOptions().disable();//Para poder acceder a la consola de h2

        http
                .headers()
                .xssProtection();

        http.httpBasic();

    }
}
