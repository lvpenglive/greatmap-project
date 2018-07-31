import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.SearchComplaintInformationReq;
import com.greatmap.tregulation.information.https.SearchDataAnalysisInformationReq;
import com.greatmap.tregulation.information.type.STypeEnum;

public class DataAnalysistest {
	@Test
	public void checkSearch() throws UnsupportedEncodingException {

		SearchDataAnalysisInformationReq ba = new SearchDataAnalysisInformationReq("");

		
		ba.setScenicid("");
		

		System.out.println(JsonUtils.objectToJson(ba));
		String message = JsonUtils.objectToJson(ba);

		String json = HttpClientUtil
				.sendGet("http://192.168.9.150:8899/passengerflow?message=" + URLEncoder.encode(message, "utf-8"));
		String json1 = HttpClientUtil
				.sendGet("http://192.168.9.150:8899/origin?message=" + URLEncoder.encode(message, "utf-8"));

		System.out.println(json);
		System.out.println(json1);
	}
}
