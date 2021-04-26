package com.billionfun.common.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ResourcesUtil {
	public static String getBaseUrl(){
		return "http://testresource.ibaking.com:8090";
	}
	
	public static String getHeaderPicDir(String userId,Date userCreateDate){
		String createDateStr = DateUtil.formatByPattern(userCreateDate, "yyyy-MM-dd");
		return createDateStr+"/"+userId+"/header";
	}
	
	public static String getHeaderPicUrl(String userId,Date userCreateDate){
		String createDateStr = DateUtil.formatByPattern(userCreateDate, "yyyy-MM-dd");
		return getBaseUrl()+"/"+createDateStr+"/"+userId+"/header";
	}
	
	public static String getUserTempDir(String userId,Date userCreateDate){
		String createDateStr = DateUtil.formatByPattern(userCreateDate, "yyyy-MM-dd");
		return createDateStr+"/"+userId+"/temp";
	}
	
	public static String getTempDir(){
		return "temp";
	}
}
