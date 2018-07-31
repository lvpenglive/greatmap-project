package com.greatmap.tregulation.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.tregulation.information.https.SearchDataAnalysisInformationRsp;
import com.greatmap.tregulation.service.DataAnalysisService;
import com.greatmap.tregulation.usermanger.Demodata;
@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

	@Override
	public SearchDataAnalysisInformationRsp passengerflow(String message) {
		// TODO Auto-generated method stub
		SearchDataAnalysisInformationRsp searchDataAnalysisInformationRsp = new SearchDataAnalysisInformationRsp(400,
				"");
		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);
			try {
				String scenicid = jsonObject.getString("scenicid");
				
				
				
				
				searchDataAnalysisInformationRsp.setStatus(200);
				searchDataAnalysisInformationRsp.setMessage(Demodata.passengerflow);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return searchDataAnalysisInformationRsp;
	}

	@Override
	public SearchDataAnalysisInformationRsp origin(String message) {
		SearchDataAnalysisInformationRsp searchDataAnalysisInformationRsp = new SearchDataAnalysisInformationRsp(400,
				"");
		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);
			try {
				String scenicid = jsonObject.getString("scenicid");
				
				
				
				
				searchDataAnalysisInformationRsp.setStatus(200);
				searchDataAnalysisInformationRsp.setMessage(Demodata.origin);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return searchDataAnalysisInformationRsp;
	}

}
