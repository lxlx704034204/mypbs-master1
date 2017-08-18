

-- -1.0.0-----权限模块 start---------------------- 

-- -1.0.1-----insert `upms_system` 	start----------------------
INSERT INTO `upms_system` VALUES ('1', 'zmdi zmdi-shield-security', '/resources/zheng-admin/images/zheng-upms.png', '#29A176', 'http://upms.zhangshuzheng.cn:1111', '1', 'zheng-upms-server', '权限管理系统', '用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）', '1', '1');
INSERT INTO `upms_system` VALUES ('2', 'zmdi zmdi-wikipedia', '/resources/zheng-admin/images/zheng-cms.png', '#455EC5', 'http://cms.zhangshuzheng.cn:2222', '1', 'zheng-cms-admin', '内容管理系统', '内容管理系统（门户、博客、论坛、问答等）', '2', '2');
INSERT INTO `upms_system` VALUES ('3', 'zmdi zmdi-paypal-alt', '/resources/zheng-admin/images/zheng-pay.png', '#F06292', 'http://pay.zhangshuzheng.cn:3331', '1', 'zheng-pay-admin', '支付管理系统', '支付管理系统', '3', '3');
INSERT INTO `upms_system` VALUES ('4', 'zmdi zmdi-account', '/resources/zheng-admin/images/zheng-ucenter.png', '#6539B4', 'http://ucenter.zhangshuzheng.cn:4441', '1', 'zheng-ucenter-home', '用户管理系统', '用户管理系统', '4', '4');
INSERT INTO `upms_system` VALUES ('5', 'zmdi zmdi-cloud', '/resources/zheng-admin/images/zheng-oss.png', '#0B8DE5', 'http://oss.zhangshuzheng.cn:7771', '1', 'zheng-oss-web', '存储管理系统', '存储管理系统', '5', '5');
-- -1.0.1-----insert `upms_system` 	end----------------------
-- -1.0.2-----insert `upms_permission` 	start----------------------
INSERT INTO `upms_permission` VALUES ('1', '1', '0', '系统组织管理', '1', '', '', 'zmdi zmdi-accounts-list', '1', '1', '1');
INSERT INTO `upms_permission` VALUES ('2', '1', '1', '系统管理', '2', 'upms:system:read', '/manage/system/index', '', '1', '2', '2');
INSERT INTO `upms_permission` VALUES ('3', '1', '1', '组织管理', '2', 'upms:organization:read', '/manage/organization/index', '', '1', '3', '3');
INSERT INTO `upms_permission` VALUES ('4', '1', '0', '角色用户管理', '1', '', '', 'zmdi zmdi-accounts', '1', '4', '4');
INSERT INTO `upms_permission` VALUES ('5', '1', '4', '角色管理', '2', 'upms:role:read', '/manage/role/index', '', '1', '6', '6');
INSERT INTO `upms_permission` VALUES ('6', '1', '4', '用户管理', '2', 'upms:user:read', '/manage/user/index', '', '1', '5', '5');
INSERT INTO `upms_permission` VALUES ('7', '1', '0', '权限资源管理', '1', '', '', 'zmdi zmdi-lock-outline', '1', '7', '7');
INSERT INTO `upms_permission` VALUES ('12', '1', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '12', '12');
INSERT INTO `upms_permission` VALUES ('14', '1', '12', '会话管理', '2', 'upms:session:read', '/manage/session/index', '', '1', '14', '14');
INSERT INTO `upms_permission` VALUES ('15', '1', '12', '日志记录', '2', 'upms:log:read', '/manage/log/index', '', '1', '15', '15');
INSERT INTO `upms_permission` VALUES ('17', '2', '0', '标签类目管理', '1', NULL, NULL, 'zmdi zmdi-menu', '1', '17', '17');
INSERT INTO `upms_permission` VALUES ('18', '2', '17', '标签管理', '2', 'cms:tag:read', '/manage/tag/index', NULL, '1', '18', '18');
INSERT INTO `upms_permission` VALUES ('19', '2', '17', '类目管理', '2', 'cms:category:read', '/manage/category/index', NULL, '1', '19', '19');
INSERT INTO `upms_permission` VALUES ('20', '2', '0', '文章评论管理', '1', NULL, NULL, 'zmdi zmdi-collection-text', '1', '20', '20');
INSERT INTO `upms_permission` VALUES ('21', '2', '20', '文章管理', '2', 'cms:article:read', '/manage/article/index', NULL, '1', '21', '21');
INSERT INTO `upms_permission` VALUES ('22', '2', '20', '回收管理', '2', 'cms:article:read', '/manage/article/recycle', NULL, '1', '22', '22');
INSERT INTO `upms_permission` VALUES ('24', '1', '2', '新增系统', '3', 'upms:system:create', '/manage/system/create', 'zmdi zmdi-plus', '1', '24', '24');
INSERT INTO `upms_permission` VALUES ('25', '1', '2', '编辑系统', '3', 'upms:system:update', '/manage/system/update', 'zmdi zmdi-edit', '1', '25', '25');
INSERT INTO `upms_permission` VALUES ('26', '1', '2', '删除系统', '3', 'upms:system:delete', '/manage/system/delete', 'zmdi zmdi-close', '1', '26', '26');
INSERT INTO `upms_permission` VALUES ('27', '1', '3', '新增组织', '3', 'upms:organization:create', '/manage/organization/create', 'zmdi zmdi-plus', '1', '27', '27');
INSERT INTO `upms_permission` VALUES ('28', '1', '3', '编辑组织', '3', 'upms:organization:update', '/manage/organization/update', 'zmdi zmdi-edit', '1', '28', '28');
INSERT INTO `upms_permission` VALUES ('29', '1', '3', '删除组织', '3', 'upms:organization:delete', '/manage/organization/delete', 'zmdi zmdi-close', '1', '29', '29');
INSERT INTO `upms_permission` VALUES ('30', '1', '6', '新增用户', '3', 'upms:user:create', '/manage/user/create', 'zmdi zmdi-plus', '1', '30', '30');
INSERT INTO `upms_permission` VALUES ('31', '1', '6', '编辑用户', '3', 'upms:user:update', '/manage/user/update', 'zmdi zmdi-edit', '1', '31', '31');
INSERT INTO `upms_permission` VALUES ('32', '1', '6', '删除用户', '3', 'upms:user:delete', '/manage/user/delete', 'zmdi zmdi-close', '1', '32', '32');
INSERT INTO `upms_permission` VALUES ('33', '1', '5', '新增角色', '3', 'upms:role:create', '/manage/role/create', 'zmdi zmdi-plus', '1', '33', '33');
INSERT INTO `upms_permission` VALUES ('34', '1', '5', '编辑角色', '3', 'upms:role:update', '/manage/role/update', 'zmdi zmdi-edit', '1', '34', '34');
INSERT INTO `upms_permission` VALUES ('35', '1', '5', '删除角色', '3', 'upms:role:delete', '/manage/role/delete', 'zmdi zmdi-close', '1', '35', '35');
INSERT INTO `upms_permission` VALUES ('36', '1', '39', '新增权限', '3', 'upms:permission:create', '/manage/permission/create', 'zmdi zmdi-plus', '1', '36', '36');
INSERT INTO `upms_permission` VALUES ('37', '1', '39', '编辑权限', '3', 'upms:permission:update', '/manage/permission/update', 'zmdi zmdi-edit', '1', '37', '37');
INSERT INTO `upms_permission` VALUES ('38', '1', '39', '删除权限', '3', 'upms:permission:delete', '/manage/permission/delete', 'zmdi zmdi-close', '1', '38', '38');
INSERT INTO `upms_permission` VALUES ('39', '1', '7', '权限管理', '2', 'upms:permission:read', '/manage/permission/index', NULL, '1', '39', '39');
INSERT INTO `upms_permission` VALUES ('46', '1', '5', '角色权限', '3', 'upms:role:permission', '/manage/role/permission', 'zmdi zmdi-key', '1', '1488091928257', '1488091928257');
INSERT INTO `upms_permission` VALUES ('48', '1', '6', '用户组织', '3', 'upms:user:organization', '/manage/user/organization', 'zmdi zmdi-accounts-list', '1', '1488120011165', '1488120011165');
INSERT INTO `upms_permission` VALUES ('50', '1', '6', '用户角色', '3', 'upms:user:role', '/manage/user/role', 'zmdi zmdi-accounts', '1', '1488120554175', '1488120554175');
INSERT INTO `upms_permission` VALUES ('51', '1', '6', '用户权限', '3', 'upms:user:permission', '/manage/user/permission', 'zmdi zmdi-key', '1', '1488092013302', '1488092013302');
INSERT INTO `upms_permission` VALUES ('53', '1', '14', '强制退出', '3', 'upms:session:forceout', '/manage/session/forceout', 'zmdi zmdi-run', '1', '1488379514715', '1488379514715');
INSERT INTO `upms_permission` VALUES ('54', '2', '18', '新增标签', '3', 'cms:tag:create', '/manage/tag/create', 'zmdi zmdi-plus', '1', '1489417315159', '1489417315159');
INSERT INTO `upms_permission` VALUES ('55', '2', '18', '编辑标签', '3', 'cms:tag:update', 'zmdi zmdi-edit', 'zmdi zmdi-widgets', '1', '1489417344931', '1489417344931');
INSERT INTO `upms_permission` VALUES ('56', '2', '18', '删除标签', '3', 'cms:tag:delete', '/manage/tag/delete', 'zmdi zmdi-close', '1', '1489417372114', '1489417372114');
INSERT INTO `upms_permission` VALUES ('57', '1', '15', '删除权限', '3', 'upms:log:delete', '/manage/log/delete', 'zmdi zmdi-close', '1', '1489503867909', '1489503867909');
INSERT INTO `upms_permission` VALUES ('58', '2', '19', '编辑类目', '3', 'cms:category:update', '/manage/category/update', 'zmdi zmdi-edit', '1', '1489586600462', '1489586600462');
INSERT INTO `upms_permission` VALUES ('59', '2', '19', '删除类目', '3', 'cms:category:delete', '/manage/category/delete', 'zmdi zmdi-close', '1', '1489586633059', '1489586633059');
INSERT INTO `upms_permission` VALUES ('60', '2', '19', '新增类目', '3', 'cms:category:create', '/manage/category/create', 'zmdi zmdi-plus', '1', '1489590342089', '1489590342089');
INSERT INTO `upms_permission` VALUES ('61', '2', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '1489835455359', '1489835455359');
INSERT INTO `upms_permission` VALUES ('62', '2', '20', '评论管理', '2', 'cms:comment:read', '/manage/comment/index', '', '1', '1489591408224', '1489591408224');
INSERT INTO `upms_permission` VALUES ('63', '2', '62', '删除评论', '3', 'cms:comment:delete', '/manage/comment/delete', 'zmdi zmdi-close', '1', '1489591449614', '1489591449614');
INSERT INTO `upms_permission` VALUES ('64', '2', '79', '单页管理', '2', 'cms:page:read', '/manage/page/index', '', '1', '1489591332779', '1489591332779');
INSERT INTO `upms_permission` VALUES ('65', '2', '64', '新增单页', '3', 'cms:page:create', '/manage/page/create', 'zmdi zmdi-plus', '1', '1489591614473', '1489591614473');
INSERT INTO `upms_permission` VALUES ('66', '2', '64', '编辑单页', '3', 'cms:page:update', '/manage/page/update', 'zmdi zmdi-edit', '1', '1489591653000', '1489591653000');
INSERT INTO `upms_permission` VALUES ('67', '2', '64', '删除单页', '3', 'cms:page:delete', '/manage/page/delete', 'zmdi zmdi-close', '1', '1489591683552', '1489591683552');
INSERT INTO `upms_permission` VALUES ('68', '2', '61', '菜单管理', '2', 'cms:menu:read', '/manage/menu/index', 'zmdi zmdi-widgets', '1', '1489591746846', '1489591746846');
INSERT INTO `upms_permission` VALUES ('69', '2', '68', '新增菜单', '3', 'cms:menu:create', '/manage/menu/create', 'zmdi zmdi-plus', '1', '1489591791747', '1489591791747');
INSERT INTO `upms_permission` VALUES ('70', '2', '68', '编辑菜单', '3', 'cms:menu:update', '/manage/menu/update', 'zmdi zmdi-edit', '1', '1489591831878', '1489591831878');
INSERT INTO `upms_permission` VALUES ('71', '2', '68', '删除菜单', '3', 'cms:menu:delete', '/manage/menu/delete', 'zmdi zmdi-close', '1', '1489591865454', '1489591865454');
INSERT INTO `upms_permission` VALUES ('72', '2', '61', '系统设置', '2', 'cms:setting:read', '/manage/setting/index', 'zmdi zmdi-widgets', '1', '1489591981165', '1489591981165');
INSERT INTO `upms_permission` VALUES ('73', '2', '72', '新增设置', '3', 'cms:setting:create', '/manage/setting/create', 'zmdi zmdi-plus', '1', '1489592024762', '1489592024762');
INSERT INTO `upms_permission` VALUES ('74', '2', '72', '编辑设置', '3', 'cms:setting:update', '/manage/setting/update', 'zmdi zmdi-edit', '1', '1489592052582', '1489592052582');
INSERT INTO `upms_permission` VALUES ('75', '2', '72', '删除设置', '3', 'cms:setting:delete', '/manage/setting/delete', 'zmdi zmdi-close', '1', '1489592081426', '1489592081426');
INSERT INTO `upms_permission` VALUES ('76', '2', '21', '新增文章', '3', 'cms:article:create', '/manage/article/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('77', '2', '21', '编辑文章', '3', 'cms:article:update', '/manage/article/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('78', '2', '21', '删除文章', '3', 'cms:article:delete', '/manage/article/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');
INSERT INTO `upms_permission` VALUES ('79', '2', '0', '单页专题管理', '1', '', '', 'zmdi zmdi-view-web', '1', '1489835320327', '1489835320327');
INSERT INTO `upms_permission` VALUES ('80', '2', '79', '专题管理', '2', 'cms:topic:read', '/manage/topic/index', 'zmdi zmdi-widgets', '1', '1489591507566', '1489591507566');
INSERT INTO `upms_permission` VALUES ('81', '2', '80', '新增专题', '3', 'cms:topic:create', '/manage/topic/create', 'zmdi zmdi-plus', '1', '1489843327028', '1489843327028');
INSERT INTO `upms_permission` VALUES ('82', '2', '80', '编辑专题', '3', 'cms:topic:update', '/manage/topic/update', 'zmdi zmdi-edit', '1', '1489843351513', '1489843351513');
INSERT INTO `upms_permission` VALUES ('83', '2', '80', '删除专题', '3', 'cms:topic:delete', '/manage/topic/delete', 'zmdi zmdi-close', '1', '1489843379953', '1489843379953');
INSERT INTO `upms_permission` VALUES ('84', '2', '68', '上移菜单', '3', 'cms:menu:up', '/manage/menu/up', 'zmdi zmdi-long-arrow-up', '1', '1489846486548', '1489846486548');
INSERT INTO `upms_permission` VALUES ('85', '2', '68', '下移菜单', '3', 'cms:menu:down', '/manage/menu/down', 'zmdi zmdi-long-arrow-down', '1', '1489846578051', '1489846578051');
-- -1.0.2-----insert `upms_permission` 		end----------------------

-- -1.0.3-----insert `upms_organization` 	start----------------------
INSERT INTO `upms_organization` VALUES ('1', NULL, '总部', '北京总部', '1');
INSERT INTO `upms_organization` VALUES ('4', NULL, '河北分部', '河北石家庄', '1488122466236');
INSERT INTO `upms_organization` VALUES ('5', NULL, '河南分部', '河南郑州', '1488122480265');
INSERT INTO `upms_organization` VALUES ('6', NULL, '湖北分部', '湖北武汉', '1488122493265');
INSERT INTO `upms_organization` VALUES ('7', NULL, '湖南分部', '湖南长沙', '1488122502752');
-- -1.0.3-----insert `upms_organization` 	end----------------------

-- -1.0.4-----insert `upms_company` 	start----------------------
INSERT INTO `upms_company`(`company_id`,`company_name`) 
VALUES ('1', 'company_name1'), #不能写成: '1L'或'1l'或1L或1l
       ( 2, 'company_name2');
-- -1.0.4-----insert `upms_company` 	end----------------------

-- -1.0.5-----insert `upms_user` 	start----------------------
INSERT INTO `upms_user` VALUES ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '张恕征', '/resources/zheng-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1'	      ,1);
INSERT INTO `upms_user` VALUES ('2', 'test',  '285C9762F5F9046F5893F752DFAF3476', 'd2d0d03310444ad388a8b290b0fe8564', '张恕征', '/resources/zheng-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1493394720495' ,1);
-- -1.0.5-----insert `upms_user` 	end----------------------

