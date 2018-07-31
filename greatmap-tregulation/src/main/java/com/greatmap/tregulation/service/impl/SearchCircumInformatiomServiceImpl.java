package com.greatmap.tregulation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.ChineseName;
import com.greatmap.common.utils.GPSLocation;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.common.utils.LocationUtils;
import com.greatmap.tregulation.information.https.SearchCircumInformationRsp;
import com.greatmap.tregulation.service.SearchCircumInformationService;

@Service
public class SearchCircumInformatiomServiceImpl implements SearchCircumInformationService {

	@Override
	public SearchCircumInformationRsp search(String message) {
		// TODO Auto-generated method stub
		SearchCircumInformationRsp searchCircumInformationRsp = new SearchCircumInformationRsp(400, message);
		
		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);
			HashMap<String, Object> hashMapaa = new HashMap<>();

			try {

				Double lat = jsonObject.getDouble("lat");
				Double lng = jsonObject.getDouble("lng");
				Double distance = jsonObject.getDouble("distance");

				
				/*
				 * 危险区域
				 * 
				 * */	
				
				ArrayList<HashMap> arrayList = new ArrayList<>();
				ChineseName sa = new ChineseName();
				GPSLocation as = new GPSLocation();
				as.setLatitude(lat);
				as.setLongitude(lng);
				for (int i = 0; i < 1; i++) {
					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put("code", "wxqy-000011");
					hashMap.put("lat", LocationUtils.GetRandomLocation(as, distance).getLatitude());
					hashMap.put("lng", LocationUtils.GetRandomLocation(as, distance).getLongitude());
					hashMap.put("name", "龙门石窟");
					hashMap.put("method", "");
					hashMap.put("phoneno", "136" + (int) ((Math.random() * 9 + 1) * 10000000));
					hashMap.put("remarks", "危险区域，游客禁止入内");
					hashMap.put("uname", sa.getName());
					
					HashMap<String, Object> hashMap1 = new HashMap<>();
					ArrayList<HashMap> arrayListaa = new ArrayList<>();
					HashMap<String, Object> hashMapaass = new HashMap<>();
					hashMapaass.put("id", "980714804102561792");
					hashMapaass.put("name", "负责人");
					arrayListaa.add(hashMapaass);
					
					hashMap1.put("gmbbaseroles", arrayListaa);
					hashMap1.put("nickname", sa.getName());
					hashMap1.put("phoneno", "15325565895");
					hashMap1.put("lat", LocationUtils.GetRandomLocation(as, distance).getLatitude());
					hashMap1.put("lng", LocationUtils.GetRandomLocation(as, distance).getLongitude());
					
					hashMap.put("userid", hashMap1);

					arrayList.add(hashMap);
				}

				
				
				
				/*
				 * 设备设施
				 * 
				 * */	
				
				
				ArrayList<HashMap> arrayList1 = new ArrayList<>();
				ChineseName sa1 = new ChineseName();
				GPSLocation as1 = new GPSLocation();
				as1.setLatitude(lat);
				as1.setLongitude(lng);
				for (int i = 0; i < 1; i++) {
					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put("code", "wxqy-000011");
					hashMap.put("lat", LocationUtils.GetRandomLocation(as1, distance).getLatitude());
					hashMap.put("lng", LocationUtils.GetRandomLocation(as1, distance).getLongitude());
					hashMap.put("name", "球机摄像头");
					hashMap.put("isuseable", false);
					hashMap.put("type", "jksb");
					
					HashMap<String, Object> hashMaptime = new HashMap<>();
					hashMaptime.put("date", "");
					hashMaptime.put("day", "");
					hashMaptime.put("hours", "");
					hashMaptime.put("minutes", "");
					hashMaptime.put("month", "");
					hashMaptime.put("seconds", "");
					hashMaptime.put("time", "1525662115569");
					hashMaptime.put("timezoneOffset", "");
					hashMaptime.put("year", "");

					hashMap.put("lastcheck", hashMaptime);
					
					HashMap<String, Object> hashMap1 = new HashMap<>();

					ArrayList<HashMap> arrayListaa = new ArrayList<>();
					HashMap<String, Object> hashMapaass = new HashMap<>();
					hashMapaass.put("id", "980714804102561792");
					hashMapaass.put("name", "负责人");
					arrayListaa.add(hashMapaass);

					hashMap1.put("gmbbaseroles", arrayListaa);
					hashMap1.put("nickname", sa.getName());
					hashMap1.put("phoneno", "15325565845");
					hashMap1.put("lat", LocationUtils.GetRandomLocation(as1, distance).getLatitude());
					hashMap1.put("lng", LocationUtils.GetRandomLocation(as1, distance).getLongitude());
					
					hashMap.put("userid", hashMap1);

					arrayList1.add(hashMap);
				}
				
				
				/*
				 * 普通人员
				 * 
				 * */	
				
				
				ArrayList<HashMap> arrayList2 = new ArrayList<>();
				GPSLocation as2 = new GPSLocation();
				as2.setLatitude(lat);
				as2.setLongitude(lng);
				for (int i = 0; i < 1; i++) {
					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put("id", "986048078190477312");
					hashMap.put("lat", LocationUtils.GetRandomLocation(as2, distance).getLatitude());
					hashMap.put("lng", LocationUtils.GetRandomLocation(as2, distance).getLongitude());
					
					ArrayList<HashMap> arrayListaa = new ArrayList<>();
					HashMap<String, Object> hashMapaass = new HashMap<>();
					hashMapaass.put("id", "980714804102561792");
					hashMapaass.put("name", "保安");
					arrayListaa.add(hashMapaass);


					hashMap.put("gmbbaseroles", arrayListaa);
					hashMap.put("nickname", sa.getName());
					hashMap.put("phoneno", "15369952760");
					hashMap.put("profile", "");

					

					arrayList2.add(hashMap);
				}
				
				
				
				
				hashMapaa.put("wxqy", arrayList);
				hashMapaa.put("sbss", arrayList1);
				hashMapaa.put("bsry", arrayList2);
				
				searchCircumInformationRsp.setStatus(200);
				searchCircumInformationRsp.setMessage(JsonUtils.objectToJson(hashMapaa));
				
				
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		
		
		
		}
		
		
		return searchCircumInformationRsp;
	}
		
		
		
	

}
