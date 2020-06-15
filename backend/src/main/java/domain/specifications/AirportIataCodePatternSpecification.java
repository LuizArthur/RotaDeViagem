package domain.specifications;

import java.util.regex.Pattern;

import domain.Airport;

public class AirportIataCodePatternSpecification implements ISpecification<Airport>{

    @Override
    public SpecificationResult isSatisfiedBy(final Airport entity) {
        final String iataCode = entity.getIataCode();
        final boolean obbeyIataPattern = Pattern.matches("[A-Z][A-Z][A-Z]", iataCode);
        if(!obbeyIataPattern) {
            return new SpecificationResult(false, "Código do aerporto não obedece o padrão IATA");
        }

        return new SpecificationResult(true, "");
    }
    
}