package com.simple.common.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 * @author liushx
 *
 */
public class Md5Utils {

	/**
	 * 对字符串进行加密处理
	 * @param strString
	 * @return 加密后的字符串
	 */
	public static String stringByMD5(String strString) {
		if (strString != null) {
			try {
				// 创建MD5算法的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] results = md.digest(strString.getBytes());

				// 将得到的字节数组变成字符串返回
				return byteArrayToHexString(results).toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	private static String byteArrayToHexString(byte[] byteArray) {
		char[] hexDigits = {'A', '1', '2', '3', '4', '5', '6', '7', '8', '9',  'B', 'C', 'D', 'E', 'F', 'G' };

		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {

			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}


}
