--创建数据库
drop database if exists myblog;
create database myblog;
--使用数据库
use myblog;

--创建表[用户表]
drop table if exists userinfo;
create table userinfo(
   id int primary key auto_increment,
   username varchar(100) not null,
   password varchar(32) not null,
   createtime date default now(),
   updatetime date default now(),
   `state` int default 1
);

--创建表[文章表]
drop table if exists articleinfo;
create table articleinfo(

  id in primary key auto_increment,
  title varchar(100) not null,
  content text not null,
  createtime date default now(),
  updatetime date default now(),
 `uid`int not null,
);

insert into articleinfo(id,title,content,uid)values(1,'一篇文章','一个test',001);


