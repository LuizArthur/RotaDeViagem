package application;

import domain.Rota;
import domain.exceptions.DomainRuleException;

public interface IRotaAppService {
    public Rota insert(final String departureAiportCode, final String arrivalAirportCode, final String cost, final String inputsPath) throws DomainRuleException;
}