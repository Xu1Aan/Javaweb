package com.xu1an.service.impl;

import com.xu1an.dao.UserDao;
import com.xu1an.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Xu1Aan
 * @Date: 2022/03/17/13:10
 * @Description:
 * 替代    <bean id="userService" class="com.xu1an.service.impl.UserServiceImpl">
 *         <property name="userDao" ref="userDao"></property>
 * 通过   component("userService") 将userService交给Spring管理 IoC
 * 或者通过 Service("userService")
 *
 * 通过   Autowired 和 Qualifier("userDao") 进行依赖注入
 *
 */

//@Component("userService")
@Service("userService")
//@Scope("prototype")
public class UserServiceImpl implements UserService {

    //按照数据类型从Spring容器中进行匹配
    //@Autowired

    //按照id的值从容器中进行匹配的，但是主要此处需要@Qualifier结合@Autowired一起使用
    //@Qualifier("userDao")

    @Resource(name = "userDao") //@Resource相当于@Qualifier+@Autowired
    private UserDao userDao;

    @Value("${jdbc.driver}")
    private String driver;

    @Override
    public void save() {
        userDao.save();
        //System.out.println(driver);
    }

    @PostConstruct
    public void init(){
        System.out.println("userService init...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("userService destroy...");
    }
}
