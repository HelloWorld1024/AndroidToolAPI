package com.akuvox.phone.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public final class PackageTools {

	/**
	 * 获取当前应用的版本号
	 * 
	 * @param context
	 *            上下文参数
	 * @return 返回版本号
	 */
	public static String getVersion(Context context) {
		if (null == context) {
			return null;
		}

		String version = null;
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			version = info.versionName;
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return null;
		}
		return version;
	}

	/**
	 * 获取当前应用的包名
	 * 
	 * @param context
	 *            上下文参数
	 * @return 返回版本号
	 */
	public static String getPackageName(Context context) {
		if (null == context) {
			return null;
		}

		String packageName = null;
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			packageName = info.packageName;
		} catch (Exception e) {
			Log.e("Catch an exception = " + e);
			return null;
		}
		return packageName;
	}
}
