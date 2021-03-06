package domain.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.specifications.SpecificationResult;
import infra.repositories.IRouteRepository;
import infra.repositories.RouteRepository;

public class RouteService implements IRouteService {

    final private IRouteRepository routeRepository;

    private IRouteRepository getRouteRepository() {
        return routeRepository;
    }

    public RouteService() {
        this.routeRepository = (IRouteRepository) new RouteRepository();
    }    

    @Override
    public List<Route> getAll() throws DomainRuleException {
        final List<Route> routes = this.getRouteRepository().getAll();
        if(routes == null) {
            throw new DomainRuleException("It was not possible to read input file");
        }
        
        Optional <SpecificationResult> firstError =
        	routes
        	.stream()
        	.map(x -> x.isValid())
        	.filter(x -> !x.isValid())
        	.findFirst();
        
        if(firstError.isPresent()) {
        	throw new DomainRuleException("The input file has inconsistent data");
        }
        		
        return routes;
    }

    private boolean checkIfExists(final Route route, final List<Route> routes) {
        final Set<String> rotasSet = new HashSet<String>(
            routes
            .stream()
            .map(x -> String.format("%s-%s", x.getDepartureAirport().getIataCode(), x.getArrivalAirport().getIataCode()))
            .collect(Collectors.toList())
        );

        return rotasSet.contains(String.format("%s-%s", route.getDepartureAirport().getIataCode(), route.getArrivalAirport().getIataCode()));
    }

    @Override
    public Route insert(
        final Airport departureAirport,
        final Airport arrivalAirport,
        final String cost,
        final List<Route> routes
    ) throws DomainRuleException {
        final Route route = new Route(departureAirport, arrivalAirport, cost);
        final SpecificationResult rotaSpecification = route.isValid();
        if (!rotaSpecification.isValid()) {
            throw new DomainRuleException(rotaSpecification.getMessage());
        }

        final boolean rotaExists = checkIfExists(route, routes);
        if(rotaExists) {
            throw new DomainRuleException("Route already exists in input");
        }

        final boolean isInserted = this.getRouteRepository().insert(route);
        if(!isInserted) {
            throw new DomainRuleException("It was not possible to insert the route");
        }
        
        return route;
    }
    
}