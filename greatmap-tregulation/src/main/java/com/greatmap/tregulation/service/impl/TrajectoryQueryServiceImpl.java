package com.greatmap.tregulation.service.impl;

import org.springframework.stereotype.Service;

import com.greatmap.tregulation.information.https.TrajectoryQueryRsp;
import com.greatmap.tregulation.service.TrajectoryQueryService;
@Service
public class TrajectoryQueryServiceImpl implements TrajectoryQueryService {

	@Override
	public TrajectoryQueryRsp query(String name) {
		// TODO Auto-generated method stub
		TrajectoryQueryRsp tq = new TrajectoryQueryRsp(400, "失败");
		tq.setStatus(200);
		tq.setMessage("");
		return tq;
	}

}
