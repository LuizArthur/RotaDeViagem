package infra.repositories;

import java.util.List;

import domain.entities.Route;

public interface IRouteRepository {

	List<Route> getAll();

}
