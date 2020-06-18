package application;

import java.util.List;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.services.IRouteService;
import domain.services.RouteService;

public class RouteAppService implements IRouteAppService {
    final private IRouteService routeService;
    final private IAirportAppService airportAppService;
    
    public RouteAppService() {
        this.routeService = new RouteService();
        this.airportAppService = new AirportAppService();
    }
    
    private IRouteService getRotaService() {
        return this.routeService;
    }
    
    private IAirportAppService getAirportAppService() {
		return this.airportAppService;
	}
    
    @Override
    public Route insert(
        final String departureAiportCode,
        final String arrivalAirportCode,
        final String cost
    ) throws DomainRuleException {

        final List<Route> routes = this.getRotaService().getAll();
        final Airport departureAiport = this.getAirportAppService().getByIata(departureAiportCode);
        final Airport arrivalAirport = this.getAirportAppService().getByIata(arrivalAirportCode);

        final Route route = this.getRotaService().insert(
            departureAiport,
            arrivalAirport,
            cost,
            routes
        );

        return route;
    }
    
}