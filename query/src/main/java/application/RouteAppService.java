package application;

import domain.exceptions.DomainRuleException;
import domain.services.IRouteService;
import domain.services.RouteService;
import domain.vobjects.BestRoute;

public class RouteAppService implements IRouteAppService{
	final private IRouteService routeService;
	
	public RouteAppService() {
		this.routeService = new RouteService();
	}

	private IRouteService getRouteService() {
		return routeService;
	}	
	
	@Override
	public BestRoute getBestRoute(
		final String departureAirportCode,
		final String arrivalAirportCode,
		final String inputsPath
	)  {
		final
		
		final BestRoute bestRoute = this.getRouteService().getBestRoute(
			departureAirportCode,
			arrivalAirportCode,
			inputsPath
		);
				
		return null;
	}

}
