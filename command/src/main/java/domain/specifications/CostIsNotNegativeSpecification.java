package domain.specifications;

import domain.Route;

public class CostIsNotNegativeSpecification implements ISpecification<Route>{

	@Override
	public SpecificationResult isSatisfiedBy(Route entity) {
		final Integer cost = entity.getCost();
		if(cost < 0) {
			return new SpecificationResult(false, "Custo não pode ser negativo");
		}
		
		return new SpecificationResult(true, "");
	}

}
