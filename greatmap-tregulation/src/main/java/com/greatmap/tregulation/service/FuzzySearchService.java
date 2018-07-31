package com.greatmap.tregulation.service;

import com.greatmap.tregulation.information.https.FuzzySearchRsp;

public interface  FuzzySearchService {

	FuzzySearchRsp  search(String message);
}
