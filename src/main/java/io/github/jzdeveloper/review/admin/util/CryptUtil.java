package io.github.jzdeveloper.review.admin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CryptUtil {

	private CryptUtil(){}
	
	/**
	 * 加密
	 * @param source
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String encrypt(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest sha = MessageDigest.getInstance("SHA");
		MessageDigest md5 = MessageDigest.getInstance("MD5");
//		System.out.println("source:"+source);
		byte[] d0 = md5.digest(source.getBytes("utf-8"));
		byte[] d1 = sha.digest(d0);
//		System.out.println("d1:"+new String(d1));
		byte[] d2 = md5.digest(d1);
//		System.out.println("d2:"+new String(d2));
		byte[] d3 = sha.digest(d2);
//		System.out.println("d3:"+new String(d3));
		byte[] d4 = md5.digest(d3);
//		System.out.println("d4:"+new String(d4));
		return Long.toString(new String(d4).hashCode(),36).toUpperCase();
	}
	
	
}
