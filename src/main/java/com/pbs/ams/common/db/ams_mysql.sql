/*
CREATE DATABASE ams;
USE ams;
*/
  
DROP TABLE `upms_role_permission`;
DROP TABLE `upms_user_role`;
DROP TABLE `upms_role`;
DROP TABLE `upms_user_permission`;
DROP TABLE `upms_user_organization`;
DROP TABLE `upms_user`;
DROP TABLE `upms_company`;
DROP TABLE `upms_organization`;
DROP TABLE `upms_permission`;
DROP TABLE `upms_system`;

-- -1.0.0-----权限模块 start---------------------- 
DROP TABLE IF EXISTS `upms_system`;-- -1.0.1-----
CREATE TABLE `upms_system` (
  `system_id` 	BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `icon` 	VARCHAR(50) DEFAULT NULL COMMENT '图标',
  `banner` 	VARCHAR(150) DEFAULT NULL COMMENT '背景',
  `theme` 	VARCHAR(50) DEFAULT NULL COMMENT '主题',
  `basepath` 	VARCHAR(100) DEFAULT NULL COMMENT '根目录',
  `status` 	TINYINT(4) DEFAULT NULL COMMENT '状态(-1:黑名单,1:正常)',
  `name` 	VARCHAR(20) DEFAULT NULL COMMENT '系统名称',
  `title` 	VARCHAR(20) DEFAULT NULL COMMENT '系统标题',
  `description` VARCHAR(300) DEFAULT NULL COMMENT '系统描述',
  `ctime` 	BIGINT(20) DEFAULT NULL COMMENT '创建时间',
  `orders` 	BIGINT(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`system_id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='系统';

DROP TABLE IF EXISTS `upms_permission`;-- -1.0.2-----
CREATE TABLE `upms_permission` (	-- UpmsApiMapper.xml	UpmsPermissionMapper.xml
  `permission_id` 	BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `system_id` 		BIGINT(10) UNSIGNED NOT NULL COMMENT '所属系统',
  `pid` 		BIGINT(10) DEFAULT NULL COMMENT '所属上级',
  `name` 		VARCHAR(20) DEFAULT NULL COMMENT '名称',
  `type` 		TINYINT(4) DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  `permission_value` 	VARCHAR(50) DEFAULT NULL COMMENT '权限值',
  `uri` 		VARCHAR(100) DEFAULT NULL COMMENT '路径',
  `icon` 		VARCHAR(50) DEFAULT NULL COMMENT '图标',
  `status` 		TINYINT(4) DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `ctime` 		BIGINT(20) DEFAULT NULL COMMENT '创建时间',
  `orders` 		BIGINT(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`)
) ENGINE=INNODB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COMMENT='权限';

