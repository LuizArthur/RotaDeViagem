package application;

import domain.entities.Airport;
import domain.exceptions.DomainRuleException;

public interface IAirportAppService {

	Airport getByIata(String airportIataCode) throws DomainRuleException;

}
