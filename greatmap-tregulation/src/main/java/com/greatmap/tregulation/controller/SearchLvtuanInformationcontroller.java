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

import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.service.SearchLvtuanInformationService;
@Controller
public class SearchLvtuanInformationcontroller {
	@Autowired
	private SearchLvtuanInformationService searchLvtuanInformationService;
	@RequestMapping(value = "/lvtuan", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String lvtuan(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		return JsonUtils.objectToJson(searchLvtuanInformationService.search(URLDecoder.decode(message,"utf-8")));

	}
	@RequestMapping(value = "/lvtuanht", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String lvtuanht(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


			
		
		

		return JsonUtils.objectToJson(searchLvtuanInformationService.search(URLDecoder.decode(message,"utf-8")));

	}
	@RequestMapping(value = "/lvtuanxc", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String lvtuanxc(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		return JsonUtils.objectToJson(searchLvtuanInformationService.search(URLDecoder.decode(message,"utf-8")));

	}
}
