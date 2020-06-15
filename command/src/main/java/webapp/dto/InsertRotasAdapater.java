package webapp.dto;

import domain.entities.Route;
import webapp.utils.JsonUtils;

public class InsertRotasAdapater {
	public InsertRotasDto RouteToDto (Route route) {
		final String body = JsonUtils.classToJson(route);
		
		return new InsertRotasDto(body);
	}
	
	public InsertRotasDto RouteToDto (Exception e) {		
		return new InsertRotasDto(e);
	}
}
