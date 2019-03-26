SET CHAR SET 'utf8';
#######################################字典表###############################################
create table IF NOT EXISTS `SYS_MAJOR` (
                                         `MAJOR_NUM`      INT               AUTO_INCREMENT
                                           COMMENT '专业编号',
                                         `MAJOR_LEVEL`    VARCHAR(100) NOT NULL
                                           COMMENT '专业分级',
                                         `MAJOR_CODE`    VARCHAR(10) NOT NULL
                                           COMMENT '专业代码',
                                         `MAJOR_ABBRE`    VARCHAR(100)
                                           COMMENT '专业简称',
                                         `MAJOR_CHN`      VARCHAR(100) NOT NULL
                                           COMMENT '专业中文全称',
                                         `MAJOR_ENG`      VARCHAR(100) NOT NULL
                                           COMMENT '专业英文全称',
                                         `START_DATE`     TIMESTAMP    NOT NULL
                                           COMMENT '启用时间',
                                         `IS_DELETE`      INTEGER      DEFAULT '0'
                                           COMMENT '是否删除',
                                         `IS_DELETE_TIME` TIMESTAMP    NULL DEFAULT NULL
                                           COMMENT '删除时间',
                                         PRIMARY KEY (`MAJOR_NUM`)
)
  COMMENT = '专业分类表';


create table IF NOT EXISTS `SYS_DEGREE` (
                                          `DEGREE_NUM` INT AUTO_INCREMENT
                                            COMMENT '学位编号',
                                          `DEGREE_CHN` VARCHAR(100) NOT NULL
                                            COMMENT '中文描述',
                                          `DEGREE_ENG` VARCHAR(100) NOT NULL
                                            COMMENT '英文描述',
                                          `START_DATE` TIMESTAMP    NOT NULL
                                            COMMENT '启用时间',
                                          PRIMARY KEY (`DEGREE_NUM`)
)
  COMMENT = '学历表';


create table IF NOT EXISTS `SYS_ADVAN_LABEL_CLASS` (
                                                     `LABEL_ID`      INT               AUTO_INCREMENT
                                                       COMMENT '标签编号',
                                                     `LABEL_LEVEL`    INTEGER      NOT NULL
                                                       COMMENT '标签分级',
                                                     `LABEL_CODE`     VARCHAR(20)  NOT NULL
                                                       COMMENT '标签代码',
                                                     `LABEL_PARENT_CODE`      VARCHAR(20)  NOT NULL
                                                       COMMENT '上级标签代码',
                                                     `LABEL_CHN`      VARCHAR(100) NOT NULL
                                                       COMMENT '中文描述',
                                                     `LABEL_ENG`      VARCHAR(100) NOT NULL
                                                       COMMENT '英文描述',
                                                     `START_TIME`     TIMESTAMP    NOT NULL
                                                       COMMENT '启用时间',
                                                     `IS_DELETE`      VARCHAR(100) NOT NULL
                                                       COMMENT '是否删除',
                                                     `IS_DELETE_TIME` TIMESTAMP    NULL DEFAULT NULL
                                                       COMMENT '删除时间',
                                                     PRIMARY KEY (`LABEL_ID`)
)
  COMMENT = '优势标签表';


create table IF NOT EXISTS `SYS_PROF_CLASS` (
                                              `PROF_NUM`       INT               AUTO_INCREMENT
                                                COMMENT '职业编号',
                                              `PROF_LEVEL`      INTEGER      NOT NULL
                                                COMMENT '职业分级',
                                              `PROF_CODE`       VARCHAR(20) NOT NULL
                                                COMMENT '职业代码',
                                              `PROF_PARENT_CODE`       VARCHAR(20) NOT NULL
                                                COMMENT '上级职业代码',
                                              `PROF_CHN`       VARCHAR(100) NOT NULL
                                                COMMENT '中文描述',
                                              `PROF_ENG`       VARCHAR(100)
                                                COMMENT '英文描述',
                                              `START_TIME`     TIMESTAMP    NOT NULL
                                                COMMENT '启用时间',
                                              `IS_DELETE`      INTEGER      DEFAULT '0'
                                                COMMENT '是否删除',
                                              `IS_DELETE_TIME` TIMESTAMP    NULL DEFAULT NULL
                                                COMMENT '删除时间',
                                              PRIMARY KEY (`PROF_NUM`)
)
  COMMENT = '职业信息分类表';


create table IF NOT EXISTS `SYS_IND_CLASS` (
                                             `IND_NUM`        INT               AUTO_INCREMENT
                                               COMMENT '行业编号',
                                             `IND_CODE`        VARCHAR(10) NOT NULL
                                               COMMENT '行业代码',
                                             `IND_CHN`        VARCHAR(100) NOT NULL
                                               COMMENT '行业中文名称',
                                             `IND_ENG`        VARCHAR(100) NOT NULL
                                               COMMENT '行业英文名称',
                                             `IND_LEVEL`      INTEGER      NOT NULL
                                               COMMENT '行业分类级别',
                                             `IND_PARENT_CODE` VARCHAR(10) NOT NULL
                                               COMMENT '上级行业代码',
                                             `START_TIME`     TIMESTAMP    NOT NULL
                                               COMMENT '启用时间',
                                             `IS_DELETE`      INTEGER      DEFAULT '0'
                                               COMMENT '是否删除',
                                             `IS_DELETE_TIME` TIMESTAMP    NULL DEFAULT NULL
                                               COMMENT '删除时间',
                                             PRIMARY KEY (`IND_NUM`)
)
  COMMENT = '行业信息分类表';


create table IF NOT EXISTS `SYS_COMPANY_SCALE` (
                                                 `SCALE_NUM`  INT AUTO_INCREMENT
                                                   COMMENT '规模编号',
                                                 `SCALE_CHN`  VARCHAR(100) NOT NULL
                                                   COMMENT '中文描述',
                                                 `SCALE_ENG`  VARCHAR(100) NOT NULL
                                                   COMMENT '英文描述',
                                                 `START_TIME` TIMESTAMP    NOT NULL
                                                   COMMENT '启用时间',
                                                 PRIMARY KEY (`SCALE_NUM`)
)
  COMMENT = '企业规模表';


create table IF NOT EXISTS `SYS_COMPANY_NATURE` (
                                                  `NATURE_NUM` INT AUTO_INCREMENT
                                                    COMMENT '性质编号',
                                                  `NATURE_CHN` VARCHAR(100) NOT NULL
                                                    COMMENT '中文描述',
                                                  `NATURE_ENG` VARCHAR(100) NOT NULL
                                                    COMMENT '英文描述',
                                                  `NATURE_DETAIL` VARCHAR(100) DEFAULT NULL
                                                    COMMENT '性质细化',
                                                  `START_TIME` TIMESTAMP    NOT NULL
                                                    COMMENT '启用时间',
                                                  PRIMARY KEY (`NATURE_NUM`)
)
  COMMENT = '企业性质表';


create table IF NOT EXISTS `SYS_COUNTRY` (
                                           `COUNTRY_ABBRE`  VARCHAR(10) NOT NULL
                                             COMMENT '国家简称',
                                           `COUNTRY_CHN`    VARCHAR(100) NOT NULL
                                             COMMENT '中文描述',
                                           `COUNTRY_ENG`    VARCHAR(100) NOT NULL
                                             COMMENT '英文描述',
                                           `START_TIME`     TIMESTAMP    NOT NULL
                                             COMMENT '启用时间',
                                           `IS_DELETE`      INTEGER      DEFAULT '0'
                                             COMMENT '是否删除',
                                           `IS_DELETE_TIME` TIMESTAMP    NULL DEFAULT NULL
                                             COMMENT '删除时间',
                                           PRIMARY KEY (`COUNTRY_ABBRE`)
)
  COMMENT = '国别表';


create table IF NOT EXISTS `SYS_CHN_REGION` (
                                              `REGION_ID`        INT               AUTO_INCREMENT
                                                COMMENT '区划ID',
                                              `REGION_NUM`       VARCHAR(10)   NOT NULL
                                                COMMENT '区划编号',
                                              `REGION_CHN`        VARCHAR(50) NOT NULL
                                                COMMENT '中文描述',
                                              `REGION_FULLCHN`    VARCHAR(100) NOT NULL
                                                COMMENT '中文全称',
                                              `REGION_ENG`        VARCHAR(100) NOT NULL
                                                COMMENT '英文描述',
                                              `REGION_LEVEL`      INTEGER      NOT NULL
                                                COMMENT '行政区划级别',
                                              `REGION_PARENT_NUM` INTEGER      NOT NULL
                                                COMMENT '上级区划代码',
                                              `START_TIME`        TIMESTAMP    NOT NULL
                                                COMMENT '启用时间',
                                              `IS_DELETE`         INTEGER      DEFAULT '0'
                                                COMMENT '是否删除',
                                              `IS_DELETE_TIME`    TIMESTAMP    NULL DEFAULT NULL
                                                COMMENT '删除时间',
                                              PRIMARY KEY (`REGION_ID`)
)
  COMMENT = '中国行政区划表';


create table IF NOT EXISTS `SYS_USA_STATE` (
                                             `STATE_ID`   INT   AUTO_INCREMENT
                                               COMMENT '州ID',
                                             `STATE_CHN`  VARCHAR(100) NOT NULL
                                               COMMENT '中文描述',
                                             `STATE_ENG`   VARCHAR(100) NOT NULL
                                               COMMENT '英文描述',
                                             `STATE_ABBRE`  VARCHAR(10)
                                               COMMENT '州简写',
                                             `START_TIME`   TIMESTAMP    NOT NULL
                                               COMMENT '启用时间',
                                             `IS_DELETE`    INTEGER      DEFAULT '0'
                                               COMMENT '是否删除',
                                             `IS_DELETE_TIME` TIMESTAMP    NULL DEFAULT NULL
                                               COMMENT '删除时间',
                                             PRIMARY KEY (`STATE_ID`)
)
  COMMENT = '美国州表';


