package com.greatmap.tregulation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.IDUtils;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.mapper.JgCheckmessageMapper;
import com.greatmap.pojo.JgCheckmessage;
import com.greatmap.pojo.JgCheckmessageExample;
import com.greatmap.pojo.JgCheckmessageExample.Criteria;
import com.greatmap.tregulation.information.https.CheckFeedbackInformationRsp;
import com.greatmap.tregulation.information.https.SearchCheckInformationRsp;
import com.greatmap.tregulation.information.type.CTypeEnum;
import com.greatmap.tregulation.information.type.STypeEnum;
import com.greatmap.tregulation.service.CheckFeedbackInformationService;
import com.greatmap.tregulation.usermanger.Demodata;

@Service
public class CheckFeedbackInformationServiceImpl implements CheckFeedbackInformationService {

	@Autowired
	private JgCheckmessageMapper checkmessageMapper;
	@Value("${DANGERINFO_URL}")
	private String DANGERINFO_URL;
	@Value("${FACILITIESINFO_URL}")
	private String FACILITIESINFO_URL;

	@Override
	public CheckFeedbackInformationRsp feedBack(String information) {
		// TODO Auto-generated method stub
		CheckFeedbackInformationRsp ck = new CheckFeedbackInformationRsp(400, "失败");

		if (information != null && !"".equals(information) && !"{}".equals(information) && information.startsWith("{")
				&& information.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(information);
			try {
				String checktype = jsonObject.getString("checktype");
				String checkmessage = jsonObject.getString("checkmessage");
				String checkuser = jsonObject.getString("checkuser");
				JgCheckmessage record = new JgCheckmessage();

				record.setCheckid(String.valueOf(IDUtils.genItemId()));
				record.setcheckmessage(checkmessage);
				record.setChecktype(checktype);
				record.setCheckuser(checkuser);

				checkmessageMapper.insert(record);
				ck.setStatus(200);
				ck.setMessage("提交成功");

			} catch (Exception e) {
				// TODO: handle exception
				ck.setStatus(400);
				ck.setMessage("提交失败");
				e.printStackTrace();
			}

		} else {
			ck.setStatus(400);
			ck.setMessage("提交失败");
		}

		return ck;
	}

	@Override
	public SearchCheckInformationRsp Search(String message) {

		SearchCheckInformationRsp sc = new SearchCheckInformationRsp(400, "失败");

		if (message != null && !"".equals(message) && !"{}".equals(message) && message.startsWith("{")
				&& message.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(message);
			String checkstype = jsonObject.getString("checkstype");
			String checkctype = jsonObject.getString("checkctype");
			String checkid = jsonObject.getString("checkid");
			System.out.println("checkid" + checkid);
			System.out.println("checkstype" + checkstype);
			System.out.println("checkctype" + checkctype);

			System.out.println("" + checkstype == STypeEnum.SC.getCode());
			System.out.println("" + checkstype.equals(STypeEnum.SC.getCode()));
			System.out.println("" + checkstype == STypeEnum.SC.getCode().toString());
			String resultjson = "";
			if (checkstype.equals(STypeEnum.SC.getCode())) {
				/*
				 * 景区相关 1.危险区域
				 */
				System.out.println("------------------------------------------------");

				if (checkctype.equals(CTypeEnum.DR.getCode())) {
					if (!Demodata.key) {
						try {
							resultjson = HttpClientUtil.sendGet(DANGERINFO_URL + checkid);

						} catch (Exception e) {
							e.printStackTrace();
							// TODO 假数据
							resultjson = Demodata.danger;

						}
					} else {
						resultjson = Demodata.danger;
					}

				} else if (checkctype.equals(CTypeEnum.FS.getCode()) || checkctype.equals(CTypeEnum.FG.getCode())
						|| checkctype.equals(CTypeEnum.XS.getCode()) || checkctype.equals(CTypeEnum.XB.getCode())
						|| checkctype.equals(CTypeEnum.JS.getCode())) {
					// 消防设施
					System.out.println("消防设施---------监控设备-------------识别设备------");
					if (!Demodata.key) {
						try {
							resultjson = HttpClientUtil
									.sendGet(FACILITIESINFO_URL + checkctype + "*merchantid=" + checkid);
							System.out.println("消防设施---------监控设备-------------识别设备" + resultjson);

						} catch (Exception e) {
							e.printStackTrace();

							if (checkctype.equals(CTypeEnum.XS.getCode())) {
								resultjson = Demodata.xiaofangshebei;

							} else if (checkctype.equals(CTypeEnum.XB.getCode())) {
								resultjson = Demodata.shibieshebei;

							} else {
								resultjson = Demodata.jiankongshebei;

							}

						}
					} else {
						// TODO 假数据

						if (checkctype.equals(CTypeEnum.XS.getCode())) {
							resultjson = Demodata.xiaofangshebei;

						} else if (checkctype.equals(CTypeEnum.XB.getCode())) {
							resultjson = Demodata.shibieshebei;

						} else {
							resultjson = Demodata.jiankongshebei;

						}
					}

					//压力预测
				}else if(checkctype.equals(CTypeEnum.YL.getCode())) {
					
					resultjson=Demodata.yaliyuce;
					
					
					
					//景区边界
				}else if(checkctype.equals(CTypeEnum.BJ.getCode())) {
					
					resultjson=Demodata.border;
					
					
					
					
				}

				/*
				 * 酒店相关
				 * 
				 */

			} else if (checkstype.equals(STypeEnum.TL.getCode())) {
				/*
				 * 旅行社
				 */
				if (checkctype.equals(CTypeEnum.JB.getCode())) {
					resultjson = Demodata.cheliang_lxs;

				}
				
				
				

			} else if (checkstype.equals(STypeEnum.FY.getCode())) {
				/*
				 * 农家乐
				 * 
				 */

			}

			sc.setStatus(200);
			sc.setMessage(resultjson);

		} else {
			sc.setStatus(400);
			sc.setMessage("查詢失败");
		}

		return sc;
	}

}
