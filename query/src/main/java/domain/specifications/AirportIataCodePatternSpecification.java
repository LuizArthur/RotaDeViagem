package domain.specifications;

import java.util.regex.Pattern;

import domain.entities.Airport;

public class AirportIataCodePatternSpecification implements ISpecification<Airport>{

    @Override
    public SpecificationResult isSatisfiedBy(final Airport entity) {
        final String iataCode = entity.getIataCode();
        final boolean hasNumber = Pattern.matches(".*\\d.*", iataCode);
        final boolean lengthDiff3 = iataCode.length() != 3;
        
        if(hasNumber || lengthDiff3) {
            return new SpecificationResult(false, "Airport code does not obey IATA pattern");
        }

        return new SpecificationResult(true, "");
    }
    
}