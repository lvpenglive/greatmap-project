import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.SearchCircumInformationReq;
import com.greatmap.tregulation.information.https.SearchLvtuanInformationReq;

public class SearchcircumInformationtest {

	@Test
	public void searchlvtuan() throws UnsupportedEncodingException {
		SearchCircumInformationReq search = new SearchCircumInformationReq(33.78435, 111.5716, 500.00);
		
		
		System.out.println(JsonUtils.objectToJson(search));
		String message = JsonUtils.objectToJson(search);

		String json = HttpClientUtil
				.sendGet("http://192.168.9.150:8899/circum?message=" + URLEncoder.encode(message, "utf-8"));

		System.out.println(json);
		
	}
}
