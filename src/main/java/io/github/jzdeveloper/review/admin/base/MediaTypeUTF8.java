package io.github.jzdeveloper.review.admin.base;

import javax.ws.rs.core.MediaType;

/**
 * 提供相关的mediatype,并采用utf-8的格式
 * @author mazhyb
 *
 */
public final class MediaTypeUTF8 {
	
	private MediaTypeUTF8(){}
	
	public static final String APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON+";charset=utf-8";
	
	public static final String TEXT_PLAIN_UTF8 = MediaType.TEXT_PLAIN +";charset=utf-8";
	
	

}
