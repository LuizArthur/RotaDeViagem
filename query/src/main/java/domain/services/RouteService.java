package domain.services;

import java.util.List;
import java.util.Optional;

import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.specifications.SpecificationResult;
import infra.repositories.IRouteRepository;
import infra.repositories.RouteRepository;

public class RouteService implements IRouteService{
	final private IRouteRepository routeRepository;
	
	public RouteService() {
        this.routeRepository = (IRouteRepository) new RouteRepository();
    }
	
    private IRouteRepository getRotaRepository() {
        return routeRepository;
    }
    
	@Override
	public List<Route> getAll(String inputsPath) throws DomainRuleException {
		if(inputsPath == null) {
            throw new DomainRuleException("O caminho do input não foi fornecido corretamente");
        }

        final List<Route> routes = this.getRotaRepository().getAll(inputsPath);
        if(routes == null) {
            throw new DomainRuleException("Não foi possível fazer a leitura do arquivo input");
        }
        
        Optional <SpecificationResult> firstError =
        	routes
        	.stream()
        	.map(x -> x.isValid())
        	.filter(x -> !x.isValid())
        	.findFirst();
        
        if(firstError.isPresent()) {
        	throw new DomainRuleException("O arquivo de input possue dados inconsistentes");
        }
        		
        return routes;
	}

}
