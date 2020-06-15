package domain.specifications;

import domain.EntityBase;

public interface ISpecification<T extends EntityBase> {
    public SpecificationResult isSatisfiedBy(T entity);
}