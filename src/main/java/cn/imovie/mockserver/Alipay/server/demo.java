//package cn.imovie.mockserver.Alipay.server;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayTradePayRequest;
//import com.alipay.api.response.AlipayTradePayResponse;
//
//public class demo {
//
//    public static void demo2(){
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
//        AlipayTradePayRequest request = new AlipayTradePayRequest();
//        request.setBizContent("{" +
//                "\"out_trade_no\":\"20150320010101001\"," +
//                "\"scene\":\"bar_code\"," +
//                "\"auth_code\":\"28763443825664394\"," +
//                "\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
//                "\"subject\":\"Iphone6 16G\"," +
//                "\"buyer_id\":\"2088202954065786\"," +
//                "\"seller_id\":\"2088102146225135\"," +
//                "\"total_amount\":88.88," +
//                "\"trans_currency\":\"USD\"," +
//                "\"settle_currency\":\"USD\"," +
//                "\"discountable_amount\":8.88," +
//                "\"body\":\"Iphone6 16G\"," +
//                "      \"goods_detail\":[{" +
//                "        \"goods_id\":\"apple-01\"," +
//                "\"goods_name\":\"ipad\"," +
//                "\"quantity\":1," +
//                "\"price\":2000," +
//                "\"goods_category\":\"34543238\"," +
//                "\"body\":\"特价手机\"," +
//                "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
//                "        }]," +
//                "\"operator_id\":\"yx_001\"," +
//                "\"store_id\":\"NJ_001\"," +
//                "\"terminal_id\":\"NJ_T_001\"," +
//                "\"extend_params\":{" +
//                "\"sys_service_provider_id\":\"2088511833207846\"," +
//                "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
//                "\"card_type\":\"S0JP0000\"" +
//                "    }," +
//                "\"timeout_express\":\"90m\"," +
//                "\"auth_confirm_mode\":\"COMPLETE：转交易支付完成结束预授权;NOT_COMPLETE：转交易支付完成不结束预授权\"," +
//                "\"terminal_params\":\"{\\\"key\\\":\\\"value\\\"}\"" +
//                "  }");
//        try {
//            AlipayTradePayResponse response = alipayClient.execute(request);
//            if(response.isSuccess()){
//                System.out.println("调用成功");
//            } else {
//                System.out.println("调用失败");
//            }
//        }catch (AlipayApiException e){
//            System.out.println(e);
//        }
//
//
//    }
//
//    public static void main(String[] args){
//        demo2();
//    }
//}
