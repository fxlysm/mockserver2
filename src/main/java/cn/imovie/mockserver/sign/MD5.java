package cn.imovie.mockserver.sign;


import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.UnsupportedEncodingException;

/**
 * Created by dell on 2017/6/27.
 */
public class MD5 {

    public static void main(String[] args) {
        String str = "charset=UTF-8&code_img_url=http://public.13322.com/25031549.jpeg&code_url=http://public.13322.com/25031549.jpeg&device_info=device_info&err_msg=http://pay.1332255.com/payCenter/core/alipay.swiftAlipayNotify.do&mch_id=7551000001&nonce_str=0210bc0d-ac8d-45e2-8990-e9c6a95a4a55&result_code=0&sign_type=MD5&status=0&version=2.0";
        System.out.println(MD5.sign(str, "&key=527508019920daf31cf31dd3e2c19232", "utf-8"));

    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if(mysign.equals(sign)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

}
