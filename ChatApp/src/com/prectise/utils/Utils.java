package com.prectise.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static String getDateFormat(String pattern, Date date) {
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String getCookie(String cookieName,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(cookieName)){
				return cookie.getValue();
			}
		}
		return null;
	}
}
