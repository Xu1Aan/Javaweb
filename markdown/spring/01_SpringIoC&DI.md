## 1. spring概述

### 1.1 Spring是什么（理解）

Spring是分层的 Java SE/EE应用 full-stack 轻量级开源框架，以 IoC（Inverse Of Control：反转控制）和 AOP（Aspect Oriented Programming：面向切面编程）为内核。

提供了展现层 SpringMVC和持久层 Spring JDBCTemplate以及业务层事务管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的Java EE 企业应用开源框架

### 1.2 Spring发展历程 （了解）

Rod Johnson （ Spring 之父）

2017  年
9 月份发布了 Spring 的最新版本 Spring5.0
通用版（GA）

### 1.3 Spring的优势（理解）

方便解耦，简化开发

AOP 编程的支持

声明式事务的支持

方便程序的测试

### 1.4 Spring的体系结构（了解）

![](.\img\图片29.png)



## 2. spring快速入门

### 2.1 Spring程序开发步骤 

①导入 Spring 开发的基本包坐标

②编写 Dao 接口和实现类

③创建 Spring 核心配置文件

④在 Spring 配置文件中配置 UserDaoImpl

⑤使用 Spring 的 API 获得 Bean 实例

### 2.2 导入Spring开发的基本包坐标

```xml
<properties>
	<spring.version>5.0.5.RELEASE</spring.version>
</properties>
<!--导入spring的context坐标，context依赖core、beans、expression-->
<dependencies> 
    <dependency>  
        <groupId>org.springframework</groupId> 
        <artifactId>spring-context</artifactId> 
        <version>${spring.version}</version>
    </dependency>
</dependencies>

```

### 2.3 编写Dao接口和实现类

```java
public interface UserDao {  
    public void save();
}

public class UserDaoImpl implements UserDao {  
        @Override  
        public void save() {
        	System.out.println("UserDao save method running....");
	}
}
```

### 2.4 创建Spring核心配置文件

在类路径下（resources）创建applicationContext.xml配置文件

```java
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"             		   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="
    http://www.springframework.org/schema/beans      	             http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
```

### 2.5 在Spring配置文件中配置UserDaoImpl

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"             		   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="
    http://www.springframework.org/schema/beans      	             http://www.springframework.org/schema/beans/spring-beans.xsd">
   <bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl"></bean>
</beans>

```

### 2.6 使用Spring的API获得Bean实例

```java
@Test
public void test1(){
		ApplicationContext applicationContext = new  
             ClassPathXmlApplicationContext("applicationContext.xml");
             UserDao userDao = (UserDao) applicationContext.getBean("userDao");   				 userDao.save();
 }
```



## 3. Spring配置文件

### 3.1 Bean标签基本配置 

用于配置对象交由Spring 来创建。

默认情况下它调用的是类中的无参构造函数，如果没有无参构造函数则不能创建成功。

基本属性：

id：Bean实例在Spring容器中的唯一标识

class：Bean的全限定名称

### 3.2 Bean标签范围配置 

scope:指对象的作用范围，取值如下： 

| 取值范围         | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| singleton        | 默认值，单例的                                               |
| prototype        | 多例的                                                       |
| request          | WEB   项目中，Spring   创建一个   Bean   的对象，将对象存入到   request   域中 |
| session          | WEB   项目中，Spring   创建一个   Bean   的对象，将对象存入到   session   域中 |
| global   session | WEB   项目中，应用在   Portlet   环境，如果没有   Portlet   环境那么globalSession   相当于   session |

1）当scope的取值为singleton时

​      Bean的实例化个数：1个

​      Bean的实例化时机：当Spring核心文件被加载时，实例化配置的Bean实例

​      Bean的生命周期：

对象创建：当应用加载，创建容器时，对象就被创建了

对象运行：只要容器在，对象一直活着

对象销毁：当应用卸载，销毁容器时，对象就被销毁了

2）当scope的取值为prototype时

​      Bean的实例化个数：多个

​      Bean的实例化时机：当调用getBean()方法时实例化Bean

对象创建：当使用对象时，创建新的对象实例

对象运行：只要对象在使用中，就一直活着

对象销毁：当对象长时间不用时，被 Java 的垃圾回收器回收了

**测试**

在UserDaoImpl中添加无参构造方法：

```java
public UserDaoImpl(){
	System.out.println("userDaoImpl creating ...");
}
```

修改applicationContext.xml中的配置信息

```xml
<!-- Bean标签的范围配置，Scope对象的作文范围： singleton：默认值，单例的；prototype：多例的 -->
	<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl" scope="singleton"></bean>
    <bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl" scope="prototype"></bean>
