# JavaFinalProject



### 项目名称

**管实BBS**


### 项目功能

用户可以自主发表博客，可以点赞和评论他人博客；平台为用户提供了修改密码、查看个人博客的功能。



### 项目框架

后端采用`springboot`，使用`thymeleaf`的模板引擎，与前端交互；同时，使用原生`Java  jdbc`进行数据库的连接。

前端使用`html`+`css`+`jquery`进行开发。



### 项目代码结构

`src`目录下主要存放我们项目编写的源代码；

`java`目录下存放我们的后端代码，即使用`Java`编写的代码；

`config`目录下存放项目中的一些配置文件，在我们的项目中主要是放置`拦截器`；

`controller`目录下存放我们的`控制器类`，这里我们可以视作`路由控制`，配合`@RequestMapping`，我们可以监听路由，处理外部请求，并执行相关操作；

`entity`目录下存放我们的相关实体类。在此项目中，我们的数据库连接、增删改查的操作以及外部接口的编写代码均放置于此；

`DemoApplication`是我们整个项目的启动类。

`resources`目录下存放我们的前端资源。

`static`目录下存放我们的静态资源，如`css` ,`js` ,`images` ,`font`。

`templates`目录下存放我们的模板`html`文件。

`application.yml`中存放我们项目的配置。

`pom.xml`存放我们的项目依赖的配置。

这里介绍一下`Maven`，项目中所用到的所用依赖`jar`包，都通过`maven`进行管理。就像`Python`中的`pip`。



### 代码分析



#### 数据库接口--实体类

#### ```Content.java```

​    实现了一个储存id，发表人，文本内容，时间的类。

​    id作为每个内容的主键，更方便把不同表中的内容联系起来。

项目中每一个“博客“，主内容、评论（甚至初设想中评论的回复）都需要这四个属性，于是为了扩展性就把它们单独作为一个父类Content。

之所以有两个构造方法，是因为当向数据库写入数据时，并不需要id（为保证id唯一，写入数据时数据库自动生成id）；当读出数据时，需要id。

重写的toString方法是为了更方便调试。下同。



#### ```Comment.java```

​    通过继承Content类，储存了一个“博客”的评论所需内容。

​    仅仅是多了评论的目标“博客”，存的是目标“博客”的id。



#### ```Log.java```

​    添加了点赞人、评论内容的实例，并且实现Comparable接口，通过自己定义getHotIndex的热度算法，实现对Log的排序。

​    点赞人为数组以便实现更少资源占用、评论为链表以便更快实现删除、添加的显示。



#### ```SqlQuery.java```

​    实现了调用数据库的一些基本操作。

​    Connection实例是通过构造方法自动创建的，因为必须要与数据库进行连接后才能发送Sql语句。

​    因为prepareStatement比Statement更安全，后面的方法主要通过它来发送Sql语句。



#### ```public String getPassword(String memberName)```

​    传入用户名，在members表查询，如果有结果则返回结果中的密码。



#### ```public String[] getMemberNames()```

​    得到所有用户名。因为后续仅仅需要做遍历判断，返回值直接用数组。



#### ```public LinkedList<Comment>  getComments(int targetlog)```

​    传入目标“博客”的id，返回它的评论。直接传入Log类，所以返回值类型用相同类型。



#### ```public String[] getLikes(int targetlog)```

​    传入目标“博客”的id，返回它的点赞人。直接传入Log类，所以返回值类型用相同类型。



#### ```public ArrayList<Log> getByName(boolean flag,String name)```

​    按要求返回Log。

​    参数flag指是否按成员名返回（true是，false否）；name则是成员名（当flag为false的时候，传入任何值都可行）

​    按照需求（越新的“博客”在越前面），这个方法中Sql语句直接按日期降序查询结果

​    查询结果仅仅包含id，发表人，内容，时间，同时调用之前的getComments，getLikes方法找到对应的评论、点赞人，最后存在一个Log里面，添加到ArrayList中并返回。



#### ```public int setpassword(String name,String newpassword)```

​    设置密码。返回值指的是影响行数（利于调试中判断Sql语句执行状态），下同。



#### ```public int setlog(Log aLog)```

​    添加一个“博客”。



#### ```public int setlike(String likegiver, int logid)```

​    添加一个点赞信息，因为需要知道点赞人和目标“博客”id，所以分别为传入参数。



#### ```public int setcomment(Comment aComment)```

​    添加一个评论信息。因为Comment类已经储存了评论对应的目标“博客”id，所以参数仅仅是Comment类型。



#### 控制器类

#### ```LoginController```

实现登录流程控制，获取前端传来的用户名和密码，与数据库进行查询比较，返回视图。

#### ```IndexController```

实现主页的点赞、评论、退出登录、修改密码和发表博客的相关路由控制；调用数据库接口，以及回应前端`Ajax`请求。

#### 配置类

#### ```SessionInterceptor```

监听访问请求，如果访问网页根目录（登陆页面），或者是验证路由，放行。如果访问其他路由，会先判断`Session`中是否有`username`，如果有，则放行；如果没有，则重定向至登录页面，即网页根目录。

#### ```SessionConfiguration```

对所有`url`应用，`@Configuration`自动将配置交给`springboot`管理。

#### ```主程序启动类```

核心注解`@SpringBootApplication`.启动即可自动搭建`springboot`项目程序。

`(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})`

这里我们使用原生`Jdbc`进行数据库操作，所以我们不需要`springboot`为我们配置数据库相关；故添加后面的一句。



#### 前端相关

#### ```html```

文件中即传统`dom`树，其中结合`thymeleaf`模板引擎。

在`login.html`中，我们通过`form`表单提交的形式传递给后端，并由后端进行相关判断，返回视图。

在`index.html`中，我们的一条条博客由`th:each`遍历后端数组生成。故博客只需写一条，且结构与后端`log`实体类对应。其中，我们需要给单个博客的点赞与提交的相关`html element`设置属性来记录之前遍历赋予的博客`ID`，从而在后面发送`Ajax`请求时能够准确记录并传递`logId`。

#### `css`

主要是前端样式文件，包括元素定位，元素大小，盒模型的样式操作。

#### ```JavaScript```

主要包括四个文件,使用`jQuery`快捷开发。

`LoginEnsure`和`PwdEnsure`主要用于前端的预判断，进行判断表单元素即账号和密码是否输入齐全，以及是否一致。

`praise`和`postComment`主要用于进行`Ajax`异步请求发送，并执行相关回调函数。

在发送点赞的异步请求前，先搜寻`dom tree`的`praise-user`中是否已存在该用户，如果存在就不发送`Ajax`请求；否则发送。遍历时主要使用`$.each`方，同时需注意我们拿到的`element`为`js`对象，需要使用`$(element)`转化为jQuery中的对象。

在评论时，首先判断该用户评论框是否为空，如果不为空就发送，否则不发送。

在执行完`Ajax`异步之后，我们需要执行回调函数，进行相应的`dom`结构的增减变化，此时我们需要使用`this`指向点击元素；故我们在编写函数时，需要使用箭头函数，来防止`this`指向函数，而非所操作的元素。



### 项目展望

**1.** 提升安全性

**2.** 增加项目功能，实现图片操作、评论的点赞等等

**3.** 面向更大的团体开放
