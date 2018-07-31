package com.greatmap.tregulation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DangerInformationcontroller {

	@RequestMapping(value = "/danger", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String alarm(@RequestParam("message") String message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return null;
	}
}
