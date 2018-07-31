import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.greatmap.common.utils.HttpClientUtil;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.tregulation.information.https.SearchLvtuanInformationReq;
import com.greatmap.tregulation.information.type.PTypeEnum;

public class SearchLvtuanInformationtest {

	@Test
	public void searchlvtuan() throws UnsupportedEncodingException {
		SearchLvtuanInformationReq search = new SearchLvtuanInformationReq("", "");
		search.setId("123123123");
		search.setWtype(PTypeEnum.LT.getCode());
		
		System.out.println(JsonUtils.objectToJson(search));
		String message = JsonUtils.objectToJson(search);

		String json = HttpClientUtil
				.sendGet("http://192.168.9.150:8899/person?message=" + URLEncoder.encode(message, "utf-8"));

		System.out.println(json);
		
	}
}
