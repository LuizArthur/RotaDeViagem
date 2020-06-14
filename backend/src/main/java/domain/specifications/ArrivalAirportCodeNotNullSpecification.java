package domain.specifications;

import domain.Rota;

public class ArrivalAirportCodeNotNullSpecification implements ISpecification<Rota> {

    @Override
    public SpecificationResult isSatisfiedBy(final Rota entity) {
        final String arrivalAirportCode = entity.getArrivalAirportCode();
        if(arrivalAirportCode == null) {
            return new SpecificationResult(false, "Código do aeroporto de chegada é inválido");
        }

        return new SpecificationResult(true, "");
    }
    
}