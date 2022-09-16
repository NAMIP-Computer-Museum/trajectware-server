import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestMediaApiServiceImpl {
	static Map<Long,java.io.File> allPic = new HashMap<>();
	
	static {
		String filePath = "./1234.jpg";
		java.io.File file_1 = new java.io.File(filePath);
    	allPic.put(1L,file_1);
    	
	}
	@Test
	public void test() {
		int index = allPic.get(1L).getName().indexOf(".");
		String id = allPic.get(1L).getName().substring(0, index);
		System.out.println(id);
	}

}
