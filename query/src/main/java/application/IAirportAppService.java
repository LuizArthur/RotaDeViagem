package application;

import java.util.List;

import domain.entities.Airport;
import domain.exceptions.DomainRuleException;

public interface IAirportAppService {

	Airport getByIata(String airportIataCode) throws DomainRuleException;

	List<Airport> getAll() throws DomainRuleException;

}
