package infra.repositories;

import java.util.List;

import domain.Rota;

public interface IRotaRepository {

	List<Rota> getAll(String inputsPath);

	boolean insert(Rota rota, String inputsPath);
    
}