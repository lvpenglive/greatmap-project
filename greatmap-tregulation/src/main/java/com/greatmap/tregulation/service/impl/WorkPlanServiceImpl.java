package com.greatmap.tregulation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.mapper.JgWorkingplanMapper;
import com.greatmap.pojo.JgWorkingplan;
import com.greatmap.pojo.JgWorkingplanExample;
import com.greatmap.pojo.JgWorkingplanExample.Criteria;
import com.greatmap.tregulation.information.https.DeleteWorkPlaningInformationRsp;
import com.greatmap.tregulation.information.https.SearchWorkPlaningInformationRsp;
import com.greatmap.tregulation.information.https.WorkPlaningInformationRsp;
import com.greatmap.tregulation.service.WorkPlanService;
@Service
public class WorkPlanServiceImpl implements WorkPlanService {
	WorkPlaningInformationRsp wi = new WorkPlaningInformationRsp(400, "失败");
	
	@Autowired
	private JgWorkingplanMapper jgWorkingplanMapper;
	@Override
	public WorkPlaningInformationRsp creat(String message) {
		// TODO Auto-generated method stub
		
		
		
		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);			
			try {
				String jobuser = jsonObject.getString("jobuser");
				String jobcontent = jsonObject.getString("jobcontent");
				String jobdate = jsonObject.getString("jobdate");
				String jobtitle = jsonObject.getString("jobtitle");
				JgWorkingplan  record= new JgWorkingplan();
				record.setJobuser(jobuser);
				record.setJobcontent(jobcontent);
				record.setJobdate(jobdate);
				record.setJobtitle(jobtitle);
				
				jgWorkingplanMapper.insert(record);
				
				
				wi.setMessage("提交成功");
				wi.setStatus(200);
			} catch (Exception e) {
				e.printStackTrace();
				wi.setMessage("提交失败");
				wi.setStatus(400);
			}
			
			
		}
		
		
		
		return wi;
	}

	@Override
	public SearchWorkPlaningInformationRsp search(String message) {
		SearchWorkPlaningInformationRsp searchrsp =new SearchWorkPlaningInformationRsp(400, "查询失败");

		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);	
			try {
				
				String jobuser = jsonObject.getString("jobuser");
				String jobstartdate = jsonObject.getString("jobstartdate");
				String jobenddate = jsonObject.getString("jobenddate");

				JgWorkingplanExample  example = new JgWorkingplanExample();
				
				Criteria criteria = example.createCriteria();
				criteria.andJobuserEqualTo(jobuser);
				criteria.andJobdateBetween(jobstartdate, jobenddate);
				List<JgWorkingplan> list = jgWorkingplanMapper.selectByExample(example);
				
				
				
				
				/*查询信息*/
				/*if (list != null && list.size() > 0) {
					for (JgWorkingplan jgWorkingplan : list) {
						Map<String, String> searchResults = new HashMap<String, String>();

						searchResults.put("", jgWorkingplan.getSno());
						jgWorkingplan.getJobuser();
						jgWorkingplan.getJobtitle();
						jgWorkingplan.getJobdate();
						jgWorkingplan.getJobcontent();
						

					}
				}
				*/
				
				
				
				
				
				searchrsp.setStatus(200);
				searchrsp.setMessage(JsonUtils.objectToJson(list));
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}
			
			
		}
		
		
		return searchrsp;
	}

	@Override
	public DeleteWorkPlaningInformationRsp delete(String message) {
		// TODO Auto-generated method stub
		DeleteWorkPlaningInformationRsp delete = new DeleteWorkPlaningInformationRsp(400, "删除成功");
		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);
			try {
				String sno = jsonObject.getString("sno");

				
				jgWorkingplanMapper.deleteByPrimaryKey(Integer.valueOf(sno));
				delete.setStatus(200);
				delete.setMessage("成功");
			} catch (Exception e) {
				//
				e.printStackTrace();
				
			}
		}
		
		
		
		return delete;
	}

	

}
