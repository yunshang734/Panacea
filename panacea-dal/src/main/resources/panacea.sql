CREATE
DATABASE `panacea`;

CREATE TABLE `fitness_item`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`     TINYINT      NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`     VARCHAR(16)  NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`    VARCHAR(16)  NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `name`        VARCHAR(256) NOT NULL DEFAULT '' COMMENT '项目名称',
    `type`        TINYINT      NOT NULL DEFAULT '0' COMMENT '训练类型，1：力量训练；2：有氧训练；',
    `body_part`   TINYINT      NOT NULL DEFAULT '0' COMMENT '训练部位，0：全身；1：胸部；2：背部；3：肩部；4：肱二头肌；5：肱三头肌；6：腿部；',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身-项目';

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
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身-用户';

CREATE TABLE `fitness_user_program`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`     TINYINT      NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`     VARCHAR(16)  NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`    VARCHAR(16)  NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `user_id`     BIGINT       NOT NULL DEFAULT '0' COMMENT '用户ID',
    `name`        VARCHAR(256) NOT NULL DEFAULT '' COMMENT '计划名称',
    `start_date`  DATE         NOT NULL DEFAULT '2000-01-01' COMMENT '开始日期',
    `end_date`    DATE         NOT NULL DEFAULT '2000-01-01' COMMENT '结束日期',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身-用户计划';

CREATE TABLE `fitness_user_group`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`     TINYINT      NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`     VARCHAR(16)  NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`    VARCHAR(16)  NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `name`        VARCHAR(256) NOT NULL DEFAULT '' COMMENT '组合名称',
    `user_id`     BIGINT       NOT NULL DEFAULT '0' COMMENT '用户ID',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身-用户组合';

CREATE TABLE `fitness_user_item`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`     TINYINT     NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`     VARCHAR(16) NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`    VARCHAR(16) NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `user_id`     BIGINT      NOT NULL DEFAULT '0' COMMENT '用户ID',
    `item_id`     BIGINT      NOT NULL DEFAULT '0' COMMENT '项目ID',
    `unit`        TINYINT     NOT NULL DEFAULT '0' COMMENT '计量单位，1：千克；2：磅；',
    `group`       TINYINT     NOT NULL DEFAULT '0' COMMENT '组数',
    `count`       TINYINT     NOT NULL DEFAULT '0' COMMENT '次数',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身-用户项目';

CREATE TABLE `fitness_user_group_item_bind`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`     TINYINT     NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`     VARCHAR(16) NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`    VARCHAR(16) NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `user_id`     BIGINT      NOT NULL DEFAULT '0' COMMENT '用户ID',
    `group_id`    BIGINT      NOT NULL DEFAULT '0' COMMENT '组合ID',
    `item_id`     BIGINT      NOT NULL DEFAULT '0' COMMENT '项目ID',
    `sort`        TINYINT     NOT NULL DEFAULT '0' COMMENT '排序',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身-用户组合项目绑定';

CREATE TABLE `fitness_user_clock`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`     TINYINT     NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`     VARCHAR(16) NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`    VARCHAR(16) NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `user_id`     BIGINT      NOT NULL DEFAULT '0' COMMENT '用户ID',
    `group_id`    BIGINT      NOT NULL DEFAULT '0' COMMENT '组合ID',
    `date`        DATE        NOT NULL DEFAULT '2000-01-01' COMMENT '打卡日期',
    `duration`    TINYINT     NOT NULL DEFAULT '0' COMMENT '总时长',
    `status`      TINYINT     NOT NULL DEFAULT '0' COMMENT '状态，0：未完成；1：已完成',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身-用户打卡';

CREATE TABLE `fitness_user_clock_detail`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`     TINYINT     NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`     VARCHAR(16) NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`    VARCHAR(16) NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `clock_id`    BIGINT      NOT NULL DEFAULT '0' COMMENT '组合ID',
    `item_id`     BIGINT      NOT NULL DEFAULT '0' COMMENT '项目ID',
    `unit`        TINYINT     NOT NULL DEFAULT '0' COMMENT '计量单位，1：千克；2：磅；',
    `group`       TINYINT     NOT NULL DEFAULT '0' COMMENT '组数',
    `count`       TINYINT     NOT NULL DEFAULT '0' COMMENT '次数',
    `duration`    TINYINT     NOT NULL DEFAULT '0' COMMENT '总时长',
    `status`      TINYINT     NOT NULL DEFAULT '0' COMMENT '状态，0：未完成；1：已完成',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='健身-用户打卡细节';

CREATE TABLE `photography_user`
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
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='摄影-用户';

CREATE TABLE `photography_category`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `deleted`     TINYINT      NOT NULL DEFAULT '0' COMMENT '是否已删除，0：否；1：是',
    `creator`     VARCHAR(16)  NOT NULL DEFAULT 'system' COMMENT '创建者',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`    VARCHAR(16)  NOT NULL DEFAULT 'system' COMMENT '修改者',
    `modify_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `name`        VARCHAR(256) NOT NULL DEFAULT '' COMMENT '名称',
    PRIMARY KEY (id) USING BTREE
) ENGINE='INNODB' DEFAULT CHARSET='UTF8MB4' AUTO_INCREMENT=0 COMMENT='摄影-分类';