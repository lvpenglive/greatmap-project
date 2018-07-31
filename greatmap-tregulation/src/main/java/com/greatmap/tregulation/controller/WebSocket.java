package com.greatmap.tregulation.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.greatmap.common.utils.CreateFileUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.factory.InformationFactory;
import com.greatmap.tregulation.information.websocket.BaseJsonInformation;
import com.greatmap.tregulation.information.websocket.CommandTaskInformationReq;
import com.greatmap.tregulation.information.websocket.CommandTaskInformationRsp;
import com.greatmap.tregulation.information.websocket.GPSUploadInformationReq;
import com.greatmap.tregulation.information.websocket.GPSUploadInformationRsp;
import com.greatmap.tregulation.information.websocket.HandingrResultInformationReq;
import com.greatmap.tregulation.information.websocket.HandingrResultInformationRsp;
import com.greatmap.tregulation.information.websocket.LoginUserInformationReq;
import com.greatmap.tregulation.information.websocket.LoginUserInformationRsp;
import com.greatmap.tregulation.information.websocket.NoticeInformationReq;
import com.greatmap.tregulation.information.websocket.NoticeInformationRsp;
import com.greatmap.tregulation.information.websocket.SearchLvtuanGPSInformationReq;
import com.greatmap.tregulation.information.websocket.SearchLvtuanGPSInformationRsp;
import com.greatmap.tregulation.information.websocket.SendDangerInformationReq;
import com.greatmap.tregulation.information.websocket.SendDangerInformationRsp;
import com.greatmap.tregulation.information.websocket.SendSosInformationReq;
import com.greatmap.tregulation.information.websocket.SendSosInformationRsp;
import com.greatmap.tregulation.usermanger.Constants;
import com.greatmap.tregulation.usermanger.Demodata;
import com.greatmap.tregulation.usermanger.GpsUploadUtil;
import com.greatmap.tregulation.usermanger.WebsocketMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 *
 * @author lvpeng
 * @date 2017年12月
 *
 */
@Controller
@ServerEndpoint(value = "/websocket")
@Configuration
@EnableWebMvc
public class WebSocket extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static int onlineCount = 0;
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
	private static Map<WebSocket, Integer> map = new HashMap<>();

	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 * @throws IOException
	 * @throws ServletException
	 */
	@OnOpen
	public void onOpen(Session session) throws IOException, ServletException {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		map.put(this, onlineCount);

		String message = "已登录1111！当前在线人数为" + getOnlineCount();
		System.out.println(message);

	}

	/**
	 * 连接关闭调用的方法
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@OnClose
	public void onClose() throws IOException, ServletException {
		webSocketSet.remove(this); // 从set中删除
		map.remove(this);
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());

	}

	/**
	 * 收到客户端消息后调用的方法 1接收用户登录信息将值存入map 2统一发送系统通告信息，指令信息，紧急信息 3.GPS上传
	 * 
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 * @throws IOException
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {

		// WebsocketMap.add(username, this);登录时添加进在线人员map

		try {
			if (message != null && message.startsWith("{") && message.endsWith("}")) {
				BaseJsonInformation base = InformationFactory.parsingCommand(message);

				switch (base.getCode()) {
				case 110:
					NoticeInformationReq nireq = (NoticeInformationReq) base;

					NoticeInformationRsp nirsp = new NoticeInformationRsp(111, Constants.time(),
							nireq.getSerialNumber(), 0);

					sendMessage(toCommand(nirsp));
					sendMessage(toCommand(nireq));

					break;

				case 111:
					NoticeInformationRsp nirsq = (NoticeInformationRsp) base;

					break;
				case 120:
					GPSUploadInformationReq gireq = (GPSUploadInformationReq) base;

					GPSUploadInformationRsp gpsload = new GPSUploadInformationRsp(121, Constants.time(),
							gireq.getSerialNumber(), 0);
					sendMessage(toCommand(gpsload));

					// 上传GPS位置
					GpsUploadUtil.sendGpsMessage(gireq);

					break;

				case 130:
					LoginUserInformationReq loginreq = (LoginUserInformationReq) base;
					WebsocketMap.add(loginreq.getAccount(), this);

					LoginUserInformationRsp loginrsp = new LoginUserInformationRsp(Constants.time(),
							loginreq.getSerialNumber(), 0);

					sendMessage(toCommand(loginrsp));

					break;
				case 170:
					HandingrResultInformationReq handingreq = (HandingrResultInformationReq) base;

					HandingrResultInformationRsp handingrsp = new HandingrResultInformationRsp(Constants.time(),
							handingreq.getSerialNumber(), 0);

					sendMessage(toCommand(handingrsp));

					break;
				case 180:
					CommandTaskInformationReq commandreq = (CommandTaskInformationReq) base;

					CommandTaskInformationRsp commandrsp = new CommandTaskInformationRsp(Constants.time(),
							commandreq.getSerialNumber(), 0);

					sendMessage(toCommand(commandrsp));

					break;
				case 190:
					SearchLvtuanGPSInformationReq comman = (SearchLvtuanGPSInformationReq) base;

					SearchLvtuanGPSInformationRsp searchLvtuanGPSInformationRsp = new SearchLvtuanGPSInformationRsp(191, Constants.time(), comman.getSerialNumber(), 400, Demodata.lvtuanguiji());

					sendMessage(toCommand(searchLvtuanGPSInformationRsp));

					break;

				}
			

			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("客户端发送信息" + message);
		sendMessage("服务端发送信息" + message);

	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 * @throws ServletException
	 */
	@OnError
	public void onError(Session session, Throwable error) throws ServletException {

		map.remove(this);

		System.out.println("发生错误");
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		System.out.println(message);
		this.session.getBasicRemote().sendText(message);

	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocket.onlineCount--;
	}

	public static void timer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");

				for (WebSocket key : map.keySet()) {
					System.out.println("--------" + map.get(key));

					try {
						key.sendMessage("定时消息发送定时消息发送定时消息发送定时消息发送定时消息发送定时消息发送定时消息发送定时消息发送定时消息发送定时消息发送"); //
					} catch (IOException e) { // TODO Auto-generated

						e.printStackTrace();
					}

				}
			}
		}, 60000, 60000);// 设定指定的时间time,此处为1300000毫秒600000 3600000 }
	}

	/* 发送至个人 */
	public void sendMessageone(String message, String username) throws IOException, ServletException {

		WebsocketMap.getwebsocket(username).session.getBasicRemote().sendText(message);

	}
	/*
	 * 发送求救请求
	 * 
	 */
	public void sendsosinformationReq(SendSosInformationReq sosinformaitonreq) throws IOException {

		System.out.println("sosinformaitonreq" + sosinformaitonreq);
		sendMessage(toCommand(sosinformaitonreq));

	}
	/*
	 * 发送危险区域请求
	 * 
	 */
	public void senddangerinformationReq(SendDangerInformationReq dangerinformaitonreq) throws IOException {

		System.out.println("dangerinformaitonreq" + dangerinformaitonreq);
		sendMessage(toCommand(dangerinformaitonreq));

	}
	

	public static String toCommand(BaseJsonInformation command) {
		Gson gson = new Gson();
		return gson.toJson(command);
	}

}