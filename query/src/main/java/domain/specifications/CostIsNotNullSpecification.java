package domain.specifications;

import domain.entities.Route;

public class CostIsNotNullSpecification implements ISpecification<Route> {

    @Override
    public SpecificationResult isSatisfiedBy(final Route entity) {
        final Integer cost = entity.getCost();
        if(cost == null) {
            return new SpecificationResult(false, "Custo não pode ser nulo");
        }

        return new SpecificationResult(true, "");
    }
    
}