##美国邮编表
create table IF NOT EXISTS `SYS_USA_ZIPCODE`(
                                              `REGION_ID`  INT AUTO_INCREMENT COMMENT '区划ID',
                                              `ZIP_CODE` VARCHAR(10)  NOT NULL COMMENT 'ZIPCODE',
                                              `REGION_CHN` VARCHAR(100) COMMENT '中文描述',
                                              `REGION_ENG` VARCHAR(100) NOT NULL COMMENT '英文描述',
                                              `REGION_CITY` VARCHAR(100) COMMENT '所属城市',
                                              `STATE_ID` INTEGER COMMENT '所属州ID',
                                              `START_TIME` TIMESTAMP NOT NULL COMMENT '启用时间',
                                              `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                              `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                              PRIMARY KEY (`REGION_ID`)
)COMMENT = '美国邮编表';


create table IF NOT EXISTS `SYS_PERMISSION` (
                                              `PERMI_ID`          INT AUTO_INCREMENT
                                                COMMENT '权限ID',
                                              `PERMI_FUNCTION`    VARCHAR(100) NOT NULL
                                                COMMENT '权限功能',
                                              `PERMI_DESCRPTIPON` VARCHAR(100) NOT NULL
                                                COMMENT '权限描述',
                                              `PERMI_TYPE`        INTEGER      NOT NULL
                                                COMMENT '权限类型',
                                              `IS_DELETE`         INTEGER      DEFAULT '0'
                                                COMMENT '是否删除',
                                              `IS_DELETE_TIME`    TIMESTAMP    NULL DEFAULT NULL
                                                COMMENT '删除时间',
                                              PRIMARY KEY (`PERMI_ID`)
)
  COMMENT = '系统权限表';


##角色权限表
create table IF NOT EXISTS `SYS_ROLE_PERMISSION`(
                                                  `ROLE_ID` INTEGER NOT NULL COMMENT '角色ID',
                                                  `PERMI_ID` INTEGER NOT NULL COMMENT '权限ID',
                                                  `STRAT_TIME` TIMESTAMP NOT NULL COMMENT '启用时间',
                                                  `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                  `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                  PRIMARY KEY (`ROLE_ID`,`PERMI_ID`)
)COMMENT = '角色权限表';


create table IF NOT EXISTS `SYS_ROLE` (
                                        `ROLE_ID`          INT AUTO_INCREMENT
                                          COMMENT '角色ID',
                                        `ROLE_DESCRPTIPON` VARCHAR(100) NOT NULL
                                          COMMENT '角色描述',
                                        `IS_DELETE`        INTEGER      DEFAULT '0'
                                          COMMENT '是否删除',
                                        `IS_DELETE_TIME`   TIMESTAMP    NULL DEFAULT NULL
                                          COMMENT '删除时间',
                                        PRIMARY KEY (`ROLE_ID`)
)
  COMMENT = '系统角色表';


##用户角色表
create table IF NOT EXISTS `SYS_USER_ROLE`(
                                            `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                            `ROLE_ID` INTEGER NOT NULL COMMENT '角色ID',
                                            `STRAT_TIME` TIMESTAMP NOT NULL COMMENT '启用时间',
                                            `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                            `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                            PRIMARY KEY (`USER_ID`,`ROLE_ID`)
)COMMENT = '用户角色表';



create table IF NOT EXISTS `SYS_USER` (
                                        `USER_ID`          INT               AUTO_INCREMENT
                                          COMMENT '用户ID',
                                        `USER_NAME`        VARCHAR(200) NOT NULL
                                          COMMENT '登录名',
                                        `USER_PASS`        VARCHAR(200) NOT NULL
                                          COMMENT '密码',
                                        `USER_REGIST_TIME` TIMESTAMP    NOT NULL
                                          COMMENT '注册时间',
                                        `USER_FIRST_NAME`   VARCHAR(200) NOT NULL
                                          COMMENT '姓',
                                        `USER_LAST_NAME`   VARCHAR(200) NOT NULL
                                          COMMENT '名',
                                        `USER_GENDER`      VARCHAR(10)  NOT NULL
                                          COMMENT '性别',
                                        `USER_AGE`         INTEGER      NOT NULL
                                          COMMENT '年龄',
                                        `USER_NATION`      VARCHAR(10)  NOT NULL
                                          COMMENT '国别',
                                        `USER_MAIL`        VARCHAR(200) NOT NULL
                                          COMMENT '邮箱',
                                        `USER_PHONE`       VARCHAR(200) NOT NULL
                                          COMMENT '手机',
                                        `USER_PHOTO`       VARCHAR(200) COMMENT '照片',
                                        `USER_LOCATION`    INTEGER COMMENT '所在地',
                                        `USER_NICKNAME`    VARCHAR(200) COMMENT '昵称',
                                        `USER_ON_JOB`          INTEGER      NOT NULL
                                          COMMENT '是否在职',
                                        `IS_DELETE`        INTEGER      DEFAULT '0'
                                          COMMENT '是否删除',
                                        `IS_DELETE_TIME`   TIMESTAMP    NULL DEFAULT NULL
                                          COMMENT '删除时间',
                                        PRIMARY KEY (`USER_ID`)
)
  COMMENT = '用户注册信息表';


##用户在职信息表
create table IF NOT EXISTS `SYS_USER_ON_JOB`(
                                              `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                              `COMPANY_ID` INTEGER NOT NULL COMMENT '企业ID',
                                              `ON_JOB_START_TIME` DATE COMMENT '工作起始时间',
                                              `ON_JOB_VERIFY` INTEGER NOT NULL COMMENT '在职验证',
                                              `VERIFY_TIME` TIMESTAMP NOT NULL COMMENT '验证时间',
                                              `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                              `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                              PRIMARY KEY (`USER_ID`)
)COMMENT = '用户在职信息表';



create table IF NOT EXISTS `SYS_HR_CONFIGURATION` (
                                                    `USER_ID` INTEGER
                                                      COMMENT '用户ID',
                                                    `CONFIG_RECEIVE_APPLICANT` INTEGER NOT NULL DEFAULT '1'
                                                      COMMENT '是否接受推荐应聘者',
                                                    PRIMARY KEY (`USER_ID`)
)
  COMMENT = '招聘者个人设置表';


##应聘者个人设置表
create table IF NOT EXISTS `SYS_STU_CONFIGURATION`(
                                                    `USER_ID` INTEGER COMMENT '用户ID',
                                                    `CONFIG_CV_VISIBLE` INTEGER NOT NULL COMMENT '简历屏蔽',
                                                    `CONFIG_RESUME_UPDATE_NOTIF` INTEGER NOT NULL COMMENT '简历修改提示',
                                                    `CONFIG_RECEIVE_JOB` INTEGER NOT NULL COMMENT '是否接受推荐职位',
                                                    `CONFIG_RECEIVE_EMAIL` INTEGER NOT NULL COMMENT '是否接受招聘邮件',
                                                    `CONFIG_BE_SEARCHED` INTEGER NOT NULL COMMENT '通过搜索找到我',
                                                    `CONFIG_BE_EMAILED` INTEGER NOT NULL COMMENT '通过邮件找到我',
                                                    `CONFIG_MOMENT` INTEGER NOT NULL COMMENT '动态展示',
                                                    `CONFIG_REFER` INTEGER NOT NULL COMMENT '能否被提及',
                                                    `CONFIG_PERSON_PRIVACY` INTEGER NOT NULL COMMENT '状态隐私',
                                                    `CONFIG_RECEIVE_FRIEND` INTEGER NOT NULL COMMENT '是否接受好友推荐',
                                                    `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                    `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                    PRIMARY KEY (`USER_ID`)
)
  COMMENT = '应聘者个人设置表';


create table IF NOT EXISTS `SYS_NOTIFICATION` (
                                                `NOTIFY_ID`      INT  AUTO_INCREMENT
                                                  COMMENT '通知ID',
                                                `NOTIFY_CONTENT` VARCHAR(800) NOT NULL
                                                  COMMENT '通知信息',
                                                `NOTIFY_TIME`    TIMESTAMP    NOT NULL
                                                  COMMENT '通知时间',
                                                `NOTIFY_READ`    INTEGER      NOT NULL
                                                  COMMENT '是否阅读',
                                                `USER_ID`         INTEGER      NOT NULL
                                                  COMMENT '用户ID',
                                                PRIMARY KEY (`NOTIFY_ID`)
)
  COMMENT = '用户通知表';


##中国学校表
create table IF NOT EXISTS `SYS_UNIVERSITY_CHN`(
                                                 `UNIVERS_ID` INTEGER COMMENT '学校ID',
                                                 `UNIVERS_CHN` VARCHAR(100) NOT NULL COMMENT '中文名称',
                                                 `UNIVERS_ENG` VARCHAR(100) COMMENT '英文名称',
                                                 `UNIVERS_CODE` BIGINT COMMENT '学校标识码',
                                                 `UNIVERS_LOCATION` INTEGER COMMENT '学校所在地',
                                                 `UNIVERS_DEPT` VARCHAR(40)  COMMENT '主管部门',
                                                 `UNIVERS_LEVEL` VARCHAR(20)  COMMENT '办学层次',
                                                 `START_TIME` TIMESTAMP NOT NULL COMMENT '启用时间',
                                                 `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                 `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                 PRIMARY KEY (`UNIVERS_ID`)
)COMMENT = '中国学校表';


##美国学校表
create table IF NOT EXISTS `SYS_UNIVERSITY_USA`(
                                                 `UNIVERS_ID` INTEGER COMMENT '学校ID',
                                                 `UNIVERS_CHN` VARCHAR(100) COMMENT '中文名称',
                                                 `UNIVERS_ENG` VARCHAR(100) NOT NULL COMMENT '英文名称',
                                                 `UNIVERS_STATE` VARCHAR(10) COMMENT '学校所在州',
                                                 `UNIVERS_STATE_ID` INTEGER COMMENT '学校所在州ID',
                                                 `UNIVERS_CITY` VARCHAR(20) COMMENT '学校所在城市',
                                                 `UNIVERS_ZIPCODE` INTEGER COMMENT '学校ZIPCODE',
                                                 `START_TIME` TIMESTAMP NOT NULL COMMENT '启用时间',
                                                 `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                 `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                 PRIMARY KEY (`UNIVERS_ID`)
)COMMENT = '美国学校表';


