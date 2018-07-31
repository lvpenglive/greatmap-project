package com.greatmap.tregulation.service;

import com.greatmap.tregulation.information.https.TrajectoryQueryRsp;

public interface TrajectoryQueryService {

	TrajectoryQueryRsp query(String name);
}
