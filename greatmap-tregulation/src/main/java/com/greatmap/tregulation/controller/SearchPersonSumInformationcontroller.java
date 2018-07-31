package com.greatmap.tregulation.controller;

/**
 * 热力查询
 * 
 * 人员 （从业人员    报备人员   游客     黑名单人员）
 * 
 * 
 * */
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
import com.greatmap.tregulation.service.SearchPersonSumInformationService;

@Controller
public class SearchPersonSumInformationcontroller {
	@Autowired
	private SearchPersonSumInformationService searchPersonSumInformationService;

	@RequestMapping(value = "/personsum", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String complain(@RequestParam("message") String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("mpersonsumessage-----------------"+message);

		return JsonUtils.objectToJson(searchPersonSumInformationService.search(URLDecoder.decode(message, "utf-8")));

	}
}
