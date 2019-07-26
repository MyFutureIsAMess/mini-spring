#> 手写SpringMVC

    模块结构
   ![image text](images/模块结构.png)
 
    Spring包结构
   ![image text](images/Spring包结构.png)
   

#> 结构设计
##>> ***`实现Core模块，包括core、beans、context包`**
##>> ***`实现Web，集成web和webmvc`**
##>> ***`添加statrer，实现类spring-boot的启动方式`**   

#> 服务器和Servlet
##>> Web服务器
###>>> ****`监听一个TCP端口`***
###>>> ****`转发请求，回收响应`***
###>>> ****`本身没有业务逻辑，连接操作系统和应用程序代码`***

#> 系统Web服务模型
   ![image text](images/系统Web服务模型.png)
##>> ***`请求分发流程`**
   ![image text](images/请求分发流程.png)
##>> ***`Servlet`**
###>>> ****`一种规范：约束了Java服务器与业务类的通信方式`***
   