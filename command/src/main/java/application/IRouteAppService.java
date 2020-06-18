package application;

import domain.entities.Route;
import domain.exceptions.DomainRuleException;

public interface IRouteAppService {
    public Route insert(final String departureAiportCode, final String arrivalAirportCode, final String cost) throws DomainRuleException;
}