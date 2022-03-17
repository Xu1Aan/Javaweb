package com.xu1an.dao.impl;

import com.xu1an.dao.UserDao;
import com.xu1an.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Xu1Aan
 * @Date: 2022/03/14/20:36
 * @Description:
 */
public class UserDaoImpl implements UserDao {

// 1、普通数据类型注入
//    private String name;
//    private int age;
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

// 2、集合数据类型(list<String>)注入
//    private List<String> names;
//
//    public void setNames(List<String> names) {
//        this.names = names;
//    }

// 3、集合数据类型（List<User>）注入
//    private List<User> users;
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

// 4、集合数据类型（ Map<String,User> ）注入
//    private Map<String,User> userMap;
//
//    public void setUserMap(Map<String, User> userMap) {
//        this.userMap = userMap;
//    }

// 5、集合数据类型（Properties）注入
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public UserDaoImpl(){
        System.out.println("userDaoImpl creating ...");
    }

    public void init(){
        System.out.println("init method!");
    }

    public void destroy(){
        System.out.println("destroy method!");
    }

    @Override
    public void save() {
        System.out.println("save running ...");
        System.out.println(properties);
    }
}
