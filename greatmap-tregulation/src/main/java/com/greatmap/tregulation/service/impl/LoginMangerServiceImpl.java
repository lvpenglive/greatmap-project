package com.greatmap.tregulation.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.LoadiIconUtil;
import com.greatmap.tregulation.service.LoginMangerService;
import com.greatmap.tregulation.usermanger.Constants;

@Service

public class LoginMangerServiceImpl implements LoginMangerService {

	@Value("${LOGIN_URL}")
	private String LOGIN_URL;
	@Value("${SEARCH_SECURITY_URL}")
	private String SEARCH_SECURITY_URL;
	@Value("${LOADICON}")
	private String LOADICON;
	@Override
	public Map<String, Object> loginManger(String username, String password) {
		Map<String, Object> usermap = new HashMap<String, Object>();
		
		
		
		
		String sid = searchl(username,password);
		if(sid!=null) {
			usermap=searchs(sid);
		}else {
			usermap.put("status", "400");
		}
		
		

		
		
		
		return usermap;
	}

	public String searchl(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("LOGIN_URL" + LOGIN_URL);
		if (LOGIN_URL == null || LOGIN_URL == "") {
			String loginurl = Constants.loginurl;
			System.out.println("loginurl---" + loginurl);
			LOGIN_URL = loginurl;

		}

		System.out.println(username + password);
		String json = HttpClientUtil.sendGet(LOGIN_URL + username + "," + password);

		String sid = null;
		System.out.println("Login Result = " + json);
		if (json != null && !"".equals(json) && json.startsWith("{") && json.endsWith("}")) {
			Map<String, String> jsonl = (Map<String, String>) JSON.parse(json);
			sid = jsonl.get("sid");
		}

		return sid;
	}

	public Map<String, Object> searchs(String queryString) {

		System.out.println(queryString);
		String json = HttpClientUtil.sendGet(SEARCH_SECURITY_URL + queryString);
		Map<String, Object> userDate = new HashMap<String, Object>();
		if (json != null && !"".equals(json) && json.startsWith("{") && json.endsWith("}")) {
			userDate = parsingUserInfo(json);
			userDate.put("wsurl", "ws://111.198.29.175:8899/websocket");
			userDate.put("status", "200");

			System.out.println(userDate);
		} else {
			userDate.put("status", "400");
		}

		System.out.println("Login Result = " + userDate);

		return userDate;
	}

	public Map<String, Object> parsingUserInfo(String json) {

		String Wsurl = "wsurl";
		// 用户性别
		String Sex = "sex";
		// 用户座机
		String Zzdh = "zzdh";
		// 用户学历
		String Education = "education";
		// 用户名
		String Username = "username";
		// 用户工作电话
		String Gzdh = "gzdh";
		// 用户状态
		String Status = "status";
		// 用户地址
		String Jtzz = "jtzz";
		// 用户身份证
		String Sfz = "sfz";
		// 用户头像
		String Photo = "photo";

		// 用户真实姓名
		String Realname = "realname";
		// 用户手机
		String Yddh = "yddh";

		// 用户岗位
		// String Positions = "positions";
		// 用户所属
		String Group = "group";
		// 用户群组List
		String Groups = "groups";
		// 群组名称
		String Groupname = "groupname";
		// 用户功能List
		String Functions = "functions";
		// 功能名称
		String Functionname = "functionname";

		// 采集服务
		String services = "services";

		// 采集权限
		String roles = "roles";

		JSONObject jsonObject = JSONObject.parseObject(json);
		Map<String, Object> userDate = new HashMap<String, Object>();
		Map<String, Object> userDatephoto = new HashMap<String, Object>();

		userDate.put(Sex, jsonObject.getBoolean(Sex));
		userDate.put(Zzdh, jsonObject.getString(Zzdh));
		userDate.put(Education, jsonObject.getString(Education));
		userDate.put(Username, jsonObject.getString(Username));
		userDate.put(Gzdh, jsonObject.getString(Gzdh));
		userDate.put(Jtzz, jsonObject.getString(Jtzz));
		userDate.put(Sfz, jsonObject.getString(Sfz));
		// userDatephoto.put("usericon",
		// jsonObject.getJSONObject(Photo).getString("filename"));

		String photoUrl = LOADICON + jsonObject.getJSONObject(Photo).getString("filepath");
		
		
		
		
		
		String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));
		userDate.put(Photo, fileName.replace("/", ""));

		// String fileName =jsonObject.getJSONObject(Photo).getString("filename");
		String filePath = Constants.iconfilePath + jsonObject.getString(Username);
		File file = LoadiIconUtil.saveUrlAs(photoUrl, filePath, fileName, "GET");
		System.out.println("photoUrl" + photoUrl);
		System.out.println("fileName" + fileName);
		System.out.println("filePath" + filePath);
		System.out.println("Run ok!/n<BR>Get URL file " + file);

		/*
		 * // 将用户信息存进map if
		 * (WebsocketMap.getLoginuser().containsKey(jsonObject.getString(Username))) {
		 * WebsocketMap.getLoginuser().replace(jsonObject.getString(Username),
		 * jsonObject.getString(Realname) + "," + jsonObject.getString(Yddh)); } else {
		 * WebsocketMap.getLoginuser().put(jsonObject.getString(Username),
		 * jsonObject.getString(Realname) + "," + jsonObject.getString(Yddh)); }
		 * 
		 * userDate.put(Realname, jsonObject.getString(Realname)); userDate.put(Group,
		 * jsonObject.getJSONObject(Group).getString(Groupname));
		 */
		List<String> groupList = new ArrayList<String>();
		for (int i = 0; i < jsonObject.getJSONArray(Groups).size(); i++) {
			groupList.add(jsonObject.getJSONArray(Groups).getJSONObject(i).getString(Groupname));
		}
		//userDate.put(Groups, groupList);
		groupList = new ArrayList<String>();
		for (int i = 0; i < jsonObject.getJSONArray(Functions).size(); i++) {
			groupList.add(jsonObject.getJSONArray(Functions).getJSONObject(i).getString(Functionname));
		}
		//userDate.put(Functions, groupList);
		jsonObject.clear();

		return userDate;
	}

}