##英国学校表
create table IF NOT EXISTS `SYS_UNIVERSITY_GBR`(
                                                 `UNIVERS_ID` INTEGER COMMENT '学校ID',
                                                 `UNIVERS_CHN` VARCHAR(100) COMMENT '中文名称',
                                                 `UNIVERS_ENG` VARCHAR(100) NOT NULL COMMENT '英文名称',
                                                 `UNIVERS_CITY` VARCHAR(20) COMMENT '学校所在城市',
                                                 `UNIVERS_ZIPCODE` VARCHAR(10) COMMENT '学校ZIPCODE',
                                                 `START_TIME` TIMESTAMP NOT NULL COMMENT '启用时间',
                                                 `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                 `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                 PRIMARY KEY (`UNIVERS_ID`)
)COMMENT = '英国学校表';


##加拿大学校表
create table IF NOT EXISTS `SYS_UNIVERSITY_CAN`(
                                                 `UNIVERS_ID` INTEGER COMMENT '学校ID',
                                                 `UNIVERS_CHN` VARCHAR(100) COMMENT '中文名称',
                                                 `UNIVERS_ENG` VARCHAR(100) NOT NULL COMMENT '英文名称',
                                                 `UNIVERS_STATE` VARCHAR(10) COMMENT '学校所在省',
                                                 `UNIVERS_CITY` VARCHAR(20) COMMENT '学校所在城市',
                                                 `UNIVERS_ZIPCODE` INTEGER COMMENT '学校ZIPCODE',
                                                 `START_TIME` TIMESTAMP NOT NULL COMMENT '启用时间',
                                                 `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                 `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                 PRIMARY KEY (`UNIVERS_ID`)
)COMMENT = '加拿大学校表';


##USNEWS学校排名表
create table IF NOT EXISTS `SYS_UNIVERSITY_RANK_USNEWS`(
                                                         `UNIVERS_ID` INTEGER COMMENT '学校ID',
                                                         `UNIVERS_CHN` VARCHAR(200)  COMMENT '中文名称',
                                                         `UNIVERS_ENG` VARCHAR(200) NOT NULL COMMENT '英文名称',
                                                         `UNIVERS_COUNTRY` VARCHAR(10) NOT NULL COMMENT '学校国别',
                                                         `UNIVERS_RANK` INTEGER COMMENT '学校排名',
                                                         `RANK_POINT` FLOAT COMMENT '排名评分',
                                                         `RANK_YEAR` INTEGER COMMENT '排名年份',
                                                         `RANK_LAST_YEAR` INTEGER COMMENT '上年排名',
                                                         PRIMARY KEY (`UNIVERS_ID`)
)COMMENT = 'USNEWS学校排名表';


##TIMES学校排名表
create table IF NOT EXISTS `SYS_UNIVERSITY_RANK_TIMES`(
                                                        `UNIVERS_ID` INTEGER COMMENT '学校ID',
                                                        `UNIVERS_CHN` VARCHAR(200)  COMMENT '中文名称',
                                                        `UNIVERS_ENG` VARCHAR(200) NOT NULL COMMENT '英文名称',
                                                        `UNIVERS_COUNTRY` VARCHAR(10) NOT NULL COMMENT '学校国别',
                                                        `UNIVERS_RANK` INTEGER COMMENT '学校排名',
                                                        `RANK_POINT` FLOAT COMMENT '排名评分',
                                                        `RANK_YEAR` INTEGER COMMENT '排名年份',
                                                        `RANK_LAST_YEAR` INTEGER COMMENT '上年排名',
                                                        PRIMARY KEY (`UNIVERS_ID`)
)COMMENT = 'TIMES学校排名表';


##QS学校排名表
create table IF NOT EXISTS `SYS_UNIVERSITY_RANK_QS`(
                                                     `UNIVERS_ID` INTEGER COMMENT '学校ID',
                                                     `UNIVERS_CHN` VARCHAR(200)  COMMENT '中文名称',
                                                     `UNIVERS_ENG` VARCHAR(200) NOT NULL COMMENT '英文名称',
                                                     `UNIVERS_COUNTRY` VARCHAR(10) NOT NULL COMMENT '学校国别',
                                                     `UNIVERS_RANK` INTEGER COMMENT '学校排名',
                                                     `RANK_POINT` FLOAT COMMENT '排名评分',
                                                     `RANK_YEAR` INTEGER COMMENT '排名年份',
                                                     `RANK_LAST_YEAR` INTEGER COMMENT '上年排名',
                                                     PRIMARY KEY (`UNIVERS_ID`)
)COMMENT = 'QS学校排名表';


##世界五百强
create table IF NOT EXISTS `SYS_TOP_COMPANY_WORLD`(
                                                    `COMPANY_ID` INTEGER COMMENT '公司ID',
                                                    `COMPANY_CHN` VARCHAR(200) COMMENT '中文名称',
                                                    `COMPANY_ENG` VARCHAR(200) COMMENT '英文名称',
                                                    `COMPANY_COUNTRY` VARCHAR(10) COMMENT '企业国别',
                                                    `COMPANY_RANK`INTEGER COMMENT '企业排名',
                                                    `RANK_YEAR`INTEGER COMMENT '排名年份',
                                                    `RANK_LAST_YEAR`INTEGER COMMENT '上年排名',
                                                    PRIMARY KEY (`COMPANY_ID`)
)COMMENT = '世界五百强';


##媒体文件名称表
create table IF NOT EXISTS `SYS_MEDIA_DOCUMENT`(
                                                 `DOCU_ID` INT AUTO_INCREMENT COMMENT '文件编号',
                                                 `DOCU_LOCAL_ID` VARCHAR(100) NOT NULL COMMENT '本地文件编号',
                                                 `DOCU_LOCAL_NAME` VARCHAR(100) NOT NULL COMMENT '文件名称',
                                                 `DOCU_LOCAL_FORMAT` VARCHAR(10) NOT NULL COMMENT '文件格式',
                                                 `DOCU_LOCAL_SIZE` VARCHAR(100) NOT NULL COMMENT '文件大小(MB)',
                                                 `DOCU_SERVER_ALI_ID` VARCHAR(100)  COMMENT '服务器1文件编号',
                                                 `DOCU_SERVER_AWS_ID` VARCHAR(100)  COMMENT '服务器2文件编号',
                                                 `CREATE_TIME` TIMESTAMP NOT NULL COMMENT '生成时间',
                                                 `UPLOAD_USER_ID` INTEGER NOT NULL COMMENT '上传人ID',
                                                 `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                 `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                 PRIMARY KEY (`DOCU_ID`)
)COMMENT = '媒体文件名称表';


#######################################视图###############################################
CREATE OR REPLACE VIEW `COUNTRY_LOCATION` AS
  SELECT
    A.`REGION_ID`   AS `REGION_ID`,
    A.`REGION_NUM`  AS `REGION_NUM`,
    A.`REGION_FULLCHN`  AS `REGION_NAME`,
    'CHN'  AS `COUNTRY` ,
    '中国'  AS `COUNTRY_CHN`
  FROM youthchina.`SYS_CHN_REGION` A
  UNION
  SELECT
    B.`REGION_ID`   AS `REGION_ID`,
    B.`ZIP_CODE`    AS `REGION_NUM`,
    B.`REGION_ENG`  AS `REGION_NAME`,
    'USA'  AS `COUNTRY`,
    '美国'  AS `COUNTRY_CHN`
  FROM youthchina.`SYS_USA_ZIPCODE` B;


CREATE OR REPLACE VIEW `COUNTRY_UNIVERSITY` AS
  SELECT
    A.`UNIVERS_ID`   AS `UNIVERS_ID`,
    A.`UNIVERS_CHN`  AS `UNIVERS_NAME`,
    'CHN'  AS `COUNTRY`
  FROM youthchina.`SYS_UNIVERSITY_CHN` A
  UNION
  SELECT
    A.`UNIVERS_ID`   AS `UNIVERS_ID`,
    A.`UNIVERS_ENG`  AS `UNIVERS_NAME`,
    'USA'  AS `COUNTRY`
  FROM youthchina.`SYS_UNIVERSITY_USA` A;




#######################################招聘模块数据表###############################################

##应聘者教育信息表
create table IF NOT EXISTS `STU_EDU_INFO` (
                                            `EDU_ID`              INT               AUTO_INCREMENT
                                              COMMENT '教育ID',
                                            `EDU_DEGREE`          INTEGER      NOT NULL
                                              COMMENT '学历编号',
                                            `EDU_SCHOOL_ID` INTEGER NOT NULL
                                              COMMENT '学校ID',
                                            `EDU_MAJOR`           VARCHAR(10)      NOT NULL
                                              COMMENT '专业',
                                            `EDU_COLLEGE`         VARCHAR(200) COMMENT '学院（选填）',
                                            `EDU_GPA`             FLOAT COMMENT 'GPA（选填）',
                                            `EDU_DIPLOMA_TYPE`    INTEGER COMMENT '学位类型（选填）',
                                            `EDU_START`           DATE         NOT NULL
                                              COMMENT '入学时间',
                                            `EDU_END`             DATE         NOT NULL
                                              COMMENT '毕业时间',
                                            `STU_ID`              INTEGER      NOT NULL
                                              COMMENT '应聘者ID',
                                            `IS_DELETE`           INTEGER      DEFAULT '0'
                                              COMMENT '是否删除',
                                            `IS_DELETE_TIME`      TIMESTAMP    NULL DEFAULT NULL
                                              COMMENT '删除时间',
                                            PRIMARY KEY (`EDU_ID`)
)
  COMMENT = '应聘者教育信息表';


