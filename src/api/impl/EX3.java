package api.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import api.JsonValidate;
import common.Tools;

public class EX3 implements JsonValidate {
	private final String CLASSPATH = "java.lang.";

	public String jsonValidate(String json, String format) throws JSONException {
		Tools tool = new Tools();
		//重寫JSONObject格式
		JSONObject targetJsonObj = tool.reWriteJsonObj(new JSONObject(json));
		JSONObject formatJsonObj = tool.reWriteJsonObj(new JSONObject(format));
		List<String> typeErrorArray = new ArrayList<String>();
		List<String> lossDatarray = new ArrayList<String>();
		Iterator<String> formatJsonIterator = formatJsonObj.keys();
		String[] keysArray = new String[formatJsonObj.length()];
		int j = 0;
		while (formatJsonIterator.hasNext()) {
			keysArray[j] = formatJsonIterator.next();
			j++;
		}
		
		// 將能轉至int型態之資料進行轉型，ex:price:"5000"(string) ->5000(int)
		targetJsonObj = tool.string2int(keysArray, targetJsonObj);
		
		Class targetJsonClass = null;
		Class formatJsonClass = null;
		for (int i = 0; i < keysArray.length; i++) {
			String formatJsonObjKey = keysArray[i];
			// 判斷是否有資料缺失
			try {
				targetJsonObj.get(formatJsonObjKey);
			} catch (JSONException e) {
				lossDatarray.add(formatJsonObjKey);
				continue;
			}

			targetJsonClass = targetJsonObj.get(formatJsonObjKey).getClass();
			try {
				if (formatJsonObjKey.equals("purchase/date")) {
					formatJsonClass = Class.forName("java.util.Date");
				} else {
					formatJsonClass = Class.forName(CLASSPATH + formatJsonObj.optString(formatJsonObjKey));
				}
			} catch (Exception e) {
			}

			// 判斷資料型別
			if (!targetJsonClass.getTypeName().equals(formatJsonClass.getTypeName())) {
				typeErrorArray.add(formatJsonObjKey + " must be " + formatJsonObj.optString(formatJsonObjKey));
			}
		}

		return tool.writeToResultStr(typeErrorArray,lossDatarray);
	}
 
}