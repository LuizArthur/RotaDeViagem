package application;

import domain.vobjects.BestRoute;

public interface IRouteAppService {

	BestRoute getBestRoute(String departureAirportCode, String arrivalAirportCode, String inputsPath);

}
