import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.BasicInformationReq;
import com.greatmap.tregulation.information.type.STypeEnum;

public class BasicInformationtest {

	@Test
	public void scenicSearch() throws UnsupportedEncodingException {

		
		
		
		BasicInformationReq ba = new BasicInformationReq("", "");

		ba.setId("980999433271377920");;
		ba.setType(STypeEnum.SC.getCode());
		
		System.out.println(JsonUtils.objectToJson(ba));
		String message = JsonUtils.objectToJson(ba);

		String json = HttpClientUtil
				.sendGet("http://192.168.9.150:8899/basic?message=" + URLEncoder.encode(message, "utf-8"));

		/*
		 * if (json != null && !"".equals(json) && !"{}".equals(json) &&
		 * json.startsWith("{") && json.endsWith("}")) { JSONObject jsonObject =
		 * JSONObject.parseObject(json); try { String status =
		 * jsonObject.getString("status"); String messages =
		 * jsonObject.getString("message");
		 * 
		 * System.out.println("status"+status);
		 * 
		 * System.out.println(messages);
		 * 
		 * 
		 * 
		 * 
		 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
		 * 
		 * }
		 * 
		 */

		System.out.println(json);
	}

}
