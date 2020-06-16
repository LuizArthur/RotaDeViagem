package domain.services;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Airport getByIata(String iataCode) throws DomainRuleException {
		Airport airport = new Airport(iataCode);
		SpecificationResult airportSpec = airport.isValid();
		
		if(!airportSpec.isValid()) {
			throw new DomainRuleException(airportSpec.getMessage());
		}
		
		return airport;
	}

	@Override
	public List<Airport> getAll(String inputsPath) throws DomainRuleException {
		if(inputsPath == null) {
            throw new DomainRuleException("O caminho do input não foi fornecido corretamente");
        }
		
		final List<Airport> airports = this.getAirportRepository().getAll(inputsPath);
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
