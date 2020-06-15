package webapp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	public static <T> String classToJson(T object) {
		final ObjectMapper mapper = new ObjectMapper();
		
		try {
			final String json = mapper.writeValueAsString(object);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
