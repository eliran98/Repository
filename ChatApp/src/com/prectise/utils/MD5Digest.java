package com.prectise.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Digest {
	
	public static String generateMD5(String original) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}

		System.out.println("original:" + original);
		System.out.println("digested(hex):" + sb.toString());
		
		return sb.toString();
	}


}
