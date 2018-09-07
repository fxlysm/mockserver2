# 测试桩说明
### 环境配置
  ####  mysql:
  
    host:192.168.100.203
    post:3306
    account:root
    password:r00tm1
   
   #### redis:
   
    host:192.168.100.203
    post:6379
    password:swimovie
   
   
 ### 运行
 
  可将配置文件外置并设置相关参数于application-test.properties：
  1. key ----签名 
  2. redis ----订单缓存 

启动命令:

     java -c XX.jar --spring.profiles.active=test 


## 微信支付

### 接口

   |名称|API接口名称|开发情况|
   |-------------|:-------------:|:-------------:|
   |/pay/unifiedorder|统一下单|已开发
   |/pay/refundquery| 退款查询|未开发
   |/pay/closeorder| 关闭订单|未开发
   |/pay/orderquery| 订单查询|已开发
   |/pay/downloadbill |对帐单下载|未开发
   |/pay/downloadfundflow|下载资金账单|未开发

请求必传：

    private  String appid;
    private String mch_id;
    private String out_trade_no;
    private String total_fee;
    private String notify_url;
    private String trade_type;
    private String spbill_create_ip;
    private String body;
    private String openid;


示例：
支付请求：

    <xml>
       <appid>wx2421b1c4370ec43b</appid>
       <attach>支付测试</attach>
       <body>JSAPI支付测试</body>
       <mch_id>10000100</mch_id>
       <detail><![CDATA[{ "goods_detail":[ { "goods_id":"iphone6s_16G", "wxpay_goods_id":"1001", "goods_name":"iPhone6s 16G", "quantity":1, "price":528800, "goods_category":"123456", "body":"苹果手机" }, { "goods_id":"iphone6s_32G", "wxpay_goods_id":"1002", "goods_name":"iPhone6s 32G", "quantity":1, "price":608800, "goods_category":"123789", "body":"苹果手机" } ] }]]></detail>
       <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
       <notify_url>http://localhost:9025/pay/handlerNotify</notify_url>
       <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
       <out_trade_no>1415659990</out_trade_no>
       <spbill_create_ip>14.23.150.211</spbill_create_ip>
       <total_fee>1</total_fee>
       <trade_type>JSAPI</trade_type>
       <sign>0CB01533B8C1EF103065174F50BCA001</sign>
    </xml>

请求响应：

    <xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid>
    <code_url><![CDATA[weixin?//wxpay/s/An4baqw]]></code_url>
    <mch_id><![CDATA[10000100]]></mch_id>
    <nonce_str><![CDATA[atidwlcc]]></nonce_str>
    <prepay_id><![CDATA[wx201410272009395522657a690389285100]]></prepay_id>
    <return_code><![CDATA[SUCCESS]]></return_code>
    <sign><![CDATA[A2D8A0F4D84205285058CEA3D0A1F523]]></sign>
    <trade_type><![CDATA[JSAPI]]></trade_type>
    </xml>
    
 支付通知：
 
     <bank_type><![CDATA[CMC]]></bank_type>
     <cash_fee><![CDATA[1]]></cash_fee>
     <mch_id><![CDATA[10000100]]></mch_id>
     <nonce_str><![CDATA[4ooea6h5]]></nonce_str>
     <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>
     <out_trade_no><![CDATA[1415659990]]></out_trade_no>
     <result_code><![CDATA[SUCCESS]]></result_code>
     <return_code><![CDATA[SUCCESS]]></return_code>
     <sign><![CDATA[5ADD48D6144DAE2ECB27F38A9C6A20CB]]></sign>
     <time_end><![CDATA[20180712190500]]></time_end>
     <total_fee><![CDATA[1]]></total_fee>
     <trade_type><![CDATA[JSAPI]]></trade_type>
     <transaction_id><![CDATA[18071219050082462314]]></transaction_id>
     </xml>
     
## 淘票票

     接口：$host:$post/router/rest
     
   |名称|API接口名称|开发情况|
   |-------------|:-------------:|:-------------:|
    |获取城市列表API|[taobao.film.data.third.party.regions.get](http://open.taobao.com/api.htm?docId=29594&docType=2)| 已开发
    |获取即将上映影片列表API|[taobao.film.data.third.party.soonshows.get](http://open.taobao.com/api.htm?docId=29600&docType=2)| 开发中
    |获取热映影片列表API|[taobao.film.data.third.party.hotshows.get](http://open.taobao.com/api.htm?docId=29607&docType=2)| 开发中
    |基于影片获取影评API|[taobao.film.data.third.party.showcomments.get](http://open.taobao.com/api.htm?docId=29903&docType=2)| 未开发
   |获取影院列表API|[taobao.film.data.third.party.cinemas.get](http://open.taobao.com/api.htm?docId=29595&docType=2)| 已开发
   |基于影院获取场次信息API|[taobao.film.data.third.party.schedules.get](http://open.taobao.com/api.htm?docId=29597&docType=2)| 未开发
   |基于场次获取座位图API|[taobao.film.data.third.party.seat.map](http://open.taobao.com/api.htm?docId=30087&docType=2)| 未开发
   |锁定座位API|[taobao.film.data.third.party.lock.seat](http://open.taobao.com/api.htm?docId=30171&docType=2)| 未开发
   |解锁座位API|[taobao.film.data.third.party.unlock.seat](http://open.taobao.com/api.htm?docId=30169&docType=2)| 未开发
   |下单出票API|[taobao.film.data.third.party.issue.order](http://open.taobao.com/api.htm?docId=30441&docType=2)| 未开发
     

