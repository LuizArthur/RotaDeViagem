package application;

import domain.IRotaService;
import domain.Rota;
import domain.RotaService;
import domain.exceptions.DomainRuleException;

public class RotaAppService implements IRotaAppService {
    final private IRotaService rotaService;

    @Override
    public Rota insert(
        final String departureAiportCode,
        final String arrivalAirportCode,
        final String cost,
        final String inputsPath) throws DomainRuleException {

        final Rota rota = this.getRotaService().insert(
            departureAiportCode,
            arrivalAirportCode,
            cost,
            inputsPath
        );

        return rota;
    }

    private IRotaService getRotaService() {
        return rotaService;
    }

    public RotaAppService() {
        this.rotaService = (IRotaService) new RotaService();
    }
    
}