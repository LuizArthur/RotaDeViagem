package domain.specifications;

import java.util.regex.Pattern;

import domain.Rota;

public class DepartureAirportCodePatternSpecification implements ISpecification<Rota> {

    @Override
    public SpecificationResult isSatisfiedBy(final Rota entity) {
        final String departureAirportCode = entity.getDepartureAirportCode();
        final boolean obbeyIATAPattern = Pattern.matches("[A-Z][A-Z][A-Z]", departureAirportCode);

        if(!obbeyIATAPattern) {
            return new SpecificationResult(
                false,
                "O Código do aeroporto de partida não obedece o padrão IATA"
            );
        }

        return new SpecificationResult(true, "");
    }
    
}