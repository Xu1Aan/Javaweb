package com.xu1an.dao.impl;

import com.xu1an.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Xu1Aan
 * @Date: 2022/03/17/13:07
 * @Description:
 * 替代  <bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl"></bean>
 * 通过 Component("userDao")
 * 或者使用 Repository("userDao")
 */
//@Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save running ...");
    }
}
