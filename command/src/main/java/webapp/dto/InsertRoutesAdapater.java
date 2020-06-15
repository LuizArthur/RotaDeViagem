package webapp.dto;

import domain.entities.Route;
import webapp.utils.JsonUtils;

public class InsertRoutesAdapater {
	public InsertRoutesDto RouteToDto (Route route) {
		final String body = JsonUtils.classToJson(route);
		
		return new InsertRoutesDto(body);
	}
	
	public InsertRoutesDto RouteToDto (Exception e) {		
		return new InsertRoutesDto(e);
	}
}
