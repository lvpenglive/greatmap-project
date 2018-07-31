import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.greatmap.common.utils.ChineseName;
import com.greatmap.common.utils.GPSLocation;
import com.greatmap.common.utils.JsonUtils;
import com.greatmap.common.utils.LocationUtils;
import com.greatmap.tregulation.usermanger.Demodata;

public class guijitest {
	@Test
	public void searchlvtuan() throws UnsupportedEncodingException, InterruptedException {
		
		

		for(int i=0;i<10;i++) {
			//System.out.println(Demodata.lvtuanguiji());
			
			

		}
		
	}
	@Test
	public void search() throws UnsupportedEncodingException, InterruptedException {
		
		ArrayList<HashMap> arrayList = new ArrayList<>();
		ArrayList<ArrayList> arrayLista = new ArrayList<>();
		HashMap<String, Object> hashMap1 = new HashMap<>();

		ChineseName sa = new ChineseName();
		GPSLocation as = new GPSLocation();
		as.setLatitude(34.66757);
		as.setLongitude(112.43067);
		for (int i = 0; i < 20; i++) {
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("name", sa.getName());
			hashMap.put("phoneno", "136" + (int) ((Math.random() * 9 + 1) * 10000000));
			hashMap.put("id", "JQJDYLG000" + i);
			hashMap.put("lat", LocationUtils.GetRandomLocation(as, 50).getLatitude());
			hashMap.put("lng", LocationUtils.GetRandomLocation(as, 50).getLongitude());
			
			
			System.out.println(LocationUtils.GetRandomLocation(as, 50).getLatitude());
			System.out.println(LocationUtils.GetRandomLocation(as, 50).getLongitude());
			arrayList.add(hashMap);
		}
		arrayLista.add(arrayList);

		hashMap1.put("message", arrayLista);
		
		String json = JsonUtils.objectToJson(hashMap1);

		
		System.out.println(json);
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		String ss =jsonObject.getJSONArray("message").getJSONArray(0).toJSONString();
		
		JSONObject jsonObject1 = JSONObject.parseObject(ss);
		

		
		System.out.println(ss);
		
		
		
		
		
		
		
		
	}
}
