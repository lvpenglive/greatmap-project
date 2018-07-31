package com.greatmap.tregulation.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.BasicInformationRsp;
import com.greatmap.tregulation.information.type.STypeEnum;
import com.greatmap.tregulation.service.BasicInformationService;
import com.greatmap.tregulation.usermanger.Demodata;

@Service
public class BasicInformationServiceImpl implements BasicInformationService {

	@Value("${BASICINFO_URL}")
	private String BASICINFO_URL;

	@Override
	public BasicInformationRsp redrectdata(String information) {
		BasicInformationRsp ba = new BasicInformationRsp(400, "失败");
		System.out.println("-------information------" + information);
		if (information != null && !"".equals(information) && !"{}".equals(information) && information.startsWith("{")
				&& information.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(information);
			try {
				/*
				 * 根据类型调用接口数据
				 */
				String datatype = jsonObject.getString("type");
				String dataname = jsonObject.getString("id");
				System.out.println("------------" + datatype + dataname);
				String json = "";
				if (!Demodata.key) {
					try {
						if (datatype != null && datatype != "") {
							json = HttpClientUtil.sendGet(BASICINFO_URL + datatype + "*id=" + dataname);
						} else {
							json = HttpClientUtil.sendGet(BASICINFO_URL + datatype + "");
						}
					} catch (Exception e) {
						e.printStackTrace();
						ba.setMessage(Demodata.jqjson);
						ba.setStatus(200);
					}
				}
				System.out.println("BASICINFO Result = " + json);
				if (json != null && !"".equals(json)) {
					ba.setStatus(200);
					ba.setMessage(json);
				} else {
					if (datatype.equals(STypeEnum.SC.getCode())) {
						json = Demodata.jqjson;
					} else if (datatype.equals(STypeEnum.HL.getCode())) {
						json = Demodata.jdjson;
					} else if (datatype.equals(STypeEnum.FY.getCode())) {
						json = Demodata.njljson;
					} else if (datatype.equals(STypeEnum.TL.getCode())) {
						json = Demodata.lxsjson;
					}
					ba.setMessage(json);
					ba.setStatus(200);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ba.setMessage("失败");
				ba.setStatus(400);
			}
		}
		return ba;
	}
}
