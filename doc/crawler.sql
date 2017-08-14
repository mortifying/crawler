DROP TABLE IF EXISTS `proxy_ip`;
CREATE TABLE `proxy_ip` (
  `id` VARCHAR(32) NOT NULL COMMENT 'id',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `used` tinyint(4) DEFAULT '0' COMMENT '使用',
  `other` varchar(255) DEFAULT NULL COMMENT '其他',
  `add_time`  DATETIME COMMENT '添加时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info` (
  `job_id` VARCHAR(32) NOT NULL COMMENT '任务 id',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` VARCHAR(50) DEFAULT NULL COMMENT '任务分组',
  `job_status` CHAR(2) DEFAULT NULL COMMENT '任务状态0：正常，1：暂停，-1：删除',
  `page_processor_class` VARCHAR(255) NOT NULL COMMENT 'page process类名全路径',
  `pipeline_class` VARCHAR(255) NOT NULL COMMENT 'pipeline类名全路径',
  `crawler_url` VARCHAR(255) NOT NULL COMMENT 'crawler url',
  `thread_num` INT DEFAULT NULL COMMENT '线程数',
  `exit_whith_complete` CHAR(1) DEFAULT '0' COMMENT '1:true,0:false',
  `cron_expression` VARCHAR(255) DEFAULT NULL COMMENT '任务运行时间表达式',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '任务描述',
  `add_time`  DATETIME COMMENT '添加时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '任务表';
