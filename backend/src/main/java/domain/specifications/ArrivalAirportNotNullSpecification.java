package domain.specifications;

import domain.Airport;
import domain.Rota;

public class ArrivalAirportNotNullSpecification implements ISpecification<Rota> {

    @Override
    public SpecificationResult isSatisfiedBy(final Rota entity) {
        final Airport arrivalAirport = entity.getArrivalAirport();
        if(arrivalAirport == null) {
            return new SpecificationResult(false, "Rota não possui aeroporto de chegada");
        }

        return new SpecificationResult(true, "");
    }
    
}