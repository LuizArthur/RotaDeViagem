package domain.services;

import java.util.List;

import domain.entities.Route;
import domain.exceptions.DomainRuleException;

public interface IRouteService {

	List<Route> getAll(String inputsPath) throws DomainRuleException;

}
