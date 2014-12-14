package io.github.jzdeveloper.review.admin.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class CryptUtilTest {

	@Test
	public void testCrypt() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String source = "111qqq";
		String result = CryptUtil.encrypt(source);
		System.out.println("source:"+source+",result:"+result);
	}
	
}
