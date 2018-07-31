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
import com.greatmap.tregulation.service.CheckFeedbackInformationService;

/**
 * 
 * 检查上报反馈信息
 * 
 * 
 * */
@Controller
public class CheckFeedbackInformationcontroller {

	@Autowired
	private CheckFeedbackInformationService feedbackInformationService;
	@RequestMapping(value = "/checkcreat", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String check(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		System.out.println("---------"+feedbackInformationService.feedBack(URLDecoder.decode(message,"utf-8")));

		return JsonUtils.objectToJson(feedbackInformationService.feedBack(URLDecoder.decode(message,"utf-8")));

	}
	@RequestMapping(value = "/checkseach", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String seach(@RequestParam("message")  String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		return JsonUtils.objectToJson(feedbackInformationService.Search(URLDecoder.decode(message,"utf-8")));

	}
}
