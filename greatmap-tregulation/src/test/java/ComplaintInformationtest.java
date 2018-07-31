import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.SearchComplaintInformationReq;
import com.greatmap.tregulation.information.type.PTypeEnum;
import com.greatmap.tregulation.information.type.STypeEnum;

public class ComplaintInformationtest {

	
	@Test
	public void tset() throws UnsupportedEncodingException {
		SearchComplaintInformationReq ba = new SearchComplaintInformationReq("", "");

		ba.setStype(STypeEnum.SC.getCode());
		ba.setComplaintid("985783928826626053");
		 
		

		System.out.println(JsonUtils.objectToJson(ba));
		String message = JsonUtils.objectToJson(ba);

		String json = HttpClientUtil
				.sendGet("http://192.168.9.150:8899/complainsearch?message=" + URLEncoder.encode(message, "utf-8"));

		System.out.println(json);
	}
}
