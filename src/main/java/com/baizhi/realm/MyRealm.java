package com.baizhi.realm;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {
    @Resource
    AdminDao adminDao;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String principal = (String) principals.getPrimaryPrincipal();
        System.out.println("进行授权校验");
        //查询数据库  根据主身份  查询对应角色
        //根据角色查询对应的权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");
        info.addRole("superAdmin");
        //赋予用户权限是，需要对权限进行去重
        info.addStringPermission("user:select:*");
        info.addStringPermission("user:add:*");
        info.addStringPermission("user:update:*");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        SimpleAuthenticationInfo info = null;
        //调用userDao 根据身份信息查询 对应的用户
        Admin admin = adminDao.queryByUsername(principal);
        System.out.println(admin);
        if (admin != null) {
            info = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(),
                    ByteSource.Util.bytes(admin.getSalt()), this.getName());
        }
        return info;
    }
}
