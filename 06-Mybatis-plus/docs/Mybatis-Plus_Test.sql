DROP TABLE IF EXISTS test_mybatisplus;

CREATE TABLE test_mybatisplus(
  id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--  BIGINT存储范围 对应 LONG ，| INT 对应 INTEGER，| 但 UNSIGNED INT 无符号，存储翻倍，和 LONG 相同
--   id INT UNSIGNED NOT NULL DEFAULT 1 AUTO_INCREMENT COMMENT '主键ID',
  name VARCHAR(30) DEFAULT NULL COMMENT '姓名',
  age INT(11) DEFAULT NULL COMMENT '年龄',
  email VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY(id)
)ENGINE=INNODB CHARSET=utf8;

INSERT INTO test_mybatisplus (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');