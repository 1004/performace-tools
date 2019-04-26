package com.performance.example.utils;

import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public final class OSJudgementUtil {

	/**
	 * 因为判断是否为小米系统是有一个读文件的过程，所以不每次都读取判断，只第一次才判断
	 */
	public static boolean mIsJudgementBefore = false;
	public static boolean mIsMIUIOS = false;

	public static boolean mISMIUI8Before = false;
	public static boolean mISMIUI8 = false;


	/**
	 * 判断是否是MIUI系统
	 *
	 * @return true 是小米平台
	 */
	public static boolean isMIUI() {
		try {
			if (!mIsJudgementBefore) {
				mIsJudgementBefore = true;
				Properties properties = new Properties();
				properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
				mIsMIUIOS = properties.getProperty("ro.miui.ui.version.code", null) != null ||
						properties.getProperty("ro.miui.ui.version.name", null) != null ||
						properties.getProperty("ro.miui.internal.storage", null) != null;
				Log.i("test", "---" + properties.getProperty("ro.miui.ui.version.code", null) + "====" + properties.getProperty("ro.miui.ui.version.name", null) + "====" + properties.getProperty("ro.miui.internal.storage", null));
			}
		} catch (Exception e) {
		}
		return mIsMIUIOS;
	}

	public static boolean isMIUI8() {
		try {
			if (!mISMIUI8Before) {
				mISMIUI8Before = true;
				Properties properties = new Properties();
				properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
				mISMIUI8 = "V8".equalsIgnoreCase(properties.getProperty("ro.miui.ui.version.name", null)) || "6".equals(properties.getProperty("ro.miui.ui.version.code", null));
			}
		} catch (Exception e) {
		}
		return mISMIUI8;
	}

	public static boolean isShowPermission(){
		return isMIUI8() || Build.VERSION.SDK_INT >= 24;
	}

}