package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import domain.specifications.ArrivalAirportNotNullSpecification;
import domain.specifications.CostIsNotNegativeSpecification;
import domain.specifications.CostIsNotNullSpecification;
import domain.specifications.DepartureAirportNotNullSpecification;
import domain.specifications.ISpecification;
import domain.specifications.SpecificationResult;

public class Rota extends EntityBase{
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Integer cost;

    public Rota(final Airport departureAirport, final Airport arrivalAirport, final String cost) {
        this.setDepartureAirport(departureAirport);
        this.setArrivalAirport(arrivalAirport);
        this.setCost(cost);
    }
    
    public Rota(final Airport departureAirport, final Airport arrivalAirport, final Integer cost) {
        this.setDepartureAirport(departureAirport);
        this.setArrivalAirport(arrivalAirport);
        this.setCost(cost);
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(final Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
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
            new ArrivalAirportNotNullSpecification(),
            new CostIsNotNullSpecification(),
            new DepartureAirportNotNullSpecification(),
            new CostIsNotNegativeSpecification()
        );

        Optional <SpecificationResult> firstError =
            specs
            .stream()
            .map(x -> x.isSatisfiedBy(this))
            .filter(x -> !x.isValid())
            .findFirst();

        if(firstError.isPresent()) {
            return firstError.get();
        }

        return new SpecificationResult(true, "");
    }
    
}