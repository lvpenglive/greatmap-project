package com.greatmap.tregulation.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.service.BasicInformationService;
/**
 * 基本信息（景区，旅馆，旅行社。。。。。）
 * 
 * */
@Controller
public class BasicInformationcontroller {

	@Autowired
	private BasicInformationService basicInformationService;
	
	private static String result;

	@RequestMapping(value = "/basic", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String alarm(@RequestParam("message") String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		System.out.println("---message------"+message);

		
		System.out.println("----message转码-----"+URLDecoder.decode(message, "utf-8"));
		
		
		
		return JsonUtils.objectToJson(basicInformationService.redrectdata(URLDecoder.decode(message,"utf-8")));

	}
}
