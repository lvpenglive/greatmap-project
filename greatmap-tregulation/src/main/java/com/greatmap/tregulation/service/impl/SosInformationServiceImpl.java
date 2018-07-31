package com.greatmap.tregulation.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.tregulation.information.https.SosInformationRsp;
import com.greatmap.tregulation.information.websocket.SendSosInformationReq;
import com.greatmap.tregulation.service.SosInformationService;
import com.greatmap.tregulation.usermanger.Constants;
import com.greatmap.tregulation.usermanger.WebsocketMap;
@Service
public class SosInformationServiceImpl implements SosInformationService {
	/**
	 * 接收求救信息  通过websocket 转发至手机端
	 * */

	@Override
	public SosInformationRsp sos(String informations) {
		SosInformationRsp sosInformationRsp = new SosInformationRsp(400, "");
		if (informations != null && !"".equals(informations) && !"{}".equals(informations)
				&& informations.startsWith("{") && informations.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(informations);
			try {
				String id = jsonObject.getString("id");// 
				String userid = jsonObject.getString("userid");// 
				String username = jsonObject.getString("username");// 
				Double lon = Double.valueOf(jsonObject.getString("lon"));// 
				Double lat = Double.valueOf(jsonObject.getString("lat"));// 
				String tel = jsonObject.getString("tel");// 
				String title = jsonObject.getString("title");// 
				String address = jsonObject.getString("address");// 
				String files = jsonObject.getString("files");// 	
				
				SendSosInformationReq  sosinformaitonreq =new SendSosInformationReq(Constants.time(), Constants.serialNumber(), id, userid, username, lon, lat, tel, title, address, files);
				if(WebsocketMap.getwebsocket("app")!=null) {
					WebsocketMap.getwebsocket("app").sendsosinformationReq(sosinformaitonreq);

				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		return sosInformationRsp;
	}

}
