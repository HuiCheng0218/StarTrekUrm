package com.chngenesis.sartrekurm.utils;

/**  
 * Description: 3DES  
 */

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;


/**
 * 加密方法DESede表示是3des加密方式
 * <p>
 * 运算模式CBC,ECB。在CBC模式下使用key,向量iv;在ECB模式下仅使用key。
 * <p>
 * 填充模式NoPadding、PKCS5Padding、SSL3Padding。
 * <p>
 * 语言之间的兼容：<br>
 * 一个是C#采用CBC Mode，PKCS7 Padding,Java采用CBC Mode，PKCS5Padding Padding,<br>
 * 另一个是C#采用ECB Mode，PKCS7 Padding,Java采用ECB Mode，PKCS5Padding Padding,
 * <p>
 * 此段代码使用的CBC模式NoPadding填充方式、用字节零填充，目的是匹配C#语言中CBC模式，zeros填充方式。
 */
public class ThreeDES {

	/*
	 * 加密方法DESede表示是3des加密方式 运算模式CBC,ECB。在CBC模式下使用key,向量iv;在ECB模式下仅使用key。
	 * 填充模式NoPadding、PKCS5Padding、SSL3Padding。 语言之间的兼容： 一个是C#采用CBC Mode，PKCS7
	 * Padding,Java采用CBC Mode，PKCS5Padding Padding, 另一个是C#采用ECB Mode，PKCS7
	 * Padding,Java采用ECB Mode，PKCS5Padding Padding,
	 * 
	 * CBC 加密算法   DESede/CBC/PKCS5Padding
	 * EBC 加密算法   DESede/ECB/PKCS7Padding    
	 */
	
	private static final String algorithm_cbc = "DESede/CBC/PKCS5Padding";
	
	
	
	/**
	 * 3des加密
	 * @param str  被加密的字符串
	 * @param base64Key
	 * @param iv 向量 八位
	 * @param algorithm 加密方法／运算模式／填充模式
	 * @return 加密后密文
	 */
	public static String encrypt(String str, String base64Key, String iv) {
		try {
			SecureRandom sr = new SecureRandom();
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			DESedeKeySpec dks = new DESedeKeySpec(base64Key.getBytes("UTF-8"));
			java.security.Key securekey = keyFactory.generateSecret(dks);
			
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes("UTF-8"));
			Cipher cipher = Cipher.getInstance(algorithm_cbc); //加密
			cipher.init(Cipher.ENCRYPT_MODE, securekey, ips, sr);
			byte[] arry = cipher.doFinal(str.getBytes("UTF-8"));
			return Base64.encode(arry);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 3des解密
	 * 
	 * @param password
	 *            密文
	 *            	 * @param base64Key
	 * @param iv 向量
	 * @param algorithm 加密方法／运算模式／填充模式
	 * @return 解密后密码
	 */
	public static String decrypt(String password, String base64Key, String iv) {
		try {
			SecureRandom sr = new SecureRandom();
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			DESedeKeySpec dks = new DESedeKeySpec(base64Key.getBytes("UTF-8"));
			java.security.Key securekey = keyFactory.generateSecret(dks);
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes("UTF-8"));
			Cipher cipher = Cipher.getInstance(algorithm_cbc); //
			cipher.init(Cipher.DECRYPT_MODE, securekey, ips, sr);
			byte[] base = Base64.decode(password);
			byte[] arry = cipher.doFinal(base);
			return new String(FormateByte(arry), "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 密码加密时，填充字符串为8的倍数。
	 * <p>
	 * （此方法在模式是CBC模式，填充方式为NoPadding方式的情况下，用字节零填充.）
	 * @param str 密码
	 * @return 加密后的密文
	 */
	public static byte[] FormateData(String str)
			throws UnsupportedEncodingException {

		int yu = str.getBytes("UTF-8").length % 8;
		if (yu != 0) {
			int size = 8 - yu;
			byte[] arr = new byte[str.getBytes("UTF-8").length + size];
			byte[] data = str.getBytes("UTF-8");
			int i = 0;
			for (; i < data.length; i++) {
				arr[i] = data[i];
			}
			for (int j = 0; j < size; j++, i++) {
				arr[i] = new byte[] { 0 }[0];
			}
			return arr;
		}
		return str.getBytes("UTF-8");
	}

	/**
	 * 密码解密时，将填充的字节零去掉！
	 * <p>
	 * (此方法只在模式是CBC模式，填充方式为NoPadding方式，用字节零填充 的情况下使用。)
	 * @param arr  密文字节组
	 * @return 密码字节组
	 */
	public static byte[] FormateByte(byte[] arr) {

		int i = 0;
		for (; i < arr.length; i++) {
			if (arr[i] == new Byte("0")) {
				break;
			}
		}
		byte[] result = new byte[i];
		for (int j = 0; j < i; j++) {
			result[j] = arr[j];
		}
		return result;
	}
	
	
	
	public static void main(String[] args) {
		
		String s= encrypt("huachuangbinganshouye", "123456789abcdefghigklmnopqrst","01234567");
		System.out.println(s);
	
		String s1= decrypt(s, "123456789abcdefghigklmnopqrst","01234567");
		System.out.println(s1);
	
	}
	
	

}
