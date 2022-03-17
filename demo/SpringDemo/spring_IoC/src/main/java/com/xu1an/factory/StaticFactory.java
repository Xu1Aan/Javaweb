package com.xu1an.factory;

import com.xu1an.dao.UserDao;
import com.xu1an.dao.impl.UserDaoImpl;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Xu1Aan
 * @Date: 2022/03/15/10:34
 * @Description:
 */
public class StaticFactory {
    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
