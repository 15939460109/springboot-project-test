package com.czg.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设定角色的权限   角色--资源
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登录功能
        http.formLogin().usernameParameter("username").passwordParameter("password")
                .loginPage("/login");

//        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设定用户 密码 角色之间的关系
        // security要求用户登录时  密码必须加密
        String password = new BCryptPasswordEncoder().encode("123");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(password).roles("VIP1", "VIP2","VIP3")
                .and()
                .withUser("guest").password(password).roles("VIP1", "VIP2")
                .and()
                .withUser("student").password(password).roles("VIP1");

//        super.configure(auth);
    }
}
