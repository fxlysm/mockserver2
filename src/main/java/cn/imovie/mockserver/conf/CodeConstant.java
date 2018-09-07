package cn.imovie.mockserver.conf;


import java.util.HashMap;
import java.util.Map;

/**
 * Auther：Steven Guo
 * Date：2017年4月28日
 * Description：应答码常量类
 */
public class CodeConstant {
    public static final Map<String, String> MAP_CODE_MAPPING = new HashMap<String, String>(); //第三方返回应答码对应自定义应答码
    public static final Map<String, String> MAP_CODE_DESC = new HashMap<String, String>();    //自定义应答码对应应答码描述

    public static final String ERROR_CODE_LT_SINGLE_LIMIT = "40001";
    public static final String ERROR_CODE_GT_SINGLE_LIMIT = "40002";
    public static final String ERROR_CODE_INVALID_CP_CHAN = "40003";
    public static final String ERROR_CODE_PARAMS_ERROR = "40006";
    public static final String ERROR_CODE_TOTAL_FEE_ERROR = "40008";
    public static final String ERROR_CODE_ORDER_STATUS_ERROR = "40022";
    public static final String ERROR_CODE_ORDER_CANNOT_CLOSE = "40023";
    public static final String ERROR_CODE_SIGNATURE_ERROR = "40101";
    public static final String ERROR_CODE_INVALID_CP_ERROR = "40102";
    public static final String ERROR_CODE_INVALID_APPID_ERROR = "40103";
    public static final String ERROR_CODE_INVALID_SERVICE = "40105";
    public static final String ERROR_CODE_UNKNOWN_SERVICE = "40106";
    public static final String  ERROR_CODE_PUBLIC_KEY_NOT_CONFIGURED="40107";
    public static final String ERROR_CODE_GT_DAY_LIMIT = "40302";
    public static final String ERROR_CODE_UNKNOW_ORDER_ERROR = "40402";
    public static final String ERROR_CODE_NETWORK_ERROR = "40403";
    public static final String ERROR_CODE_REPEAT_ORDER = "40901";

    public static final String ERROR_CODE_SYSTEM_ERROR = "50001";
    public static final String ERROR_CODE_UNKNOWN_ERROR = "50002";
    public static final String ERROR_CODE_PROVIDER_ERROR = "50201";
    public static final String ERRORCODE_ABNORMAL_ERROR = "50099";


