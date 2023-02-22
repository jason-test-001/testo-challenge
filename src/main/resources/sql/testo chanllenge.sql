# sys_user
CREATE TABLE sys_user  (
                           `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id 作为表主键 用于关联',
                           `userName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录帐号',
                           `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录密码',
                           `userRemarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，预留字段',
                           PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO sys_user(`userId`, `userName`, `password`, `userRemarks`) VALUES
(10001, 'adminHong', '202cb962ac59075b964b07152d234b70', 'adminHong as admin,password is 123'),
(20001, 'jason', 'e165421110ba03099a1c0393373c5b43', 'jason as common,password is 233'),
(30001, 'kevin', 'e165421110ba03099a1c0393373c5b43', 'kevin as common,password is 233');


# sys_role
CREATE TABLE sys_role  (
                           `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 角色id 作为表主键 用于关联',
                           `roleName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
                           `roleRemarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，预留字段',
                           PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO sys_role(`roleId`, `roleName`, `roleRemarks`) VALUES
('100', 'admin', 'admin has all the permissions'),
('200', 'common', 'only has his own permission');


# create table: sys_permissions
CREATE TABLE sys_permissions  (
                                  `perId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限表id 作为表主键 用于关联',
                                  `permissionsName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
                                  `perRemarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，预留字段',
                                  PRIMARY KEY (`perId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO sys_permissions(`perId`, `permissionsName`, `perRemarks`) VALUES
('A0001', 'queryAllRecord', 'query all short url record.'),
('U0001', 'queryUserRecord', 'query user own short url record.');



#create table: user_role
CREATE TABLE user_role  (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表主键id',
                            `userId` int(11) NULL DEFAULT NULL COMMENT '帐号表的主键id',
                            `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色表的主键id',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO user_role(`id`, `userId`, `roleId`) VALUES
(1, 10001, '100'),
(2, 20001, '200'),
(3, 30001, '200');


#create table: role_per
CREATE TABLE role_per  (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表主键id',
                           `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色表的主键id',
                           `perId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限表的主键id',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO role_per(`id`, `roleId`, `perId`) VALUES
(1, '100', 'A0001'),
(2, '100', 'U0001'),
(3, '200', 'U0001');


#get user permission

SELECT user.userId ,user.userName,role.roleName,role.roleId,per.permissionsName ,per.perId,per.perRemarks
FROM sys_user AS user,
			 sys_role AS role,
			 sys_permissions AS per,
			 role_per,
			 user_role
WHERE user.userName= 'kevin'
  AND user.userId=user_role.userId
  AND user_role.roleId=role.roleId
  AND role_per.roleId=role.roleId
  AND role_per.perId=per.perId

    #create table: short_url
CREATE TABLE short_url  (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
                            `createUser` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'create user',
                            `createDate` date NULL DEFAULT NULL COMMENT 'create time',
                            `updateUser` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'update user',
                            `updateDate` date NULL DEFAULT NULL COMMENT 'create time',
                            `shortUrl` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'create user',
                            `longUrl` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'create user',
                            `shortTimes` int(11)  COMMENT 'number of short url',
                            `accessTimes` int(11)  COMMENT 'number of access url',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO short_url (createUser,createDate,updateUser,updateDate,shortUrl,longUrl,shortTimes,accessTimes) VALUES
('jason','2023-02-21','kevin','2023-02-21','https://a.cn/EVRFr2','http://video.sinaA.com.cn/p/news/s/v/2015-09-02/105265067233.html',2,1),
('adminHong','2023-02-21','jason','2023-02-21','https://a.cn/EVRFr2','http://video.sinaA.com.cn/p/news/s/v/2015-09-02/105265067233.html',2,1),
('kevinjason','2023-02-21','admin','2023-02-22','https://a.cn/R7vimm','http://video.sina.com.cn/p/news/s/v/2015-09-02/105265067233.html',6,1),
('customer','2023-02-21','customer','2023-02-21','https://a.cn/RFJNFr','http://video.sina.com.cn/p/news/sss/v/2015-09-02/105265067233.html',2,1);
