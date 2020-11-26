# JavaFinalProject



### 项目名称

**管实BBS**



### 项目背景

该项目旨在为管实班同学提供一个公共发言平台。每位同学都可以在这个平台上分享日常、吐槽课程、发起讨论等。



### 项目功能

用户可以自主发表博客，可以点赞和评论他人博客；平台为用户提供了修改密码、查看个人博客的功能。



### 项目框架

后端采用`springboot`，使用`thymeleaf`的模板引擎，与前端交互；同时，使用原生`Java  jdbc`进行数据库的连接。

前端使用`html`+`css`+`jquery`进行开发。



### 项目分工

`前端代码编写、框架搭建及交互设计`：周云猛

`后端数据库连接、接口编写`：刘明锝、李洪浩



### 项目代码结构

![image-20201126123735880](imgs\image-20201126123735880.png)

`src`目录下主要存放我们项目编写的源代码；

`java`目录下存放我们的后端代码，即使用`Java`编写的代码；

`config`目录下存放项目中的一些配置文件，在我们的项目中主要是放置`拦截器`；

`controller`目录下存放我们的`控制器类`，这里我们可以视作`路由控制`，配合`@RequestMapping`，我们可以监听路由，处理外部请求，并执行相关操作；

`entity`目录下存放我们的相关实体类。在此项目中，我们的数据库连接、增删改查的操作以及外部接口的编写代码均放置于此；

`DemoApplication`是我们整个项目的启动类。

`@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})`

这里我们使用原生`Jdbc`进行数据库操作，所以我们不需要`springboot`为我们配置数据库相关；故添加后面的一句。



`resources`目录下存放我们的前端资源。

`static`目录下存放我们的静态资源，如`css` ,`js` ,`images` ,`font`。

`templates`目录下存放我们的模板`html`文件。



`application.yml`中存放我们项目的配置。

`pom.xml`存放我们的项目依赖的配置。

这里介绍一下`Maven`，项目中所用到的所用依赖`jar`包，都通过`maven`进行管理。就像`Python`中的`pip`。





