package domain.services;

import java.util.List;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;

public interface IRouteService {

	List<Route> getAll() throws DomainRuleException;

	Route insert(Airport departureAiport, Airport arrivalAirport, String cost, List<Route> routes) throws DomainRuleException;
        
}