create table IF NOT EXISTS `STU_SUB_INFO` (
                                            `SUB_ID`          INT            AUTO_INCREMENT
                                              COMMENT '附加信息ID',
                                            `SUB_COURSE`      VARCHAR(200) COMMENT '相关课程信息',
                                            `SUB_HONOR`       VARCHAR(200) COMMENT '相关荣誉',
                                            `SUB_AWARD`       VARCHAR(200) COMMENT '相关奖项',
                                            `SUB_SKILL`       VARCHAR(200) COMMENT '相关技能',
                                            `SUB_FOREIGN`     VARCHAR(200) COMMENT '外语语言',
                                            `SUB_INTEREST`    VARCHAR(200) COMMENT '兴趣特长',
                                            `SUB_INTRODUCTION`    VARCHAR(400) COMMENT '自我介绍',
                                            `STU_ID`          INTEGER   NOT NULL
                                              COMMENT '用户ID',
                                            `IS_DELETE`       INTEGER   DEFAULT '0'
                                              COMMENT '是否删除',
                                            `IS_DELETE_TIME`  TIMESTAMP NULL DEFAULT NULL
                                              COMMENT '删除时间',
                                            PRIMARY KEY (`SUB_ID`)
)
  COMMENT = '应聘者附加信息表';


##应聘者技能证书表
create table IF NOT EXISTS `STU_CERTIFICATE`(
                                              `CERTIFICATE_ID` INT AUTO_INCREMENT
                                                COMMENT '证书ID',
                                              `CERTIFICATE_NAME` VARCHAR(200) NOT NULL
                                                COMMENT '证书名称',
                                              `CERTIFICATE_INSTI` VARCHAR(200) NOT NULL
                                                COMMENT '证书颁发机构',
                                              `INSTI_COUNTRY` VARCHAR(10) NOT NULL
                                                COMMENT '机构国别',
                                              `CERTIFICATE_GRANT_DATE` DATE NOT NULL
                                                COMMENT '获得时间',
                                              `CERTIFICATE_EXPIR_DATE` DATE
                                                COMMENT '到期时间',
                                              `DOCU_LOCAL_ID` VARCHAR(100) DEFAULT NULL
                                                COMMENT '本地文件编号',
                                              `STU_ID` INTEGER NOT NULL
                                                COMMENT '用户ID',
                                              `IS_DELETE`       INTEGER   DEFAULT '0'
                                                COMMENT '是否删除',
                                              `IS_DELETE_TIME`  TIMESTAMP NULL DEFAULT NULL
                                                COMMENT '删除时间',
                                              PRIMARY KEY (`CERTIFICATE_ID`)
)
  COMMENT = '技能证书表';


##应聘者相关项目表
create table IF NOT EXISTS `STU_PROJECT` (
                                           `PROJ_ID`           INT               AUTO_INCREMENT
                                             COMMENT '项目ID',
                                           `PROJ_INSTITUTE`         VARCHAR(200)
                                             COMMENT '项目所属机构',
                                           `INSTI_COUNTRY`         VARCHAR(10)
                                             COMMENT '机构国别',
                                           `PROJ_NAME`         VARCHAR(200) NOT NULL
                                             COMMENT '项目名称',
                                           `PROJ_ROLE`         VARCHAR(200) NOT NULL
                                             COMMENT '担任角色',
                                           `PROJ_START_TIME`   DATE         NOT NULL
                                             COMMENT '项目开始时间',
                                           `PROJ_END_TIME`     DATE         NOT NULL
                                             COMMENT '项目结束时间',
                                           `PROJ_DELIVER`      VARCHAR(200)
                                             COMMENT '项目成果',
                                           `DELIVER_PUBLISH`   INTEGER
                                             COMMENT '成果是否发表',
                                           `DELIVER_PUB_INSTI` VARCHAR(200)
                                             COMMENT '发表机构',
                                           `STU_ID`            INTEGER      NOT NULL
                                             COMMENT '应聘者ID',
                                           `IS_DELETE`         INTEGER      DEFAULT '0'
                                             COMMENT '是否删除',
                                           `IS_DELETE_TIME`    TIMESTAMP    NULL DEFAULT NULL
                                             COMMENT '删除时间',
                                           PRIMARY KEY (`PROJ_ID`)
)
  COMMENT = '相关项目表';


##应聘者工作经验表
create table IF NOT EXISTS `STU_WORK` (
                                        `WORK_ID`         INT               AUTO_INCREMENT
                                          COMMENT '工作ID',
                                        `WORK_COMPANY`    VARCHAR(200) NOT NULL
                                          COMMENT '工作公司名称',
                                        `WORK_LOCATION`   INTEGER
                                          COMMENT '工作所在城市',
                                        `WORK_POSITION`   VARCHAR(200) NOT NULL
                                          COMMENT '工作职位',
                                        `WORK_SECTOR`     VARCHAR(200)
                                          COMMENT '所在部门（选填）',
                                        `WORK_START_TIME` DATE         NOT NULL
                                          COMMENT '开始时间',
                                        `WORK_END_TIME`   DATE         NOT NULL
                                          COMMENT '结束时间',
                                        `WORK_DUTY`       VARCHAR(200) NOT NULL
                                          COMMENT '工作内容',
                                        `WORK_NATURE`     INTEGER      NOT NULL
                                          COMMENT '工作性质',
                                        `STU_ID`          INTEGER      NOT NULL
                                          COMMENT '应聘者ID',
                                        `IS_DELETE`       INTEGER      DEFAULT '0'
                                          COMMENT '是否删除',
                                        `IS_DELETE_TIME`  TIMESTAMP    NULL DEFAULT NULL
                                          COMMENT '删除时间',
                                        PRIMARY KEY (`WORK_ID`)
)
  COMMENT = '工作经验表';


##应聘者组织活动表
create table IF NOT EXISTS `STU_ACTIVITY` (
                                            `ACT_ID`           INT               AUTO_INCREMENT
                                              COMMENT '活动ID',
                                            `ACT_NAME`         VARCHAR(200) NOT NULL
                                              COMMENT '活动名称',
                                            `ACT_ORGANIZATION` VARCHAR(200)
                                              COMMENT '组织活动单位',
                                            `ORG_COUNTRY`    VARCHAR(10)
                                              COMMENT '单位国别',
                                            `ACT_ROLE`         VARCHAR(200) NOT NULL
                                              COMMENT '担任角色',
                                            `ACT_START_TIME`   DATE         NOT NULL
                                              COMMENT '开始时间',
                                            `ACT_END_TIME`     DATE         NOT NULL
                                              COMMENT '结束时间',
                                            `ACT_DETAIL`       VARCHAR(200) NOT NULL
                                              COMMENT '活动内容',
                                            `STU_ID`           INTEGER      NOT NULL
                                              COMMENT '应聘者ID',
                                            `IS_DELETE`        INTEGER      DEFAULT '0'
                                              COMMENT '是否删除',
                                            `IS_DELETE_TIME`   TIMESTAMP    NULL DEFAULT NULL
                                              COMMENT '删除时间',
                                            PRIMARY KEY (`ACT_ID`)
)
  COMMENT = '组织活动表';


create table IF NOT EXISTS `STU_JOB_COLLECT` (
                                               `COLLECT_ID`     INT            AUTO_INCREMENT
                                                 COMMENT '收藏ID',
                                               `JOB_ID`         INTEGER   NOT NULL
                                                 COMMENT '职位ID',
                                               `JOB_COLL_TIME`  TIMESTAMP     NOT NULL
                                                 COMMENT '职位收藏时间',
                                               `STU_ID`         INTEGER   NOT NULL
                                                 COMMENT '用户ID',
                                               `IS_DELETE`      INTEGER   DEFAULT '0'
                                                 COMMENT '是否删除',
                                               `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL
                                                 COMMENT '删除时间',
                                               PRIMARY KEY (`COLLECT_ID`)
)
  COMMENT = '职位信息收藏表';


create table IF NOT EXISTS `STU_COMP_COLLECT` (
                                                `COLLECT_ID`        INT   AUTO_INCREMENT
                                                  COMMENT '收藏ID',
                                                `COMPANY_ID`        INTEGER   NOT NULL
                                                  COMMENT '企业ID',
                                                `COMPANY_COLL_TIME` TIMESTAMP      NOT NULL
                                                  COMMENT '企业收藏时间',
                                                `STU_ID`            INTEGER   NOT NULL
                                                  COMMENT '用户ID',
                                                `IS_DELETE`         INTEGER   DEFAULT '0'
                                                  COMMENT '是否删除',
                                                `IS_DELETE_TIME`    TIMESTAMP NULL DEFAULT NULL
                                                  COMMENT '删除时间',
                                                PRIMARY KEY (`COLLECT_ID`)
)
  COMMENT = '企业信息收藏表';


create table IF NOT EXISTS `STU_ADVAN_LABEL` (
                                               `LABEL_ID`       INT    AUTO_INCREMENT
                                                 COMMENT '标签ID',
                                               `LABEL_CODE`      VARCHAR(20)   NOT NULL
                                                 COMMENT '优势标签',
                                               `STU_ID`         INTEGER   NOT NULL
                                                 COMMENT '用户ID',
                                               `IS_DELETE`      INTEGER   DEFAULT '0'
                                                 COMMENT '是否删除',
                                               `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL
                                                 COMMENT '删除时间',
                                               PRIMARY KEY (`LABEL_ID`)
)
  COMMENT = '应聘者优势标签表';


