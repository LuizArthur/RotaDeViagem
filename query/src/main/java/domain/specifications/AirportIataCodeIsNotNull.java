package domain.specifications;

import domain.entities.Airport;

public class AirportIataCodeIsNotNull implements ISpecification<Airport> {

    @Override
    public SpecificationResult isSatisfiedBy(final Airport entity) {
        final String iataCode = entity.getIataCode();
        if(iataCode == null) {
            return new SpecificationResult(false, "O Código do aeroporto fornecido é nulo");
        }

        return new SpecificationResult(true, "");
    }
    
}