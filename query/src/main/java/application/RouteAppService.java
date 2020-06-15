package application;

import java.util.List;

import domain.entities.Route;
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
	) throws DomainRuleException  {
		
		final List<Route> routes = this.getRouteService().getAll(inputsPath);
		
		
				
		return null;
	}

}
