package domain;

import domain.specifications.SpecificationResult;

public abstract class EntityBase {
    public SpecificationResult isValid() {
        return new SpecificationResult(true, "");
    }
}