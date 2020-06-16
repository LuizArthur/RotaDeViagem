package infra.repositories;

import java.util.List;

import domain.entities.Airport;

public interface IAirportRepository {

	List<Airport> getAll(String inputsPath);

}