```

当scope为singleton时，在applicationContext.xml创建时就实例化了Bean的实例。

当scope为prototype时，在调用getBean()方法时就实例化Bean的实例。

### 3.3 Bean生命周期配置

init-method：指定类中的初始化方法名称

destroy-method：指定类中销毁方法名称

指定`com.xu1an.dao.impl.UserDaoImpl`的`init()`和`destroy()`方法

```xml
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl" init-method="init" destroy-method="destroy"></bean>
```

结果打印为

```
userDaoImpl creating ...
init method!
save running ...
```

### 3.4 Bean实例化三种方式

1） 使用无参构造方法实例化

​      它会根据默认无参构造方法来创建类对象，如果bean中没有默认无参构造函数，将会创建失败

```xml
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl"></bean>
```

2） 工厂静态方法实例化

​      工厂的静态方法返回Bean实例

```java
public class StaticFactory {
    public static UserDao getUserDao(){    
    return new UserDaoImpl();
    }
}
```

```xml
<bean id="userDao" class="com.xu1an.factory.StaticFactory" 
      factory-method="getUserDao" />
```

3） 工厂实例方法实例化

​      工厂的非静态方法返回Bean实例

```java
public class DynamicFactory {  
	public UserDao getUserDao(){        
		return new UserDaoImpl(); 
	}
}
```

```xml
<bean id="factoryBean" class="com.xu1an.factory.DynamicFactory"/>
<bean id="userDao" factory-bean="factoryBean" factory-method="getUserDao"/>
```

Bean三种实例化在application

```xml
<!-- Bean实例化的三种方式   -->
<!-- 1、无参构造方法实例化-->
    <bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl"></bean>
<!-- 2、工厂静态方法实例化-->
    <bean id="userDao" class="com.xu1an.factory.StaticFactory" factory-method="getUserDao"></bean>
<!-- 3、工厂实例方法实例化-->
    <bean id="factory" class="com.xu1an.factory.DynamicFactory"></bean>
    <bean id="userDao" factory-bean="factory" factory-method="getUserDao"></bean>
```

### 3.5 Bean的依赖注入入门

①创建 UserService，UserService 内部在调用 UserDao的save() 方法

```java
public class UserServiceImpl implements UserService {
	@Override
	public void save() {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml"); 
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");	
        userDao.save();
 	}
 }
```

②将 UserServiceImpl 的创建权交给 Spring

```xml
<bean id="userService" class="com.xu1an.service.impl.UserServiceImpl"/>
```

③从 Spring 容器中获得 UserService 进行操作

```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
UserService userService = (UserService) applicationContext.getBean("userService");
userService.save();
```

### 3.6 Bean的依赖注入概念

依赖注入（Dependency Injection）：它是 Spring 框架核心 IOC 的具体实现。

在编写程序时，通过控制反转，把对象的创建交给了 Spring，但是代码中不可能出现没有依赖的情况。

IOC 解耦只是降低他们的依赖关系，但不会消除。例如：业务层仍会调用持久层的方法。

那这种业务层和持久层的依赖关系，在使用 Spring 之后，就让 Spring 来维护了。

简单的说，就是坐等框架把持久层对象传入业务层，而不用我们自己去获取

### 3.7 Bean的依赖注入方式

①构造方法

​      创建有参构造

```java
public class UserServiceImpl implements UserService {
@Override
public void save() {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");   
    UserDao userDao = (UserDao) applicationContext.getBean("userDao");    
    userDao.save();
    }
 }
```

​      配置Spring容器调用有参构造时进行注入

```xml
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl"/>
<bean id="userService" class="com.xu1an.service.impl.UserServiceImpl" >
	<constructor-arg name="userDao" ref="userDao"></constructor-arg>
</bean>
```

有参构造方法必须使用`<construtor-arg name="xxx" ref="xxx"><construtor-arg/>`,不然程序会报错

```java
//如果使用<property name="xxx" ref="xxx"><property/>
 No default constructor found;