-- -1.0.6-----insert `upms_user_organization` 	start----------------------
INSERT INTO `upms_user_organization` VALUES ('19', '1', '1');
INSERT INTO `upms_user_organization` VALUES ('20', '1', '4');
INSERT INTO `upms_user_organization` VALUES ('21', '1', '5');
INSERT INTO `upms_user_organization` VALUES ('22', '1', '6');
INSERT INTO `upms_user_organization` VALUES ('23', '1', '7');
-- -1.0.6-----insert `upms_user_organization` 	end----------------------
-- -1.0.7-----insert `upms_user_permission` 	start----------------------
INSERT INTO `upms_user_permission` VALUES ('3', '1', '22', '-1');
INSERT INTO `upms_user_permission` VALUES ('4', '1', '22', '1');
INSERT INTO `upms_user_permission` VALUES ('5', '2', '24', '-1');
INSERT INTO `upms_user_permission` VALUES ('6', '2', '26', '-1');
INSERT INTO `upms_user_permission` VALUES ('7', '2', '27', '-1');
INSERT INTO `upms_user_permission` VALUES ('8', '2', '29', '-1');
INSERT INTO `upms_user_permission` VALUES ('9', '2', '32', '-1');
INSERT INTO `upms_user_permission` VALUES ('10', '2', '51', '-1');
INSERT INTO `upms_user_permission` VALUES ('11', '2', '48', '-1');
INSERT INTO `upms_user_permission` VALUES ('12', '2', '50', '-1');
INSERT INTO `upms_user_permission` VALUES ('13', '2', '35', '-1');
INSERT INTO `upms_user_permission` VALUES ('14', '2', '46', '-1');
INSERT INTO `upms_user_permission` VALUES ('15', '2', '37', '-1');
INSERT INTO `upms_user_permission` VALUES ('16', '2', '38', '-1');
INSERT INTO `upms_user_permission` VALUES ('17', '2', '57', '-1');
INSERT INTO `upms_user_permission` VALUES ('18', '2', '56', '-1');
INSERT INTO `upms_user_permission` VALUES ('19', '2', '59', '-1');
INSERT INTO `upms_user_permission` VALUES ('20', '2', '78', '-1');
INSERT INTO `upms_user_permission` VALUES ('21', '2', '67', '-1');
INSERT INTO `upms_user_permission` VALUES ('22', '2', '83', '-1');
INSERT INTO `upms_user_permission` VALUES ('23', '2', '71', '-1');
INSERT INTO `upms_user_permission` VALUES ('24', '2', '75', '-1');
-- -1.0.7-----insert `upms_user_permission` 	end----------------------
-- -1.0.8-----insert `upms_role` 	start----------------------
INSERT INTO `upms_role` VALUES ('1', 'super', '超级管理员', '拥有所有权限', '1', '1');
INSERT INTO `upms_role` VALUES ('2', 'admin', '管理员', '拥有除权限管理系统外的所有权限', '1487471013117', '1487471013117');
-- -1.0.8-----insert `upms_role` 	end----------------------
-- -1.0.9-----insert `upms_user_role` 	start----------------------
INSERT INTO `upms_user_role` VALUES ('4', '1', '1');
INSERT INTO `upms_user_role` VALUES ('5', '1', '2');
INSERT INTO `upms_user_role` VALUES ('6', '2', '1');
INSERT INTO `upms_user_role` VALUES ('7', '2', '2');
-- -1.0.9-----insert `upms_user_role` 	end----------------------
-- -1.1.0-----insert `upms_role_permission` 	start----------------------
INSERT INTO `upms_role_permission` VALUES ('1', '1', '1');
INSERT INTO `upms_role_permission` VALUES ('2', '1', '2');
INSERT INTO `upms_role_permission` VALUES ('3', '1', '3');
INSERT INTO `upms_role_permission` VALUES ('4', '1', '4');
INSERT INTO `upms_role_permission` VALUES ('5', '1', '5');
INSERT INTO `upms_role_permission` VALUES ('6', '1', '6');
INSERT INTO `upms_role_permission` VALUES ('7', '1', '7');
INSERT INTO `upms_role_permission` VALUES ('8', '1', '39');
INSERT INTO `upms_role_permission` VALUES ('12', '1', '12');
INSERT INTO `upms_role_permission` VALUES ('14', '1', '14');
INSERT INTO `upms_role_permission` VALUES ('15', '1', '15');
INSERT INTO `upms_role_permission` VALUES ('17', '1', '17');
INSERT INTO `upms_role_permission` VALUES ('19', '1', '19');
INSERT INTO `upms_role_permission` VALUES ('20', '1', '20');
INSERT INTO `upms_role_permission` VALUES ('21', '1', '21');
INSERT INTO `upms_role_permission` VALUES ('24', '1', '24');
INSERT INTO `upms_role_permission` VALUES ('27', '1', '27');
INSERT INTO `upms_role_permission` VALUES ('28', '1', '28');
INSERT INTO `upms_role_permission` VALUES ('29', '1', '29');
INSERT INTO `upms_role_permission` VALUES ('30', '1', '30');
INSERT INTO `upms_role_permission` VALUES ('31', '1', '31');
INSERT INTO `upms_role_permission` VALUES ('32', '1', '32');
INSERT INTO `upms_role_permission` VALUES ('33', '1', '33');
INSERT INTO `upms_role_permission` VALUES ('34', '1', '34');
INSERT INTO `upms_role_permission` VALUES ('35', '1', '35');
INSERT INTO `upms_role_permission` VALUES ('36', '1', '36');
INSERT INTO `upms_role_permission` VALUES ('37', '1', '37');
INSERT INTO `upms_role_permission` VALUES ('38', '1', '38');
INSERT INTO `upms_role_permission` VALUES ('39', '1', '46');
INSERT INTO `upms_role_permission` VALUES ('40', '1', '51');
INSERT INTO `upms_role_permission` VALUES ('44', '1', '48');
INSERT INTO `upms_role_permission` VALUES ('45', '1', '50');
INSERT INTO `upms_role_permission` VALUES ('47', '1', '53');
INSERT INTO `upms_role_permission` VALUES ('48', '1', '18');
INSERT INTO `upms_role_permission` VALUES ('49', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('50', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('51', '1', '55');
INSERT INTO `upms_role_permission` VALUES ('52', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('53', '1', '55');
INSERT INTO `upms_role_permission` VALUES ('54', '1', '56');
INSERT INTO `upms_role_permission` VALUES ('55', '1', '57');
INSERT INTO `upms_role_permission` VALUES ('56', '1', '58');
INSERT INTO `upms_role_permission` VALUES ('57', '1', '58');
INSERT INTO `upms_role_permission` VALUES ('58', '1', '59');
INSERT INTO `upms_role_permission` VALUES ('59', '1', '60');
INSERT INTO `upms_role_permission` VALUES ('60', '1', '61');
INSERT INTO `upms_role_permission` VALUES ('61', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('62', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('63', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('64', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('65', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('66', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('67', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('68', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('69', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('70', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('71', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('72', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('73', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('74', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('75', '1', '66');
INSERT INTO `upms_role_permission` VALUES ('76', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('77', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('78', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('79', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('80', '1', '66');
INSERT INTO `upms_role_permission` VALUES ('81', '1', '67');
INSERT INTO `upms_role_permission` VALUES ('82', '1', '68');
INSERT INTO `upms_role_permission` VALUES ('83', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('84', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('85', '1', '70');
INSERT INTO `upms_role_permission` VALUES ('86', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('87', '1', '70');
INSERT INTO `upms_role_permission` VALUES ('88', '1', '71');
INSERT INTO `upms_role_permission` VALUES ('89', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('90', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('91', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('92', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('93', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('94', '1', '74');
INSERT INTO `upms_role_permission` VALUES ('95', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('96', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('97', '1', '74');
INSERT INTO `upms_role_permission` VALUES ('98', '1', '75');
INSERT INTO `upms_role_permission` VALUES ('99', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('100', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('101', '1', '77');
INSERT INTO `upms_role_permission` VALUES ('102', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('103', '1', '77');
INSERT INTO `upms_role_permission` VALUES ('105', '1', '79');
INSERT INTO `upms_role_permission` VALUES ('106', '1', '80');
INSERT INTO `upms_role_permission` VALUES ('107', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('108', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('109', '1', '82');
INSERT INTO `upms_role_permission` VALUES ('110', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('111', '1', '82');
INSERT INTO `upms_role_permission` VALUES ('112', '1', '83');
INSERT INTO `upms_role_permission` VALUES ('113', '1', '84');
INSERT INTO `upms_role_permission` VALUES ('114', '1', '84');
INSERT INTO `upms_role_permission` VALUES ('115', '1', '85');
INSERT INTO `upms_role_permission` VALUES ('121', '1', '78');
INSERT INTO `upms_role_permission` VALUES ('122', '1', '78');
INSERT INTO `upms_role_permission` VALUES ('124', '1', '25');
INSERT INTO `upms_role_permission` VALUES ('125', '1', '26');
-- -1.1.0-----insert `upms_role_permission` 	end----------------------


-- -1-----权限模块 end---------------------- 











