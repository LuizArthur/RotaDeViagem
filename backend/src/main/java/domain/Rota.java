package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import domain.specifications.ArrivalAirportCodeNotNullSpecification;
import domain.specifications.ArrivalAirportCodePatternSpecification;
import domain.specifications.CostIsNotNullSpecification;
import domain.specifications.DepartureAirportCodeNotNullSpecification;
import domain.specifications.DepartureAirportCodePatternSpecification;
import domain.specifications.ISpecification;
import domain.specifications.SpecificationResult;

public class Rota extends EntityBase{
    private String departureAirportCode;
    private String arrivalAirportCode;
    private Integer cost;

    public Rota(final String departureAirportCode, final String arrivalAirportCode, final String cost) {
        this.setDepartureAirportCode(departureAirportCode);
        this.setArrivalAirportCode(arrivalAirportCode);
        this.setCost(cost);
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(String cost) {
        try {
            this.cost = Integer.parseInt(cost);
        } catch (Exception e) {
            this.cost = null;
        }
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public SpecificationResult isValid() {
        List<ISpecification<Rota>> specs = Arrays.asList(
            new ArrivalAirportCodeNotNullSpecification(),
            new CostIsNotNullSpecification(),
            new DepartureAirportCodeNotNullSpecification(),
            new ArrivalAirportCodePatternSpecification(),
            new DepartureAirportCodePatternSpecification()
        );

        Optional <SpecificationResult> firstError =
            specs
            .stream()
            .map(x -> x.isSatisfiedBy(this))
            .filter(x -> x.isValid())
            .findFirst();

        if(firstError.isPresent()) {
            return firstError.get();
        }

        return new SpecificationResult(true, "");
    }
    
}