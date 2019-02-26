package com.youthchina;

import com.youthchina.service.zhongyang.JwtService;
import com.youthchina.util.zhongyang.GetUserByJwtFilter;
import com.youthchina.util.zhongyang.JwtAuthenticationProvider;
import com.youthchina.util.zhongyang.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String JWTTOKEN = null;
    private String REGISTER_URL = null;
    private String URL_PREFIX = null;

    private String LOGIN_URL = null;

    private JwtService jwtService;

    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    public SecurityConfig(JwtService jwtService, @Value("${web.url.prefix}") String url_prefix, JwtAuthenticationProvider jwtAuthenticationProvider, @Value("${security.token.header}") String JWTTOKEN) {
        this.jwtService = jwtService;
        this.URL_PREFIX = url_prefix;
        this.LOGIN_URL = URL_PREFIX + "/login";
        this.REGISTER_URL = URL_PREFIX + "/*/register";
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.JWTTOKEN = JWTTOKEN;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .cors().and()
                .csrf().disable()

                .authenticationProvider(jwtAuthenticationProvider)

                .authorizeRequests()
                .antMatchers(LOGIN_URL).permitAll()
                .antMatchers(REGISTER_URL).permitAll()
                .antMatchers(this.URL_PREFIX + "/home/**").permitAll()
                .antMatchers(this.URL_PREFIX + "/job-for-you/**").permitAll()
                .antMatchers(this.URL_PREFIX + "/discovery/**").permitAll()
                .antMatchers("/**").authenticated()
                .anyRequest().permitAll()


                .and()
                .addFilterBefore(getLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(getUserByJwtFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        ArrayList<String> header = new ArrayList();
        header.add(this.JWTTOKEN);
        header.add("X-LANGUAGE");
        header.add("Content-Type");
        configuration.setAllowedHeaders(header);
        configuration.setExposedHeaders(header);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    private GetUserByJwtFilter getUserByJwtFilter() throws Exception {
        return new GetUserByJwtFilter(jwtService);
    }

    private LoginFilter getLoginFilter() throws Exception {
        return new LoginFilter(LOGIN_URL, authenticationManager(), jwtService);
    }


    @Bean
    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}