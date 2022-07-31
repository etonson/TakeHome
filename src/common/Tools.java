package common;

import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Tools {
	public Tools() {
	}
	public JSONObject reWriteJsonObj(JSONObject jsonObj) throws JSONException {
		JSONObject resultObj = new JSONObject();
		JSONObject sunObj = null;
		Iterator<String> fatherIterator = jsonObj.keys();
		
		while (fatherIterator.hasNext()) {
			sunObj = new JSONObject();
			String fatherNodeKey = fatherIterator.next();
			StringBuffer tmpKey;
			try {
				sunObj = jsonObj.getJSONObject(fatherNodeKey);
				Iterator<String> sunIterator = sunObj.keys();
				while (sunIterator.hasNext()) {
					tmpKey = new StringBuffer();
					String sunObjKey = sunIterator.next();
					tmpKey.insert(tmpKey.length(), fatherNodeKey + "/" + sunObjKey);
					resultObj.put(tmpKey.toString(), sunObj.get(sunObjKey));
				}
			} catch (JSONException ex) {
				resultObj.put(fatherNodeKey, jsonObj.optString(fatherNodeKey));
			}
		}
		if(hasJSONobj(resultObj)) 
		{
			resultObj=reWriteJsonObj(resultObj);
		}
		return resultObj;
	}
	
	public String writeToResultStr(List<String> typeErrorArray, List<String> lossDatarray) {
		StringBuffer finalStr = new StringBuffer();
		if(typeErrorArray.size()>0) {finalStr.insert(finalStr.length(), "type error:");}
		for (int k = 0; k < typeErrorArray.size(); k++) {
			finalStr.insert(finalStr.length(), typeErrorArray.get(k) + ",");
		}
		if(lossDatarray.size()>0) {finalStr.insert(finalStr.length(), "loss data:");}
		for (int k = 0; k < lossDatarray.size(); k++) {
			finalStr.insert(finalStr.length(), lossDatarray.get(k) + ",");
		}
		return finalStr.toString();
	}
	
	public JSONObject string2int (String[] arr ,JSONObject obj) throws JSONException
	{
		for (int i = 0; i < arr.length; i++) {
			try {
				int ValiInt = Integer.parseInt(obj.optString(arr[i]));
				obj.put(arr[i], ValiInt);
			} catch (Exception e) {
			}
		}
		return obj;
	}

	boolean hasJSONobj(JSONObject jsonObj) {
		boolean resault=true;
		Iterator<String> jsonObjKey = jsonObj.keys();
		while (jsonObjKey.hasNext()) {
			String fatherNodeKey = jsonObjKey.next();
			if(jsonObj.get(fatherNodeKey).getClass().getName().equals("org.json.JSONObject")) 
			{
				resault=true;
				break;
			}else {
				resault=false;
			}
		}
		return resault;
	}
}