##应聘者意向城市表
create table IF NOT EXISTS `STU_PREFER_CITY` (
                                               `PRE_CITY_ID`    INT    AUTO_INCREMENT
                                                 COMMENT '意向ID',
                                               `PRE_REGION_NUM` INTEGER   NOT NULL
                                                 COMMENT '意向城市',
                                               `STU_ID`         INTEGER   NOT NULL
                                                 COMMENT '用户ID',
                                               `IS_DELETE`      INTEGER   DEFAULT '0'
                                                 COMMENT '是否删除',
                                               `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL
                                                 COMMENT '删除时间',
                                               PRIMARY KEY (`PRE_CITY_ID`)
)
  COMMENT = '应聘者意向城市表';


create table IF NOT EXISTS `STU_PREFER_INDUSTRY` (
                                                   `PRE_IND_ID`     INT    AUTO_INCREMENT
                                                     COMMENT '意向ID',
                                                   `PRE_IND_CODE`    VARCHAR(10)   NOT NULL
                                                     COMMENT '意向行业',
                                                   `STU_ID`         INTEGER   NOT NULL
                                                     COMMENT '用户ID',
                                                   `IS_DELETE`      INTEGER   DEFAULT '0'
                                                     COMMENT '是否删除',
                                                   `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL
                                                     COMMENT '删除时间',
                                                   PRIMARY KEY (`PRE_IND_ID`)
)
  COMMENT = '应聘者意向行业表';


##应聘者意向职位表
create table IF NOT EXISTS `STU_PREFER_PROF` (
                                               `PRE_PROF_ID`    INT    AUTO_INCREMENT
                                                 COMMENT '意向ID',
                                               `PRE_PROF_CODE`   VARCHAR(20)   NOT NULL
                                                 COMMENT '意向职位代码',
                                               `PRE_AVAIL_TIME` DATE      NOT NULL
                                                 COMMENT '预计到岗时间',
                                               `STU_ID`         INTEGER   NOT NULL
                                                 COMMENT '用户ID',
                                               `IS_DELETE`      INTEGER   DEFAULT '0'
                                                 COMMENT '是否删除',
                                               `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL
                                                 COMMENT '删除时间',
                                               PRIMARY KEY (`PRE_PROF_ID`)
)
  COMMENT = '应聘者意向职位表';


##应聘者意向工作类型表
create table IF NOT EXISTS `STU_PREFER_JOB_TYPE` (
                                                   `PRE_TYPE_ID`    INT    AUTO_INCREMENT
                                                     COMMENT '意向ID',
                                                   `JOB_TYPE`  INTEGER   NOT NULL
                                                     COMMENT '职位性质',
                                                   `PRE_PROF_ID` INTEGER    NOT NULL
                                                     COMMENT '意向职位ID',
                                                   `STU_ID`         INTEGER   NOT NULL
                                                     COMMENT '用户ID',
                                                   `IS_DELETE`      INTEGER   DEFAULT '0'
                                                     COMMENT '是否删除',
                                                   `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL
                                                     COMMENT '删除时间',
                                                   PRIMARY KEY (`PRE_TYPE_ID`)
)
  COMMENT = '应聘者意向工作类型表';


##应聘者意向薪资表
create table IF NOT EXISTS `STU_PREFER_SALARY` (
                                                 `PRE_SALA_ID`    INT    AUTO_INCREMENT
                                                   COMMENT '薪资ID',
                                                 `PRE_SALA_FLOOR` INTEGER
                                                   COMMENT '意向薪资下限',
                                                 `PRE_SALA_CAP`   INTEGER
                                                   COMMENT '意向薪资上限',
                                                 `PRE_PROF_ID` INTEGER NOT NULL
                                                   COMMENT '意向职位ID',
                                                 `STU_ID`         INTEGER   NOT NULL
                                                   COMMENT '用户ID',
                                                 `IS_DELETE`      INTEGER   DEFAULT '0'
                                                   COMMENT '是否删除',
                                                 `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL
                                                   COMMENT '删除时间',
                                                 PRIMARY KEY (`PRE_SALA_ID`)
)
  COMMENT = '应聘者意向薪资表';


create table IF NOT EXISTS `STU_INTRODUCTION_VIDEO` (
                                                      `INTRO_VIDEO_ID`   INT    AUTO_INCREMENT
                                                        COMMENT '视频ID',
                                                      `INTRO_VIDEO_PATH` VARCHAR(400)   NOT NULL
                                                        COMMENT '视频存储路径',
                                                      `STU_ID`           INTEGER   NOT NULL
                                                        COMMENT '用户ID',
                                                      `IS_DELETE`        INTEGER   DEFAULT '0'
                                                        COMMENT '是否删除',
                                                      `IS_DELETE_TIME`   TIMESTAMP NULL DEFAULT NULL
                                                        COMMENT '删除时间',
                                                      PRIMARY KEY (`INTRO_VIDEO_ID`)
)
  COMMENT = '应聘者介绍视频表';


create table IF NOT EXISTS `STU_RESUME_TEMPLATE` (
                                                   `TEMPLATE_ID`    INT     AUTO_INCREMENT
                                                     COMMENT '模板ID',
                                                   `TEMPLATE_PATH`  VARCHAR(400) NOT NULL
                                                     COMMENT '模板存储路径',
                                                   `STU_ID`         INTEGER      NOT NULL
                                                     COMMENT '用户ID',
                                                   `TEMPLATE_TIME`  TIMESTAMP    NOT NULL
                                                     COMMENT '模板生成时间',
                                                   `IS_DELETE`      INTEGER      DEFAULT '0'
                                                     COMMENT '是否删除',
                                                   `IS_DELETE_TIME` TIMESTAMP    NULL DEFAULT NULL
                                                     COMMENT '删除时间',
                                                   PRIMARY KEY (`TEMPLATE_ID`)
)
  COMMENT = '应聘者简历模板表';


/*##简历存储表
create table IF NOT EXISTS `STU_RESUME`(
`RESUME_ID` INT AUTO_INCREMENT COMMENT '简历ID',
`RESUME_URL` VARCHAR(200) NOT NULL COMMENT '简历上传地址',
`RESUME_UPLOAD_TIME` TIMESTAMP NOT NULL COMMENT '简历上传时间',
`USER_ID` INTEGER NOT NULL COMMENT '用户ID',
`IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
`IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
PRIMARY KEY (`RESUME_ID`)
)COMMENT = '简历存储表';


##应聘者简历上传表
create table IF NOT EXISTS `STU_RESUME_UPLOAD`(
`RESUME_ID` INT AUTO_INCREMENT COMMENT '简历ID',
`RESUME_URL` VARCHAR(200) NOT NULL COMMENT '简历上传地址',
`RESUME_UPLOAD_TIME` TIMESTAMP NOT NULL COMMENT '简历上传时间',
`USER_ID` INTEGER COMMENT '用户ID',
`IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
`IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
PRIMARY KEY (`RESUME_ID`)
)COMMENT = '应聘者简历上传表';


##应聘者简历生成表
create table IF NOT EXISTS `STU_RESUME_CREATE`(
`RESUME_ID` INT AUTO_INCREMENT COMMENT '简历ID',
`USER_ID` INTEGER NOT NULL COMMENT '用户ID',
`TEMPLATE_TIME` TIMESTAMP NOT NULL COMMENT '生成时间',
`IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
`IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
PRIMARY KEY (`RESUME_ID`)
)COMMENT = '应聘者简历生成表';*/


##应聘者简历JSON表
create table IF NOT EXISTS `STU_RESUME_JSON`(
                                              `RESUME_ID` INT AUTO_INCREMENT COMMENT '简历ID',
                                              `JSON_COUNT` INTEGER NOT NULL COMMENT 'json数量',
                                              `JSON_1` VARCHAR(10000) COMMENT 'json1',
                                              `JSON_2` VARCHAR(500) COMMENT 'json2',
                                              `JSON_3` VARCHAR(500) COMMENT 'json3',
                                              `JSON_4` VARCHAR(500) COMMENT 'json4',
                                              `JSON_5` VARCHAR(500) COMMENT 'json5',
                                              `JSON_6` VARCHAR(500) COMMENT 'json6',
                                              `JSON_7` VARCHAR(500) COMMENT 'json7',
                                              `JSON_8` VARCHAR(500) COMMENT 'json8',
                                              `JSON_9` VARCHAR(500) COMMENT 'json9',
                                              `JSON_10` VARCHAR(500)  COMMENT 'json10',
                                              `USER_ID` INTEGER   NOT NULL  COMMENT '用户ID',
                                              `CREATE_TIME` TIMESTAMP NOT NULL COMMENT '生成时间',
                                              `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                              `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                              PRIMARY KEY (`RESUME_ID`)
)COMMENT = '应聘者简历JSON表';



##LOGO表
create table IF NOT EXISTS `COMPANY_JOB_LOGO`(
                                               `LOGO_ID` INT AUTO_INCREMENT
                                                 COMMENT 'LOGO-ID',
                                               `DOCU_LOCAL_ID` VARCHAR(200) NOT NULL
                                                 COMMENT '本地文件编号',
                                               `RELA_TYPE` INTEGER NOT NULL
                                                 COMMENT '关联类型',
                                               `RELA_ID` INTEGER NOT NULL
                                                 COMMENT '关联ID',
                                               `IS_DELETE`          INTEGER      DEFAULT '0'
                                                 COMMENT '是否删除',
                                               `IS_DELETE_TIME`     TIMESTAMP    NULL DEFAULT NULL
                                                 COMMENT '删除时间',
                                               PRIMARY KEY ( `LOGO_ID` )
)COMMENT = 'LOGO表';


##企业基本信息表
create table IF NOT EXISTS `COMPANY_INFO` (
                                            `COMPANY_ID`         INT               AUTO_INCREMENT
                                              COMMENT '企业ID',
                                            `COMPANY_NAME`       VARCHAR(200) NOT NULL
                                              COMMENT '企业名称',
                                            `COMPANY_CODE`       VARCHAR(200)
                                              COMMENT '企业三证号码',
                                            `COMPANY_COUNTRY`    VARCHAR(10)  NOT NULL
                                              COMMENT '企业国别',
                                            `COMPANY_INTRODUC`   VARCHAR(1000)
                                              COMMENT '企业简介',
                                            `COMPANY_SCALE_NUM`  INTEGER      NOT NULL
                                              COMMENT '企业规模',
                                            `COMPANY_NATURE`     INTEGER NOT NULL
                                              COMMENT '企业性质',
                                            `COMPANY_LOCATION`   VARCHAR(10) NOT NULL
                                              COMMENT '企业所在地',
                                            `COMPANY_MAIL`       VARCHAR(200)
                                              COMMENT '企业邮箱',
                                            `COMPANY_WEBSITE`    VARCHAR(200)
                                              COMMENT '企业官网',
                                            `COMPANY_START_DATE` DATE
                                              COMMENT '成立日期',
                                            `COMPANY_VERIFY`     INTEGER      NOT NULL
                                              COMMENT '企业认证',
                                            `USER_ID`            INTEGER      NOT NULL
                                              COMMENT '录入人ID',
                                            `ADD_TIME`           TIMESTAMP      NOT NULL
                                              COMMENT '录入时间',
                                            `IS_DELETE`          INTEGER      DEFAULT '0'
                                              COMMENT '是否删除',
                                            `IS_DELETE_TIME`     TIMESTAMP    NULL DEFAULT NULL
                                              COMMENT '删除时间',
                                            PRIMARY KEY (`COMPANY_ID`)
)
  COMMENT = '企业基本信息表';


create table IF NOT EXISTS `COMPANY_INDUSTRY` (
                                                `COMPANY_INDUS_ID` INT            AUTO_INCREMENT
                                                  COMMENT '企业所属行业ID',
                                                `COMPANY_IND_CODE`          VARCHAR(10)   NOT NULL
                                                  COMMENT '行业ID',
                                                `COMPANY_ID`       INTEGER   NOT NULL
                                                  COMMENT '企业ID',
                                                `IS_DELETE`        INTEGER   DEFAULT '0'
                                                  COMMENT '是否删除',
                                                `IS_DELETE_TIME`   TIMESTAMP NULL DEFAULT NULL
                                                  COMMENT '删除时间',
                                                PRIMARY KEY (`COMPANY_INDUS_ID`)
)
  COMMENT = '企业所属行业表';


create table IF NOT EXISTS `COMPANY_VERIFICATION` (
                                                    `VERIFY_ID`       INT            AUTO_INCREMENT
                                                      COMMENT '认证ID',
                                                    `VERIFY_TIME`     DATE      NOT NULL
                                                      COMMENT '认证时间',
                                                    `VERIFY_END_TIME` DATE      NOT NULL
                                                      COMMENT '到期时间',
                                                    `OPER_USER_ID`    INTEGER   NOT NULL
                                                      COMMENT '操作人',
                                                    `COMPANY_ID`      INTEGER   NOT NULL
                                                      COMMENT '企业ID',
                                                    `IS_DELETE`       INTEGER   DEFAULT '0'
                                                      COMMENT '是否删除',
                                                    `IS_DELETE_TIME`  TIMESTAMP NULL DEFAULT NULL
                                                      COMMENT '删除时间',
                                                    PRIMARY KEY (`VERIFY_ID`)
)
  COMMENT = '企业认证表';


/*##企业评价表
create table IF NOT EXISTS `COMPANY_EVALUATE`(
`EVALU_ID` INT AUTO_INCREMENT COMMENT '评价ID',
`USER_ID` INTEGER NOT NULL COMMENT '评价用户',
`EVALU_CONTENT` VARCHAR(2000) NOT NULL COMMENT '评价内容',
`EVALU_TIME` TIMESTAMP NOT NULL COMMENT '评价时间',
`COMPANY_ID` INTEGER NOT NULL COMMENT '企业ID',
`IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
`IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
PRIMARY KEY (`EVALU_ID`)
)
COMMENT = '企业评价表';*/



##企业相册表
create table IF NOT EXISTS `COMPANY_PHOTO`(
                                            `PHOTO_ID` INT AUTO_INCREMENT  COMMENT '主键ID',
                                            `DOCU_LOCAL_ID` VARCHAR(200) NOT NULL COMMENT '图片文件ID',
                                            `PHOTO_UPLOAD_TIME` TIMESTAMP NOT NULL COMMENT '上传时间',
                                            `COMPANY_ID` INTEGER NOT NULL COMMENT '企业ID',
                                            `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                            `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                            PRIMARY KEY (`PHOTO_ID`)
)
  COMMENT = '企业相册表';


##企业在职人员表
create table IF NOT EXISTS `COMPANY_EMPLOYEE`(
                                               `EMPLOYEE_ID` INT AUTO_INCREMENT COMMENT '雇员ID',
                                               `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                               `COMPANY_ID` INTEGER NOT NULL COMMENT '企业ID',
                                               `WORK_START_TIME` DATE NOT NULL COMMENT '开始时间',
                                               `WORK_END_TIME` DATE NOT NULL COMMENT '结束时间',
                                               `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                               `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                               PRIMARY KEY (`EMPLOYEE_ID`)
)
  COMMENT = '企业在职人员表';


