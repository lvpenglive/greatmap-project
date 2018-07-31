package com.greatmap.tregulation.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.tregulation.information.https.SearchLvtuanInformationRsp;
import com.greatmap.tregulation.service.SearchLvtuanInformationService;
import com.greatmap.tregulation.usermanger.Demodata;

@Service
public class SearchLvtuanInformationServiceImpl implements SearchLvtuanInformationService {

	@Override
	public SearchLvtuanInformationRsp search(String message) {
		// TODO Auto-generated method stub
		SearchLvtuanInformationRsp searchLvtuanInformationRsp = new SearchLvtuanInformationRsp(400, "");
		
		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);
			String json="";
			try {
				

				//String checkid = jsonObject.getString("checkid");
		
				
				searchLvtuanInformationRsp.setStatus(200);
				searchLvtuanInformationRsp.setMessage(Demodata.lvtuanht1);
				
			}catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		return searchLvtuanInformationRsp;
	}

}
