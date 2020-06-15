package domain.specifications;

import domain.Rota;

public class CostIsNotNullSpecification implements ISpecification<Rota> {

    @Override
    public SpecificationResult isSatisfiedBy(final Rota entity) {
        final Integer cost = entity.getCost();
        if(cost == null) {
            return new SpecificationResult(false, "Custo não pode ser nulo");
        }

        return new SpecificationResult(true, "");
    }
    
}