create table IF NOT EXISTS `JOB_INFO` (
                                        `JOB_ID`          INT               AUTO_INCREMENT
                                          COMMENT '职位ID',
                                        `JOB_NAME`        VARCHAR(200) NOT NULL
                                          COMMENT '职位名称',
                                        `JOB_PROF_CODE`    VARCHAR(20)      NOT NULL
                                          COMMENT '职位类别编号',
                                        `JOB_START_TIME`  DATE         NOT NULL
                                          COMMENT '职位起始时间',
                                        `JOB_END_TIME`    DATE         NOT NULL
                                          COMMENT '职位截止时间',
                                        `JOB_TYPE`        INTEGER      NOT NULL
                                          COMMENT '职位性质',
                                        `JOB_DESCRIPTION` VARCHAR(400) NOT NULL
                                          COMMENT '职位描述',
                                        `JOB_DUTY`        VARCHAR(2000) COMMENT '职责描述',
                                        `JOB_HIGHLIGHT`   VARCHAR(200) COMMENT '职位亮点',
                                        `JOB_SALARY_FLOOR`    INTEGER COMMENT '职位薪资下限',
                                        `JOB_SALARY_CAP`      INTEGER COMMENT '职位薪资上限',
                                        `JOB_LINK`      VARCHAR(500) COMMENT '职位链接',
                                        `CV_RECEI_MAIL`   VARCHAR(200) NOT NULL
                                          COMMENT '简历接收邮箱',
                                        `CV_NAME_RULE`    VARCHAR(200) COMMENT '简历命名规则',
                                        `JOB_ACTIVE`      INTEGER      NOT NULL
                                          COMMENT '职位状态',
                                        `USER_ID`           INTEGER      NOT NULL
                                          COMMENT '用户ID',
                                        `COMPANY_ID`      INTEGER      NOT NULL
                                          COMMENT '企业ID',
                                        `ADD_TIME`           TIMESTAMP      NOT NULL
                                          COMMENT '录入时间',
                                        `IS_DELETE`       INTEGER      DEFAULT '0'
                                          COMMENT '是否删除',
                                        `IS_DELETE_TIME`  TIMESTAMP    NULL DEFAULT NULL
                                          COMMENT '删除时间',
                                        PRIMARY KEY (`JOB_ID`)
)
  COMMENT = '职位基本信息表';


##职位学历要求表
create table IF NOT EXISTS `JOB_DEGREE_REQUIRE`(
                                                 `JOB_REQUIRE_ID` INT AUTO_INCREMENT COMMENT '主键ID',
                                                 `JOB_DEGREE_NUM` INTEGER NOT NULL COMMENT '学历编号',
                                                 `JOB_ID` INTEGER NOT NULL COMMENT '职位ID',
                                                 `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                 `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                 PRIMARY KEY (`JOB_REQUIRE_ID`)
)COMMENT = '职位学历要求表';


create table IF NOT EXISTS `JOB_LOCATION`(
                                           `JOB_REGION_ID` INT AUTO_INCREMENT COMMENT '地点ID',
                                           `JOB_COUNTRY` VARCHAR(10) NOT NULL COMMENT '职位工作国别',
                                           `JOB_REGION_NUM` VARCHAR(10) NOT NULL COMMENT '工作城市',
                                           `JOB_ID` INTEGER NOT NULL COMMENT '职位ID',
                                           `IS_DELETE` INTEGER NOT NULL COMMENT '是否删除',
                                           `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                           PRIMARY KEY (`JOB_REGION_ID`)
)COMMENT = '职位工作地点表';


create table IF NOT EXISTS `JOB_INDUSTRY` (
                                            `JOB_IND_ID`     INT            AUTO_INCREMENT
                                              COMMENT '行业ID',
                                            `JOB_IND_CODE`    VARCHAR(10)   NOT NULL
                                              COMMENT '行业编号',
                                            `JOB_ID`         INTEGER   NOT NULL
                                              COMMENT '职位ID',
                                            `IS_DELETE`      INTEGER   DEFAULT '0'
                                              COMMENT '是否删除',
                                            `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL
                                              COMMENT '删除时间',
                                            PRIMARY KEY (`JOB_IND_ID`)
)COMMENT = '职位行业表';


create table IF NOT EXISTS `HR_VIDEO_INVITATION` (
                                                   `INVITE_ID`     INT AUTO_INCREMENT
                                                     COMMENT '邀请ID',
                                                   `INVITE_TIME`   TIMESTAMP NOT NULL
                                                     COMMENT '邀请时间',
                                                   `INVITED_USER_ID` INTEGER   NOT NULL
                                                     COMMENT '受邀人ID',
                                                   `INVITE_USER_ID`  INTEGER   NOT NULL
                                                     COMMENT '邀请人ID',
                                                   PRIMARY KEY (`INVITE_ID`)
)
  COMMENT = '视频邀请表';


