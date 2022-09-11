# BooksManagement

拉取项目后，先创建一个数据库books 字符集utf8mb4，排序规则utf8mb4_general_ci

然后运行doc/books.sql 文件，进行初始化数据库。

预置两个账号信息 一个是普通用户user 账号 hex 密码 hexile

另一个是管理员用户user+admin+manage 账号 admin 密码 admin

拉取代码后根据个人电脑数据库账号密码进行配置 路径如下

bm-main/src/main/resources/application-prod.yml

可从bm-main/src/main/resources/application.yml文件中修改映射的配置文件



通过登录接口获取到sessionID后，将该ID以Bm-Token的命名放入header中，实现登录控制

在部分接口中添加了权限控制，只有具有相关权限的用户访问才能通过，否则提示无该权限

日志启动后自动记录在log/bm-main下

部分表加入数据缓存功能，在进行增删改后会将缓存清空

## 详细接口文档可参考doc/books.md

## 或启动项目后打开http://localhost:16666/main/swagger-ui.html



bm-common 用于存放工具配置类及一些通用代码

bm-main 实现项目具体逻辑

使用SpringBoot，Mybatis，MyBatisGenerator，Redis， Druid， Lombok， Swagger-UI

实现图书的增删改查功能，人员授权，注册登录控制，用户信息修改，图书借用及归还逻辑，3天后还书提醒