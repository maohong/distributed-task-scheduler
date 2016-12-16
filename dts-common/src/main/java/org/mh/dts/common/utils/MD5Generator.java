package org.mh.dts.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Generator {

	private final static String algorithm = "MD5";

	public static String generateTokenString(String seed) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(seed.getBytes());
			byte[] bytes = md.digest();
			return toHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Converts a byte to hex digit and writes to the supplied buffer
	 */
	private static void byte2hex(byte b, StringBuffer buf) {
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		int high = ((b & 0xf0) >> 4);
		int low = (b & 0x0f);
		buf.append(hexChars[high]);
		buf.append(hexChars[low]);
	}

	/*
	 * Converts a byte array to hex string
	 */
	private static String toHexString(byte[] block) {
		StringBuffer buf = new StringBuffer();
		int len = block.length;
		for (int i = 0; i < len; i++) {
			byte2hex(block[i], buf);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		String token = MD5Generator.generateTokenString("223" + "235");
		System.out.println(token);
		System.out.println( MD5Generator.generateTokenString("123456"));
		//04D6A1B9827531872B8BE15797AA9116
		//04D6A1B9827531872B8BE15797AA9116
	}
}