create table IF NOT EXISTS `STU_JOB_APPLY` (
                                             `APPLY_ID`         INT AUTO_INCREMENT
                                               COMMENT '申请ID',
                                             `STU_ID`           INTEGER      NOT NULL
                                               COMMENT '用户ID',
                                             `JOB_ID`           INTEGER      NOT NULL
                                               COMMENT '职位ID',
                                             `JOB_CV_SEND`      INTEGER    NOT NULL
                                               COMMENT '简历是否发送',
                                             `JOB_APPLY_TIME`   TIMESTAMP      NOT NULL
                                               COMMENT '职位申请时间',
                                             `JOB_APPLY_STATUS` VARCHAR(200) NOT NULL
                                               COMMENT '职位申请状态',
                                             PRIMARY KEY (`APPLY_ID`)
)
  COMMENT = '职位申请记录表';



#######################################社区数据表###############################################
##标签映射表
create table IF NOT EXISTS `COM_LABEL_MAP`(
                                            `LAB_ID` INT AUTO_INCREMENT COMMENT '主键ID',
                                            `LAB_NUM` INT NOT NULL COMMENT '标签编号',
                                            `TARGET_TYPE` INTEGER NOT NULL COMMENT '目标类型',
                                            `TARGET_ID` INTEGER NOT NULL COMMENT '目标ID',
                                            `RELA_TIME` TIMESTAMP NOT NULL COMMENT '关联时间',
                                            PRIMARY KEY ( `LAB_ID`)
)COMMENT = '标签映射表';


##标签分类表
create table IF NOT EXISTS `SYS_COM_LABEL_CLASS`(
                                                  `LAB_NUM` INT AUTO_INCREMENT COMMENT '标签ID',
                                                  `LAB_CHN` VARCHAR(20) NOT NULL COMMENT '中文描述',
                                                  `LAB_ENG` VARCHAR(20) NOT NULL COMMENT '英文描述',
                                                  `START_STATUS` INTEGER NOT NULL COMMENT '是否启用',
                                                  `START_DATE` TIMESTAMP NOT NULL COMMENT '启用时间',
                                                  PRIMARY KEY ( `LAB_NUM` )
)COMMENT = '标签分类表';


##问题表
create table IF NOT EXISTS `COM_QUESTION`(
                                           `QUES_ID` INT AUTO_INCREMENT COMMENT '问题ID',
                                           `QUES_TITLE` VARCHAR(200) NOT NULL COMMENT '问题题目',
                                           `QUES_ABBRE` VARCHAR(200) NOT NULL COMMENT '问题简述',
                                           `QUES_BODY` INTEGER NOT NULL COMMENT '问题正文',
                                           `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                           `USER_ANONY` INTEGER NOT NULL DEFAULT '0' COMMENT '是否匿名',
                                           `QUES_PUB_TIME` TIMESTAMP NOT NULL COMMENT '发布时间',
                                           `QUES_EDIT_TIME` TIMESTAMP NOT NULL COMMENT '编辑时间',
                                           `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                           `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                           `RELA_TYPE` INTEGER NOT NULL COMMENT '关联类型',
                                           `RELA_ID` INTEGER DEFAULT '0' COMMENT '关联ID',
                                           PRIMARY KEY ( `QUES_ID` )
)COMMENT = '问题表';


##文章表
create table IF NOT EXISTS `COM_ESSAY`(
                                        `ESSAY_ID` INT AUTO_INCREMENT COMMENT '文章ID',
                                        `ESSAY_TITLE` VARCHAR(200) NOT NULL COMMENT '文章题目',
                                        `ESSAY_ABBRE` VARCHAR(200) NOT NULL COMMENT '文章简述',
                                        `ESSAY_BODY` INTEGER NOT NULL COMMENT '文章正文',
                                        `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                        `USER_ANONY` INTEGER NOT NULL DEFAULT '0' COMMENT '是否匿名',
                                        `ESSAY_PUB_TIME` TIMESTAMP NOT NULL COMMENT '发布时间',
                                        `ESSAY_EDIT_TIME` TIMESTAMP NOT NULL COMMENT '编辑时间',
                                        `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                        `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                        `RELA_TYPE` INTEGER NOT NULL COMMENT '关联类型',
                                        `RELA_ID` INTEGER DEFAULT '0' COMMENT '关联ID',
                                        PRIMARY KEY ( `ESSAY_ID` )
)COMMENT = '文章表';


##短评表
create table IF NOT EXISTS `COM_BRIEF_REVIEW`(
                                               `REVIEW_ID` INT AUTO_INCREMENT COMMENT '短评ID',
                                               `REVIEW_TIME` TIMESTAMP NOT NULL COMMENT '短评时间',
                                               `REVIEW_BODY` INTEGER NOT NULL COMMENT '短评内容',
                                               `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                               `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                               `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                               `RELA_TYPE` INTEGER NOT NULL COMMENT '关联类型',
                                               `RELA_ID` INTEGER DEFAULT '0' COMMENT '关联ID',
                                               PRIMARY KEY (`REVIEW_ID`)
)COMMENT = '短评表';


##视频表
create table IF NOT EXISTS `COM_VIDEO`(
                                        `VIDEO_ID` INT AUTO_INCREMENT COMMENT '视频ID',
                                        `VIDEO_TITLE` VARCHAR(200) NOT NULL COMMENT '视频标题',
                                        `VIDEO_NAME` VARCHAR(200) NOT NULL COMMENT '视频文件名',
                                        `VIDEO_DECRIPTION` VARCHAR(2000) NOT NULL COMMENT '视频描述',
                                        `VIDEO_VIEW_COUNT` INTEGER NOT NULL COMMENT '观看次数',
                                        `VIDEO_UPLOAD_TIME` TIMESTAMP NOT NULL COMMENT '上传时间',
                                        `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                        `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                        `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                        `RELA_TYPE` INTEGER NOT NULL COMMENT '关联类型',
                                        `RELA_ID` INTEGER DEFAULT '0' COMMENT '关联ID',
                                        PRIMARY KEY (`VIDEO_ID`)
)COMMENT = '视频表';


##关注表
create table IF NOT EXISTS `COM_ATTENTION`(
                                            `ATTEN_ID` INT AUTO_INCREMENT COMMENT '关注ID',
                                            `TARGET_TYPE` INTEGER NOT NULL COMMENT '目标类型',
                                            `TARGET_ID` INTEGER NOT NULL COMMENT '目标ID',
                                            `USER_ID` INTEGER NOT NULL COMMENT '关注用户',
                                            `ATTEN_TIME` TIMESTAMP NOT NULL COMMENT '关注时间',
                                            `ATTEN_CANCEL` INTEGER NOT NULL COMMENT '取消关注',
                                            `ATTEN_CANCEL_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '取消关注时间',
                                            PRIMARY KEY ( `ATTEN_ID` )
)COMMENT = '关注表';


##回复邀请表
create table IF NOT EXISTS `COM_REPLY_INVITATION`(
                                                   `INVIT_ID` INT AUTO_INCREMENT COMMENT '邀请ID',
                                                   `TARGET_TYPE` INTEGER NOT NULL COMMENT '目标类型',
                                                   `TARGET_ID` INTEGER NOT NULL COMMENT '目标ID',
                                                   `INVIT_USER_ID` INTEGER NOT NULL COMMENT '邀请人ID',
                                                   `INVIT_TIME` TIMESTAMP NOT NULL COMMENT '邀请时间',
                                                   `INVITED_USER_ID` INTEGER NOT NULL COMMENT '被邀请人ID',
                                                   `INVIT_ACCEPT` INTEGER DEFAULT '0' COMMENT '是否接受',
                                                   PRIMARY KEY ( `INVIT_ID` )
)COMMENT = '回复邀请表';


##回答表
create table IF NOT EXISTS `COM_ANSWER`(
                                         `ANSWER_ID` INT AUTO_INCREMENT COMMENT '回答ID',
                                         `ANSWER_BODY` INTEGER NOT NULL COMMENT '回答内容',
                                         `TARGET_TYPE` INTEGER NOT NULL COMMENT '目标类型',
                                         `TARGET_ID` INTEGER NOT NULL COMMENT '目标ID',
                                         `USER_ID` INTEGER NOT NULL COMMENT '回答作者',
                                         `USER_ANONY` INTEGER NOT NULL DEFAULT '0' COMMENT '是否匿名',
                                         `ANSWER_PUB_TIME` TIMESTAMP NOT NULL COMMENT '发布时间',
                                         `ANSWER_EDIT_TIME` TIMESTAMP NOT NULL COMMENT '编辑时间',
                                         `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                         `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                         PRIMARY KEY ( `ANSWER_ID` )
)COMMENT = '回答表';


##评价表
create table IF NOT EXISTS `COM_EVALUATE`(
                                           `EVALU_ID` INT AUTO_INCREMENT COMMENT '评价ID',
                                           `TARGET_TYPE` INTEGER NOT NULL COMMENT '目标类型',
                                           `TARGET_ID` INTEGER NOT NULL COMMENT '目标ID',
                                           `USER_ID` INTEGER NOT NULL COMMENT '评价用户',
                                           `EVALU_TYPE` INTEGER NOT NULL COMMENT '评价类别',
                                           `EVALU_TIME` TIMESTAMP NOT NULL COMMENT '评价时间',
                                           PRIMARY KEY ( `EVALU_ID` )
)COMMENT = '评价表';


##评论表
create table IF NOT EXISTS `COM_COMMENT`(
                                          `COMMENT_ID` INT AUTO_INCREMENT COMMENT '评论ID',
                                          `TARGET_TYPE` INTEGER NOT NULL COMMENT '目标类型',
                                          `TARGET_ID` INTEGER NOT NULL COMMENT '目标ID',
                                          `COMMENT_CONTENT` VARCHAR(5000) NOT NULL COMMENT '评论内容',
                                          `USER_ID` INTEGER NOT NULL COMMENT '评论作者',
                                          `USER_ANONY` INTEGER NOT NULL DEFAULT '0' COMMENT '是否匿名',
                                          `COMMENT_PUB_TIME` TIMESTAMP NOT NULL COMMENT '发布时间',
                                          `COMMENT_EDIT_TIME` TIMESTAMP NOT NULL COMMENT '编辑时间',
                                          `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                          `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                          PRIMARY KEY ( `COMMENT_ID` )
)COMMENT = '评论表';


##讨论表
create table IF NOT EXISTS `COM_DISCUSS`(
                                          `DISCUSS_ID` INT AUTO_INCREMENT COMMENT '讨论ID',
                                          `COMMENT_ID` INTEGER NOT NULL COMMENT '回复ID',
                                          `DISCUSS_CONTENT` VARCHAR(500) NOT NULL COMMENT '讨论内容',
                                          `USER_ID` INTEGER NOT NULL COMMENT '讨论作者',
                                          `USER_ANONY` INTEGER NOT NULL DEFAULT '0' COMMENT '是否匿名',
                                          `DISCUSS_PUB_TIME` TIMESTAMP NOT NULL COMMENT '发布时间',
                                          `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                          `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                          PRIMARY KEY ( `DISCUSS_ID` )
)COMMENT = '讨论表';


##富文本表
create table IF NOT EXISTS `COM_RICH_TEXT`(
                                            `TEXT_ID` INT AUTO_INCREMENT COMMENT '富文本ID',
                                            `COMPILE_TYPE` INTEGER NOT NULL COMMENT '解析类型',
                                            `JSON_CONTENT` TEXT(15000) NOT NULL COMMENT 'JSON字符串',
                                            `TEXT_CONTENT` TEXT(15000) NOT NULL COMMENT '文本字符串',
                                            PRIMARY KEY (`TEXT_ID`)
)COMMENT = '富文本表';


##好友申请记录表
create table IF NOT EXISTS `COM_FRIEND_APPLY`(
                                               `APPLY_ID` INT AUTO_INCREMENT COMMENT '申请ID',
                                               `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                               `FRIEND_ID` INTEGER NOT NULL COMMENT '好友ID',
                                               `FRI_APPLY_TIME` TIMESTAMP NOT NULL COMMENT '好友申请时间',
                                               `FRI_APPLY_ACCEPT` INTEGER DEFAULT '0' COMMENT '申请是否通过',
                                               PRIMARY KEY (`APPLY_ID`)
)COMMENT = '好友申请记录表';


