package com.greatmap.tregulation.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.service.LoginMangerService;

@Controller
public class LoginMangercontroller {
	@Autowired
	private LoginMangerService loginMangerService;
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String alarm(@RequestParam("account") String account,@RequestParam("password") String password, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		System.out.println("name"+account);
		System.out.println("password"+password);
		
					
		HashMap<String, String> hashMap1 = new HashMap<>();

		hashMap1.put("wsurl", "ws://39.106.157.136:8899/websocket");
		hashMap1.put("education", "");
		hashMap1.put("sfz", "");
		hashMap1.put("sex", "true");
		hashMap1.put("gzdh", "");
		hashMap1.put("photo", "");
		hashMap1.put("jtzz", "");
		hashMap1.put("zzdh", "");
		hashMap1.put("username", account);
		hashMap1.put("status", "200");
		
		
		
		//return JsonUtils.objectToJson(loginMangerService.loginManger(account, password));

		
		
		
		
		return JsonUtils.objectToJson(hashMap1);

	

	}
}
