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
import com.greatmap.tregulation.service.DataAnalysisService;
@Controller
public class DataAnalysiscontroller {

	/**
	 * 数据分析
	 * 
	 * 1.客流量
	 * 2.客源地 origin
	 * */
	@Autowired
	private DataAnalysisService dataAnalysisService;
	@RequestMapping(value = "/passengerflow", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String passengerflow(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		return JsonUtils.objectToJson(dataAnalysisService.passengerflow(URLDecoder.decode(message,"utf-8")));

	}
	@RequestMapping(value = "/origin", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String origin(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		return JsonUtils.objectToJson(dataAnalysisService.origin(URLDecoder.decode(message,"utf-8")));

	}
}
