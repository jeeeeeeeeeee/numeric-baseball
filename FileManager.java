import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;

public class FileManager {

	private static String file_name = "NumberBaseball";
	private static File file = new File(file_name + ".json");
	private static JSONArray array = new JSONArray();
	private JSONArray sortedArray;
	
	public FileManager() {
		
		try {
		     if (file.createNewFile()) {
		         System.out.println("File을 새로 만들었습니다");
		     } else {
		         System.out.println("File이 이미 존재합니다");
		         
				 try {
					Object ob = new JSONParser().parse(new FileReader(file_name + ".json"));
					array = (JSONArray)ob;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
		     }
		 } catch (IOException e) {
		     e.printStackTrace();
		 }
	}
	public boolean canWriteFile(JSONArray arr) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(arr.toString());
			writer.close();
		}catch(IOException e) {
			System.out.println("파일에 쓸 수 없습니다");
			return false;
		}
		return true;
	}
	
	public JSONArray getArray() {
		return array;
	}
	
	// @param result 이겼으면 1, 졌으면 0
	public void writeResult(String name, int result) {
		//System.out.println(array);
		String res;
		if(result == 1) res = "승";
		else res = "패";
		try {
			for(int i=0; i<array.size(); i++) {
				JSONObject tmp = (JSONObject)array.get(i);
				JSONObject tmpInfo = (JSONObject)tmp.get(name);
				if(tmpInfo != null) {
					tmpInfo.put(res, (Long)tmpInfo.get(res)+1);
					if(canWriteFile(array)) System.out.println("결과를 저장했습니다");
					
				}
			}
		}catch(IndexOutOfBoundsException e) {
		}
	}
	
	public long getRecord(String id, String what) {
		long num=0;
		for(int i=0; i<array.size(); i++) {
			JSONObject tmp = (JSONObject)array.get(i);
			JSONObject tmpInfo = (JSONObject)tmp.get(id);
			if(tmpInfo!=null)
				num = (long)tmpInfo.get(what);
		}
		return num;
	}
	
}