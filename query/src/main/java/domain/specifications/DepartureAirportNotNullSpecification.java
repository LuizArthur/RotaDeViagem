package domain.specifications;

import domain.entities.Airport;
import domain.entities.Route;

public class DepartureAirportNotNullSpecification implements ISpecification<Route> {

    @Override
    public SpecificationResult isSatisfiedBy(final Route entity) {
        final Airport departureAirport = entity.getDepartureAirport();
        if(departureAirport == null) {
            return new SpecificationResult(false, "Route does not have departure airport");
        }

        return new SpecificationResult(true, "");
    }
    
}