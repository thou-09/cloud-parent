package com.itany.constants;

/**
 * AppConsts.
 *
 * @author Thou
 * @date 2022/10/18
 */
public interface AppConsts {

    // 全局使用
    /**
     * 默认用户头像
     */
    String DEFAULT_HEADIMG = "/2022/10/22/61378b57d9714d919d597b48656aabf1apple.png";
    /**
     * 默认页面起始页
     */
    Integer DEFAULT_PAGE_NO = 1;
    /**
     * 默认页面大小
     */
    Integer DEFAULT_PAGE_SIZE = 10;
    /**
     * 用于上传文件
     */
    String FORMAT_DATE_UPLOAD = "yyyy/MM/dd";
    /**
     * 时间格式 yyyy-MM-dd
     */
    String FORMAT_DATE = "yyyy-MM-dd";
    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 启用状态
     */
    Integer STATUS_ENABLE = 1;
    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = 0;

    // User 相关
    /**
     * 用户性别男
     */
    Integer USER_SEX_MALE = 1;
    /**
     * 用户性别女
     */
    Integer USER_SEX_FEMALE = 2;

    // Examine 相关
    /**
     * 加盟审核
     */
    Integer EXAMINE_TYPE_JOIN = 1;
    /**
     * 服务审核
     */
    Integer EXAMINE_TYPE_SERVE = 2;
    /**
     * 待审核
     */
    Integer EXAMINE_FLAG_WAIT = 0;
    /**
     * 审核通过
     */
    Integer EXAMINE_FLAG_SUCCESS = 1;
    /**
     * 审核不通过
     */
    Integer EXAMINE_FLAG_FAIL = 2;

    // ServerCommpany 相关
    /**
     * 生活服务
     */
    Integer SERVER_COMMPANY_TYPE_LIFE = 1;
    /**
     * 商务服务
     */
    Integer SERVER_COMMPANY_TYPE_BUSINESS = 2;

    // Type 相关
    /**
     * 生活类型服务
     */
    Integer TYPE_TYPE_LIFE = 1;
    /**
     * 商务类型服务
     */
    Integer TYPE_TYPE_BUSINESS = 2;

    // Annex 相关
    /**
     * 服务图片
     */
    Integer ANNEX_TYPE_SERVER_IMAGE = 1;
    /**
     * 服务商图片
     */
    Integer ANNEX_TYPE_SERVER_COMMPANY_IMAGE = 2;
    /**
     * 服务商执照图片
     */
    Integer ANNEX_TYPE_SERVER_COMMPANY_LICENSE_IMAGE = 3;
    /**
     * 关联审核表
     */
    Integer ANNEX_TABLETYPE_EXAMINE = 1;
    /**
     * 关联服务商表
     */
    Integer ANNEX_TABLETYPE_SERVER_COMMPANY = 2;
    /**
     * 关联服务表
     */
    Integer ANNEX_TABLETYPE_SERVER = 3;

    // 支付相关
    /**
     * VIP NO
     */
    Integer[] VIP_NO = {
            1, 2, 3, 4
    };
    /**
     * VIP 价格
     */
    String[] VIP_PRICE = {
            "10", "50", "100", "200"
    };
    /**
     * VIP 天数
     */
    Integer[] VIP_DAY = {
            3, 30, 180, 365
    };
    /**
     * 未支付状态
     */
    Integer ORDER_UNPAID = 0;
    /**
     * 支付状态
     */
    Integer ORDER_PAID = 1;
}
