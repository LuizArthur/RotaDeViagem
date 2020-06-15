package domain.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import domain.specifications.AirportIataCodeIsNotNull;
import domain.specifications.AirportIataCodePatternSpecification;
import domain.specifications.ISpecification;
import domain.specifications.SpecificationResult;

public class Airport extends EntityBase {
    private String iataCode;

    public Airport(String iataCode) {
        this.setIataCode(iataCode.toUpperCase());
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    @Override
    public SpecificationResult isValid() {                
        List<ISpecification<Airport>> specs = Arrays.asList(
            new AirportIataCodeIsNotNull(),
            new AirportIataCodePatternSpecification()
        );

        Optional <SpecificationResult> firstError =
            specs
            .stream()
            .map(x -> x.isSatisfiedBy(this))
            .filter(x -> !x.isValid())
            .findFirst();

        if(firstError.isPresent()) {
            return firstError.get();
        }

        return new SpecificationResult(true, "");
    }
    
}