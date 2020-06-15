package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.exceptions.DomainRuleException;
import domain.specifications.SpecificationResult;
import infra.repositories.IRotaRepository;
import infra.repositories.RotaRepository;

public class RotaService implements IRotaService {

    final private IRotaRepository rotaRepository;

    private IRotaRepository getRotaRepository() {
        return rotaRepository;
    }

    public RotaService() {
        this.rotaRepository = (IRotaRepository) new RotaRepository();
    }    

    @Override
    public List<Rota> getAll(final String inputsPath) throws DomainRuleException {
        if(inputsPath == null) {
            throw new DomainRuleException("O caminho do input não foi fornecido corretamente");
        }

        final List<Rota> rotas = this.getRotaRepository().getAll(inputsPath);
        if(rotas == null) {
            throw new DomainRuleException("Não foi possível fazer a leitura do arquivo input");
        }

        return rotas;
    }

    private boolean checkIfExists(final Rota rota, final List<Rota> rotas) {
        final Set<String> rotasSet = new HashSet<String>(
            rotas
            .stream()
            .map(x -> String.format("%s-%s", x.getDepartureAirport().getIataCode(), x.getArrivalAirport().getIataCode()))
            .collect(Collectors.toList())
        );

        return rotasSet.contains(String.format("%s-%s", rota.getDepartureAirport().getIataCode(), rota.getArrivalAirport().getIataCode()));
    }

    @Override
    public Rota insert(
        final String departureAiportCode,
        final String arrivalAirportCode,
        final String cost,
        final String inputsPath
    ) throws DomainRuleException {
        final Airport departureAirport = new Airport(departureAiportCode);
        final Airport arrivalAirport = new Airport(arrivalAirportCode);

        final SpecificationResult departureAirportSpec = departureAirport.isValid();
        if(!departureAirportSpec.isValid()) {
            throw new DomainRuleException(departureAirportSpec.getMessage());
        }

        final SpecificationResult arrivalAirportSpec = arrivalAirport.isValid();
        if(!arrivalAirportSpec.isValid()) {
            throw new DomainRuleException(arrivalAirportSpec.getMessage());
        }

        final Rota rota = new Rota(departureAirport, arrivalAirport, cost);
        final SpecificationResult rotaSpecification = rota.isValid();
        if (!rotaSpecification.isValid()) {
            throw new DomainRuleException(rotaSpecification.getMessage());
        }

        final List<Rota> rotas = this.getAll(inputsPath);
        final boolean rotaExists = checkIfExists(rota, rotas);
        if(rotaExists) {
            throw new DomainRuleException("Rota já existe no arquivo de input");
        }

        final boolean isInserted = this.getRotaRepository().insert(rota, inputsPath);
        if(!isInserted) {
            throw new DomainRuleException("Nã foi possível inserir a rota no arquivo de inputs");
        }
        
        return rota;
    }
    
}