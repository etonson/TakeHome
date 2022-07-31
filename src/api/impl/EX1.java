package api.impl;

import org.json.JSONObject;

import api.JsonValidate;
import common.Tools;

public class EX1 implements JsonValidate {
	private  final String CLASSPATH="java.lang.";
	public String jsonValidate(String json, String format) {
		Tools tool = new Tools();
		JSONObject jsonObj = new JSONObject(json);
		JSONObject formatJson = new JSONObject(format);
		String[] array = { "id", "name", "price", "brand" };
		
		StringBuffer finalStr = new StringBuffer();
		//將能轉至int型態之資料進行轉型，ex:price:"5000"(string) ->5000(int)
		jsonObj = tool.string2int(array, jsonObj);

		//判斷型別
		Class objectJsonClass =null;
		Class formatJsonClass=null;
		for (int i = 0; i < array.length; i++) {
			try {
				objectJsonClass =  jsonObj.get(array[i]).getClass();
				formatJsonClass = Class.forName(CLASSPATH+formatJson.optString(array[i]));
			}catch(Exception e) {}
			if(objectJsonClass.getTypeName().equals(formatJsonClass.getTypeName())!=true)
			{

			}
		}
		
		return finalStr.toString();
	}
}
