package webapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.vobjects.BestRoute;

public class BestRouteAdapter {
	public BestRouteDto BestRouteToDto (BestRoute bestRoute) {
		List<String> iataCodes = bestRoute.getAirports()
			.stream()
			.map(x -> x.getIataCode())
			.collect(Collectors.toList());
		
		Integer cost = bestRoute.getCost();

		final String body = String.format("%s > $%s", String.join(" - ", iataCodes), cost);	
		
		return new BestRouteDto(body);
	}
	
	public BestRouteDto RouteToDto (Exception e) {		
		return new BestRouteDto(e);
	}
}
