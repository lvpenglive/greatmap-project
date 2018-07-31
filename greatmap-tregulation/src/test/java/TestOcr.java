import java.io.File;
import java.io.IOException;

import org.junit.Test;
public class TestOcr {
	/** * @param args 
	 * @return */ 
	
	
	@Test
	public void ocrs() {
		//输入图片地址
		String path = "d://test//test.png";
		try {
			String valCode = new OCR().recognizeText(new File(path), "png");
			System.out.println(valCode);
			
			
			} catch (IOException e) {
				e.printStackTrace();
				} catch (Exception e) { 
					e.printStackTrace(); 
					} 
		}
	
	
}