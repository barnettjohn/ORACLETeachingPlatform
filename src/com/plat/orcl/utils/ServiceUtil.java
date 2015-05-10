package com.plat.orcl.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;


public class ServiceUtil {
	
	public static String md5(String str){
		
		String res = "";
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			res = Base64.encodeBase64String(md.digest(str.getBytes()));
			
			} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
		
		return res;
	}
	
	//获取当前最邻近的课程时间
	//public static 
}
