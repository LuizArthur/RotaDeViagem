package domain.services;

import domain.entities.Airport;
import domain.exceptions.DomainRuleException;

public interface IAirportService {
	public Airport getByIata(String iataCode) throws DomainRuleException;
}
