--人物信息表
CREATE TABLE `oracle`.`person_info` (
  `pid` VARCHAR(20) NOT NULL,
  `pname` VARCHAR(32) NULL,
  `password` VARCHAR(32) NULL,
  `pclass` VARCHAR(32) NULL,
  `email` VARCHAR(48) NULL,
  `group` VARCHAR(32) NULL,
  `pstate` CHAR(4) NULL DEFAULT 's' COMMENT '“s”is student\n“t”is teacher\n“a”is admin',
  PRIMARY KEY (`pid`));

--课程计划表
CREATE TABLE `oracle`.`course_plan` (
  `cid` VARCHAR(20) NOT NULL,
  `tid` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cid`, `tid`));
  
--课程信息表
CREATE TABLE `oracle`.`course_info` (
  `cid` VARCHAR(20) NOT NULL,
  `pid` VARCHAR(20) NULL,
  `cname` VARCHAR(48) NOT NULL,
  `detail` VARCHAR(200) NULL,
  `chnum` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cid`));

  
--时间计划表
CREATE TABLE `oracle`.`time_plan` (
  `tid` VARCHAR(20) NOT NULL COMMENT '时间id的标示规则：\n星期：1~7\n课数：1~9\n星期+课数' ,
  `tdetail` VARCHAR(45) NOT NULL COMMENT 'tdetail：时间范围' ,
  `tsign` VARCHAR(10) NOT NULL COMMENT '跟tdetail配合使用' ,
  `tweek` VARCHAR(32) NOT NULL COMMENT '星期：星期一~ 星期日',
  PRIMARY KEY (`tid`));

--文件信息表
CREATE TABLE `oracle`.`file_info` (
  `fid` VARCHAR(32) NOT NULL,
  `fname` VARCHAR(45) NULL,
  `pidup` VARCHAR(20) NULL,
  `piddown` VARCHAR(200) NULL,
  `url` VARCHAR(45) NULL,
  `time` DATE NULL,
  `count` INT NULL,
  PRIMARY KEY (`fid`));

--分数表
CREATE TABLE `oracle`.`score` (
  `cid` VARCHAR(20) NOT NULL,
  `pid` VARCHAR(20) NOT NULL,
  `stu` DOUBLE NULL,
  `tea` DOUBLE NULL,
  `finalscore` DOUBLE NULL,
  PRIMARY KEY (`pid`, `cid`));

--签到表
CREATE TABLE `oracle`.`sign_in` (
  `siid` INT(12) NOT NULL AUTO_INCREMENT,
  `pid` VARCHAR(20) NOT NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`siid`));
  
--文件下载表
CREATE TABLE `oracle`.`file_download` (
  `fdid` INT(11) NOT NULL AUTO_INCREMENT,
  `fid` VARCHAR(20) NOT NULL,
  `pid` VARCHAR(20) NOT NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`fdid`));
 --课程章节表
CREATE TABLE `oracle`.`chapter` (
  `chaptid` VARCHAR(40) NOT NULL,
  `cid` VARCHAR(45) NOT NULL,
  `chaptna` VARCHAR(45) NOT NULL,
  `detial` VARCHAR(45) NULL,
  `difficulty` VARCHAR(10) NULL,
  PRIMARY KEY (`chaptid`));
  
 --测试分数表
CREATE TABLE `oracle`.`test_score` (
  `pid` VARCHAR(45) NOT NULL,
  `testid` VARCHAR(45) NOT NULL,
  `score` DOUBLE NULL,
  PRIMARY KEY (`pid`, `testid`));
 --测试表
 CREATE TABLE `oracle`.`test` (
  `testid` VARCHAR(45) NOT NULL,
  `testname` VARCHAR(45) NULL,
  `time` DATETIME NULL ,
  `detail` VARCHAR(100) NULL ,
  PRIMARY KEY (`testid`));
  
 --问题表
 CREATE TABLE `oracle`.`question` (
  `qid` VARCHAR(45) NOT NULL,
  `qname` VARCHAR(200) NULL,
  `qcontent` VARCHAR(200) NULL,
  `qright` VARCHAR(45) NULL,
  `qdetail` VARCHAR(200) NULL,
  `chpatid` VARCHAR(45) NULL,
  `pupid` VARCHAR(45) NULL,
  PRIMARY KEY (`qid`));
 --问题和测试对应表
 CREATE TABLE `oracle`.`test_question` (
  `testid` VARCHAR(45) NOT NULL,
  `qid` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`testid`, `qid`));
 --评分表问题
 CREATE TABLE `oracle`.`form_question` (
  `formqid` VARCHAR(45) NOT NULL,
  `formid` VARCHAR(45) NULL,
  `content` VARCHAR(100) NULL,
  `power` INT NULL,
  `detail` VARCHAR(45) NULL,
  PRIMARY KEY (`formqid`));
 --评分表
 CREATE TABLE `oracle`.`from` (
  `fromid` VARCHAR(45) NOT NULL,
  `chaptid` VARCHAR(45) NULL,
  `detail` VARCHAR(45) NULL,
  PRIMARY KEY (`fromid`));
 --学生评分列表
 CREATE TABLE `oracle`.`stu_score` (
  `pid` VARCHAR(45) NOT NULL,
  `pidup` VARCHAR(45) NOT NULL,
  `formid` VARCHAR(45) NOT NULL,
  `score` DOUBLE NULL,
  `detail` VARCHAR(45) NULL,
  PRIMARY KEY (`pid`, `pidup`, `formid`));
  
  
