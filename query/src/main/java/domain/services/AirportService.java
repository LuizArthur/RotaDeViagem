package domain.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.entities.Airport;
import domain.exceptions.DomainRuleException;
import domain.specifications.SpecificationResult;
import infra.repositories.AiportRepository;
import infra.repositories.IAirportRepository;

public class AirportService implements IAirportService{
	
	final private IAirportRepository airportRepository;
	
	public AirportService() {
		this.airportRepository = (IAirportRepository) new AiportRepository();
	}
	
	private IAirportRepository getAirportRepository() {
		return airportRepository;
	}
	
	private boolean checkExistance(String iataCode, List<Airport> airports) {
		HashSet<String> iataCodeSet = new HashSet<String>(
			airports
			.stream()
			.map(x -> x.getIataCode())
			.collect(Collectors.toList())
		);
		
		return iataCodeSet.contains(iataCode);
	}

	@Override
	public Airport getByIata(String iataCode) throws DomainRuleException {
		List<Airport> airports = this.getAll();
		boolean airportExists = checkExistance(iataCode, airports);
		if(!airportExists) {
			throw new DomainRuleException("Aiport does not exists in DataBase");
		}
		
		Airport airport = new Airport(iataCode);
		SpecificationResult airportSpec = airport.isValid();
		
		if(!airportSpec.isValid()) {
			throw new DomainRuleException(airportSpec.getMessage());
		}
		
		return airport;
	}

	@Override
	public List<Airport> getAll() throws DomainRuleException {		
		final List<Airport> airports = this.getAirportRepository().getAll();
		if(airports == null) {
			throw new DomainRuleException("Não foi possível fazer a leitura do arquivo input");
		}
		
		Optional <SpecificationResult> firstError =
	        	airports
	        	.stream()
	        	.map(x -> x.isValid())
	        	.filter(x -> !x.isValid())
	        	.findFirst();
		if(firstError.isPresent()) {
        	throw new DomainRuleException("O arquivo de input possue dados inconsistentes");
        }
        		
        return airports;
	}
	
}
