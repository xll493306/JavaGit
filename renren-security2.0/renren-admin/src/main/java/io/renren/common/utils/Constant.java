/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.utils;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
    /** 数据权限过滤 */
	public static final String SQL_FILTER = "sql_filter";
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";
    /**
     * appid
     */
    public static final String appID = "wxbf214ab22aece6da";
    /**
     * appsecret
     */
    public static final String appsecret = "d227d734b64026223fe54b41a406e1d2";
    /**
     * 参赛者角色ID
     */
    public static  final Long roleId =(long)2;
    /**
     * 店员角色ID
     */
    public static final Long mangerId=(long)1;
    /**
     * 杭州
     * endpoint
     */
    public static final String endpoint = "sts.cn-hangzhou.aliyuncs.com";
    /**
     * AccessKeyId
     */
    public static final String AccessKeyId = "LTAI4G1xg2fAci7fK7fKN3Qv";
    /**
     * access-key-secret
     */
    public static final String accessKeySecret = "PjNZ54Yg8kZUUTWlNqofoYcPm5R7uO";
    /**
     * role-arn
     */
    public static final String roleArn  = "acs:ram::1867221078049225:role/short-video-author";
    /**
	 * 菜单类型
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