##好友关系表
create table IF NOT EXISTS `COM_FRIEND_RELATION`(
                                                  `RELA_ID` INT AUTO_INCREMENT COMMENT '关系ID',
                                                  `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                                  `ADD_TIME` TIMESTAMP NOT NULL COMMENT '添加时间',
                                                  `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                                  `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                                  `FRIEND_ID` INTEGER NOT NULL COMMENT '好友ID',
                                                  PRIMARY KEY (`RELA_ID`)
)COMMENT = '好友关系表';


##好友分组映射表
create table IF NOT EXISTS `COM_FRIEND_GROUP_MAP`(
                                                   `GROUP_ID` INT AUTO_INCREMENT COMMENT '分组ID',
                                                   `RELA_ID` INTEGER NOT NULL COMMENT '关系ID',
                                                   PRIMARY KEY ( `GROUP_ID` )
)COMMENT = '好友分组映射表';


##好友分组表
create table IF NOT EXISTS `COM_FRIEND_GROUP`(
                                               `GROUP_ID` INT AUTO_INCREMENT COMMENT '分组ID',
                                               `GROUP_NUM` INTEGER NOT NULL COMMENT '分组编号',
                                               `GROUP_NAME` VARCHAR(20) NOT NULL COMMENT '分组名称',
                                               `ADD_TIME` TIMESTAMP NOT NULL COMMENT '添加时间',
                                               `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                               `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                               PRIMARY KEY (`GROUP_ID`)
)COMMENT = '好友分组表';


#############################02-26##################################
##群聊映射表
create table IF NOT EXISTS `COM_GROUP_CHAT_MAP`(
                                                 `CHAT_ID` INT AUTO_INCREMENT COMMENT '群聊ID',
                                                 `USER_ID` INTEGER NOT NULL COMMENT '用户ID',
                                                 PRIMARY KEY ( `CHAT_ID`,`USER_ID` )
)COMMENT = '群聊映射表';


##群聊表
create table IF NOT EXISTS `COM_GROUP_CHAT`(
                                             `CHAT_ID` INT AUTO_INCREMENT COMMENT '群聊ID',
                                             `CHAT_NAME` VARCHAR(20) NOT NULL COMMENT '群聊名称',
                                             `CREATE_TIME` TIMESTAMP NOT NULL COMMENT '创建时间',
                                             `IS_DELETE` INTEGER DEFAULT '0' COMMENT '是否删除',
                                             `IS_DELETE_TIME` TIMESTAMP NULL DEFAULT NULL COMMENT '删除时间',
                                             PRIMARY KEY (`CHAT_ID`)
)COMMENT = '群聊表';


############################探索页推荐表###################################
##企业类推荐表
create table IF NOT EXISTS `RECOM_COMPANY`(
                                            `COMPANY_ID` INTEGER COMMENT '企业ID',
                                            `COMPANY_NAME` VARCHAR(200) COMMENT '企业名称',
                                            `COMPANY_INTRODUC` VARCHAR(1000) COMMENT '企业简介',
                                            `COMPANY_WEBSITE` VARCHAR(200) COMMENT '企业官网',
                                            `RECOM_TYPE` INTEGER COMMENT '推荐类型',
                                            `DATA_TIME` DATE COMMENT '数据日期'
)COMMENT = '企业类推荐表';


##职位类推荐表
create table IF NOT EXISTS `RECOM_JOB`(
                                        `JOB_ID` INTEGER COMMENT '企业ID',
                                        `JOB_NAME` VARCHAR(200) COMMENT '职位名称',
                                        `JOB_DESCRIPTION` VARCHAR(1000) COMMENT '职位描述',
                                        `JOB_COUNTRY` VARCHAR(10) COMMENT '工作国家',
                                        `JOB_LOCATION` VARCHAR(10) COMMENT '工作地点',
                                        `JOB_HIGHLIGHT` VARCHAR(200) COMMENT '职位亮点',
                                        `RECOM_TYPE` INTEGER COMMENT '推荐类型',
                                        `DATA_TIME` DATE COMMENT '数据日期'
)COMMENT = '职位类推荐表';


##文本类推荐表
create table IF NOT EXISTS `RECOM_TEXT`(
                                         `TEXT_ID` INTEGER COMMENT '企业ID',
                                         `TEXT_TITLE` VARCHAR(2000) COMMENT '职位名称',
                                         `RECOM_TYPE` INTEGER COMMENT '推荐类型',
                                         `DATA_TIME` DATE COMMENT '数据日期'
)COMMENT = '文本类推荐表';


##视频推荐表
create table IF NOT EXISTS `RECOM_VIDEO`(
                                          `VIDEO_ID` INTEGER COMMENT '视频ID',
                                          `VIDEO_TITLE` VARCHAR(200) COMMENT '视频标题',
                                          `RECOM_REASON` INTEGER COMMENT '推荐理由',
                                          `DATA_TIME` DATE COMMENT '数据日期'
)COMMENT = '视频推荐表';


##人脉推荐表
create table IF NOT EXISTS `RECOM_USER`(
                                         `USER_ID` INTEGER COMMENT '用户ID',
                                         `USER_FIRST_NAME` VARCHAR(200) COMMENT '用户姓',
                                         `USER_LAST_NAME` VARCHAR(200) COMMENT '用户名',
                                         `RECOM_REASON` INTEGER COMMENT '推荐理由',
                                         `DATA_TIME` DATE COMMENT '数据日期'
)COMMENT = '人脉推荐表';



##影响力分析表
create table IF NOT EXISTS `PERSON_INFLUENCE`(
                                               `USER_ID` INT AUTO_INCREMENT COMMENT '用户ID',
                                               `PERS_PROFILE` FLOAT COMMENT '个人资料完善度',
                                               `PERS_IDENT_VERIFY` FLOAT COMMENT '身份认证',
                                               `PERS_UNIVERSITY` FLOAT COMMENT '学校排名',
                                               `PERS_WORK` FLOAT COMMENT '工作实习质量',
                                               `PERS_FRIEND_COUNT` FLOAT COMMENT '好友数量',
                                               `PERS_FRIEND_QUALITY` FLOAT COMMENT '好友质量',
                                               `PERS_INTERACTION` FLOAT COMMENT '互动关系',
                                               `PERS_LIKE_COUNT` FLOAT COMMENT '点赞数',
                                               `PERS_POSI_EVALUATE` FLOAT COMMENT '职位评价',
                                               `PERS_TOTAL` FLOAT COMMENT '总分',
                                               PRIMARY KEY (`USER_ID`)
)COMMENT = '影响力分析表';


##校园招聘表
create table IF NOT EXISTS `RECOM_CAMPUS_RECRUIT`(
                                                   `COMPANY_ID` INT COMMENT '企业ID',
                                                   `COMPANY_NAME` VARCHAR(200) COMMENT '企业名称',
                                                   `COMPANY_INTRODUC` VARCHAR(1000) COMMENT '企业简介',
                                                   `COMPANY_NATURE` VARCHAR(200) COMMENT '企业性质',
                                                   `COMPANY_LOCATION` VARCHAR(200) COMMENT '企业所在地',
                                                   `COMPANY_WEBSITE` VARCHAR(200) COMMENT '企业官网',
                                                   `COMPANY_START_DATE` DATE COMMENT '成立日期',
                                                   `COMPANY_LOGO` VARCHAR(200) COMMENT '企业LOGO',
                                                   `JOB_ID` INTEGER COMMENT '职位ID',
                                                   `JOB_NAME` VARCHAR(200)  COMMENT '职位名称',
                                                   `JOB_TYPE` INTEGER  COMMENT '职位性质',
                                                   `DATA_TIME` DATE COMMENT '数据日期'
)COMMENT = '校园招聘表';

