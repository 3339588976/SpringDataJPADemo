# SpringDataJPADemo

（一）：JPA:不是框架，而是类似于JDBC的一组规范，主要包括三个方面：

API标准：操作实体对象，实现CRUD，从繁杂的JDBC中解脱

JPQL:面向对象的查询语言，而非面向数据库，避免SQL的紧密耦合

ORM:元数据关系映射，元数据描述了对象和表之间的映射关系，框架据将对象此持久化到数据库

注意：JPA是Hibernate的一个抽象，Hibernate是JPA的具体实现

实现CRUD的过程

1：创建persistence.xml配置文件，配置持久化单元

指定相关的数据库

指定JPA使用的ORM框架，以及持久层框架的基本属性

2：创建实体类

使用annotation描述实体类和数据库表之间的关系

3：JPA API实现增删改查的操作

底层实现的两个类

EntityManagerFactory：对象工厂,类似于MyBatis的SqlSessionFactory

EntityManager：操作数据库的对象，类似于MyBatis的SqlSession

（二）：Spring Data JPA:在JPA规范下实现了Repository层的实现，便于不同ORM框架的切换

JPA规范的再次封装抽象，底层还是用Hibernate和JPA实现的

注意:mybatis不是ORM框架，mybatis只是将数据库中的内容映射为实体，并没有将实体映射为数据库中的字段

![img](D:\mytools\YNote\YNoteFile\weixinobU7VjvKWlnafBa2CCuqTRkGHtOw\f4955635114e4c258a8a164b33ca2465\6fa75240-252e-11e8-8c14-19da63913af3.png)
扩展：

Spring Data:是Spring为了简化数据库持久层而开发的模块

已完成JPA的简单查询，包括分页和排序

继续实现复杂查询和事务处理

序列化无限递归的情况：

A类中，有个属性：List<B> b， A与B的关系为 OneToMany；在B类中，有属性A a,引用到A中的字段id，并作为外键。hibernate查询结果正常，可以看到返回的A对象中，有b参数值，但在json转换的时候就出现了无限递归的情况。个人分析，应该是json在序列化A中的b属性的时候，找到了B类，然后序列化B类，而B类中有a属性，因此，为了序列化a属性，json又得去序列化A类，如此递归反复，造成该问题。

解决：

在B类中a的getter setter方法上加注解@JsonBackReference，其实自己试过只在setter方法上加@JsonBackReference也够了

（三）：Spring Data JPA通过继承JpaSpecificationExecutor来实现Criteria查询

关联表查询

一对多：关系的维护在One端，实现级联操作（保存/更新/删除等）

多对多：关系的维护任意端，需要在关系维护端配置关联表及其外键，只能在关系的维护端进行删除、更新操作（先解除关联关系，再删除等）

Example的实例查询

通过一个实例查询相匹配的对象

Object obj

ExampleMatcher mathcer

Example example(obj,matcher)

（四）Jpa的复杂查询：Specification的原理

page(第page+1页)从0开始

Page 2 of 0：第2页共0
