package com.greatmap.tregulation.service;

import com.greatmap.tregulation.information.https.DangerInformationRsp;

public interface DangerInformationService {

	DangerInformationRsp danger(String message);
}
