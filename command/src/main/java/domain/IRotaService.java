package domain;

import java.util.List;

import domain.exceptions.DomainRuleException;

public interface IRotaService {

	List<Rota> getAll(String inputsPath) throws DomainRuleException;

	Rota insert(String departureAiportCode, String arrivalAirportCode, String cost, String inputsPath) throws DomainRuleException;
        
}