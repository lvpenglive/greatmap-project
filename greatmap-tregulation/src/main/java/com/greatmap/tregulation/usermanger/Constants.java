package com.greatmap.tregulation.usermanger;

import java.io.File;
import java.util.Random;

public class Constants {


	public static long time() {

		return System.currentTimeMillis();
	}

	public static int serialNumber() {

		return new Random().nextInt(90000000) + 10000000;
	}

	public static final int currentByteIndex = 0;

	public static final long currentPackSize = 512 * 1024;
	
	public static final String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "webapp" + File.separator + "filerecv" + File.separator;

	public static final String iconfilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "webapp" + File.separator + "icon" + File.separator;

	public static final String search_dailytask_url = "http://192.168.9.150:8888/service_manage/gm-servlet/com.greatmap.servlet.ServiceServlet?id=";
	
	public static final String search_security_url = "http://192.168.9.150:8080/GMManager/rest/security?sid=";

	public static final String loginurl = "http://192.168.9.150:8080/GMManager/rest/random?up=";
}
