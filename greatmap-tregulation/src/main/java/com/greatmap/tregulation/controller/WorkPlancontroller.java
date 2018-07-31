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

import com.greatmap.tregulation.service.WorkPlanService;
/**
 * 工作安排接口  填报  查询  
 * */
@Controller
public class WorkPlancontroller {

	@Autowired
	private WorkPlanService workplanservice;
	@RequestMapping(value = "/workcreat", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String workcreat(@RequestParam("message") String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("message" + message);

		return "创建成功" + workplanservice.creat(URLDecoder.decode(message,"utf-8"));

	}
	@RequestMapping(value = "/worksearch", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String worksearch(@RequestParam("message") String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("message" + message);

		return "查詢成功" + workplanservice.search(URLDecoder.decode(message,"utf-8"));

	}
	@RequestMapping(value = "/workdelete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String workdelete(@RequestParam("message") String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("message" + message);

		return "刪除成功" + workplanservice.delete(URLDecoder.decode(message,"utf-8"));

	}
}
