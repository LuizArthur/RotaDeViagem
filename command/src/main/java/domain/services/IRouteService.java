package domain.services;

import java.util.List;

import domain.entities.Route;
import domain.exceptions.DomainRuleException;

public interface IRouteService {

	List<Route> getAll(String inputsPath) throws DomainRuleException;

	Route insert(String departureAiportCode, String arrivalAirportCode, String cost, List<Route> routes, String inputsPath) throws DomainRuleException;
        
}