```

②set方法

​      在UserServiceImpl中添加setUserDao方法

```java
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;  
        } 
    @Override    
    public void save() {      
   		 userDao.save();
	}
}
```

​      配置Spring容器调用set方法进行注入

```xml
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl"/>
<bean id="userService" class="com.xu1an.service.impl.UserServiceImpl">
	<property name="userDao" ref="userDao"/>
</bean>
```

**set方法:P命名空间注入**

​      P命名空间注入本质也是set方法注入，但比起上述的set方法注入更加方便，主要体现在配置文件中，如下：

​      首先，需要引入P命名空间：

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

其次，需要修改注入方式

```xml
<bean id="userService" class="com.xu1an.service.impl.UserServiceImpl" p:userDao-ref="userDao"/>
```

```xml
<!-- Bean依赖注入的两种方式-->
    <bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl"></bean>
    <bean id="userService" class="com.xu1an.service.impl.UserServiceImpl" >
<!-- 1、有参构造方法注入-->
        <constructor-arg name="userDao" ref="userDao"></constructor-arg>
<!-- 2、set方法注入-->
        <property name="userDao" ref="userDao"/>-->
    </bean>
<!--    set p命名空间注入-->
    <bean id="userService" class="com.xu1an.service.impl.UserServiceImpl" p:userDao-ref="userDao"/>
