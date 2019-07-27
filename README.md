># 手写SpringMVC
>> * [模块结构](#模块结构)
>> * [Spring包结构](#Spring包结构)
>> * [结构设计](#结构设计)
>>      * [实现Core模块，包括core、beans、context包](#实现Core模块，包括core、beans、context包)
>>      * [实现Web，集成web和webmvc](#实现Web，集成web和webmvc)
>>      * [添加statrer，实现类spring-boot的启动方式](#添加statrer，实现类spring-boot的启动方式)
>> * [服务器和Servlet](#服务器和Servlet)
>>      * [Web服务器](#Web服务器)
>> * [系统Web服务模型](#系统Web服务模型)
>>      * [请求分发流程](#请求分发流程)
>>      * [Servlet](#Servlet)
>> * [Tomcat](#Tomcat)
>> * [Servlet的维护](#Servlet的维护)
>> * [原Servlet调度流程](#原Servlet调度流程)
>> * [服务器调度问题](#服务器调度问题)
>> * [Spring调度流程](#Spring调度流程)


># 模块结构
   ![image text](images/模块结构.png)
># Spring包结构
   ![image text](images/Spring包结构.png)
   

># 结构设计
>>## ***`实现Core模块，包括core、beans、context包`**
>>## ***`实现Web，集成web和webmvc`**
>>## ***`添加statrer，实现类spring-boot的启动方式`**   

># 服务器和Servlet
>>## Web服务器
>>>### ****`监听一个TCP端口`***
>>>### ****`转发请求，回收响应`***
>>>### ****`本身没有业务逻辑，连接操作系统和应用程序代码`***

># 系统Web服务模型
   ![image text](images/系统Web服务模型.png)
>>## ***`请求分发流程`**
   ![image text](images/请求分发流程.png)
>>## ***`Servlet`**
>>>### ****`一种规范：约束了Java服务器与业务类的通信方式`***
>>>### ****`一个接口：javax.servlet.Servlet`***
>>>### ****`一种Java类：实现了Servlet接口的应用程序类`***

># Tomcat
>>## ***`Java原生，运行在JVM上`**
>>## ***`多种并发模型，高性能`**
>>## ***`支持嵌入式应用程序`**

># Servlet的维护
>>## ***`由服务器调度配置`**
>>## ***`使用web.xml中性化配置`**
>>## ***`每种业务添加一个Servlet，对应一个URI`**

># 原Servlet调度流程
   ![image text](images/原Servlet调度流程.png)
   
># 服务器调度问题
>>## ***`配置集中，大而杂，不易管理`**
>>## ***`多次实现Servlet接口，不必要`**

># Spring调度流程
   ![image text](images/Spring调度流程.png)
   
># Spring调度优势
>>## ***`用注解，实现简单，按需实现`**
>>## ***`配置分散，不杂乱`**
>>## ***`容器内实现，易控制`**
