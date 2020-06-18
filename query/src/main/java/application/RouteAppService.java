package application;

import java.util.List;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.services.IRouteService;
import domain.services.RouteService;
import domain.vobjects.BestRoute;

public class RouteAppService implements IRouteAppService{
	final private IRouteService routeService;
	final private IAirportAppService airportAppService;
	
	public RouteAppService() {
		this.routeService = new RouteService();
		this.airportAppService = new AirportAppService();
	}

	private IRouteService getRouteService() {
		return routeService;
	}
	
	private IAirportAppService getAirportAppService() {
		return airportAppService;
	}
	
	@Override
	public BestRoute getBestRoute(
		final String departureAirportCode,
		final String arrivalAirportCode
	) throws DomainRuleException  {		
		final Airport departureAirport = this.getAirportAppService().getByIata(departureAirportCode);
		final Airport arrivalAirport = this.getAirportAppService().getByIata(arrivalAirportCode);
		
		final List<Route> routes = this.getRouteService().getAll();
		final List<Airport> airports = this.getAirportAppService().getAll();
		
		final BestRoute bestRoute = this.getRouteService().getBestRoute(
			departureAirport,
			arrivalAirport,
			airports,
			routes
		);
				
		return bestRoute;
	}

}
