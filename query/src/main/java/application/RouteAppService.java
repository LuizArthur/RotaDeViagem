package application;

import java.util.List;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.services.AirportService;
import domain.services.IAirportService;
import domain.services.IRouteService;
import domain.services.RouteService;
import domain.vobjects.BestRoute;

public class RouteAppService implements IRouteAppService{
	final private IRouteService routeService;
	final private IAirportService airportService;
	
	public RouteAppService() {
		this.routeService = new RouteService();
		this.airportService = new AirportService();
	}

	private IRouteService getRouteService() {
		return routeService;
	}
	
	private IAirportService getAirportService() {
		return airportService;
	}
	
	@Override
	public BestRoute getBestRoute(
		final String departureAirportCode,
		final String arrivalAirportCode
	) throws DomainRuleException  {
		
		final Airport departureAirport = this.getAirportService().getByIata(departureAirportCode);
		final Airport arrivalAirport = this.getAirportService().getByIata(arrivalAirportCode);
		
		final List<Route> routes = this.getRouteService().getAll();
		final List<Airport> airports = this.getAirportService().getAll();
		
		final BestRoute bestRoute = this.getRouteService().getBestRoute(
			departureAirport,
			arrivalAirport,
			airports,
			routes
		);
				
		return bestRoute;
	}

}
