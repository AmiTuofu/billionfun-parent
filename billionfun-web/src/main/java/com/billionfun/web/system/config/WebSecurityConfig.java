package com.billionfun.web.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2021/3/15 8:48 下午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                //.loginPage("/login.html")
                //.loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
    //            .failureUrl("/login.html")
                .and()
                .authorizeRequests()
                .antMatchers("/login.html", "/login")
                .permitAll()
                .antMatchers("/order")
                .hasAnyAuthority("ROLE_admin", "ROLE_user")
                .antMatchers("/system/user", "/system/role", "/system/menu")
                .hasAnyRole("admin")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhuyi")
                .password(bCryptPasswordEncoder().encode("123456"))
                .roles("user", "admin")
                .and()
                .passwordEncoder(bCryptPasswordEncoder());

    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
