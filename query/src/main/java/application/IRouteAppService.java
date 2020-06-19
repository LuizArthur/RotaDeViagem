package application;

import domain.exceptions.DomainRuleException;
import domain.vobjects.BestRoute;

public interface IRouteAppService {

	BestRoute getBestRoute(String routeString) throws DomainRuleException;

}
