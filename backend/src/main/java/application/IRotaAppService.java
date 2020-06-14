package application;

import domain.Rota;

public interface IRotaAppService {
    public Rota insert(final String departureAiportCode, final String arrivalAirportCode, final String cost, final String inputsPath);
}