package domain.specifications;

import domain.Airport;
import domain.Route;

public class DepartureAirportNotNullSpecification implements ISpecification<Route> {

    @Override
    public SpecificationResult isSatisfiedBy(final Route entity) {
        final Airport departureAirport = entity.getDepartureAirport();
        if(departureAirport == null) {
            return new SpecificationResult(false, "Rota não possui aeroporto de partida");
        }

        return new SpecificationResult(true, "");
    }
    
}