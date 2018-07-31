package com.greatmap.tregulation.service;

import java.util.Map;

import org.springframework.stereotype.Service;

public interface LoginMangerService {

	Map<String, Object> loginManger(String name,String password);
}
