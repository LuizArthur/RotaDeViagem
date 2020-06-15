package application;

import domain.IRouteService;
import domain.Route;
import domain.RouteService;
import domain.exceptions.DomainRuleException;

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