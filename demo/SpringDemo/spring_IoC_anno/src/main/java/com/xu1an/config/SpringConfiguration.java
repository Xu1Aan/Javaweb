package com.xu1an.config;

import org.springframework.context.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Xu1Aan
 * @Date: 2022/03/17/14:22
 * @Description:
 */

//标志该类时Spring的核心配置类
@Configuration

//主键扫描 <context:component-scan base-package="com.xu1an"></context:component-scan>
@ComponentScan("com.xu1an")

//添加配置 <import resource=""/>
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {

}
