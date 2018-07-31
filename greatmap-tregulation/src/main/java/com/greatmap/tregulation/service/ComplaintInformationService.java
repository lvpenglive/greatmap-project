package com.greatmap.tregulation.service;

import com.greatmap.tregulation.information.https.ComplaintInformationRsp;
import com.greatmap.tregulation.information.https.SearchComplaintInformationRsp;

public interface ComplaintInformationService {
	ComplaintInformationRsp complain(String message);
	SearchComplaintInformationRsp searchcomplain(String message);
}
