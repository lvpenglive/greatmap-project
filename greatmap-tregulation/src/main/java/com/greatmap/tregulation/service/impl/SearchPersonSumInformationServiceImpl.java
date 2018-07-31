package com.greatmap.tregulation.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.SearchPersonSumInformationRsp;
import com.greatmap.tregulation.information.type.PTypeEnum;
import com.greatmap.tregulation.information.type.STypeEnum;
import com.greatmap.tregulation.service.SearchPersonSumInformationService;
import com.greatmap.tregulation.usermanger.Demodata;

@Service
public class SearchPersonSumInformationServiceImpl implements SearchPersonSumInformationService {
	@Value("${SENICEPERSONINFO_URL}")
	private String SENICEPERSONINFO_URL;

	@Override
	public SearchPersonSumInformationRsp search(String informations) {
		// TODO Auto-generated method stub

		SearchPersonSumInformationRsp searchPersonSumInformationRsp = new SearchPersonSumInformationRsp(200, "");

		System.out.println("informations" + informations);
		if (informations != null && !"".equals(informations) && !"{}".equals(informations)
				&& informations.startsWith("{") && informations.endsWith("}")) {
			JSONObject jsonObject = JSONObject.parseObject(informations);
			try {
				String stype = jsonObject.getString("stype");// 景区 旅行社---
				String persontype = jsonObject.getString("ptype");
				String id = jsonObject.getString("id");// 景区名称

				String json = "";

				if (stype.equals(STypeEnum.SC.getCode())) {
					/*
					 * 景区相关
					 */

					if (persontype.equals(PTypeEnum.JD.getCode()) || persontype.equals(PTypeEnum.BE.getCode())
							|| persontype.equals(PTypeEnum.HD.getCode()) || persontype.equals(PTypeEnum.SP.getCode())) {

						System.out.println("景区执法监督---------报备人员--------重点游客");

						if (!Demodata.key) {

							try {
								json = HttpClientUtil.sendGet(SENICEPERSONINFO_URL + persontype + "*merchantid=" + id);
								System.out.println("景区人员---json-------------------------" + json);

							} catch (Exception e) {
								e.printStackTrace();
								// TODO 假数据

								if (persontype.equals(PTypeEnum.JD.getCode())) {
									json = Demodata.jiandurenyuan;

								} else if (persontype.equals(PTypeEnum.BE.getCode())) {
									json = Demodata.baobei;

								} else if (persontype.equals(PTypeEnum.HD.getCode())) {
									json = Demodata.zhongdianyouke;

								} else {
									json = Demodata.suoyourenyuan;
								}

							}

						} else {

							System.out.println("---------121212----------------");
							if (persontype.equals(PTypeEnum.JD.getCode())) {
								json = Demodata.jiandurenyuan;

							} else if (persontype.equals(PTypeEnum.BE.getCode())) {
								json = Demodata.baobei;

							} else if (persontype.equals(PTypeEnum.HD.getCode())) {
								json = Demodata.zhongdianyouke;

							} else if (persontype.equals(PTypeEnum.SP.getCode())) {
								json = Demodata.suoyourenyuan;
							}

						}

					}

				} else if (stype.equals(STypeEnum.HL.getCode())) {

					/*
					 * 酒店相关
					 */

				} else if (stype.equals(STypeEnum.TL.getCode())) {
					/*
					 * 旅行社
					 */
					if (persontype.equals(PTypeEnum.SP.getCode())) {
						json = Demodata.congyerenyuan_lxs;

					} else if (persontype.equals(PTypeEnum.DY.getCode())) {
						json = Demodata.daoyou;

					} else if (persontype.equals(PTypeEnum.JS.getCode())) {
						json = Demodata.jiashi;

					} else if (persontype.equals(PTypeEnum.LT.getCode())) {
						json = Demodata.lvtuannameqb;

					} else if (persontype.equals(PTypeEnum.SN.getCode())) {
						json = Demodata.lvtuannamesn;

					} else if (persontype.equals(PTypeEnum.SW.getCode())) {
						json = Demodata.lvtuannamesw;

					} else if (persontype.equals(PTypeEnum.JW.getCode())) {
						json = Demodata.lvtuannamejw;

					} else if (persontype.equals(PTypeEnum.GAT.getCode())) {
						json = Demodata.lvtuannamegat;

					} else if (persontype.equals(PTypeEnum.LTP.getCode())) {

					String lvtuansn = "[{\"id\":\"SNYK0001\",\"name\":\"郭德纲\",\"tel\":\"13717503841\",\"ecp\":\"李明月\",\"ecptel\":\"13817503841\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.558301,\"lng\":112.481594},{\"lat\":34.55882,\"lng\":112.482565},{\"lat\":34.557647,\"lng\":112.482806},{\"lat\":34.55244,\"lng\":112.4925},{\"lat\":34.55944,\"lng\":112.4985}],\"ptype\":\"DY\"},"
								+ "{\"id\":\"SNYK0002\",\"name\":\"郭麒麟\",\"tel\":\"13717503842\",\"ecp\":\"李明月\",\"ecptel\":\"13817503842\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.658301,\"lng\":112.481594},{\"lat\":34.65782,\"lng\":112.482565},{\"lat\":34.657647,\"lng\":112.482806},{\"lat\":34.65744,\"lng\":112.4825},{\"lat\":34.65844,\"lng\":112.4925}],\"ptype\":\"DY\"},"
								+ "{\"id\":\"SNYK0003\",\"name\":\"岳云鹏\",\"tel\":\"13717503843\",\"ecp\":\"李明月\",\"ecptel\":\"13817503843\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.758301,\"lng\":112.481594},{\"lat\":34.75782,\"lng\":112.482565},{\"lat\":34.757647,\"lng\":112.482806},{\"lat\":34.75744,\"lng\":112.4825},{\"lat\":34.75844,\"lng\":112.4925}],\"ptype\":\"YK\"},"
								+ "{\"id\":\"SNYK0004\",\"name\":\"于谦\",\"tel\":\"13717503844\",\"ecp\":\"李明月\",\"ecptel\":\"13817503844\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.858301,\"lng\":112.481594},{\"lat\":34.85782,\"lng\":112.482565},{\"lat\":34.857647,\"lng\":112.482806},{\"lat\":34.85744,\"lng\":112.4825},{\"lat\":34.85844,\"lng\":112.4925}],\"ptype\":\"YK\"},"
								+ "{\"id\":\"SNYK0005\",\"name\":\"姚晨\",\"tel\":\"13717503845\",\"ecp\":\"孙红雷\",\"ecptel\":\"13817503845\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.958301,\"lng\":112.481594},{\"lat\":34.95782,\"lng\":112.482565},{\"lat\":34.957647,\"lng\":112.482806},{\"lat\":34.95744,\"lng\":112.4825},{\"lat\":34.95844,\"lng\":112.4925}],\"ptype\":\"LD\"},"
								+ "{\"id\":\"SNYK0006\",\"name\":\"范冰冰\",\"tel\":\"13717503846\",\"ecp\":\"范晨晨\",\"ecptel\":\"13817503846\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.458301,\"lng\":112.481594},{\"lat\":34.55782,\"lng\":112.482565},{\"lat\":34.557647,\"lng\":112.482806},{\"lat\":34.55744,\"lng\":112.4825},{\"lat\":34.55844,\"lng\":112.4925}],\"ptype\":\"YK\"},"
								+ "{\"id\":\"SNYK0007\",\"name\":\"李晨\",\"tel\":\"13717503847\",\"ecp\":\"王力宏\",\"ecptel\":\"13817503847\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.358301,\"lng\":112.481594},{\"lat\":34.35782,\"lng\":112.482565},{\"lat\":34.357647,\"lng\":112.482806},{\"lat\":34.35744,\"lng\":112.4825},{\"lat\":34.35844,\"lng\":112.4925}],\"ptype\":\"YK\"},"
								+ "{\"id\":\"SNYK0008\",\"name\":\"邓超\",\"tel\":\"13717503848\",\"ecp\":\"王三\",\"ecptel\":\"13817503848\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.258301,\"lng\":112.481594},{\"lat\":34.55782,\"lng\":112.482565},{\"lat\":34.557647,\"lng\":112.482806},{\"lat\":34.55744,\"lng\":112.4825},{\"lat\":34.55844,\"lng\":112.4925}],\"ptype\":\"YK\"},"
								+ "{\"id\":\"SNYK0009\",\"name\":\"王宝强\",\"tel\":\"13717503849\",\"ecp\":\"李四\",\"ecptel\":\"13817503849\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.158301,\"lng\":112.481594},{\"lat\":34.15782,\"lng\":112.482565},{\"lat\":34.157647,\"lng\":112.482806},{\"lat\":34.15744,\"lng\":112.4825},{\"lat\":34.15844,\"lng\":112.4925}],\"ptype\":\"YK\"},"
								+ "{\"id\":\"SNYK0010\",\"name\":\"马蓉\",\"tel\":\"13717503850\",\"ecp\":\"宋小宝\",\"ecptel\":\"13817503850\",\"pro\":\"河南\",\"ltid\":'"+id+"',\"ltname\":\"洛阳龙门石窟豪华一日游\",\"gps\":[{\"lat\":34.058301,\"lng\":112.481594},{\"lat\":34.05782,\"lng\":112.482565},{\"lat\":34.057647,\"lng\":112.482806},{\"lat\":34.05744,\"lng\":112.4825},{\"lat\":34.05844,\"lng\":112.4925}],\"ptype\":\"YK\"}]";

						json = lvtuansn;

					} else if (persontype.equals(PTypeEnum.ZYT.getCode())) {
						json = Demodata.lvtuannamezy;

					}

				} else if (stype.equals(STypeEnum.FY.getCode())) {
					/*
					 * 农家乐
					 */

				}

				searchPersonSumInformationRsp.setMessage(json);
				searchPersonSumInformationRsp.setStatus(200);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return searchPersonSumInformationRsp;
	}

}
