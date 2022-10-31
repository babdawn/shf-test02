package com.atguigu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserDetailsService userDetailsService;
    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);禁用调用父类中的认证方式
//        内存中指定登录的用户信息（模拟的数据库中的用户）
       /* auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("");
        auth.inMemoryAuthentication().withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles();
        auth.inMemoryAuthentication().withUser("admin").password(bCryptPasswordEncoder.encode("admin")).roles("");*/
        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println(encode);
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

    }


    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
