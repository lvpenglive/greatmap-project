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

import com.greatmap.tregulation.service.FuzzySearchService;
/**
 * 
 * 模糊搜索
 * */
@Controller
public class FuzzySearchcontroller {
	@Autowired
	private FuzzySearchService searchHotInformationService;

	@RequestMapping(value = "/fuzzysearch", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String alarm(@RequestParam("message") String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("message" + message);

		return "查询成功" + searchHotInformationService.search(URLDecoder.decode(message,"utf-8"));

	}

}
