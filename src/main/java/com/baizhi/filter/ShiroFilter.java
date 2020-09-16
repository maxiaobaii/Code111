package com.baizhi.filter;

import com.baizhi.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @ClassNmae: ShiroFilter
 * @Author: yddm
 * @DateTime: 2020/9/15 19:26
 * @Description: TODO
 */

@Configuration
public class ShiroFilter {

    //将shiro过滤器工厂实例交由工厂管理
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        HashMap<String, String> map = new HashMap<>();
        //把当前项目所有的资源设置为认证资源   authc 认证过滤器
        map.put("/**", "authc");
        //将当前资源设置为匿名资源   anon  匿名过滤器
        map.put("/code/code", "anon");
//        map.put("/admin/login", "anon");
        map.put("/admin/**", "anon");
        map.put("/main/main.jsp", "anon");
        map.put("/bootstrap/**", "anon");
        map.put("/index.jsp", "anon");
        map.put("/login/login.jsp", "anon");
        //配置允许免密登录访问的资源
        //登出
        map.put("/logout", "logout");
        //配置过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //将安全管理器交由工厂管理
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        shiroFilterFactoryBean.setLoginUrl("/index.jsp");
        return shiroFilterFactoryBean;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm realm, EhCacheManager cacheManager, CookieRememberMeManager cookieRememberMeManager) {
        //创建安全管理器
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        //将cookie管理器交由安全管理器
        webSecurityManager.setRememberMeManager(cookieRememberMeManager);
        //将myrealm交由安全管理器
        webSecurityManager.setRealm(realm);
        //将缓存管理器交由安全管理器
        webSecurityManager.setCacheManager(cacheManager);
        return webSecurityManager;
    }

    @Bean
    public MyRealm getMyRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        //创建自定义Realm
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    //配置凭证匹配器
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        //先创建凭证匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置算法
        matcher.setHashAlgorithmName("md5");
        //设置散列次数
        matcher.setHashIterations(1024);
        return matcher;
    }

    //设置cookie管理
    @Bean
    public CookieRememberMeManager getCookieRememberMeManager(SimpleCookie simpleCookie) {
        //创建cookie管理器
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        return cookieRememberMeManager;
    }

    //cookie
    @Bean
    public SimpleCookie getSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("sevenDay");
        //设置记住我cookie生效时间  参数：秒
        simpleCookie.setMaxAge(60 * 60 * 24 * 7);
        return simpleCookie;
    }

    //设置缓存的管理
    @Bean
    public EhCacheManager getCacheManger() {
        //创建缓存管理器
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }
}
