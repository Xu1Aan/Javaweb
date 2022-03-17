package com.xu1an.factory;

import com.xu1an.dao.UserDao;
import com.xu1an.dao.impl.UserDaoImpl;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Xu1Aan
 * @Date: 2022/03/15/10:39
 * @Description:
 */
public class DynamicFactory {
    public UserDao getUserDao(){
        return  new UserDaoImpl();
    }
}
