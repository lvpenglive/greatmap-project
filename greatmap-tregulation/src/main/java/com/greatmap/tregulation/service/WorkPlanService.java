package com.greatmap.tregulation.service;

import com.greatmap.tregulation.information.https.DeleteWorkPlaningInformationRsp;
import com.greatmap.tregulation.information.https.SearchWorkPlaningInformationRsp;
import com.greatmap.tregulation.information.https.WorkPlaningInformationRsp;

public interface WorkPlanService {

	WorkPlaningInformationRsp creat(String message);
	SearchWorkPlaningInformationRsp search(String message);
	DeleteWorkPlaningInformationRsp delete(String message);
}
