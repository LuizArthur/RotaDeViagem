package domain.specifications;

import domain.Rota;

public class CostIsNotNegativeSpecification implements ISpecification<Rota>{

	@Override
	public SpecificationResult isSatisfiedBy(Rota entity) {
		final Integer cost = entity.getCost();
		if(cost < 0) {
			return new SpecificationResult(false, "Custo não pode ser negativo");
		}
		
		return new SpecificationResult(true, "");
	}

}
