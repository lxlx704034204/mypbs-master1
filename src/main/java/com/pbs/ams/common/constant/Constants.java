package com.pbs.ams.common.constant;

/**
 * Created by liupeng on 2015/5/28.
 */
public final class Constants {
    
    public static final String COMPANY_NAME = "ruixin";
    public static final String RUIXIN_PRODUCT_GUID = "37a9857a-83b6-4d05-a500-d72222e2a84f";
    public static final int MIN_HASH_LENGTH = 10;
    public static final String APPLICATION_CHARACTER_ENCODING = "UTF-8";
    public static final String APPLICATION_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final int SERVICE_VERIFY_REGIST_HOUR = 48;
    public static final int SERVICE_VERIFY_RESET_MINUTE = 30;
    public static final String SERVICE_MAIL_TITLE_REGIST = "|睿歆机构控制台--邮箱身份验证";
    public static final String SERVICE_MAIL_TITLE_RESET = "|睿歆机构控制台--密码修改验证";
    public static final String VERIFY_URL_KEY = "paas.domain.verify.regist";
    public static final String BOSS_VERIFY_REGIST="users/verify/regist?key=";
    public static final String BOSS_VERIFY_RESET="users/verify/reset?key=";
    public static final String CONTROLLER_SESSION_COND = "SEARCH_PAGE_CONDITION_";
    public static final String CONTROLLER_SESSION_CURRENT_INSTITUTION_GUID = "currentInstitutionGuid";
    public static final String CONTROLLER_SESSION_SERVER_NAME = "serverName";



    public static final String CONTROLLER_SESSION_INDEX = "menuIndex";
    public static final String CONTROLLER_SESSION_VERIFY_CODE = "verifyCode";
    public static final String CONTROLLER_SESSION_LOGIN_USER = "loginUser";
    public static final String CONTROLLER_SESSION_INSTITUTION = "institution";
    public static final String CONTROLLER_SESSION_INSTITUTION_TYPE = "institutionType";
    public static final String CONTROLLER_SESSION_LOGIN_USER_NICKNAME = "nickname";
    public static final String CONTROLLER_SESSION_LOGIN_USER_MOBILE = "mobile";
    public static final String CONTROLLER_SESSION_LOGIN_USER_PASSWORD = "password";
    public static final String CONTROLLER_SESSION_SYS_LOGIN_USER = "sysLoginUser";

    public static final String CURRENT_FUNDPRODUCT = "currentFundProduct";
    public static final String CURRENT_FUNDNETVALUE = "currentFundNetValue";
    public static final String CURRENT_FUNDPOSITION = "currentFundPosition";
    public static final String CURRENT_FUNDTRANSACTION = "currentFundTransaction";
    public static final String CURRENT_FUNDCONSIGNATION = "currentFundConsignation";
    public static final String CURRENT_FUNDREDEEM = "currentFundRedeem";
    public static final String CURRENT_FUNDVALUATION = "currentFundValuation";

    public static final int DAO_PAGE_QUERY_SIZE = 20;
    public static final int DAO_PAGE_INFORMATION_SIZE = 6;
    public static final int DAO_PAGE_DEFAULT_NUMBER = 1;
    public static final String DAO_HTTP_PARAMETER_SEPARATOR = ",";
    public static final String UTIL_IP_URL_TAOBAO = "http://ip.taobao.com/service/getIpInfo.php?ip=";
    public static final String UTIL_IP_UNKNOW = "未分配或者内网IP";


    public static final String UTIL_MAIL_FROM = "service@mtcent.com";
    public static final String UTIL_MAIL_PASSWORD = "mt@240871";
    public static final String UTIL_MAIL_SMTP = "smtp.mxhichina.com";

    public static final int UTIL_MAIL_PORT = 25;
    public static final String UTIL_ENCRYPT_PASSWORD_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int UTIL_ENCRYPT_PASSWORD_SALT_BYTE_SIZE = 32;
    public static final int UTIL_ENCRYPT_PASSWORD_HASH_BYTE_SIZE = 32;
    public static final int UTIL_ENCRYPT_PASSWORD_ALGORITHM_ITERATIONS = 1000;
    public static final String UTIL_ENCRYPT_TEXT_ALGORITHM = "DES";
    public static final String UTIL_IP_ADDRESS_LOCALHOST = "127.0.0.1";
    public static final String DAO_KEY_SALT = "3ca508c502cb1d6309df71949fc84659d3c87463e290e6fb39f7d54adb4e3a16";
    public static final int BATCH_INSERT_CNT = 500;
    // 审核员角色
    public static final String APPROVEL_ROLE_GUID="af45d105-bb38-4cc3-b3d4-0b1ac905fc4b";

    public static final String SESSION_CATEGORY_DATA = "session_category_data";



