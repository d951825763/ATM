package com.feicui.atm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public final class CommonUtil {

	private static Scanner input = new Scanner(System.in);//全局输入流
	
	//从properties中获取正则表达式
	public static String getRegex(String key) {
		if(key == null) {
			return key;
		}
		String path = "res" + File.separator + "regex.properties";
		String str = getPropertiesFromFile(path).getProperty(key);
		return str == null ? key : str;
	}
	
	//从Properties中获取界面消息
	public static String getMessage(String key) {
		if(key == null) {
			return key;
		}
		String path = "res" + File.separator + "message.properties";
		String str = getPropertiesFromFile(path).getProperty(key);
		return str == null ? key : str;//没找到，返回原文本
	}
	
	private static Properties getPropertiesFromFile(String path) {
		Properties prop = new Properties();//创建Properties对象
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			prop.load(fis);//从文件输入流中加载Properties
		}catch(IOException e) {
			CommonUtil.printStackTrace(e);
		}finally {
			try {
				fis.close();
			}catch(IOException e) {
				CommonUtil.printStackTrace(e);
			}
		}
		return prop;
	}
	//判断一个字符串是否是数字
	/*public static boolean isDigit(String str) { 
        try {
        	Double.valueOf(str);
        } catch (NumberFormatException e) {
        	return false;
        }
        return true;

        String figure = CommonUtil.getRegex("digit");
        return str.matches(figure);
    }*/
	public static void printStackTrace(Exception e) {
		//自定义异常信息，防止与抛出的异常混淆
		String str = e.getClass() + ":" + e.getMessage() + "\n";
		
		for(StackTraceElement item : e.getStackTrace()) {
			str += item + "\n";
		}
		System.out.println(str);
	}
	//打印语句，若有存在的键值对，打印对应的值，否则打印原值.
	//无内容时不打印，内容为空时换行
	
	public static void printLine(String key,Object ...args) {
		//多参函数写法，不定参写在最后
		String value = CommonUtil.getMessage(key);
		if(value != null) {
			System.out.println(String.format(value, args));
		}
	}
	
	//输出一句提示语，然后获取用户输入；若key为空则不打印提示语
	public static String inputLine(String key,Object ... args) {
		if(!(key ==null || key.isEmpty())) {//key不为空
			CommonUtil.printLine(key, args);
		}
		return CommonUtil.input.nextLine().trim();//去除左右空格
	}
}