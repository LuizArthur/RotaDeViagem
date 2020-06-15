package application;

import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.services.IRouteService;
import domain.services.RouteService;

public class RouteAppService implements IRouteAppService {
    final private IRouteService routeService;

    @Override
    public Route insert(
        final String departureAiportCode,
        final String arrivalAirportCode,
        final String cost,
        final String inputsPath) throws DomainRuleException {

        final Route route = this.getRotaService().insert(
            departureAiportCode,
            arrivalAirportCode,
            cost,
            inputsPath
        );

        return route;
    }

    private IRouteService getRotaService() {
        return routeService;
    }

    public RouteAppService() {
        this.routeService = (IRouteService) new RouteService();
    }
    
}