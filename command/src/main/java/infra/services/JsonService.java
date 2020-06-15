package infra.services;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonService implements IJsonService{

	@Override
	public <T> String classToJson(T entity) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(entity);
			
			return json;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
