package test.JsonData;

import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import net.objecthunter.exp4j.*;
import com.google.gson.*;

public class App {
	
	// Input JSON Array...
	static String jsonData = "[\r\n"
			+ "    {\r\n"
			+ "        \"calculateField\": \"customFloat1\",\r\n"
			+ "        \"formula\": \"10+15\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "        \"calculateField\": \"customFloat2\",\r\n"
			+ "        \"formula\": \"customFloat1*10\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "        \"calculateField\": \"customFloat3\",\r\n"
			+ "        \"formula\": \"customFloat2-18\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "        \"calculateField\": \"customFloat4\",\r\n"
			+ "        \"formula\": \"customFloat3+customFloat2\"\r\n"
			+ "    }\r\n"
			+ "]";
	// Input JSON Array linear format...
/*	static String jsonData = "[{\"calculateField\":\"customFloat1\",\"formula\":\"10+15\"},{\"calculateField\":\"customFloat2\",\"formula\":\"customFloat1*10\"},{\"calculateField\":\"customFloat3\",\"formula\":\"customFloat2-18\"},{\"calculateField\":\"customFloat4\",\"formula\":\"customFloat3+customFloat2\"}]";*/
	
	// Map object for processing...
	static Map<String, String> map = new TreeMap<String, String>();
	
	// Map object getter method...
	static void setMap(JSONObject val) {
		map.put(String.valueOf(val.get("calculateField")), String.valueOf(val.get("formula")));
	}
	
	
	// Map object setter method...
	static Map<String, String> getMap() {
		return map;
	}
	
	// Method to evaluate mathematical expression...
	static String evaluation(String val){
		Expression expression = new ExpressionBuilder(val).build();
	    String result = String.valueOf((int)(expression.evaluate()));
	    return result;
	}
	
	// Method to generate resultant JSON Array...
	static JSONArray solution(Map<String, String> map){
		JSONArray resultJsonArray = new JSONArray();
		map.forEach((key, value) -> {
			JSONObject resultJson = new JSONObject();
			resultJson.put("FieldName", key);
			resultJson.put("Value", evaluation(value));
			resultJsonArray.add(resultJson);
		});
		
		return resultJsonArray;
	}
	
	// Main method...
	public static void main(String[] args) {
		
		// JSON parser object...
		JSONParser jsonParser = new JSONParser();
		try {	
			// Parsing JSON data to object...
			Object jsonObj = jsonParser.parse(jsonData);
			// Holding values in JSON Array...
			JSONArray jsonDataArray = (JSONArray) jsonObj;			
			jsonDataArray.forEach(val -> setMap((JSONObject) val));
			map.forEach((key, value)->{
				map.forEach((key1, value1)->{
					if(value1.contains(key)) {
						map.put(key1, value1.replace(key, "("+value+")"));
					}
				});
			});
			System.out.println("Input:");
			System.out.println(jsonData);
			// Resultant JSON array...
			JSONArray resultJsonArray = solution(map);
			// Pretty-Print JSON in Java...
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonOutput = gson.toJson(resultJsonArray);
			System.out.println("Output:");
			System.out.println(jsonOutput);					
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
