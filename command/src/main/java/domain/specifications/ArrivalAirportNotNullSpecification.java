package domain.specifications;

import domain.entities.Airport;
import domain.entities.Route;

public class ArrivalAirportNotNullSpecification implements ISpecification<Route> {

    @Override
    public SpecificationResult isSatisfiedBy(final Route entity) {
        final Airport arrivalAirport = entity.getArrivalAirport();
        if(arrivalAirport == null) {
            return new SpecificationResult(false, "Rota não possui aeroporto de chegada");
        }

        return new SpecificationResult(true, "");
    }
    
}