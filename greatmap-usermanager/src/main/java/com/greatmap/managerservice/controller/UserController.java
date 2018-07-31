package com.greatmap.managerservice.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greatmap.managerservice.entity.User;
@Controller
public class UserController {

	@RequestMapping(value="/subLogin",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String subLogin(User user) {
		
		
		System.out.println("----------"+user.getUsername()+user.getPassword());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token= new UsernamePasswordToken(user.getUsername(), user.getPassword());
		
		try {
			token.setRememberMe(user.getRememberMe());
			subject.login(token);

		} catch (Exception e) {
			return e.getMessage();
		}
		if(subject.hasRole("admin")) {
			//return "有权";
		}
		if(subject.isPermitted("user:delete")) {
			//return "有删除权限";
		}
		
		return "登陆成功";
		//return "登陆成功";
		
	}
	//@RequiresRoles("admin")
	@RequestMapping(value = "/testRole",method = RequestMethod.GET)
	@ResponseBody
	public String testRole() {
		return "testRole success";
		
	}
	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/testRole1",method = RequestMethod.GET)
	@ResponseBody
	public String testRole1() {
		return "testRole1 success";
		
	}
	@RequestMapping(value = "/testPerms",method = RequestMethod.GET)
	@ResponseBody
	public String testPerms() {
		return "testPerms success";
		
	}
	@RequestMapping(value = "/testPerms1",method = RequestMethod.GET)
	@ResponseBody
	public String testPerms1() {
		return "testPerms1 success";
		
	}
	
}
