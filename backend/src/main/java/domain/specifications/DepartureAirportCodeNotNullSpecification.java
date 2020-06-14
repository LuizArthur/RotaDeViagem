package domain.specifications;

import domain.Rota;

public class DepartureAirportCodeNotNullSpecification implements ISpecification<Rota> {

    @Override
    public SpecificationResult isSatisfiedBy(final Rota entity) {
        final String departureAirportCode = entity.getDepartureAirportCode();
        if(departureAirportCode == null) {
            return new SpecificationResult(false, "Código do aeroporto de partida é inválido");
        }

        return new SpecificationResult(true, "");
    }
    
}