package application;

import java.util.List;

import domain.entities.Airport;
import domain.exceptions.DomainRuleException;
import domain.services.AirportService;
import domain.services.IAirportService;

public class AirportAppService implements IAirportAppService {
	
	final private IAirportService airportService;
	
	public AirportAppService() {
		this.airportService = new AirportService();
	}
	
	private IAirportService getAirportService() {
		return this.airportService;
	}

	@Override
	public Airport getByIata(String airportIataCode) throws DomainRuleException {
		return this.getAirportService().getByIata(airportIataCode);
	}

	@Override
	public List<Airport> getAll() throws DomainRuleException {
		return this.getAirportService().getAll();
	}

}
