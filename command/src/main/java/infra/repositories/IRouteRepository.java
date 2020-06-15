package infra.repositories;

import java.util.List;

import domain.entities.Route;

public interface IRouteRepository {

	List<Route> getAll(String inputsPath);

	boolean insert(Route route, String inputsPath);
    
}