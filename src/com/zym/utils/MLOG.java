package com.zym.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zym
 * @since 2019-10-24
 * format [yyyy-MM-dd HH:mm:ss:SSS LogLevel] tid packname.classname methodname:line  msg
 * */
public class MLOG {
	
	private static final boolean INFO = true;
	private static final boolean DEBUG = true;
	
	private static final boolean WARNNING = true;
	private static final boolean ERROR = true;
	
	/**
	 * stack level for obtain info of caller
	 * */
	private static final int LEVEL = 4;
	
	private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
	
	public static void i(String msg) {
		if(INFO) {
			System.out.printf("%s %s\n", getLogHeadInfo("I"), msg);
		}
	}
	
	public static void d(String msg) {
		if(DEBUG) {
			System.out.printf("%s %s\n", getLogHeadInfo("D"), msg);
		}
	}
	
	public static void w(String msg) {
		if(WARNNING) {
			System.out.printf("%s %s\n", getLogHeadInfo("W"), msg);
		}
	}
	
	public static void e(String msg) {
		if(ERROR) {
			System.out.printf("%s %s\n", getLogHeadInfo("E"), msg);
		}
	}
	
	private static String getLogHeadInfo(String level) {
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		buf.append(getCurTimeText());
		buf.append(" ");
		buf.append(level);
		buf.append("]");
		buf.append(" ");
		buf.append(Thread.currentThread().getId());
		buf.append(" ");
		buf.append(getClassInfo());
		buf.append(" ");
		return buf.toString();
	}
	
	private static String getCurTimeText() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
		return format.format(date);
	}
	
	private static String getClassInfo() {
		StringBuffer buf = new StringBuffer();
		Thread curThread = Thread.currentThread();
		if(curThread == null) {
			return buf.toString();
		}
		
		StackTraceElement[] element = curThread.getStackTrace();
		if(element != null && element.length > LEVEL) {
			buf.append(element[LEVEL].getClassName());
			buf.append(" ");
			buf.append(element[LEVEL].getMethodName());
			buf.append(":");
			buf.append(element[LEVEL].getLineNumber());
		}
		return buf.toString();
	}
}
