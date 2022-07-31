package exe;

import api.impl.EX1;
import api.impl.EX2;
import api.impl.EX3;

public class Example {
	static final String FORMAT ="{"
			+ "\"price\":\"Integer\","
			+ "\"name\":\"String\","
			+ "\"id\":\"String\","
			+ "\"brand\":\"String\""
			+ "}";
	static final String FORMAT03 ="{"
			+ "\"id\":\"String\","
			+ "\"name\":\"String\","
			+ "\"brand\":\"String\","
			+ "\"price\":\"Integer\","
			+ "\"user\":{\"uid\":\"String\",\"name\":\"String\"},"
			+ "\"purchase\":{\"id\":\"String\",\"date\":\"Date\"}"
			+ "}";
	static final String FORMAT04="{id:String, name:String,"
			+ "price:Integer,"
			+ "user:{uid:String,"
			+ "name:{first_name:String,"
			+ "last_name:String}},"
			+ "brand:String}"; 
	public static void main(String[] args) {
		String example01Str ="{"
				+ "\"price\":\"ji5000\","
				+ "\"name\":\"monitor\","
				+ "\"id\":\"A0001\","
				+ "\"brand\":\"ViewSonic\""
				+ "}";

		EX1 ex1 = new EX1();
		String result01 = ex1.jsonValidate(example01Str, FORMAT);
		System.out.println("---第一題---");
		System.out.println(result01);
		
		String example02Str ="{"
				+ "\"price\":\"ji5000\","
				+ "\"name\":\"monitor\","
				+ "\"id\":\"A0001\","
				+ "\"memo\":\"ViewSonic V20\""
				+ "}";
		
		EX2 ex2 = new EX2();
		String result02 = ex2.jsonValidate(example02Str, FORMAT);
		System.out.println("---第二題---");
		System.out.println(result02);
		
		String example03Str ="{"
				+ "\"id\": \"A0001\","
				+ "\"name\": \"monitor\","
				+ "\"price\": \"5000\","
				+ "\"user\": {"
				+ "\"uid\": \"U0001\""
				+ "},"
				+ "\"purchase\":{"
				+ "\"id\":\"P0001\","
				+ "\"date\":\"20220206\""
				+ "}"
				+ "}";

		
		System.out.println("---第三題---");
		EX3 ex3 = new EX3();
		String result03 = ex3.jsonValidate(example03Str, FORMAT03);
		System.out.println(result03);
		
		String example04Str ="{"
				+ "\"id\": \"A0001\","
				+ "\"name\": \"monitor\","
				+ "\"price\": \"5000\","
				+ "\"user\": {"
				+ "\"uid\": \"U0001\","
				+ "\"name\": \"Steven Rogers\","
				+ "\"tel\": \"001\""
				+ "}"
				+ "}";
		System.out.println("---第四題---");
		String result04 = ex3.jsonValidate(example04Str, FORMAT04);
		System.out.println(result04);

	}

}