DROP TABLE IF EXISTS `upms_organization`;-- -1.0.3-----
CREATE TABLE `upms_organization` (
  `organization_id` 	BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` 		BIGINT(10) DEFAULT NULL COMMENT '所属上级',
  `name` 		VARCHAR(20) DEFAULT NULL COMMENT '组织名称',
  `description` 	VARCHAR(1000) DEFAULT NULL COMMENT '组织描述',
  `ctime` 		BIGINT(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`organization_id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='组织';


DROP TABLE IF EXISTS `upms_company`;-- -1.0.4-----
CREATE TABLE `upms_company` (
  `company_id` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_name` 	VARCHAR(50) ,
  `operator_id` 	BIGINT ,
  `manager_id` 		BIGINT ,
  `manager_phone` 	VARCHAR(11) ,
  `manager_address` 	VARCHAR(300) ,
  `company_address` 	VARCHAR(300),
  `company_fax` 	VARCHAR(50) ,
  `manager_email` 	VARCHAR(50) ,
  `description` 	VARCHAR(500) , 
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT , 
  PRIMARY KEY (`company_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;  

DROP TABLE IF EXISTS `upms_user`;-- -1.0.5-----
CREATE TABLE `upms_user` (
  `user_id` 	BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` 	VARCHAR(20) NOT NULL COMMENT '帐号',
  `password` 	VARCHAR(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  `salt` 	VARCHAR(32) DEFAULT NULL COMMENT '盐',
  `realname` 	VARCHAR(20) DEFAULT NULL COMMENT '姓名',
  `avatar` 	VARCHAR(150) DEFAULT NULL COMMENT '头像',
  `phone` 	VARCHAR(20) DEFAULT NULL COMMENT '电话',
  `email` 	VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
  `sex` 	TINYINT(4) DEFAULT NULL COMMENT '性别',
  `locked` 	TINYINT(4) DEFAULT NULL COMMENT '状态(0:正常,1:锁定)',
  `ctime` 	BIGINT(20) DEFAULT NULL COMMENT '创建时间',	-- 原生 BIGINT
  `company_id` 	BIGINT ,
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `upms_user_organization`;-- -1.0.6-----
CREATE TABLE `upms_user_organization` (
  `user_organization_id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` 		 BIGINT(10) UNSIGNED NOT NULL COMMENT '用户编号',
  `organization_id` 	 BIGINT(10) UNSIGNED NOT NULL COMMENT '组织编号',
  PRIMARY KEY (`user_organization_id`)
) ENGINE=INNODB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='用户组织关联表';

DROP TABLE IF EXISTS `upms_user_permission`;-- -1.0.7-----
CREATE TABLE `upms_user_permission` (
  `user_permission_id` 	BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` 		BIGINT(10) UNSIGNED NOT NULL COMMENT '用户编号',
  `permission_id` 	BIGINT(10) UNSIGNED NOT NULL COMMENT '权限编号',
  `type` 		TINYINT(4) NOT NULL COMMENT '权限类型(-1:减权限,1:增权限)',
  PRIMARY KEY (`user_permission_id`)
) ENGINE=INNODB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';
DROP TABLE IF EXISTS `upms_role`;-- -1.0.8----- 
CREATE TABLE `upms_role` (
  `role_id` 	BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` 	VARCHAR(20) DEFAULT NULL COMMENT '角色名称',
  `title` 	VARCHAR(20) DEFAULT NULL COMMENT '角色标题',
  `description` VARCHAR(1000) DEFAULT NULL COMMENT '角色描述',
  `ctime` 	BIGINT(20) NOT NULL COMMENT '创建时间',
  `orders` 	BIGINT(20) NOT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色';
DROP TABLE IF EXISTS `upms_user_role`;-- -1.0.9----- 
CREATE TABLE `upms_user_role` (
  `user_role_id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` 	 BIGINT(10) UNSIGNED NOT NULL COMMENT '用户编号',
  `role_id` 	 BIGINT(10) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_role_id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';
DROP TABLE IF EXISTS `upms_role_permission`;-- -1.1.0----- 
CREATE TABLE `upms_role_permission` (
  `role_permission_id` 	BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` 		BIGINT(10) UNSIGNED NOT NULL COMMENT '角色编号',
  `permission_id` 	BIGINT(10) UNSIGNED NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`role_permission_id`),
  KEY `FK_Reference_23` (`role_id`),
  CONSTRAINT `FK_Reference_23` FOREIGN KEY (`role_id`) REFERENCES `upms_role` (`role_id`)
) ENGINE=INNODB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';
DROP TABLE IF EXISTS `upms_log`;-- -1.1.1----- 
CREATE TABLE `upms_log` (
  `log_id` 	BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `description` VARCHAR(100) DEFAULT NULL COMMENT '操作描述',
  `username` 	VARCHAR(20) DEFAULT NULL COMMENT '操作用户',
  `start_time` 	BIGINT(20) DEFAULT NULL COMMENT '操作时间',
  `spend_time` 	INT(11) DEFAULT NULL COMMENT '消耗时间',
  `base_path` 	VARCHAR(500) DEFAULT NULL COMMENT '根路径',
  `uri` 	VARCHAR(500) DEFAULT NULL COMMENT 'URI',
  `url` 	VARCHAR(500) DEFAULT NULL COMMENT 'URL',
  `method` 	VARCHAR(10) DEFAULT NULL COMMENT '请求类型',
  `parameter` 	MEDIUMTEXT,
  `user_agent` 	VARCHAR(500) DEFAULT NULL COMMENT '用户标识',
  `ip` 		VARCHAR(30) DEFAULT NULL COMMENT 'IP地址',
  `result` 	MEDIUMTEXT,
  `permissions` VARCHAR(100) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`log_id`),
  KEY `log_id` (`log_id`)
) ENGINE=INNODB AUTO_INCREMENT=779 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';
-- -1.0.0-----权限模块 end---------------------- 

 
 
 
 
 
 


