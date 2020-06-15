package domain.specifications;

import domain.entities.EntityBase;

public interface ISpecification<T extends EntityBase> {
    public SpecificationResult isSatisfiedBy(T entity);
}