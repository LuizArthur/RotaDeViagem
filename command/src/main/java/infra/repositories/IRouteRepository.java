package infra.repositories;

import java.util.List;

import domain.Route;

public interface IRouteRepository {

	List<Route> getAll(String inputsPath);

	boolean insert(Route route, String inputsPath);
    
}