CREATE TABLE `upms_company_user` ( #----2---- 
  `company_user_id`	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, 
  `company_id` 		BIGINT UNSIGNED,
  `user_id` 		BIGINT UNSIGNED, 
  PRIMARY KEY (`company_user_id`)
  #CONSTRAINT `FK_Reference_53` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`),
  #CONSTRAINT `FK_Reference_54` FOREIGN KEY (`user_id`) REFERENCES `upms_user` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 

CREATE TABLE `ams_broker` ( #----3---- 
  `broker_id` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `broker_type` 	INTEGER ,
  `fin_instr_type` 	BIT ,
  `broker_abbr_name` 	VARCHAR(50) ,
  `broker_logo` 	VARCHAR(50) ,
  `broker_name` 	VARCHAR(50) ,
  `creditable` 		BIT ,
  `day_begin` 		BIGINT ,
  `day_end` 		BIGINT ,
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`broker_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8; 
CREATE TABLE `upms_broker_user` ( #----4---- 
  `broker_user_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT  , 
  `broker_id` 		BIGINT UNSIGNED,	 
  `user_id` 		BIGINT UNSIGNED,
  PRIMARY KEY (`broker_user_id`)
  #CONSTRAINT `FK_Reference_55` FOREIGN KEY (`broker_id`) REFERENCES `ams_broker` (`broker_id`),
  #CONSTRAINT `FK_Reference_56` FOREIGN KEY (`user_id`) REFERENCES `upms_user` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;    
  
  
CREATE TABLE `rsk_blackwhite_list` (  -- 黑白名单列表   #----5---- 
  `blackwhite_id`		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` 			BIGINT UNSIGNED,
  `fin_instr_id` 		BIGINT ,
  `fin_instr_type` 		BIT ,
  `blackwhite_list_type` 	BIT ,
  `is_black` 			BIT , 
  `create_time` 		BIGINT ,
  `update_time` 		BIGINT , 
  `operator_id` 		BIGINT ,  
  PRIMARY KEY (`blackwhite_id`)
  #CONSTRAINT `FK_Reference_50` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   

CREATE TABLE `ams_trade_account` (  #----6---- 
  `trade_account_id`		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` 			BIGINT UNSIGNED,
  `trade_account` 		BIGINT ,
  `trade_account_name` 		VARCHAR(50) ,
  `trade_account_password` 	VARCHAR(50) ,
  `broker_id` 			BIGINT UNSIGNED,
  `trade_account_status` 	VARCHAR(50), 
  `create_time` 		BIGINT ,
  `update_time` 		BIGINT , 
  `operator_id` 		BIGINT , 
  `is_login` 			INTEGER , 
  PRIMARY KEY (`trade_account_id`)
  #CONSTRAINT `FK_Reference_51` FOREIGN KEY (`broker_id`) REFERENCES `ams_broker` (`broker_id`),
  #CONSTRAINT `FK_Reference_52` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   

CREATE TABLE `ams_trade_account_ext` (	-- 有可能出错  主键重复 #----7---- 
  `trade_account_id`		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `profit_loss` 		VARCHAR(50) ,
  `available_fund` 		DECIMAL ,
  `frozen_fund` 		DECIMAL ,
  `fee` 			DECIMAL ,
  `total_fund` 			DECIMAL , 
  `create_time` 		BIGINT ,
  `update_time` 		BIGINT , 
  `operator_id` 		BIGINT ,  
  PRIMARY KEY (`trade_account_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='基金公司';  



CREATE TABLE `ams_product` (	#----8---- 
  `product_id` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` 		BIGINT UNSIGNED,
  `product_name` 	VARCHAR(50) ,
  `product_type` 	BIT ,
  `product_code` 	VARCHAR(50) ,
  `product_manager` 	VARCHAR(50) ,
  `product_supervisor` 	VARCHAR(50),
  `product_status` 	VARCHAR(50) ,
  `product_share_source` BIT ,
  `start_date` 		BIGINT,
  `end_date` 		BIGINT ,
  `product_shares` 	DECIMAL ,
  `product_desc` 	VARCHAR(300),  
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  `o32_id` 		BIGINT ,  
  PRIMARY KEY (`product_id`)
  #CONSTRAINT `FK_Reference_57` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;  
CREATE TABLE `ams_product_user` (	#----9---- 
  `product_user_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` 		BIGINT UNSIGNED,
  `user_id` 		BIGINT UNSIGNED,  
  PRIMARY KEY (`product_user_id`)
  #CONSTRAINT `FK_Reference_58` FOREIGN KEY (`product_id`) REFERENCES `ams_product` (`product_id`),
  #CONSTRAINT `FK_Reference_89` FOREIGN KEY (`user_id`) REFERENCES `upms_user` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 


CREATE TABLE `ams_product_detail` (	-- 主键可能有问题！	#----10---- 
  `product_id` 			BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` 			BIGINT UNSIGNED,
  `product_net_value` 		DECIMAL ,
  `net_asset_value` 		DECIMAL ,
  `stock_total_assets` 		DECIMAL ,
  `security_total_value`	DECIMAL ,
  `stock_total_value` 		DECIMAL , 
  `short_total_value` 		DECIMAL ,
  `update_time` 		BIGINT ,
  `operator_id` 		BIGINT ,
  `is_login` 			BIGINT ,
  PRIMARY KEY (`product_id`)
  #CONSTRAINT `FK_Reference_60` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;  
CREATE TABLE `ams_product_snaps` (	 #----11---- 
  `snaps_time` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` 		BIGINT UNSIGNED,
  `company_id` 		BIGINT UNSIGNED,
  `product_name` 	VARCHAR(50) ,
  `product_type` 	BIT ,
  `product_code` 	VARCHAR(50) ,
  `product_manager` 	VARCHAR(50) , 
  `product_status` 	BIT ,
  `product_share_source` BIT ,
  `start_date` 		BIGINT,
  `end_date` 		BIGINT ,
  `product_desc` 	VARCHAR(300),  
  `product_shares` 	DECIMAL , 
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  `product_supervisor` 	VARCHAR(50),
  `o32_id` 		BIGINT , 
  PRIMARY KEY (`snaps_time`)
  #CONSTRAINT `FK_Reference_61` FOREIGN KEY (`product_id`) REFERENCES `ams_product` (`product_id`),
  #CONSTRAINT `FK_Reference_62` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;  

/*
CREATE TABLE `ams_trade_account` (	#----11.1---- 
  `trade_account_id` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` 			BIGINT UNSIGNED,
  `trade_account` 		BIGINT ,
  `trade_account_name` 		VARCHAR(50) ,
  `trade_account_password` 	VARCHAR(50) ,
  `broker_id` 			BIGINT UNSIGNED,
  `trade_account_status` 	VARCHAR(50) , 
  `create_time` 		BIGINT ,
  `update_time` 		BIGINT ,
  `operator_id` 		BIGINT ,
  `is_login` 			BIGINT ,
  PRIMARY KEY (`trade_account_id`)
  #CONSTRAINT `FK_Reference_63` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`),
  #CONSTRAINT `FK_Reference_64` FOREIGN KEY (`broker_id`) REFERENCES `ams_broker` (`broker_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 

*/


CREATE TABLE `ams_product_account` (	#----12---- 
  `product_trade_account_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` 			BIGINT UNSIGNED,
  `trade_account_id` 		BIGINT UNSIGNED,  
  PRIMARY KEY (`product_trade_account_id`)
  #CONSTRAINT `FK_Reference_65` FOREIGN KEY (`product_id`) REFERENCES `ams_product` (`product_id`),
  #CONSTRAINT `FK_Reference_66` FOREIGN KEY (`trade_account_id`) REFERENCES `ams_trade_account` (`trade_account_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 

CREATE TABLE `ams_stock_category` ( #----13---- 
  `stock_category_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` 		BIGINT UNSIGNED,
  `stock_category_name` VARCHAR(200) , 
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`stock_category_id`)
  #CONSTRAINT `FK_Reference_67` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;  
CREATE TABLE `ams_trade_fee_template` (		#----14----  
  `fee_templatete_id`		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `stock_category_id` 		BIGINT UNSIGNED,
  `commission_rate` 		VARCHAR(50) ,
  `minimum_commission` 		VARCHAR(50) ,
  `transfer_fee_rate` 		VARCHAR(50) ,
  `minimum_charge_count` 	VARCHAR(50) ,
  `buy_stamp_duty_rate` 	VARCHAR(50) ,
  `sell_stamp_duty_rate` 	VARCHAR(50) , 
  `create_time` 		BIGINT ,
  `update_time` 		BIGINT , 
  `operator_id` 		BIGINT ,  
  PRIMARY KEY (`fee_templatete_id`)
  #CONSTRAINT `FK_Reference_68` FOREIGN KEY (`stock_category_id`) REFERENCES `ams_stock_category` (`stock_category_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;  
CREATE TABLE `ams_trade_account_fee` (	 	#----15----  
  `ams_trade_account_fee_id`		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ams_trade_account_id` 		BIGINT UNSIGNED,
  `ams_trade_fee_template_id` 		BIGINT UNSIGNED,  
  PRIMARY KEY (`ams_trade_account_fee_id`)
  #CONSTRAINT `FK_Reference_69` FOREIGN KEY (`ams_trade_account_id`) REFERENCES `ams_trade_account` (`trade_account_id`),
  #CONSTRAINT `FK_Reference_70` FOREIGN KEY (`ams_trade_fee_template_id`) REFERENCES `ams_trade_fee_template` (`fee_templatete_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;  
CREATE TABLE `ams_stock` (		#----16----  
  `stock_id` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `stock_name` 		VARCHAR(50) ,
  `stock_code` 		VARCHAR(50) ,
  `stock_category_id` 	BIGINT UNSIGNED, 
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`stock_id`)
  #CONSTRAINT `FK_Reference_71` FOREIGN KEY (`stock_category_id`) REFERENCES `ams_stock_category` (`stock_category_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 

CREATE TABLE `ams_trade_instruct` (	#----17----  
  `instruct_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `trade_platform_type` TINYINT(4) ,
  `trade_order_type` 	TINYINT(4) ,
  `instruct_user_id` 	BIGINT , 			#???
  `remark` 		VARCHAR(50) ,
  `executed_qty` 	INTEGER , 
  `instruct_status` 	TINYINT(4) ,
  `start_time` 		BIGINT ,
  `end_time` 		BIGINT ,
  `ip_source` 		VARCHAR(50) ,
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`instruct_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 
CREATE TABLE `ams_trade_instruct_snaps` (	#----18----  
  `snaps_time` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `instruct_id` 	BIGINT UNSIGNED,
  `trade_platform_type` TINYINT(4) ,
  `trade_order_type` 	TINYINT(4) ,
  `instruct_user_id` 	BIGINT , 		#???
  `remark` 		VARCHAR(50) ,
  `executed_qty` 	INTEGER , 
  `instruct_status` 	TINYINT(4) ,
  `start_time` 		BIGINT ,
  `end_time` 		BIGINT ,
  `ip_source` 		VARCHAR(50) ,
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`snaps_time`)
  #CONSTRAINT `FK_Reference_72` FOREIGN KEY (`instruct_id`) REFERENCES `ams_trade_instruct` (`instruct_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 
CREATE TABLE `ams_trade_instruct_stock` (	#----19----  
  `ams_trade_instruct_stock_id`	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ams_trade_instruct_id`	BIGINT UNSIGNED,
  `stock_id` 			BIGINT UNSIGNED,
  PRIMARY KEY (`ams_trade_instruct_stock_id`)
  #CONSTRAINT `FK_Reference_73` FOREIGN KEY (`ams_trade_instruct_id`) REFERENCES `ams_trade_instruct` (`instruct_id`),
  #CONSTRAINT `FK_Reference_74` FOREIGN KEY (`stock_id`) REFERENCES `ams_stock` (`stock_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 

CREATE TABLE `ams_market` (		#----20----  
  `market_id` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `market_name` 	VARCHAR(50) ,
  `market_code` 	VARCHAR(50) , 
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`market_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 
CREATE TABLE `ams_stock_black_white_list` ( 	#----21----  
  `black_white_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` 		BIGINT  UNSIGNED,
  `stock_id` 		BIGINT  UNSIGNED,
  `market_id` 		BIGINT  UNSIGNED, 
  `is_global` 		BIT ,
  `is_black` 		BIT ,
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`black_white_id`)
  #CONSTRAINT `FK_Reference_75` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`),
  #CONSTRAINT `FK_Reference_76` FOREIGN KEY (`stock_id`) REFERENCES `ams_stock` (`stock_id`),
  #CONSTRAINT `FK_Reference_77` FOREIGN KEY (`market_id`) REFERENCES `ams_market` (`market_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 
CREATE TABLE `ams_stock_holding` (	 	#----22----  
  `stock_holding_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` 		BIGINT UNSIGNED,
  `broker_id` 		BIGINT UNSIGNED,
  `stock_account_id` 	BIGINT  ,
  `market_id` 		BIGINT UNSIGNED,
  `stock_id` 		BIGINT UNSIGNED,
  `market_code` 	VARCHAR(50) ,
  `market_name` 	VARCHAR(50) ,
  `stock_code` 		VARCHAR(50) ,
  `stock_name` 		VARCHAR(50) ,
  `current_stock_holding` INTEGER ,
  `position_date` 	BIGINT ,
  `position_cost` 	DECIMAL ,
  `cost_value` 		DECIMAL ,
  `single_cost_value` 	DECIMAL ,
  `profit_loss` 	VARCHAR(50) ,
  `market_value` 	DECIMAL ,
  `stockholder_account` VARCHAR(50) ,
  `frozen_amount` 	INTEGER ,
  `remaining` 		DECIMAL ,
  `transit_shares` 	INTEGER ,
  `share_remaining` 	DECIMAL ,
  `latest_price` 	DECIMAL ,
  `profit_rate` 	VARCHAR(50) , 
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`stock_holding_id`)
  #CONSTRAINT `FK_Reference_78` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`),
  #CONSTRAINT `FK_Reference_79` FOREIGN KEY (`stock_id`) REFERENCES `ams_stock` (`stock_id`),
  #CONSTRAINT `FK_Reference_80` FOREIGN KEY (`market_id`) REFERENCES `ams_market` (`market_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   

CREATE TABLE `ams_entrust` (	 	#----23----  
  `entrust_id` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `stock_id` 		BIGINT UNSIGNED,
  `company_id` 		BIGINT UNSIGNED,
  `entrust_status` 	BIT ,
  `entrust_date` 	BIGINT ,
  `entrust_price` 	DECIMAL ,
  `traded_price` 	DECIMAL,
  `cancel_amount` 	INTEGER ,
  `buy_sell` 		VARCHAR(50) ,
  `entrust_type` 	VARCHAR(50) ,
  `ip_source` 		VARCHAR(50) ,
  `mac_address` 	VARCHAR(300) ,
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`entrust_id`)
  #CONSTRAINT `FK_Reference_81` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`),
  #CONSTRAINT `FK_Reference_82` FOREIGN KEY (`stock_id`) REFERENCES `ams_stock` (`stock_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   
CREATE TABLE `ams_knock` (	 	#----24----  
  `knock_id` 		BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `stock_id` 		BIGINT UNSIGNED,
  `company_id` 		BIGINT UNSIGNED,
  `contract_id` 	VARCHAR(50) ,		#???
  `knock_price` 	DECIMAL ,
  `knock_volume` 	INTEGER ,
  `entrust_id` 		BIGINT UNSIGNED,
  `knock_date` 		BIGINT ,
  `knock_amount` 	DECIMAL ,
  `buy_sell` 		VARCHAR(50) ,
  `fee` 		DECIMAL , 
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`knock_id`)
  #CONSTRAINT `FK_Reference_83` FOREIGN KEY (`company_id`) REFERENCES `upms_company` (`company_id`),
  #CONSTRAINT `FK_Reference_84` FOREIGN KEY (`stock_id`) REFERENCES `ams_stock` (`stock_id`),
  #CONSTRAINT `FK_Reference_85` FOREIGN KEY (`entrust_id`) REFERENCES `ams_entrust` (`entrust_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   










CREATE TABLE `ams_trade_order` (		 #----25----  
  `trade_order_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `contract_id` 	VARCHAR(50) ,
  `trade_order_status` 	TINYINT ,
  `trade_order_date` 	BIGINT ,
  `knock_avg_price` 	DECIMAL ,
  `cancel_amount` 	INTEGER ,
  `trade_order_type` 	TINYINT,
  `ip_source` 		VARCHAR(50) ,
  `mac_address` 	VARCHAR(200) ,
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`trade_order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   
CREATE TABLE `ams_trade_order_feed` (		 #----26---- 
  `trade_order_feed_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `contract_id` 		VARCHAR(50) , 
  `trade__price` 		DECIMAL ,
  `trade_volume` 		INTEGER ,
  `trade_date` 			BIGINT,
  `trade_order_feed_amount`	DECIMAL ,
  `trade_mark` 			VARCHAR(50) ,
  `fee` 			DECIMAL , 
  `operator_id` 		BIGINT ,
  PRIMARY KEY (`trade_order_feed_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   

CREATE TABLE `sys_global_const_data` (		 #----27---- 
  `global_const_data_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `global_const_data_value` 	VARCHAR(50) , 
  `global_const_code` 		VARCHAR(50) , 
  `global_const_data_name` 	VARCHAR(50) , 
  `is_delete` 			BIT,
  `parent_global_data_id`	BIGINT , 
  PRIMARY KEY (`global_const_data_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   

CREATE TABLE `sys_sequence` ( #权限			 #----28---- 
  `SEQUENCE_CODE` 	VARCHAR(50) , 
  `MAX_VALUE` 		BIGINT    
  #PRIMARY KEY (`global_const_data_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   


CREATE TABLE `ams_platform` (			 #----29---- 
  `platform_id` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `platform_logo` 	VARCHAR(50) ,
  `platform_name` 	VARCHAR(50) , 
  `platform_abbr_name` 	VARCHAR(50) ,
  `platform_type` 	INTEGER , 
  `creditable` 		BIT , 
  `create_time` 	BIGINT ,
  `update_time` 	BIGINT ,
  `operator_id` 	BIGINT ,
  PRIMARY KEY (`platform_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8; 





DROP TABLE `ams_platform`;                                                               ##29
DROP TABLE `sys_sequence`;                                                               ##28
DROP TABLE `sys_global_const_data`;                                                      ##27
DROP TABLE `ams_trade_order_feed`;                                                       ##26
DROP TABLE `ams_trade_order`;                                                            ##25

DROP TABLE `ams_knock`;                                                                  ##24
DROP TABLE `ams_entrust`;                                                                ##23
DROP TABLE `ams_stock_holding`;                                                          ##22
DROP TABLE `ams_stock_black_white_list`;                                                 ##21
DROP TABLE `ams_market`;                                                                 ##20
DROP TABLE `ams_trade_instruct_stock`;                                                   ##19
DROP TABLE `ams_trade_instruct_snaps`;                                                   ##18
DROP TABLE `ams_trade_instruct`;                                                         ##17
DROP TABLE `ams_stock`;                                                                  ##16
DROP TABLE `ams_trade_account_fee`;                                                      ##15
DROP TABLE `ams_trade_fee_template`;                                                     ##14
DROP TABLE `ams_stock_category`;                                                         ##13
DROP TABLE `ams_product_account`;                                                        ##12
DROP TABLE `ams_trade_account`;                                                          ##11.1
DROP TABLE `ams_product_snaps`;                                                          ##11
DROP TABLE `ams_product_detail`;                                                         ##10
DROP TABLE `ams_product_user`;                                                           ##9
DROP TABLE `ams_product`;                                                                ##8
DROP TABLE `ams_trade_account_ext`;                                                      ##7
DROP TABLE `ams_trade_account`;                                                          ##6
DROP TABLE `rsk_blackwhite_list`;                                                        ##5
DROP TABLE `upms_broker_user`;                                                           ##4
DROP TABLE `ams_broker`;                                                                 ##3
DROP TABLE `upms_company_user`;                                                          ##2







