package com.peach.dec.util.constant;

public class SystemVariable {

	private static String appName;


	public static void setAppName(String appName) {
		SystemVariable.appName = appName;
	}

	public static String getAppName() {
		return appName;
	}
}
