package com.rotadeviagem.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	public static JsonNode stringToJson(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode json = mapper.readTree(jsonString);
			
			return json;
		} catch (Exception e) {
			return null;
		}
	}
}
