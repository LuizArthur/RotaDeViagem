package application;

import java.util.List;

import domain.IRotaService;
import domain.Rota;
import domain.RotaService;

public class RotaAppService implements IRotaAppService {
    final private IRotaService rotaService;

    @Override
    public Rota insert(
        final String departureAiportCode,
        final String arrivalAirportCode,
        final String cost,
        final String inputsPath) {

        final List<Rota> rotas = rotaService.getAll(inputsPath);

        return null;
    }

    public IRotaService RotaService() {
        return rotaService;
    }

    public RotaAppService() {
        this.rotaService = (IRotaService) new RotaService();
    }
    
}