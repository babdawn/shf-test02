package com.atguigu.config;

import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @DubboReference
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库根据用户名字查询用户信息
        Admin admin = adminService.findAdminByUserName(username);
        if (admin == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new User(admin.getUsername(), admin.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
    }
}
