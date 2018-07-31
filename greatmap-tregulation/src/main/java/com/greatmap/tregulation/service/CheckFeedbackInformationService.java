package com.greatmap.tregulation.service;

import com.greatmap.tregulation.information.https.CheckFeedbackInformationRsp;
import com.greatmap.tregulation.information.https.SearchCheckInformationRsp;

public interface CheckFeedbackInformationService {
	CheckFeedbackInformationRsp feedBack(String information);
	SearchCheckInformationRsp Search(String checkid);

}
