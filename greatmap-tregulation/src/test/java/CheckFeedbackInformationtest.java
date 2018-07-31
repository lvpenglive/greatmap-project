import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.SearchCheckInformationReq;
import com.greatmap.tregulation.information.https.SearchComplaintInformationReq;
import com.greatmap.tregulation.information.type.CTypeEnum;
import com.greatmap.tregulation.information.type.STypeEnum;

public class CheckFeedbackInformationtest {

	@Test
	public void checkSearch() throws UnsupportedEncodingException {

		SearchCheckInformationReq ba = new SearchCheckInformationReq("", "","");

		ba.setCheckstype(STypeEnum.TL.getCode());
		ba.setCheckctype(CTypeEnum.LT.getCode());
		ba.setCheckid("985895666720964608");
		
		

		System.out.println(JsonUtils.objectToJson(ba));
		String message = JsonUtils.objectToJson(ba);

		String json = HttpClientUtil
				.sendGet("http://192.168.9.150:8899/checkseach?message=" + URLEncoder.encode(message, "utf-8"));

		System.out.println(json);
	}

}
