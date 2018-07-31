package com.greatmap.tregulation.service;

import com.greatmap.tregulation.information.https.SearchDataAnalysisInformationRsp;

public interface DataAnalysisService {

	SearchDataAnalysisInformationRsp passengerflow(String message);
	SearchDataAnalysisInformationRsp origin(String message);
}
