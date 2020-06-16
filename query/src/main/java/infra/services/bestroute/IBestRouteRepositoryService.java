package infra.services.bestroute;

import java.util.List;

import domain.entities.Airport;
import domain.entities.Route;
import domain.vobjects.BestRoute;

public interface IBestRouteRepositoryService {

	BestRoute bestRoute(Airport departureAirport, Airport arrivalAirport, List<Airport> airports, List<Route> routes);

}