```

###    3.8 Bean的依赖注入的数据类型

上面的操作，都是注入的引用Bean，处了对象的引用可以注入，普通数据类型，集合等都可以在容器中进行注入。

注入数据的三种数据类型 

- 普通数据类型

- 引用数据类型

- 集合数据类型

其中引用数据类型，此处就不再赘述了，之前的操作都是对UserDao对象的引用进行注入的，下面将以set方法注入为例，演示普通数据类型和集合数据类型的注入。

**Bean的依赖注入的数据类型**

（1）普通数据类型的注入

```java
public class UserDaoImpl implements UserDao {
	private String name;
    private int age;
    public void setname(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void save() {
        System.out.println(name+"==="+age);
        System.out.println("UserDao save method running....");   
    }
}
```

```xml
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl">
    <property name="name" value="xu1an"></property>
    <property name="age" value="15"></property>
</bean>
```

（2）集合数据类型（List<String>）的注入

```java
public class UserDaoImpl implements UserDao {
	private List<String> strList;
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public void save() {
        System.out.println(strList);
        System.out.println("UserDao save method running....");
	}
}
```

```xml
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl">
    <property name="strList">
        <list>
            <value>aaa</value>
            <value>bbb</value>
            <value>ccc</value>
        </list>
    </property>
</bean>
```

（3）集合数据类型（List<User>）的注入

```java
public class UserDaoImpl implements UserDao {
	private List<User> userList;
	public void setUserList(List<User> userList) {
	this.userList = userList;  
 }
public void save() {
	System.out.println(userList);
	System.out.println("UserDao save method running....");
	}
}
```

```xml
<bean id="u1" class="com.xu1an.domain.User"/>
<bean id="u2" class="com.xu1an.domain.User"/>
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl">
    <property name="userList">
        <list>
            <bean class="com.xu1an.domain.User"/>
            <bean class="com.xu1an.domain.User"/>
            <ref bean="u1"/>
            <ref bean="u2"/>       
        </list>
    </property>
</bean>
```

输出

```
[com.xu1an.domain.User@7b49cea0, com.xu1an.domain.User@887af79, com.xu1an.domain.User@7fac631b, com.xu1an.domain.User@5b87ed94]
```

（4）集合数据类型（ Map<String,User> ）的注入

```java
public class UserDaoImpl implements UserDao {
    private Map<String,User> userMap;
    public void setUserMap(Map<String, User> userMap) {
    this.userMap = userMap;
    }    
public void save() {      
	System.out.println(userMap);
	System.out.println("UserDao save method running....");
	}
}
```

```xml
<bean id="u1" class="com.xu1an.domain.User"/>
<bean id="u2" class="com.xu1an.domain.User"/>
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl">
    <property name="userMap">
        <map>            
            <entry key="xu1an" value-ref="u1"/>
            <entry key="xu2an" value-ref="u2"/>
        </map>
    </property>
</bean>
```

输出

```
{xu1an=com.xu1an.domain.User@7fac631b, xu2an=com.xu1an.domain.User@5b87ed94}
```

（5）集合数据类型（Properties）的注入

```java
public class UserDaoImpl implements UserDao {
    private Properties properties;
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
	public void save() {
		System.out.println(properties);
		System.out.println("UserDao save method running....");
	}
}
```

```xml
<bean id="userDao" class="com.xu1an.dao.impl.UserDaoImpl">
    <property name="properties">
        <props>
            <prop key="p1">aaa</prop>
            <prop key="p2">bbb</prop> 
            <prop key="p3">ccc</prop>
        </props>
    </property>
</bean>
```

输出

```
{p3=ccc, p2=bbb, p1=aaa}
```

### 3.9 引入其他配置文件（分模块开发）

实际开发中，Spring的配置内容非常多，这就导致Spring配置很繁杂且体积很大，所以，可以将部分配置拆解到其他配置文件中，而在Spring主配置文件通过import标签进行加载

```xml
<import resource="applicationContext-xxx.xml"/>
```

## 4. spring相关API

### 4.1 ApplicationContext的继承体系

applicationContext：接口类型，代表应用上下文，可以通过其实例获得 Spring 容器中的 Bean 对象

### 4.2 ApplicationContext的实现类

1）ClassPathXmlApplicationContext 

​      它是从类的根路径下加载配置文件 推荐使用这种

2）FileSystemXmlApplicationContext 

​      它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置。

3）AnnotationConfigApplicationContext

​      当使用注解配置容器对象时，需要使用此类来创建 spring 容器。它用来读取注解。

### 4.3 getBean()方法使用

```java
public Object getBean(String name) throws BeansException {  
	assertBeanFactoryActive();   
	return getBeanFactory().getBean(name);
}
public <T> T getBean(Class<T> requiredType) throws BeansException {   			    	assertBeanFactoryActive();
	return getBeanFactory().getBean(requiredType);
}
```

其中，当参数的数据类型是字符串时，表示根据Bean的id从容器中获得Bean实例，返回是Object，需要强转。

当参数的数据类型是Class类型时，表示根据类型从容器中匹配Bean实例，当容器中相同类型的Bean有多个时，则此方法会报错

**getBean()方法使用**

```java
ApplicationContext applicationContext = new 
            ClassPathXmlApplicationContext("applicationContext.xml");
  UserService userService1 = (UserService) applicationContext.getBean("userService");
  UserService userService2 = applicationContext.getBean(UserService.class);
```

---

## 5.Spring配置数据源

### 5.1 数据源（连接池）的作用 

数据源(连接池)是提高程序性能如出现的

事先实例化数据源，初始化部分连接资源

使用连接资源时从数据源中获取

使用完毕后将连接资源归还给数据源

常见的数据源(连接池)：DBCP、C3P0、BoneCP、Druid等

**开发步骤**

①导入数据源的坐标和数据库驱动坐标

②创建数据源对象

③设置数据源的基本连接数据

④使用数据源获取连接资源和归还连接资源

### 5.2 数据源的手动创建

①导入c3p0和druid的坐标

```xml
<!-- C3P0连接池 -->
<dependency>
    <groupId>c3p0</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.1.2</version>
</dependency>
<!-- Druid连接池 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.10</version>
</dependency>
```

①导入mysql数据库驱动坐标

```xml
<!-- mysql驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.39</version>
</dependency>
```

②创建C3P0连接池

```java
@Test
public void testC3P0() throws Exception {
	//创建数据源
	ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//设置数据库连接参数
    dataSource.setDriverClass("com.mysql.jdbc.Driver");    	               	               dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    dataSource.setUser("root");
    dataSource.setPassword("root");
	//获得连接对象
	Connection connection = dataSource.getConnection();
	System.out.println(connection);
}

```

②创建Druid连接池

```java
@Test
public void testDruid() throws Exception {
    //创建数据源
    DruidDataSource dataSource = new DruidDataSource();
    //设置数据库连接参数
    dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
    dataSource.setUrl("jdbc:mysql://localhost:3306/test");   
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    //获得连接对象
    Connection connection = dataSource.getConnection();    
    System.out.println(connection);
}
```

③提取jdbc.properties配置文件

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test
jdbc.username=root
jdbc.password=root
```

④读取jdbc.properties配置文件创建连接池

```java
@Test
public void testC3P0ByProperties() throws Exception {
    //加载类路径下的jdbc.properties
    ResourceBundle rb = ResourceBundle.getBundle("jdbc");
    ComboPooledDataSource dataSource = new ComboPooledDataSource(); 
    dataSource.setDriverClass(rb.getString("jdbc.driver"));   
    dataSource.setJdbcUrl(rb.getString("jdbc.url")); 
    dataSource.setUser(rb.getString("jdbc.username")); 
    dataSource.setPassword(rb.getString("jdbc.password"));
    Connection connection = dataSource.getConnection();   
    System.out.println(connection);
}
```

### 5.3 Spring配置数据源

可以将DataSource的创建权交由Spring容器去完成

DataSource有无参构造方法，而Spring默认就是通过无参构造方法实例化对象的

DataSource要想使用需要通过set方法设置数据库连接信息，而Spring可以通过set方法进行字符串注入

```xml
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="com.mysql.jdbc.Driver"/>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"/>
    <property name="user" value="root"/>
    <property name="password" value="root"/>
</bean>
```

测试从容器当中获取数据源

```java
ApplicationContext applicationContext = new 
           ClassPathXmlApplicationContext("applicationContext.xml");
               DataSource dataSource = (DataSource) 
applicationContext.getBean("dataSource");
Connection connection = dataSource.getConnection();
System.out.println(connection);
```

### 5.4 抽取jdbc配置文件

applicationContext.xml加载jdbc.properties配置文件获得连接信息。

首先，需要引入context命名空间和约束路径：

命名空间：xmlns:context="http://www.springframework.org/schema/context"

约束路径：http://www.springframework.org/schema/context
​                       

​                   http://www.springframework.org/schema/context/spring-context.xsd

```xml
<context:property-placeholder location="classpath:jdbc.properties"/>
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="${jdbc.driver}"/>
    <property name="jdbcUrl" value="${jdbc.url}"/>
    <property name="user" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>
```

### 5.5 知识要点 

Spring容器加载properties文件

```xml
<context:property-placeholder location="xx.properties"/>
<property name="" value="${key}"/>
```

## 6. Spring注解开发

### 6.1 Spring原始注解

Spring是轻代码而重配置的框架，配置比较繁重，影响开发效率，所以注解开发是一种趋势，注解代替xml配置文件可以简化配置，提高开发效率。 

Spring原始注解主要是替代<Bean>的配置

| 注解           | 说明                                           |
| -------------- | ---------------------------------------------- |
| @Component     | 使用在类上用于实例化Bean                       |
| @Controller    | 使用在web层类上用于实例化Bean                  |
| @Service       | 使用在service层类上用于实例化Bean              |
| @Repository    | 使用在dao层类上用于实例化Bean                  |
| @Autowired     | 使用在字段上用于根据类型依赖注入               |
| @Qualifier     | 结合@Autowired一起使用用于根据名称进行依赖注入 |
| @Resource      | 相当于@Autowired+@Qualifier，按照名称进行注入  |
| @Value         | 注入普通属性                                   |
| @Scope         | 标注Bean的作用范围                             |
| @PostConstruct | 使用在方法上标注该方法是Bean的初始化方法       |
| @PreDestroy    | 使用在方法上标注该方法是Bean的销毁方法         |

注意：

使用注解进行开发时，需要在applicationContext.xml中配置组件扫描，作用是指定哪个包及其子包下的Bean需要进行扫描以便识别使用注解配置的类、字段和方法。

```java
<!--注解的组件扫描-->
<context:component-scan base-package="com.itheima"></context:component-scan>
```

使用@Compont或@Repository标识UserDaoImpl需要Spring进行实例化。

```java
//@Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
    	System.out.println("save running... ...");
    }
}
```

使用@Compont或@Service标识UserServiceImpl需要Spring进行实例化

使用@Autowired或者@Autowired+@Qulifier或者@Resource进行userDao的注入

```java
//@Component("userService")
@Service("userService")
public class UserServiceImpl implements UserService {
    /*@Autowired
    @Qualifier("userDao")*/
    @Resource(name="userDao")
    private UserDao userDao;
    @Override
    public void save() {       
   	  userDao.save();
    }
}
```

使用@Value进行字符串的注入

```java
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Value("注入普通数据")
    private String str;
    @Value("${jdbc.driver}")
    private String driver;
    @Override
    public void save() {
        System.out.println(str);
        System.out.println(driver);
        System.out.println("save running... ...");
    }
}
```

使用@Scope标注Bean的范围

```java
//@Scope("prototype")
@Scope("singleton")
public class UserDaoImpl implements UserDao {
   //此处省略代码
}
```

使用@PostConstruct标注初始化方法，使用@PreDestroy标注销毁方法

```java
@PostConstruct
public void init(){
	System.out.println("初始化方法....");
}
@PreDestroy
public void destroy(){
	System.out.println("销毁方法.....");
}
```

### 6.2 Spring新注解

使用上面的注解还不能全部替代xml配置文件，还需要使用注解替代的配置如下：

非自定义的Bean的配置：<bean>

加载properties文件的配置：<context:property-placeholder>

组件扫描的配置：<context:component-scan>

引入其他文件：<import>

| 注解            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| @Configuration  | 用于指定当前类是一个 Spring   配置类，当创建容器时会从该类上加载注解 |
| @ComponentScan  | 用于指定 Spring   在初始化容器时要扫描的包。   作用和在 Spring   的 xml 配置文件中的   <context:component-scan   base-package="com.itheima"/>一样 |
| @Bean           | 使用在方法上，标注将该方法的返回值存储到   Spring   容器中   |
| @PropertySource | 用于加载.properties   文件中的配置                           |
| @Import         | 用于导入其他配置类                                           |

@Configuration

@ComponentScan

@Import

```java
@Configuration
@ComponentScan("com.itheima")
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {
}
```

@PropertySource

@value

```java
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
```

@Bean

```java
@Bean(name="dataSource")
public DataSource getDataSource() throws PropertyVetoException { 
    ComboPooledDataSource dataSource = new ComboPooledDataSource(); 
    dataSource.setDriverClass(driver);
    dataSource.setJdbcUrl(url);
    dataSource.setUser(username);
    dataSource.setPassword(password);
    return dataSource;
} 
```

测试加载核心配置类创建Spring容器

```java
@Test
public void testAnnoConfiguration() throws Exception {
ApplicationContext applicationContext = new 
          AnnotationConfigApplicationContext(SpringConfiguration.class);    UserService userService = (UserService)    
    applicationContext.getBean("userService");
    userService.save();
    DataSource dataSource = (DataSource) 
    applicationContext.getBean("dataSource");
    Connection connection = dataSource.getConnection(); 
    System.out.println(connection);
    }
```

## 7. Spring整合Junit

### 7.1 原始Junit测试Spring的问题

在测试类中，每个测试方法都有以下两行代码：

```java
 ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
 IAccountService as = ac.getBean("accountService",IAccountService.class);
```

这两行代码的作用是获取容器，如果不写的话，直接会提示空指针异常。所以又不能轻易删掉。

### 7.2 上述问题解决思路

让SpringJunit负责创建Spring容器，但是需要将配置文件的名称告诉它

将需要进行测试Bean直接在测试类中进行注入

### 7.3 Spring集成Junit步骤

①导入spring集成Junit的坐标

②使用@Runwith注解替换原来的运行期

③使用@ContextConfiguration指定配置文件或配置类

④使用@Autowired注入需要测试的对象

⑤创建测试方法进行测试

### 7.4 Spring集成Junit代码实现

①导入spring集成Junit的坐标

```xml
<!--此处需要注意的是，spring5 及以上版本要求 junit 的版本必须是 4.12 及以上-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.0.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```

②使用@Runwith注解替换原来的运行期

```java
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJunitTest {
}
```

③使用@ContextConfiguration指定配置文件或配置类

```java
@RunWith(SpringJUnit4ClassRunner.class)
//加载spring核心配置文件
//@ContextConfiguration(value = {"classpath:applicationContext.xml"})
//加载spring核心配置类
@ContextConfiguration(classes = {SpringConfiguration.class})
public class SpringJunitTest {
}
```

④使用@Autowired注入需要测试的对象

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class SpringJunitTest {
    @Autowired
    private UserService userService;
}
```

⑤创建测试方法进行测试

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})public class SpringJunitTest {
    @Autowired
    private UserService userService;
    @Test
    public void testUserService(){
   	 userService.save();
    }
}
```

Spring集成Junit步骤

①导入spring集成Junit的坐标

②使用@Runwith注解替换原来的运行期

③使用@ContextConfiguration指定配置文件或配置类

④使用@Autowired注入需要测试的对象

⑤创建测试方法进行测试