    static {
        //Start ->>自定义应答码对应应答码描述
        MAP_CODE_DESC.put("40001","小于单笔最小限额");
        MAP_CODE_DESC.put("40002","超过单笔最大限额");
        MAP_CODE_DESC.put("40003","支付通道未开通");
        MAP_CODE_DESC.put("40004","金额无效");
        MAP_CODE_DESC.put("40005","退款请求已存在");
        MAP_CODE_DESC.put("40006","参数错误");
        MAP_CODE_DESC.put("40007","已退款");
        MAP_CODE_DESC.put("40008","订单金额不匹配");
        MAP_CODE_DESC.put("40009","订单币种不匹配");
        MAP_CODE_DESC.put("40010","姓名有误");
        MAP_CODE_DESC.put("40011","银行预留手机号有误");
        MAP_CODE_DESC.put("40012","银行卡无效或状态有误");
        MAP_CODE_DESC.put("40013","余额不足");
        MAP_CODE_DESC.put("40014","格式错误");
        MAP_CODE_DESC.put("40015","字段超长");
        MAP_CODE_DESC.put("40016","卡已经被使用或者不存在");
        MAP_CODE_DESC.put("40017","卡序列号无效");
        MAP_CODE_DESC.put("40018","卡或pin不正确 ");
        MAP_CODE_DESC.put("40019","支付卡号错误");
        MAP_CODE_DESC.put("40020","错误的API版本 ");
        MAP_CODE_DESC.put("40021","订单已关闭");
        MAP_CODE_DESC.put(ERROR_CODE_ORDER_STATUS_ERROR, "订单状态错误");
        MAP_CODE_DESC.put(ERROR_CODE_ORDER_CANNOT_CLOSE, "订单已支付成功或退款，无法关闭！");
        MAP_CODE_DESC.put("40101","签名错误");
        MAP_CODE_DESC.put("40102","无效商户");
        MAP_CODE_DESC.put("40103","应用不存在或已禁用");
        MAP_CODE_DESC.put("40104","无效的mpin");
        MAP_CODE_DESC.put("40105","服务未授权");
        MAP_CODE_DESC.put("40106","未知的服务");
        MAP_CODE_DESC.put("40107","商户公钥未配置");
        MAP_CODE_DESC.put("40301","Session异常 ");
        MAP_CODE_DESC.put("40302","超过单日限额");
        MAP_CODE_DESC.put("40303","卡信息或银行预留手机号有误");
        MAP_CODE_DESC.put("40304","密码错误次数超限");
        MAP_CODE_DESC.put("40305","密码验证失败");
        MAP_CODE_DESC.put("40306","短信验证码错误");
        MAP_CODE_DESC.put("40307","CVN验证失败 ");
        MAP_CODE_DESC.put("40308","身份证号有误");
        MAP_CODE_DESC.put("40309","短信验证码验证错误次数超限");
        MAP_CODE_DESC.put("40401","短信校验码已过期");
        MAP_CODE_DESC.put("40402","订单不存在");
        MAP_CODE_DESC.put("40403","网络连接异常");
        MAP_CODE_DESC.put("40901","重复交易");
        MAP_CODE_DESC.put("42901","操作过于频繁");
        MAP_CODE_DESC.put("42902","短信验证码发送次数超限");
        MAP_CODE_DESC.put("42903","短信验证码发送频率过高");
        MAP_CODE_DESC.put("50099","异常");
        MAP_CODE_DESC.put("50001","系统错误");
        MAP_CODE_DESC.put("50002","其它错误");
        MAP_CODE_DESC.put("50201","卡服务商错误");
        MAP_CODE_DESC.put("50202","卡被冻结");
        MAP_CODE_DESC.put("50203","卡片超期");
        MAP_CODE_DESC.put("50204","卡序列号和pin不匹配 ");
        MAP_CODE_DESC.put("50205","卡未激活");
        MAP_CODE_DESC.put("50206","卡格式错误");
        MAP_CODE_DESC.put("50401","卡被阻塞24小时");
        MAP_CODE_DESC.put("50402","请持卡人与发卡银行联系");

        MAP_CODE_DESC.put("50403","请填写交易名称");
        MAP_CODE_DESC.put("50404","请填写交易金额");
        MAP_CODE_DESC.put("50405","请填写金额币种");
        MAP_CODE_DESC.put("50406","请填写订单类型");
        MAP_CODE_DESC.put("50407","请填写异步通知页");
        MAP_CODE_DESC.put("50408","请填写订单号");

        MAP_CODE_DESC.put("50409","商品描述为空");
        MAP_CODE_DESC.put("50410","用户openid错误");
        MAP_CODE_DESC.put("50411","appId为空");
        //End ->>自定义应答码对应应答码描述

        //Start ->>第三方返回应答码对应自定义应答码
        //CardChargingGw
        MAP_CODE_MAPPING.put("56","50099");
        MAP_CODE_MAPPING.put("99","50099");
        MAP_CODE_MAPPING.put("63","40402");
        MAP_CODE_MAPPING.put("12","40901");
        MAP_CODE_MAPPING.put("58","40006");
        MAP_CODE_MAPPING.put("64","40101");
        MAP_CODE_MAPPING.put("60","40102");
        MAP_CODE_MAPPING.put("62","40305");
        MAP_CODE_MAPPING.put("61","40010");
        MAP_CODE_MAPPING.put("-24","40019");
        MAP_CODE_MAPPING.put("4","40019");
        MAP_CODE_MAPPING.put("5","40015");
        MAP_CODE_MAPPING.put("3","40301");
        MAP_CODE_MAPPING.put("7","40301");
        MAP_CODE_MAPPING.put("3","40301");
        MAP_CODE_MAPPING.put("-11","50201");
        MAP_CODE_MAPPING.put("10","50201");
        MAP_CODE_MAPPING.put("-10","50206");
        MAP_CODE_MAPPING.put("-2","50202");
        MAP_CODE_MAPPING.put("-3","50203");
        MAP_CODE_MAPPING.put("50","40016");
        MAP_CODE_MAPPING.put("51","40017");
        MAP_CODE_MAPPING.put("52","50204");
        MAP_CODE_MAPPING.put("53","40018");
        MAP_CODE_MAPPING.put("55","50401");
        MAP_CODE_MAPPING.put("57","40104");
        MAP_CODE_MAPPING.put("59","50205");
        MAP_CODE_MAPPING.put("8","40403");
        MAP_CODE_MAPPING.put("11","40403");
        MAP_CODE_MAPPING.put("9","50001");
        MAP_CODE_MAPPING.put("13","50001");

        //Cherry
        MAP_CODE_MAPPING.put("ERROR:","50002"); //Cherry Credit: 无代码，通过判断响应字符串以“ERROR:” 开头识别异常

        //Mol支付
        MAP_CODE_MAPPING.put("40014","40001");
        MAP_CODE_MAPPING.put("40013","40002");
        MAP_CODE_MAPPING.put("40005","40003");
        MAP_CODE_MAPPING.put("40015","40003");
        MAP_CODE_MAPPING.put("40104","40003");
        MAP_CODE_MAPPING.put("404","40402");
        MAP_CODE_MAPPING.put("40004","40901");
        MAP_CODE_MAPPING.put("40006","40004");
        MAP_CODE_MAPPING.put("40103","40101");
        MAP_CODE_MAPPING.put("40003","40009");
        MAP_CODE_MAPPING.put("40007","40305");
        MAP_CODE_MAPPING.put("40101","40103");
        MAP_CODE_MAPPING.put("40001","40014");
        MAP_CODE_MAPPING.put("40002","40020");
        MAP_CODE_MAPPING.put("40008","40403");
        MAP_CODE_MAPPING.put("40102","40403");
        MAP_CODE_MAPPING.put("40009","50002");

        //Upay
        MAP_CODE_MAPPING.put("400","40006");
        MAP_CODE_MAPPING.put("403","40103");
        MAP_CODE_MAPPING.put("502","40019");
        MAP_CODE_MAPPING.put("500","50001");
        MAP_CODE_MAPPING.put("504","50001");

        //wxpay
        MAP_CODE_MAPPING.put("NOAUTH","40003");
        MAP_CODE_MAPPING.put("ORDERNOTEXIST","40402");
        MAP_CODE_MAPPING.put("ORDERPAID","40901");
        MAP_CODE_MAPPING.put("OUT_TRADE_NO_USED","40901");
        MAP_CODE_MAPPING.put("LACK_PARAMS","40006");
        MAP_CODE_MAPPING.put("REQUIRE_POST_METHOD","40006");
        MAP_CODE_MAPPING.put("POST_DATA_EMPTY","40006");
        MAP_CODE_MAPPING.put("SIGNERROR","40101");
        MAP_CODE_MAPPING.put("MCHID_NOT_EXIST","40102");
        MAP_CODE_MAPPING.put("NOTENOUGH","40013");
        MAP_CODE_MAPPING.put("APPID_NOT_EXIST","40103");
        MAP_CODE_MAPPING.put("APPID_MCHID_NOT_MATCH","40103");
        MAP_CODE_MAPPING.put("XML_FORMAT_ERROR","40014");
        MAP_CODE_MAPPING.put("NOT_UTF8","40014");
        MAP_CODE_MAPPING.put("ORDERCLOSED","40021");
        MAP_CODE_MAPPING.put("SYSTEMERROR","50001");
        MAP_CODE_MAPPING.put("SYSTEMERROR","50001");

        //支付宝
        MAP_CODE_MAPPING.put("PAY_CHECK_FAIL","50099");
        MAP_CODE_MAPPING.put("TAOBAO_ANTI_PHISHING_CHECK_FAIL","50099");
        MAP_CODE_MAPPING.put("SUBJECT_HAS_FORBIDDENWORD","50099");
        MAP_CODE_MAPPING.put("BODY_HAS_FORBIDDENWORD","50099");
        MAP_CODE_MAPPING.put("TRADE_NOT_FOUND","40402");
        MAP_CODE_MAPPING.put("TRADE_GOOD_INFO_NOT_FOUND","40402");
        MAP_CODE_MAPPING.put("ILLEGAL_FEE_PARAM","40004");
        MAP_CODE_MAPPING.put("TRADE_TOTALFEE_NOT_MATCH","40004");
        MAP_CODE_MAPPING.put("SELLER_NOT_IN_SPECIFIED_SELLERS","40006");
        MAP_CODE_MAPPING.put("ILLEGAL_PAYMENT_TYPE","40006");
        MAP_CODE_MAPPING.put("ILLEGAL_ARGUMENT","40006");
        MAP_CODE_MAPPING.put("DEFAULT_BANK_INVALID","40006");
        MAP_CODE_MAPPING.put("ROYALTY_FORAMT_ERROR","40006");
        MAP_CODE_MAPPING.put("NEED_CTU_CHECK_PARAMETER_ERROR","40006");
        MAP_CODE_MAPPING.put("EBANK_CERDIT_GW_RULE_NOT_OPEN","50402");
        MAP_CODE_MAPPING.put("ILLEGAL_EXTRA_COMMON_PARAM","40014");
        MAP_CODE_MAPPING.put("TRADE_NOT_ALLOWED_PAY","40021");
        MAP_CODE_MAPPING.put("NOT_SUPPORT_GATEWAY","40403");
        MAP_CODE_MAPPING.put("FAIL_CREATE_CASHIER_PAY_ORDER","50001");
        MAP_CODE_MAPPING.put("TRADE_SELLER_NOT_MATCH","50002");
        MAP_CODE_MAPPING.put("TRADE_BUYER_NOT_MATCH","50002");
        MAP_CODE_MAPPING.put("SUBJECT_MUST_NOT_BE_NULL","50002");
        MAP_CODE_MAPPING.put("TRADE_PRICE_NOT_MATCH","50002");
        MAP_CODE_MAPPING.put("TRADE_QUANTITY_NOT_MATCH","50002");
        MAP_CODE_MAPPING.put("DIRECT_PAY_WITHOUT_CERT_CLOSE","50002");
        MAP_CODE_MAPPING.put("BUYER_SELLER_EQUAL","50002");
        MAP_CODE_MAPPING.put("SELLER_NOT_EXIST","50002");
        MAP_CODE_MAPPING.put("BUYER_EMAIL_ID_MUST_NULL","50002");
        MAP_CODE_MAPPING.put("PRODUCT_NOT_ALLOWED","50002");
        MAP_CODE_MAPPING.put("ROYALTY_RECEIVER_NOT_IN_SPECIFIED_ACCOUNTS","50002");
        MAP_CODE_MAPPING.put("ROYALTY_LENGTH_ERROR","50002");
        MAP_CODE_MAPPING.put("DIS_NOT_SIGN_PROTOCOL","50002");
        MAP_CODE_MAPPING.put("SELF_TIMEOUT_NOT_SUPPORT","50002");
        MAP_CODE_MAPPING.put("ILLEGAL_OUTTIME_ARGUMENT","50002");
        MAP_CODE_MAPPING.put("DIRECTIONAL_PAY_FORBIDDEN","50002");
        MAP_CODE_MAPPING.put("SELLER_ENABLE_STATUS_FORBID","50002");
        MAP_CODE_MAPPING.put("ROYALTY_SELLER_ENABLE_STATUS_FORBID","50002");
        MAP_CODE_MAPPING.put("ROYALTY_SELLER_NOT_CERTIFY","50002");
        MAP_CODE_MAPPING.put("ROYALTY_TYPE_ERROR","50002");
        MAP_CODE_MAPPING.put("ROYALTY_RECEIVE_EMAIL_NOT_EXIST","50002");
        MAP_CODE_MAPPING.put("ROYALTY_RECEIVE_EMAIL_NOT_CERTIFY","50002");
        MAP_CODE_MAPPING.put("ROYALTY_PAY_EMAIL_NOT_EXIST","50002");
        MAP_CODE_MAPPING.put("NEED_CTU_CHECK_NOT_ALLOWED","50002");
        MAP_CODE_MAPPING.put("BUYER_NOT_EXIST","50002");
        MAP_CODE_MAPPING.put("HAS_NO_PRIVILEGE","50002");

        //智慧支付
        MAP_CODE_MAPPING.put("2009","50099");
        MAP_CODE_MAPPING.put("2003","40002");
        MAP_CODE_MAPPING.put("2002","40402");
        MAP_CODE_MAPPING.put("2004","40402");
        MAP_CODE_MAPPING.put("2005","40402");
        MAP_CODE_MAPPING.put("2001","40901");
        MAP_CODE_MAPPING.put("1","40006");
        MAP_CODE_MAPPING.put("2","40101");
        MAP_CODE_MAPPING.put("1003","40007");
        MAP_CODE_MAPPING.put("2000","40102");
        MAP_CODE_MAPPING.put("2006","42901");
        MAP_CODE_MAPPING.put("2010","40008");
        MAP_CODE_MAPPING.put("2011","40009");
        MAP_CODE_MAPPING.put("2012","40303");
        MAP_CODE_MAPPING.put("2013","40304");
        MAP_CODE_MAPPING.put("2014","40305");
        MAP_CODE_MAPPING.put("2015","40306");
        MAP_CODE_MAPPING.put("2016","40307");
        MAP_CODE_MAPPING.put("2017","40308");
        MAP_CODE_MAPPING.put("2018","42902");
        MAP_CODE_MAPPING.put("2019","42903");
        MAP_CODE_MAPPING.put("2020","40309");
        MAP_CODE_MAPPING.put("2021","40401");
        MAP_CODE_MAPPING.put("2022","40010");
        MAP_CODE_MAPPING.put("2023","40011");
        MAP_CODE_MAPPING.put("2024","40012");
        MAP_CODE_MAPPING.put("2025","40013");
        MAP_CODE_MAPPING.put("2026","50402");
        MAP_CODE_MAPPING.put("9999","50001");
        MAP_CODE_MAPPING.put("2007","50002");
        MAP_CODE_MAPPING.put("2008","50002");
        //End ->>第三方返回应答码对应自定义应答码

    }

}
