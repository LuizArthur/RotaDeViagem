package domain.services;

import java.util.List;

import domain.entities.Airport;
import domain.exceptions.DomainRuleException;

public interface IAirportService {
	public Airport getByIata(String iataCode) throws DomainRuleException;
	
	public List<Airport> getAll(String inputsPath) throws DomainRuleException;
}
