CREATE
DATABASE `panacea`;

CREATE TABLE `fitness_user`
(
    `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`          TINYINT       NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`          VARCHAR(16)   NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time`      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`         VARCHAR(16)   NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time`      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `username`         VARCHAR(256)  NOT NULL DEFAULT '' COMMENT '用户名',
    `password`         VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '密码',
    `nickname`         VARCHAR(256)  NOT NULL DEFAULT '' COMMENT '昵称',
    `gender`           TINYINT       NOT NULL DEFAULT '0' COMMENT '性别，1：男性；2：女性',
    `first_login_time` DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '首次登录时间',
    `last_login_time`  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新登录时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身用户';