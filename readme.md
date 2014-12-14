review-admin为review的后台管理系统

=====

框架：
1. 后台为服务，采用的是 dropwizard(jersey+hibernate+jetty)，嵌入式的开发
（选型时由于采用springmvc+jetty，参考spring-boot感觉太麻烦，如需要可以修改为spring框架
  采用Hibernate是由于对于性能要求不高，所以不采用原生Jdbc处理了
）
2. 前台采用angularjs+bootstrap，依赖angularjs相关插件

功能：
1. review内容的管理，功能包括：a)关闭 review b)关闭issue c)issue查询 d)issue生成任务
2. 项目管理 a)项目定义  b)项目任务情况统计
3. 任务管理 a)任务创建 c)任务
4. 角色管理
5. 人员管理
