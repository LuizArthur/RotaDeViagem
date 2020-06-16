package application;

import java.util.List;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.services.AirportService;
import domain.services.IAirportService;
import domain.services.IRouteService;
import domain.services.RouteService;

public class RouteAppService implements IRouteAppService {
    final private IRouteService routeService;
    final private IAirportService airportService;

    @Override
    public Route insert(
        final String departureAiportCode,
        final String arrivalAirportCode,
        final String cost,
        final String inputsPath) throws DomainRuleException {

        final List<Route> routes = this.getRotaService().getAll(inputsPath);
        final Airport departureAiport = this.getAirportService().getByIata(departureAiportCode);
        final Airport arrivalAirport = this.getAirportService().getByIata(arrivalAirportCode);

        final Route route = this.getRotaService().insert(
            departureAiport,
            arrivalAirport,
            cost,
            routes,
            inputsPath
        );

        return route;
    }

    private IRouteService getRotaService() {
        return routeService;
    }
    
    private IAirportService getAirportService() {
		return airportService;
	}

    public RouteAppService() {
        this.routeService = (IRouteService) new RouteService();
        this.airportService = (IAirportService) new AirportService();
    }
    
}