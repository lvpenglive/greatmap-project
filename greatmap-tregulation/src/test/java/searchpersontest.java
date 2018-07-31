import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.SearchCheckInformationReq;
import com.greatmap.tregulation.information.https.SearchPersonSumInformationReq;
import com.greatmap.tregulation.information.type.CTypeEnum;
import com.greatmap.tregulation.information.type.PTypeEnum;
import com.greatmap.tregulation.information.type.STypeEnum;

public class searchpersontest {
	@Test
	public void checkSearch() throws UnsupportedEncodingException {

		SearchPersonSumInformationReq ba = new SearchPersonSumInformationReq("", "","");

		ba.setStype(STypeEnum.TL.getCode());
		ba.setPtype(PTypeEnum.DY.getCode());
		
		

		System.out.println(JsonUtils.objectToJson(ba));
		String message = JsonUtils.objectToJson(ba);

		String json = HttpClientUtil
				.sendGet("http://192.168.9.150:8899/personsum?message=" + URLEncoder.encode(message, "utf-8"));

		System.out.println(json);
	}
}
