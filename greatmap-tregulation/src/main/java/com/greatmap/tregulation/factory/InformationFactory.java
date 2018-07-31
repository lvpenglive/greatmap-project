package com.greatmap.tregulation.factory;

import com.google.gson.Gson;
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
import com.greatmap.tregulation.information.websocket.SearchGPSInformationReq;
import com.greatmap.tregulation.information.websocket.SearchGPSInformationRsp;
import com.greatmap.tregulation.information.websocket.SearchLvtuanGPSInformationReq;
import com.greatmap.tregulation.information.websocket.SearchLvtuanGPSInformationRsp;
import com.greatmap.tregulation.information.websocket.SendDangerInformationReq;
import com.greatmap.tregulation.information.websocket.SendDangerInformationRsp;
import com.greatmap.tregulation.information.websocket.SendSosInformationReq;
import com.greatmap.tregulation.information.websocket.SendSosInformationRsp;



public class InformationFactory {
	private static Gson gson = new Gson();

	public static BaseJsonInformation parsingCommand(String json) {
		BaseJsonInformation Information = gson.fromJson(json, BaseJsonInformation.class);
		switch (Information.getCode()) {
		case 110:
			Information = gson.fromJson(json, NoticeInformationReq.class);
			break;
		case 111:
			Information = gson.fromJson(json, NoticeInformationRsp.class);
			break;
		case 120:
			Information = gson.fromJson(json, GPSUploadInformationReq.class);
			break;
		case 121:
			Information = gson.fromJson(json, GPSUploadInformationRsp.class);
			break;
		case 130:
			Information = gson.fromJson(json, LoginUserInformationReq.class);
			break;
		case 131:
			Information = gson.fromJson(json, LoginUserInformationRsp.class);
			break;
		case 140:
			Information = gson.fromJson(json, SearchGPSInformationReq.class);
			break;
		case 141:
			Information = gson.fromJson(json, SearchGPSInformationRsp.class);
			break;
		case 150:
			Information = gson.fromJson(json, SendSosInformationReq.class);
			break;
		case 151:
			Information = gson.fromJson(json, SendSosInformationRsp.class);
			break;
		case 160:
			Information = gson.fromJson(json, SendDangerInformationReq.class);
			break;
		case 161:
			Information = gson.fromJson(json, SendDangerInformationRsp.class);
			break;
		case 170:
			Information = gson.fromJson(json, HandingrResultInformationReq.class);
			break;
		case 171:
			Information = gson.fromJson(json, HandingrResultInformationRsp.class);
			break;
		case 180:
			Information = gson.fromJson(json, CommandTaskInformationReq.class);
			break;
		case 181:
			Information = gson.fromJson(json, CommandTaskInformationRsp.class);
			break;
		case 190:
			Information = gson.fromJson(json, SearchLvtuanGPSInformationReq.class);
			break;
		case 191:
			Information = gson.fromJson(json, SearchLvtuanGPSInformationRsp.class);
			break;
		}
		return Information;

	}
}