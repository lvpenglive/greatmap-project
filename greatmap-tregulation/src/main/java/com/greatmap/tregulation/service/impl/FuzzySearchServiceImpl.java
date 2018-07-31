package com.greatmap.tregulation.service.impl;

import org.springframework.stereotype.Service;

import com.greatmap.tregulation.information.https.FuzzySearchRsp;
import com.greatmap.tregulation.service.FuzzySearchService;
@Service
public class  FuzzySearchServiceImpl implements  FuzzySearchService {

	@Override
	public FuzzySearchRsp search(String message) {
		// TODO Auto-generated method stub
		FuzzySearchRsp fs = new FuzzySearchRsp(400, "失败");
		fs.setMessage("");
		fs.setStatus(200);
		return fs;
	}

}
