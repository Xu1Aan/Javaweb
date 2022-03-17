package com.xu1an;

import com.xu1an.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Xu1Aan
 * @Date: 2022/03/15/9:42
 * @Description:
 */
public class SpringTest {
    /**
     * 测试Scope
     */
    @Test
    public void test1(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) app.getBean("userDao");
//        UserDao userDao2 = (UserDao) app.getBean("userDao");
        System.out.println(userDao1);
        userDao1.save();
//        System.out.println(userDao2);
    }
}
