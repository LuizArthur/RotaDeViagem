package domain.specifications;

import domain.entities.Route;

public class CostIsNotNegativeSpecification implements ISpecification<Route>{

	@Override
	public SpecificationResult isSatisfiedBy(Route entity) {
		final Integer cost = entity.getCost();
		if(cost < 0) {
			return new SpecificationResult(false, "Cost cannot be negative");
		}
		
		return new SpecificationResult(true, "");
	}

}
