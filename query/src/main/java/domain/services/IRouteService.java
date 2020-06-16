package domain.services;

import java.util.List;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.vobjects.BestRoute;

public interface IRouteService {

	List<Route> getAll(String inputsPath) throws DomainRuleException;

	BestRoute getBestRoute(Airport departureAirport, Airport arrivalAirport, List<Airport> airports, List<Route> routes) throws DomainRuleException;

}