ALTER TABLE `oracle`.`form` 
ADD COLUMN `fname` VARCHAR(45) NULL AFTER `detail`,
ADD COLUMN `time` DATETIME NULL AFTER `fname`;
ALTER TABLE `oracle`.`form` 
CHANGE COLUMN `detail` `detail` VARCHAR(45) NULL DEFAULT NULL AFTER `time`;
ALTER TABLE `oracle`.`form` 
CHANGE COLUMN `fname` `fname` VARCHAR(45) NULL DEFAULT NULL AFTER `formid`;
ALTER TABLE `oracle`.`test` 
ADD COLUMN `cid` VARCHAR(45) NULL AFTER `detail`;
ALTER TABLE `oracle`.`file_info` 
ADD COLUMN `authority` DATETIME NULL AFTER `time`;

  
/******************************************************************************************************
--插入的数据
--Date: 2015-03-27 13:37

********************************************************************************************************/

--录入的人员基础信息
--Query: SELECT * FROM oracle.person_info

INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('1','张庆伟','xMpCOKC5I4INzFCab3WEmw==','1','123@qq.com',NULL,'s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111221','彭程','c8w0YaUCQQ/n0qxMHZoMaw==','经1109','20111221@qq.com',NULL,'s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111223','乔岩','xMpCOKC5I4INzFCab3WEmw==','经1109','20111223@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111224','尹石林','xMpCOKC5I4INzFCab3WEmw==','经1109','20111224@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111225','彭煜','xMpCOKC5I4INzFCab3WEmw==','经1109','20111225@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111226','朱程楠','xMpCOKC5I4INzFCab3WEmw==','经1109','20111226@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111227','吕莘莘','xMpCOKC5I4INzFCab3WEmw==','经1109','20111227@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111228','池志昊','xMpCOKC5I4INzFCab3WEmw==','经1109','20111228@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111229','赵志刚','xMpCOKC5I4INzFCab3WEmw==','经1109','20111229@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111230','晋朝','xMpCOKC5I4INzFCab3WEmw==','经1109','20111230@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111231','李楠','xMpCOKC5I4INzFCab3WEmw==','经1109','20111231@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111232','袁飞龙','xMpCOKC5I4INzFCab3WEmw==','经1109','20111232@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111233','张昊文','xMpCOKC5I4INzFCab3WEmw==','经1109','20111233@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111234','乔兴富','xMpCOKC5I4INzFCab3WEmw==','经1109','20111234@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111235','尼蒙','xMpCOKC5I4INzFCab3WEmw==','经1109','20111235@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111236','张庆伟','xMpCOKC5I4INzFCab3WEmw==','经1109','zqwbarnettjohn@gmail.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111237','刘福成','xMpCOKC5I4INzFCab3WEmw==','经1109','20111237@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111238','胡乔木','xMpCOKC5I4INzFCab3WEmw==','经1109','20111238@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111239','马金男','xMpCOKC5I4INzFCab3WEmw==','经1109','20111239@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111240','张春光','xMpCOKC5I4INzFCab3WEmw==','经1109','20111240@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111241','谢健','xMpCOKC5I4INzFCab3WEmw==','经1109','20111241@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111242','管玺畯','xMpCOKC5I4INzFCab3WEmw==','经1109','20111242@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111243','吴超凡','xMpCOKC5I4INzFCab3WEmw==','经1109','20111243@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111244','李雪姣','xMpCOKC5I4INzFCab3WEmw==','经1109','20111244@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111245','王珊','xMpCOKC5I4INzFCab3WEmw==','经1109','20111245@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111246','邢艳春','xMpCOKC5I4INzFCab3WEmw==','经1109','20111246@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111247','张旭明','xMpCOKC5I4INzFCab3WEmw==','经1109','20111247@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111248','贾月桥','xMpCOKC5I4INzFCab3WEmw==','经1109','20111248@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111249','李颖','xMpCOKC5I4INzFCab3WEmw==','经1109','20111249@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111250','杜杉','xMpCOKC5I4INzFCab3WEmw==','经1109','20111250@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111251','徐娅群','xMpCOKC5I4INzFCab3WEmw==','经1109','20111251@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111252','李亚男','xMpCOKC5I4INzFCab3WEmw==','经1109','20111252@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111253','陈红','xMpCOKC5I4INzFCab3WEmw==','经1109','20111253@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('20111254','史媛','xMpCOKC5I4INzFCab3WEmw==','经1109','20111254@qq.com','','s');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('t1','潘晓','xMpCOKC5I4INzFCab3WEmw==','0','234@qq.com',NULL,'t');
INSERT INTO `person_info` (`pid`,`pname`,`password`,`pclass`,`email`,`group`,`pstate`) VALUES ('﻿20111222','赵钰锋','xMpCOKC5I4INzFCab3WEmw==','经1109','20111222@qq.com','','s');
 