package domain.services;

import domain.entities.Airport;
import domain.exceptions.DomainRuleException;
import domain.specifications.SpecificationResult;

public class AirportService implements IAirportService{

	@Override
	public Airport getByIata(String iataCode) throws DomainRuleException {
		Airport airport = new Airport(iataCode);
		SpecificationResult airportSpec = airport.isValid();
		
		if(!airportSpec.isValid()) {
			throw new DomainRuleException(airportSpec.getMessage());
		}
		
		return airport;
	}

}
