import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class LoginManager{
	
	private static FileManager f_m;
	private static JSONArray arr = new JSONArray();
	 
	public LoginManager(){
		f_m = new FileManager();
		arr = f_m.getArray();
	}

	public static boolean isRight(String id, String pwd) {
		try {
			for(int i=0; i<arr.size(); i++) {
				JSONObject tmp = (JSONObject)arr.get(i);
				JSONObject tmpInfo = (JSONObject)tmp.get(id);
				if(tmpInfo!=null) System.out.println(tmpInfo.get(pwd));
				if(tmpInfo != null && tmpInfo.get("pwd").equals(pwd)) return true;
			}
		}catch(IndexOutOfBoundsException e) {
		}
		return false;

	}
	
	public static boolean isExist(String id) {
		try {
			for(int i=0; i<arr.size(); i++) {
				JSONObject tmp = (JSONObject)arr.get(i);
				JSONObject tmpInfo = (JSONObject)tmp.get(id);
				if(tmpInfo != null) return true;
			}

		}catch(IndexOutOfBoundsException e) {
		}
		return false;

	}
	/**
	 * 회원가입 창 띄우는 함수
	 */
	public static void signup() {
		new SignupFrame();
	}
	/**
	 * 
	 * @param id 회원가입 아이디
	 * @param pwd 회원가입 비밀번호
	 * @return 회원가입이 성공했는지 안했는지
	 */
	public static boolean _signup(String id, String pwd){
		if(isExist(id)) return false;
		
		// id, 승, 무, 패 순으로 기록
		JSONObject info = new JSONObject();
		info.put("pwd", pwd);
		info.put("승", 0);
		info.put("패", 0);
		JSONObject js = new JSONObject();
		js.put(id, info);
		arr.add(js);
		
		if(f_m.canWriteFile(arr)) return true;
		else return false;
 		
	}
	
	//test용
	/*
	public static void main(String[] args){
		loginManager lManager = new loginManager();
		lManager.signup();
		f_m.writeResult("멘토스", 1);
		
	}
	*/
	
}