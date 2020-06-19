package domain.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.specifications.SpecificationResult;
import domain.vobjects.BestRoute;
import infra.repositories.IRouteRepository;
import infra.repositories.RouteRepository;
import infra.services.bestroute.BestRouteRepositoryService;
import infra.services.bestroute.IBestRouteRepositoryService;

public class RouteService implements IRouteService{
	final private IRouteRepository routeRepository;
	final private IBestRouteRepositoryService bestRouteRepositoryService;
	
	public RouteService() {
        this.routeRepository = (IRouteRepository) new RouteRepository();
        this.bestRouteRepositoryService = (IBestRouteRepositoryService) new BestRouteRepositoryService();
    }
	
    private IRouteRepository getRotaRepository() {
        return routeRepository;
    }
    
    private IBestRouteRepositoryService getBestRouteRepositoryService() {
    	return bestRouteRepositoryService;
    }
    
	@Override
	public List<Route> getAll() throws DomainRuleException {
        final List<Route> routes = this.getRotaRepository().getAll();
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
	
	private boolean checkAiportExistance(
		final Airport departureAirport,
		final Airport arrivalAirport,
		final List<Airport> airports) {

		final List<String> iataCodes = airports
			.stream()
			.map(x -> x.getIataCode())
			.collect(Collectors.toList());
		final HashSet<String> iataCodesSet = new HashSet<String>(iataCodes);
		
		if(!iataCodesSet.contains(departureAirport.getIataCode()) || !iataCodesSet.contains(arrivalAirport.getIataCode())) {
			return false;
		}
		
		return true;		
	}

	@Override
	public BestRoute getBestRoute(
		Airport departureAirport,
		Airport arrivalAirport,
		List<Airport> airports,
		List<Route> routes
	) throws DomainRuleException {
		boolean airportExists = checkAiportExistance(departureAirport, arrivalAirport, airports);
		if(!airportExists) {
			throw new DomainRuleException("Aiport is not in the input");
		}
		
		BestRoute bestRoute = this.getBestRouteRepositoryService().bestRoute(departureAirport, arrivalAirport, airports, routes);
		if(bestRoute.getAirports().size() == 0) {
			throw new DomainRuleException("A route for these airports does not exist");
		}		
		
		return bestRoute;
	}

	

}