    public static final String SERVICE_MAIL_CONTENT_REGIST = "<div style=\"background-color:#d0d0d0;background-image:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg.png);text-align:center;padding:40px;\">\n" +
            "<div class=\"mmsgLetter\" style=\"width:720px;margin:0 auto;padding:10px;color:#333;background-color:#fff;border:0px solid #aaa;border-radius:5px;-webkit-box-shadow:3px 3px 10px #999;-moz-box-shadow:3px 3px 10px #999;box-shadow:3px 3px 10px #999;font-family:Verdana, sans-serif; \">\n" +
            "<div class=\"mmsgLetterHeader\" style=\"height:23px;background:url(http://static.boss.com/boss/image/banner_2.png) repeat-x 0 0;\">\n" +
            "</div>\n" +
            " <div class=\"mmsgLetterContent\" style=\"text-align:left;font-size:14px;line-height:1.5;background:url({4}) no-repeat top right;\">\n" +
            "<div>\n" +
            "<p>尊敬的{1}，您好！</p>\n" +
            " <p>\n" +
            "感谢您在{3}|睿歆机构控制台上注册。 <br>\n" +
            "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请点击下面的链接完成您的激活，链接有效期为" + SERVICE_VERIFY_REGIST_HOUR +
            "小时，激活成功后自动失效。</p>\n" +
            "<br>\n" +
            "<a href=\"\n" +
            "{2}\n" +
            "\" target=\"_blank\" >点击这里立即激活</a>\n" +
            "<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;链接如下：</p>\n" +
            "<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "\t\t\t\t\t\t\t{2}\n" +
            "\t\t\t\t\t\t\t</p>\n" +

            "\t\t\t\t\t\t\t如果以上链接无法点击，请将上面链接的地址复制你的浏览器（如IE、Chrome等）的地址栏直接打开。\n" +

            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;谢谢！ </p>\n" +
            "\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-- </p>\n" +
            "\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{3}|睿歆机构控制台 </p>\n" +
            "\t\t\t\t\t\t\t<div style=\"font-size:16px; color: #fff;height:24px; line-height:24px; background-color:#d0d0d0 ;\">\n" +
            "\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（这是一封自动产生的email，请勿直接回复。）</p>\n" +
            "\t\t\t\t\t\t\t </div>\n" +
            "\t\t\t\t\t\t\t </div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t </div>\n" +
            "\t\t\t\t\t\t\t </div>";
    public static final String SERVICE_MAIL_CONTENT_RESET ="<div style=\"background-color:#d0d0d0;background-image:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg.png);text-align:center;padding:40px;\">\n" +
            "\t\t\t\t\t\t\t<div class=\"mmsgLetter\" style=\"width:720px;margin:0 auto;padding:10px;color:#333;background-color:#fff;border:0px solid #aaa;border-radius:5px;-webkit-box-shadow:3px 3px 10px #999;-moz-box-shadow:3px 3px 10px #999;box-shadow:3px 3px 10px #999;font-family:Verdana, sans-serif; \">\n" +
            "\t\t\t\t\t\t\t<div class=\"mmsgLetterHeader\" style=\"height:23px;background:url(http://static.boss.com/boss/image/banner_2.png) repeat-x 0 0;\">\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t <div class=\"mmsgLetterContent\" style=\"text-align:left;font-size:14px;line-height:1.5;background:url({4}) no-repeat top right;\">\n" +
            "\t\t\t\t\t\t\t<div>\n" +
            "\t\t\t\t\t\t\t<p>你好!</p>\n" +
            "\t\t\t\t\t\t\t <p>\n" +
            "\t\t\t\t\t\t\t感谢您使用{3}|睿歆机构控制台。 <br>\n" +
            "\t\t\t\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "\t\t\t\t\t\t\t<a href=\"\n" +
            "\t\t\t\t\t\t\t{2}\n" +
            "\t\t\t\t\t\t\t\" target=\"_blank\" >点击这里立即重置密码</a>\n" +
            "\t\t\t\t\t\t\t<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;链接如下：</p>\n" +
            "\t\t\t\t\t\t\t<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "\t\t\t\t\t\t\t{2}\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t如果以上链接无法点击，请将上面链接的地址复制你的浏览器（如IE、Chrome等）的地址栏直接打开。\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;谢谢！ </p>\n" +
            "\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-- </p>\n" +
            "\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{3}|睿歆机构控制台 </p>\n" +
            "\t\t\t\t\t\t\t<div style=\"font-size:16px; color: #fff;height:24px; line-height:24px; background-color:#d0d0d0 ;\">\n" +
            "\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（这是一封自动产生的email，请勿直接回复。）</p>\n" +
            "\t\t\t\t\t\t\t </div>\n" +
            "\t\t\t\t\t\t\t </div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t </div>\n" +
            "\t\t\t\t\t\t\t </div>";
    private Constants() {
        throw new UnsupportedOperationException();
    }


}
