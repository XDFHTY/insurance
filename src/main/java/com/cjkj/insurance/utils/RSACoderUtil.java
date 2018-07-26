package com.cjkj.insurance.utils;


import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA安全编码组件
 *  
 * @version 1.0
 * @since 1.0
 */
public abstract class RSACoderUtil extends CoderUtil {

	public static String privateKey;

	@Value("${privateKey}")
	public static void setApiURL(String privateKey) {
		RSACoderUtil.privateKey = privateKey;
	}




	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
//	private static ResourceBundle config = ResourceBundle.getBundle("config/config");
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	//开发环境私钥
//	public static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN90tXnUT20Qtm+I3dbKS7QQrs2l" +
//			"o8QcPpspR6G9WZeGjxSTRnkX3hdLIjmaiehXffC8PeYGAUXn/rdblnCSQrm4FdpWEY//d0rQSgGd" +
//			"8cs2t8Kpl4DvbP/+qGBxrVD3Q7Smc0J35pTArNOgwy6Sl4k3h7V0hRIZjfxXOkAzO5zJAgMBAAEC" +
//			"gYEAgu+eTy8LA3uhiyWF6BBN38tOwo3msklingTIRovvbYyZVpMd3mMP7lJGUb6uRIjP8To8gwbN" +
//			"xCq25LY0Ju5tTdGl2lKiUbRXE8zUi9vZwd6WfXYi3WWmTBYjcoVqeWsI8tlx5o/swzN6lFYvs2eC" +
//			"Hfs0Drs2R+Ta56gbIxIGkCECQQD0VIZBSoKuXbO6x+2zSzkPzbSa9qi2OlCatv+b0WJjCZHmDwAB" +
//			"P4lOtkpKOxtSnXsMqQ0Is7lX4NTLedRAyuMNAkEA6iDzSG/vLj5Eb4OzJUpIxFEn56sMFX7GuDi6" +
//			"6xP6D8qiupmrjXFHrjAifCSF6nbkaga1puojVqL6b1I4NWmhrQJBAIK2OYDykMkh3gZd8T/LTYKz" +
//			"5RxGO2oJ9pdesY61zPH467Htcm44hIe0pDfkOTDQiUTzp8JxDAYEhTM6QSBMqn0CQAsFZA5b3olx" +
//			"uuz46RzvQz+ihltcbOQyJI6VdQ8N0K6fnktkYnP1CifD8kufuIIR+KyZBkIGMYWphFprJ2Q0Rb0C" +
//			"QCm4ONB07E/e8/bneyvck3mzyT11dWEIt5a2YSAvqXxXwLvcfbdEvSjMG0Z9UHN4bK9FEtv+KmYB" +
//			"Tg8w1My9Swo=";

//	//生产环境私钥
//	public static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO6EbBtBromYnqUPvSq8a4+R+tH40eWC0" +
//			"446GrZs700TfGoF0xcQv+6TKuXMbUu0UmcH5ri/DYlite3toJ4JrrDd20FCSFTOWsABit5hCJRNmynGU7TpRHWjrLSXOaZd0R5nxiXTvjnR" +
//			"4BJz5Q8dJ3b1h+pjLbnAoItPZG+GaPOpAgMBAAECgYEAuVLI0LDLFXwxH91HxPHbvRTWxtjG9cYd7G93G/EuSjvuuk5GQrCwAIX2mdCpx12" +
//			"XfRhli3xe3zWEWBb/amvpf2EK4prSqzWzv3SXFIKPAY4BQyQ0SiA6kUb//D/t+t12smRTlLt5TvOpAH8FIhe87Yosxz9lXEnugy2yI3p2oG" +
//			"UCQQD6UgyseWJcgQTT5Y9c8LzaiSc4U+g8WMmE3j/V+gCsIMoTqGHh+aIuGGwUK/S/BYb1nfuCGlkNyqNqUCjLeFTTAkEA8+3Qv2N7NUfZt" +
//			"odqTndAPY9G3+iNGfirr2SeOzeUTVbSh/qg7u/zlLfRXjtWTMhtqK1FO+CgtLY3tW7YDJG4EwJAK01QRfHFkyz6cdFvSGuYr9E0CKlzLiVJ" +
//			"zwNHVbOmtCAD9PyW2il95a1x3Ndxwi2pmAmZPXtjVmBsfnKZbAFH4wJBAL3HDF2a3ES7vdqQyFh71vMOAao6l2zZV1mCAsk3mJ4DKpC4oXT" +
//			"EItJVoQKbT601UnulMvQ+80kla3ow3s4IoRkCQGjQseOaJrrAPVdNUmPrtMUOfdnJeHH69/hxRTLv9FsVVAAz80Xy3Y1JTcTBefJtQFz/Vm" +
//			"FS/fWkMSE6JxHTlpk=";
	public static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfdLV51E9tELZviN3Wyku0EK7NpaPEHD6bKUehvVmXho8Uk0Z5F94XSyI5monoV33wvD3mBgFF5/63W5ZwkkK5uBXaVhGP/3dK0EoBnfHLNrfCqZeA72z//qhgca1Q90O0pnNCd+aUwKzToMMukpeJN4e1dIUSGY38VzpAMzucyQIDAQAB";
	public static String str = "TLbbzQOcdq1eZVIkS7JHUo1knGgAQWex3fOBk935ZSmT9eVJQASOy7MESMLKnZDF";




	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            签名数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKey);

		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);

		return encryptBASE64(signature.sign());
	}


	public static String sign(String data) throws Exception {
		return sign(data.getBytes(), privateKey);
	}
	
	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * 
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {

		// 解密由base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);

		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);

		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));
	}
	public static boolean verify(String data, String sign) throws Exception {
      boolean flag= false;
		try{
			flag =verify(data.getBytes(), publicKey, sign);

		}catch (Exception e){
			System.out.println("验证签名是异常 "+e.getMessage()+e.getStackTrace());

		}

		return flag;
	}
	
	/**
	 * 解密<br>
	 * 用私钥解密 http://www.5a520.cn http://www.feng123.com
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(privateKey);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * 解密<br>
	 * 用公钥解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(publicKey);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * 加密<br>
	 * 用公钥加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data) throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(publicKey);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * 加密<br>
	 * 用私钥加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(privateKey);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * 取得私钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);

		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 取得公钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);

		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);

		KeyPair keyPair = keyPairGen.generateKeyPair();

		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);

		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	public static void main(String[] args) throws Exception {	
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN90tXnUT20Qtm+I3dbKS7QQrs2l" +
				"o8QcPpspR6G9WZeGjxSTRnkX3hdLIjmaiehXffC8PeYGAUXn/rdblnCSQrm4FdpWEY//d0rQSgGd" +
				"8cs2t8Kpl4DvbP/+qGBxrVD3Q7Smc0J35pTArNOgwy6Sl4k3h7V0hRIZjfxXOkAzO5zJAgMBAAEC" +
				"gYEAgu+eTy8LA3uhiyWF6BBN38tOwo3msklingTIRovvbYyZVpMd3mMP7lJGUb6uRIjP8To8gwbN" +
				"xCq25LY0Ju5tTdGl2lKiUbRXE8zUi9vZwd6WfXYi3WWmTBYjcoVqeWsI8tlx5o/swzN6lFYvs2eC" +
				"Hfs0Drs2R+Ta56gbIxIGkCECQQD0VIZBSoKuXbO6x+2zSzkPzbSa9qi2OlCatv+b0WJjCZHmDwAB" +
				"P4lOtkpKOxtSnXsMqQ0Is7lX4NTLedRAyuMNAkEA6iDzSG/vLj5Eb4OzJUpIxFEn56sMFX7GuDi6" +
				"6xP6D8qiupmrjXFHrjAifCSF6nbkaga1puojVqL6b1I4NWmhrQJBAIK2OYDykMkh3gZd8T/LTYKz" +
				"5RxGO2oJ9pdesY61zPH467Htcm44hIe0pDfkOTDQiUTzp8JxDAYEhTM6QSBMqn0CQAsFZA5b3olx" +
				"uuz46RzvQz+ihltcbOQyJI6VdQ8N0K6fnktkYnP1CifD8kufuIIR+KyZBkIGMYWphFprJ2Q0Rb0C" +
				"QCm4ONB07E/e8/bneyvck3mzyT11dWEIt5a2YSAvqXxXwLvcfbdEvSjMG0Z9UHN4bK9FEtv+KmYB" +
				"Tg8w1My9Swo=";

		String str = "TLbbzQOcdq1eZVIkS7JHUo1knGgAQWex3fOBk935ZSmT9eVJQASOy7MESMLKnZDF";
		System.out.println("签名内容：" + str);
		String signStr = sign(str.getBytes(), privateKey);
		System.out.println("签名结果：" + signStr);
		
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfdLV51E9tELZviN3Wyku0EK7NpaPEHD6bKUeh" +
				"vVmXho8Uk0Z5F94XSyI5monoV33wvD3mBgFF5/63W5ZwkkK5uBXaVhGP/3dK0EoBnfHLNrfCqZeA" +
				"72z//qhgca1Q90O0pnNCd+aUwKzToMMukpeJN4e1dIUSGY38VzpAMzucyQIDAQAB";
		
		boolean b = verify(str.getBytes(), publicKey, signStr);
		System.out.println("验签结果：" + b);
	}
}