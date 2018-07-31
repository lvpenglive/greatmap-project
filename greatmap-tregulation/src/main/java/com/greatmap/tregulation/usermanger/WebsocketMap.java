package com.greatmap.tregulation.usermanger;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import com.greatmap.tregulation.controller.WebSocket;



public class WebsocketMap {	
	private static  Map<String, WebSocket> connections = new HashMap<>();
	private static int onlineCount = 0;
	private static  Map<String, String> loginuser = new HashMap<>();

	private static  Map<String, String> fileupload = new HashMap<>();

	public static Map<String, String> getFileupload() {
		System.out.println(fileupload);
		return fileupload;
	}


	public static void addFileupload(String oldname,String newname) {
		WebsocketMap.fileupload.put(oldname, newname);
	}
	

	
	public static Map<String, String> getLoginuser() {
		return loginuser;
	}

	public static void setLoginuser(Map<String, String> loginuser) {
		WebsocketMap.loginuser = loginuser;
	}

	public static int getOnlineCount() {
		return onlineCount;
	}

	public static Map<String, WebSocket> getConnections() {
		return WebsocketMap.connections;
	}

	public static void add(String username,WebSocket websocket) throws ServletException {
		connections.put(username, websocket);
	}
	public static void remove(String username) throws ServletException {
		connections.remove(username);
	}
	public static WebSocket getwebsocket(String username) throws ServletException {
		// 根据map的key获取map的value
		 WebSocket key = null;
			for (Entry<String, WebSocket> entry : connections.entrySet()) {
				if (username.equals(entry.getKey())) {
					key = entry.getValue();
				}
			}
			return key;
		
	}
	/*
	 * 是否在线
	 * 
	 * */
	public static boolean online(String username) {
		if(connections.containsKey(username)) {
			return true;
		}
		
		return false;
		
	}
	
	
	

}
