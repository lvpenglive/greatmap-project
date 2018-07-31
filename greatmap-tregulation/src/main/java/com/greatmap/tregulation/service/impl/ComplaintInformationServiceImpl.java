package com.greatmap.tregulation.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.tregulation.information.https.ComplaintInformationRsp;
import com.greatmap.tregulation.information.https.SearchComplaintInformationRsp;
import com.greatmap.tregulation.information.type.PTypeEnum;
import com.greatmap.tregulation.information.type.STypeEnum;
import com.greatmap.tregulation.service.ComplaintInformationService;
import com.greatmap.tregulation.usermanger.Demodata;

@Service
public class ComplaintInformationServiceImpl implements ComplaintInformationService {

	@Value("${COMPLAININFO_URL}")
	private String COMPLAININFO_URL;

	@Override
	public ComplaintInformationRsp complain(String message) {
		// TODO Auto-generated method stub
		ComplaintInformationRsp complaintInformationRsp = new ComplaintInformationRsp(400, "");

		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);
			try {
				//String complaintid = jsonObject.getString("complaintid");
				//String complaintmessage = jsonObject.getString("complaintmessage");
				
				
				System.out.println("投诉信息返回------------------"+message);
				complaintInformationRsp.setMessage("处理完成");
				complaintInformationRsp.setStatus(200);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return complaintInformationRsp;
	}

	@Override
	public SearchComplaintInformationRsp searchcomplain(String message) {
		// TODO Auto-generated method stub
		SearchComplaintInformationRsp searchComplaintInformationRsp = new SearchComplaintInformationRsp(400, "");

		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);
			try {

				String stype = jsonObject.getString("stype");

				String complaintid = jsonObject.getString("complaintid");

				String resultjson = "";
				if (stype.equals(STypeEnum.SC.getCode())) {
					/*
					 * 景区投诉信息
					 */
					System.out.println("------------------------------------------------");

					if (complaintid != null && complaintid != "") {

						try {
							// resultjson = HttpClientUtil.sendGet(COMPLAININFO_URL + complaintid);
							resultjson = Demodata.senccomplaint;

						} catch (Exception e) {
							e.printStackTrace();
							// TODO 假数据

						}

					}

				} else if (stype.equals(STypeEnum.HL.getCode())) {

					/*
					 * 酒店投诉相关
					 * 
					 */
					if (complaintid != null && complaintid != "") {

						try {
							// resultjson = HttpClientUtil.sendGet(COMPLAININFO_URL + complaintid);
							resultjson = Demodata.jiudiancomplaint;

						} catch (Exception e) {
							e.printStackTrace();
							// TODO 假数据

						}

					}

				} else if (stype.equals(STypeEnum.TL.getCode())) {
					/*
					 * 旅行社投诉
					 */
					if (complaintid != null && complaintid != "") {

						try {
							// resultjson = HttpClientUtil.sendGet(COMPLAININFO_URL + complaintid);
							resultjson = Demodata.lvxingcomplaint;

						} catch (Exception e) {
							e.printStackTrace();
							// TODO 假数据

						}

					}

				} else if (stype.equals(STypeEnum.FY.getCode())) {
					/*
					 * 农家乐投诉
					 * 
					 */
					if (complaintid != null && complaintid != "") {

						try {
							// resultjson = HttpClientUtil.sendGet(COMPLAININFO_URL + complaintid);
							resultjson = Demodata.daoyoucomplaint;

						} catch (Exception e) {
							e.printStackTrace();
							// TODO 假数据

						}

					}

				}else if (stype.equals(PTypeEnum.DY.getCode())) {
					/*
					 * 导游投诉
					 * 
					 */
					if (complaintid != null && complaintid != "") {

						try {
							// resultjson = HttpClientUtil.sendGet(COMPLAININFO_URL + complaintid);
							resultjson = Demodata.daoyoucomplaint;

						} catch (Exception e) {
							e.printStackTrace();
							// TODO 假数据

						}

					}

				}

				searchComplaintInformationRsp.setMessage(resultjson);
				searchComplaintInformationRsp.setStatus(200);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return searchComplaintInformationRsp;
	}

}
