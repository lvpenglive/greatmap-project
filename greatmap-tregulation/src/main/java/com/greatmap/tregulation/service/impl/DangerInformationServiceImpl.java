package com.greatmap.tregulation.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.tregulation.information.https.DangerInformationRsp;
import com.greatmap.tregulation.information.websocket.SendDangerInformationReq;
import com.greatmap.tregulation.service.DangerInformationService;
import com.greatmap.tregulation.usermanger.Constants;
import com.greatmap.tregulation.usermanger.WebsocketMap;
@Service
public class DangerInformationServiceImpl implements DangerInformationService {

	@Override
	public DangerInformationRsp danger(String informations) {
		DangerInformationRsp dangerInformationRsp = new DangerInformationRsp(400, "");
		if (informations != null && !"".equals(informations) && !"{}".equals(informations)
				&& informations.startsWith("{") && informations.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(informations);
			try {
				String id = jsonObject.getString("id");// 
				String scenicname = jsonObject.getString("scenicname");// 
				String yktel = jsonObject.getString("yktel");// 
				String address = jsonObject.getString("address");// 	
				Double lon = Double.valueOf(jsonObject.getString("lon"));// 
				Double lat = Double.valueOf(jsonObject.getString("lat"));// 
				String fzrid = jsonObject.getString("fzrid");// 
				String fzrname = jsonObject.getString("fzrname");// 
				String fzrtel = jsonObject.getString("fzrtel");// 
				String remarks = jsonObject.getString("remarks");// 	
				SendDangerInformationReq dangerinformaitonreq = new SendDangerInformationReq(Constants.time(), Constants.serialNumber(), id, scenicname, yktel, address, lon, lat, fzrid, fzrname, fzrtel, remarks);
						
				if(WebsocketMap.getwebsocket("app")!=null) {
					WebsocketMap.getwebsocket("app").senddangerinformationReq(dangerinformaitonreq);

				}
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		return dangerInformationRsp;
	}

}
