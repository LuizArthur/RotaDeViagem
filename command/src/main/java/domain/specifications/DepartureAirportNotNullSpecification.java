package domain.specifications;

import domain.Airport;
import domain.Rota;

public class DepartureAirportNotNullSpecification implements ISpecification<Rota> {

    @Override
    public SpecificationResult isSatisfiedBy(final Rota entity) {
        final Airport departureAirport = entity.getDepartureAirport();
        if(departureAirport == null) {
            return new SpecificationResult(false, "Rota não possui aeroporto de partida");
        }

        return new SpecificationResult(true, "");
    }
    
}