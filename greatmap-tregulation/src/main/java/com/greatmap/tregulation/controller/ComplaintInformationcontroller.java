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
import com.greatmap.tregulation.service.ComplaintInformationService;
@Controller
public class ComplaintInformationcontroller {

	/**
	 * 
	 * 投诉意见
	 * 
	 * */
	@Autowired
	private ComplaintInformationService complaintInformationService;
	@RequestMapping(value = "/complain", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String complain(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		return JsonUtils.objectToJson(complaintInformationService.complain(URLDecoder.decode(message,"utf-8")));

	}
	@RequestMapping(value = "/complainsearch", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String search(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		return JsonUtils.objectToJson(complaintInformationService.searchcomplain(URLDecoder.decode(message,"utf-8")));

	}
}
