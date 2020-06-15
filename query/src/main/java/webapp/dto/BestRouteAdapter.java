package webapp.dto;

import webapp.utils.JsonUtils;

public class BestRouteAdapter {
	public BestRouteDto BestRouteToDto (BestRoute bestRoute) {
		final String body = JsonUtils.classToJson(bestRoute);
		
		return new BestRouteDto(body);
	}
	
	public BestRouteDto RouteToDto (Exception e) {		
		return new BestRouteDto(e);
	}
}
