package domain.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.specifications.SpecificationResult;
import infra.repositories.IRouteRepository;
import infra.repositories.RouteRepository;

public class RouteService implements IRouteService {

    final private IRouteRepository routeRepository;

    private IRouteRepository getRotaRepository() {
        return routeRepository;
    }

    public RouteService() {
        this.routeRepository = (IRouteRepository) new RouteRepository();
    }    

    @Override
    public List<Route> getAll(final String inputsPath) throws DomainRuleException {
        if(inputsPath == null) {
            throw new DomainRuleException("O caminho do input não foi fornecido corretamente");
        }

        final List<Route> routes = this.getRotaRepository().getAll(inputsPath);
        if(routes == null) {
            throw new DomainRuleException("Não foi possível fazer a leitura do arquivo input");
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
        final String departureAiportCode,
        final String arrivalAirportCode,
        final String cost,
        final String inputsPath
    ) throws DomainRuleException {
        final Airport departureAirport = new Airport(departureAiportCode);
        final Airport arrivalAirport = new Airport(arrivalAirportCode);

        final SpecificationResult departureAirportSpec = departureAirport.isValid();
        if(!departureAirportSpec.isValid()) {
            throw new DomainRuleException(departureAirportSpec.getMessage());
        }

        final SpecificationResult arrivalAirportSpec = arrivalAirport.isValid();
        if(!arrivalAirportSpec.isValid()) {
            throw new DomainRuleException(arrivalAirportSpec.getMessage());
        }

        final Route route = new Route(departureAirport, arrivalAirport, cost);
        final SpecificationResult rotaSpecification = route.isValid();
        if (!rotaSpecification.isValid()) {
            throw new DomainRuleException(rotaSpecification.getMessage());
        }

        final List<Route> routes = this.getAll(inputsPath);
        final boolean rotaExists = checkIfExists(route, routes);
        if(rotaExists) {
            throw new DomainRuleException("Rota já existe no arquivo de input");
        }

        final boolean isInserted = this.getRotaRepository().insert(route, inputsPath);
        if(!isInserted) {
            throw new DomainRuleException("Nã foi possível inserir a rota no arquivo de inputs");
        }
        
        return route;
    }
    
}