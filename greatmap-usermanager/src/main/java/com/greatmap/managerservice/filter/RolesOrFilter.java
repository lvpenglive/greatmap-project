package com.greatmap.managerservice.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 
 * @author lvpen
 * @date: 
 * */
public class RolesOrFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest arg0, ServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		Subject subject = getSubject(arg0, arg1);
		
		String[] roles = (String[]) arg2;
		
		if(roles == null || roles.length == 0) {
			return true;
			
		}
		for(String role :roles) {
			if(subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}

	
}
