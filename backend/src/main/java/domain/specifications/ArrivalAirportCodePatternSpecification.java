package domain.specifications;

import java.util.regex.Pattern;

import domain.Rota;

public class ArrivalAirportCodePatternSpecification implements ISpecification<Rota> {

    @Override
    public SpecificationResult isSatisfiedBy(final Rota entity) {
        final String arrivalAirportCode = entity.getArrivalAirportCode();
        final boolean obbeyIATAPattern = Pattern.matches("[A-Z][A-Z][A-Z]", arrivalAirportCode);

        if(!obbeyIATAPattern) {
            return new SpecificationResult(
                false,
                "O Código do aeroporto de chegada não obedece o padrão IATA"
            );
        }

        return new SpecificationResult(true, "");
    }
    
}