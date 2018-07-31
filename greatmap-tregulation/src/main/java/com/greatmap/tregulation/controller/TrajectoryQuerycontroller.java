package com.greatmap.tregulation.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrajectoryQuerycontroller {

	/**
	 * 查询游客，导游，监管人员，轨迹接口
	 * 
	 * */
	@RequestMapping(value = "/trajectory", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String alarm(@RequestParam("message") String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("查询人员身份标识" + URLDecoder.decode(message,"utf-8"));

		return "查询成功";

	}

}
