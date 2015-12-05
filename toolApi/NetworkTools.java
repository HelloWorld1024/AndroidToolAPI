package com.akuvox.phone.utils;

import java.lang.reflect.Method;

import android.content.ContentResolver;

public final class NetworkTools {

	private static Class<?> mSystemClassType = null;
	private static Class<?> mSecureClassType = null;

	private static Method mSystemGetStringMethod = null;
	private static Method mSystemGetIntMethod = null;
	private static Method mSystemSetStringMethod = null;
	private static Method mSystemSetIntMethod = null;

	private static Method mSecureGetIntMethod = null;
	private static Method mSecureSetIntMethod = null;

	/**
	 * 判断一个字符串是否是合法的IP地址 (目前只支持IPv4)
	 * @param ipAddr
	 * 		需要判断的字符串
	 * @return
	 * 		true表示为IP地址, false表示不是
	 */
	public static boolean isValidIp(String ipAddr) {
		if (null == ipAddr || ipAddr.isEmpty()) {
			return false;
		}

		if (ipAddr.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = ipAddr.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							return true;
		}

		return false;
	}
	
	public static boolean isValidDomain(String domain) {
		if (null == domain || domain.isEmpty()) {
			return false;
		}
		if (domain.length() > 63) {
			return false;
		}
		String regularExpression = "[a-zA-Z0-9][-a-zA-Z0-9]*(\\.[a-zA-Z0-9][-a-zA-Z0-9]*)+\\.?";
		
		if (domain.matches(regularExpression)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断一个字符串是否是合法的子网掩码 (目前只支持IPv4)
	 * @param ipAddr
	 * 		需要判断的字符串
	 * @return
	 * 		true表示为合法子网掩码, false表示不是
	 */
	public static boolean isValidNetmask(String netmask) {
		if (null == netmask || netmask.isEmpty()) {
			return false;
		}

		if (netmask.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = netmask.split("\\.");
			if (Integer.parseInt(s[0]) <= 255)
				if (Integer.parseInt(s[1]) <= 255)
					if (Integer.parseInt(s[2]) <= 255)
						if (Integer.parseInt(s[3]) <= 255)
							return true;
		}

		return false;
	}
	
	/**
	 * 判断一个端口是否合法
	 * @param ipAddr
	 * 		需要判断的字符串
	 * @return
	 * 		true表示为合法 false表示不是
	 */
	public static boolean isValidPort(int port) {
		if (1024 > port ||65535 < port) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 获取Settings System的设置--字符串形式
	 * 
	 * @param key
	 *            变量名称
	 * @param defaultVal
	 *            获取失败时的默认值
	 * @return 返回获取的变量值
	 */
	public static String getStringSystem(ContentResolver contentResolver,
			String key, String defaultVal) {
		if (null == mSystemClassType && 0 != initSystemClassType()) {
			return defaultVal;
		}

		if (null == mSystemGetStringMethod && 0 != initSystemGetStringMethod()) {
			return defaultVal;
		}

		String value = defaultVal;
		try {
			value = (String) mSystemGetStringMethod.invoke(mSystemClassType,
					contentResolver, key);
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return defaultVal;
		}

		return value;
	}

	/**
	 * 获取Settings System的设置--整形形式
	 * 
	 * @param key
	 *            变量名称
	 * @param defaultVal
	 *            获取失败时的默认值
	 * @return 返回获取的变量值
	 */
	public static int getIntSystem(ContentResolver contentResolver, String key,
			int defaultVal) {
		if (null == mSystemClassType && 0 != initSystemClassType()) {
			return defaultVal;
		}

		if (null == mSystemGetIntMethod && 0 != initSystemGetIntMethod()) {
			return defaultVal;
		}

		int value = defaultVal;
		try {
			Integer tmp = (Integer) mSystemGetIntMethod.invoke(
					mSystemClassType, contentResolver, key, defaultVal);
			value = tmp.intValue();
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return defaultVal;
		}

		return value;
	}

	/**
	 * 设置Settings System的设置--字符串形式
	 * 
	 * @param key
	 *            变量名称
	 * @param defaultVal
	 *            获取失败时的默认值
	 * @return 0表示成功, 小于0表示失败
	 */
	public static int setStringSystem(ContentResolver contentResolver,
			String key, String value) {
		if (null == mSystemClassType && 0 != initSystemClassType()) {
			return -1;
		}

		if (null == mSystemSetStringMethod && 0 != initSystemSetStringMethod()) {
			return -1;
		}

		int ret = 0;
		try {
			Boolean tmp = (Boolean) mSystemSetStringMethod.invoke(
					mSystemClassType, contentResolver, key, value);
			ret = tmp.booleanValue() ? 0 : -1;
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return ret;
	}

	/**
	 * 设置Settings System的设置--整形形式
	 * 
	 * @param key
	 *            变量名称
	 * @param defaultVal
	 *            获取失败时的默认值
	 * @return 0表示成功, 小于0表示失败
	 */
	public static int setIntSystem(ContentResolver contentResolver, String key,
			int value) {
		if (null == mSystemClassType && 0 != initSystemClassType()) {
			return -1;
		}

		if (null == mSystemSetIntMethod && 0 != initSystemSetIntMethod()) {
			return -1;
		}

		int ret = 0;
		try {
			Boolean tmp = (Boolean) mSystemSetIntMethod.invoke(
					mSystemClassType, contentResolver, key, value);
			ret = tmp.booleanValue() ? 0 : -1;
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return ret;
	}

	/**
	 * 获取Settings Secure的设置--整形形式
	 * 
	 * @param key
	 *            变量名称
	 * @param defaultVal
	 *            获取失败时的默认值
	 * @return 返回获取的变量值
	 */
	public static int getIntSecure(ContentResolver contentResolver, String key,
			int defaultVal) {
		if (null == mSecureClassType && 0 != initSecureClassType()) {
			return defaultVal;
		}

		if (null == mSecureGetIntMethod && 0 != initSecureGetIntMethod()) {
			return defaultVal;
		}

		int value = defaultVal;
		try {
			Integer tmp = (Integer) mSecureGetIntMethod.invoke(
					mSecureClassType, contentResolver, key, defaultVal);
			value = tmp.intValue();
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return defaultVal;
		}
		
		return value;
	}

	/**
	 * 设置Settings Secure的设置--整形形式
	 * 
	 * @param key
	 *            变量名称
	 * @param defaultVal
	 *            获取失败时的默认值
	 * @return 0表示成功, 小于0表示失败
	 */
	public static int setIntSecure(ContentResolver contentResolver, String key,
			int value) {
		if (null == mSecureClassType && 0 != initSecureClassType()) {
			return -1;
		}

		if (null == mSecureSetIntMethod && 0 != initSecureSetIntMethod()) {
			return -1;
		}

		int ret = 0;
		try {
			Integer tmp = (Integer) mSecureSetIntMethod.invoke(
					mSecureClassType, contentResolver, key, value);
			ret = tmp.intValue();
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return ret;
	}

	// 私有方法----------------------------------------------------

	private static int initSystemClassType() {
		if (null != mSystemClassType) {
			return 0;
		}

		try {
			mSystemClassType = Class
					.forName("android.provider.Settings$System");
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return 0;
	}

	private static int initSecureClassType() {
		if (null != mSecureClassType) {
			return 0;
		}

		try {
			mSecureClassType = Class
					.forName("android.provider.Settings$Secure");
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return 0;
	}

	private static int initSystemGetStringMethod() {
		if (null != mSystemGetStringMethod) {
			return 0;
		}

		if (null == mSystemClassType && 0 != initSystemClassType()) {
			return -1;
		}

		try {
			mSystemGetStringMethod = mSystemClassType.getDeclaredMethod(
					"getString",
					ContentResolver.class, String.class);
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return 0;
	}

	private static int initSystemGetIntMethod() {
		if (null != mSystemGetIntMethod) {
			return 0;
		}

		if (null == mSystemClassType && 0 != initSystemClassType()) {
			return -1;
		}

		try {
			mSystemGetIntMethod = mSystemClassType.getDeclaredMethod(
					"getInt",
					ContentResolver.class, String.class, int.class);
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return 0;
	}

	private static int initSystemSetStringMethod() {
		if (null != mSystemSetStringMethod) {
			return 0;
		}

		if (null == mSystemClassType && 0 != initSystemClassType()) {
			return -1;
		}

		try {
			mSystemSetStringMethod = mSystemClassType.getDeclaredMethod(
					"putString",
					ContentResolver.class, String.class, String.class);
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return 0;
	}

	private static int initSystemSetIntMethod() {
		if (null != mSystemSetIntMethod) {
			return 0;
		}

		if (null == mSystemClassType && 0 != initSystemClassType()) {
			return -1;
		}

		try {
			mSystemSetIntMethod = mSystemClassType.getDeclaredMethod(
					"putInt",
					ContentResolver.class, String.class, int.class);
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return 0;
	}

	private static int initSecureGetIntMethod() {
		if (null != mSecureGetIntMethod) {
			return 0;
		}

		if (null == mSecureClassType && 0 != initSecureClassType()) {
			return -1;
		}

		try {
			mSecureGetIntMethod = mSecureClassType.getDeclaredMethod(
					"getInt",
					ContentResolver.class, String.class, int.class);
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return 0;
	}

	private static int initSecureSetIntMethod() {
		if (null != mSecureSetIntMethod) {
			return 0;
		}

		if (null == mSecureClassType && 0 != initSecureClassType()) {
			return -1;
		}

		try {
			mSecureSetIntMethod = mSecureClassType.getDeclaredMethod(
					"android.os.SystemProperties",
					ContentResolver.class, String.class, int.class);
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return -1;
		}

		return 0;
	}
}
