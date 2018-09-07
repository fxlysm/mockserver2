package cn.imovie.mockserver.sign;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSA {
	private static final Logger logger = LoggerFactory.getLogger(RSA.class);
	public static final String SIGN_SHA1WITHRSA = "SHA1WithRSA";
	public static final String SIGN_SHA256WITHRSA = "SHA256WithRSA";



	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key)keyMap.get("RSAPrivateKey");
		return Base64.encode(key.getEncoded());
	}

	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key)keyMap.get("RSAPublicKey");
		return Base64.encode(key.getEncoded());
	}

	/**
	 * RSA签名
	 * 
	 * @param content
	 *            待签名数据
	 * @param privateKey
	 *            商户私钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名值
	 */
	public static String sign(String content, String privateKey, String input_charset) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			Signature signature = Signature.getInstance(SIGN_SHA1WITHRSA);
			signature.initSign(priKey);
			signature.update(content.getBytes(input_charset));

			byte[] signed = signature.sign();
			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * RSA签名
	 *
	 * @param content
	 *            待签名数据
	 * @param privateKey
	 *            商户私钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名值
	 */
	public static String signRSA256(String content, String privateKey, String input_charset) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			Signature signature = Signature.getInstance(SIGN_SHA256WITHRSA);
			signature.initSign(priKey);
			signature.update(content.getBytes(input_charset));

			byte[] signed = signature.sign();
			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * RSA验签名检查
	 *
	 * @param content
	 *            待签名数据
	 * @param sign
	 *            签名值
	 * @param public_key
	 *            平台公钥
	 * @param input_charset
	 *            编码格式
	 * @return 布尔值
	 */
	public static boolean verify(String content, String sign, String public_key, String input_charset) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(public_key);
			if (encodedKey == null){
				return false;
			}
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			Signature signature = Signature.getInstance(SIGN_SHA1WITHRSA);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(input_charset));

			boolean bverify = signature.verify(Base64.decode(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * RSA验签名检查
	 *
	 * @param content
	 *            待签名数据
	 * @param sign
	 *            签名值
	 * @param public_key
	 *            平台公钥
	 * @param input_charset
	 *            编码格式
	 * @return 布尔值
	 */
	public static boolean verifyRSA256(String content, String sign, String public_key, String input_charset) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(public_key);
			if (encodedKey == null){
				return false;
			}
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			Signature signature = Signature.getInstance(SIGN_SHA256WITHRSA);
			
			signature.initVerify(pubKey);
			signature.update(content.getBytes(input_charset));
			
			boolean bverify = signature.verify(Base64.decode(sign));
			logger.debug("bverify:"+bverify);
			return bverify;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 秘钥长度: 2048
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPairRSA256() throws Exception {
		//随机生成密钥对
		KeyPairGenerator keyPairGen = null;
		keyPairGen = KeyPairGenerator.getInstance("RSA");

		// 初始化密钥对生成器，密钥大小为1025-2048位
		keyPairGen.initialize(2048, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 得到公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 得到私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap(2);
		keyMap.put("RSAPublicKey", publicKey);
		keyMap.put("RSAPrivateKey", privateKey);

		return keyMap;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            密文
	 * @param private_key
	 *            商户私钥
	 * @param input_charset
	 *            编码格式
	 * @return 解密后的字符串
	 */
	public static String decrypt(String content, String private_key, String input_charset) throws Exception {
		PrivateKey prikey = getPrivateKey(private_key);

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, prikey);

		InputStream ins = new ByteArrayInputStream(Base64.decode(content));
		ByteArrayOutputStream writer = new ByteArrayOutputStream();
		// rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
		byte[] buf = new byte[128];
		int bufl;

		while ((bufl = ins.read(buf)) != -1) {
			byte[] block = null;

			if (buf.length == bufl) {
				block = buf;
			} else {
				block = new byte[bufl];
				for (int i = 0; i < bufl; i++) {
					block[i] = buf[i];
				}
			}

			writer.write(cipher.doFinal(block));
		}

		return new String(writer.toByteArray(), input_charset);
	}

	/**
	 * 得到私钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;

		keyBytes = Base64.decode(key);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

		return privateKey;
	}

//
	public static  void main(String args[]) throws Exception{
//		Map<String, Object> keyMap = genKeyPair();
//		System.out.println("公钥: \n" + getPublicKey(keyMap));
//		System.out.println();
//		System.out.println("私钥： \n" + getPrivateKey(keyMap));

		String charSet = "utf-8";
		String content = "cpId=660026&cpOrderId=hb201707261812460015&currency=CNY&inputCharset=UTF-8&signType=RSA&status=0&totalFee=372368&tradeType=hhly.pay.alipay.native&transId=1163975526667054&transTime=20170726181309";
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3EJ+xUCLq3zg6qTr6LhEU1vePE5Jh1jdSGdt5jtGkzTqN8xm/lzhEqcnvCBhXk4KUx82le7U0nBDSbRKEITUSP65d7F5wFdJx0s6iqTNeHW/ppEr+vmc6yUncOl3b8A4pb7gz/U3xaHZjddBA7K339ufIzas9mx6Jc+Sjp8WWtn1xrkKQOMCJKpXds8V2C/dF2c14O9jSXivzxWOoru8FzDBpAEBVWU6DduTxZ13n1lu16C5unl7rR9L9iddTap6EIOAi2kqQuMC5gniIUZHUQBCOJhuapIMUlqcg6Uug7VOTB9AZIxypqC2a1bxgbjcKhv2FHwuKxv9RHhdWsYGNAgMBAAECggEAaYf3xyu/NU7BB3APQ7HSvwS9aYmK1UO8WGP3lbezOyHkI3iE22G9WjfW5Sjxf2rV2BbmggMyjsV5HRUrm2fZRE/ZoONl/Y9W5a701+haaGLn5PSFFx2EPOtU3NP4z5UvVMvGkJHxoLzroqKEDylhkJSRQbzufnExxWM95uoMibhnBSGASLAVjpeVlJmTYaMWA2YVcVJ/DAraov9AjIE09mAC7hxHUZu/9avsm9CtmsvD11Iv/Vg9E2wqOc+axLdD3ppqHhzM7PNGqxirON6WtNZS78NkIyTJhuExjXffqUXBdansRozU0wSv8k8vunsikf7Ik0CgZPbqkB8qBc/yAQKBgQDuxj5UD+l1ISfQH8IcgtKLgPgrrYk/5qjTJB5KR7JCmBRdH7e57Pu0jDt925NJMluYvCRHkAb5aeWKuKl6IlNyDKEQedaodd9QCGus05sFb0BThesYfctasZl1g7fVumjPsbAD1Lt3hDutYSwMqgEAinJrcJNeFByefEfQdYKhLQKBgQDERYWGMGdgA7ecbwyTG//hhTZ2dMAXwGjy6eB7Y71gBkEgdwN7ELd396OvPRgFj6jnAOFCrMWsmi0JzrB3ggKY+s3W66vEg/VcNkHEdWmMSj101tubEbqwIJCqUYkUwDydtu3OmE/u3vFDwys0NtPLpcXzZeCf9G8hqGbBW3bd4QKBgFSr6B5XJipq/3tGR8dkfNULfvW+LBWE7gkxnawpkIUc+NutC7Ha38c+kzomSyyBtwmKbjMSV+bKLqPqp+6vjXe8EsGM8xn58p6m3cgDoc1jjiQQMTYWJa6bnBeYBfC8kb5U/F3fajsPoTnyY3R6NLDEZ57B5QBPkXaCC9VYVmJhAoGAGSmYjrWR6ztOxN1vzURfr7f2XYVmQyVFzy6wuuD0nGTw4IqhhZuFCfpTn9nDRKNAUi1Is9WWmiRB9enrscaXMqmkzhL0uIL6Z9IE6HztBTFXY61QBjdlqcLjCRLe8iwbGVUkc8IPLV6jq75Pwafty0P7dtLu01LxFG2UjuF9AcECgYEA6YXHEOYxOyX7fd0WjhnkdELLvdYcSgCdwheEoUd2eNdMrL6Bqndb7L/KOruOi/uo8C1zCCLo77bc+EaS6or5ioaDpKaDWjdTGa57Wud9MP4Xhu4XSo799y3VNV5wKhLHXk6V3rYh3FQ0cM5q0mmjep3/88U5CBLpvcWP0ff/420=";
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtxCfsVAi6t84Oqk6+i4RFNb3jxOSYdY3UhnbeY7RpM06jfMZv5c4RKnJ7wgYV5OClMfNpXu1NJwQ0m0ShCE1Ej+uXexecBXScdLOoqkzXh1v6aRK/r5nOslJ3Dpd2/AOKW+4M/1N8Wh2Y3XQQOyt9/bnyM2rPZseiXPko6fFlrZ9ca5CkDjAiSqV3bPFdgv3RdnNeDvY0l4r88VjqK7vBcwwaQBAVVlOg3bk8Wdd59Zbtegubp5e60fS/YnXU2qehCDgItpKkLjAuYJ4iFGR1EAQjiYbmqSDFJanIOlLoO1TkwfQGSMcqagtmtW8YG43Cob9hR8Lisb/UR4XVrGBjQIDAQAB";
		System.out.println("\n校验结果：");
		String sign = "jnCr3JGlTEwpEtey59xbiCXvSj+8OtCpov093fvxv5HnRazPfGdxfY9U0Nr4jMt62fCSWsGvOpLAMfqy9h0sv8LDhKnaTLtIHcu58NH+3bL5BYbjzWvsmHibiAMgYOKBbme1VUY+aTAHBahMhCt8xFUcCD9NiJuWkwamOhGdb1DoQDx8aghlHyCpjC9Fr2JxuxtnLLnxFiVul8hbAjjIaB6EdLpobMfn7HpND5aG4zTl5JIqnlshi7oJkvBP3pk8WSQFGYc/o7TFAedyWGPR2KgL6W8Les3/DZTMStOd//tu0hs4BQupSgKyb4LAkQJ/s65eVHhFtsqQU3/9z8ejlQ==";
		System.out.println(verifyRSA256(content,sign,publicKey,charSet));
	}

}