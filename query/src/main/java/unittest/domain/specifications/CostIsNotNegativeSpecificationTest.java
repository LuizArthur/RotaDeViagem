package unittest.domain.specifications;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.entities.Route;
import domain.specifications.CostIsNotNegativeSpecification;
import domain.specifications.SpecificationResult;

public class CostIsNotNegativeSpecificationTest {
	@Test
	public void routeWithRightCost() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		Integer cost = 10;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		CostIsNotNegativeSpecification spec = new CostIsNotNegativeSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertTrue(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "");	
	}
	
	@Test
	public void routeWithRightCostString() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		String cost = "10";
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		CostIsNotNegativeSpecification spec = new CostIsNotNegativeSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertTrue(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "");	
	}
	
	@Test
	public void routeWithNegativeCost() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		Integer cost = -10;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		CostIsNotNegativeSpecification spec = new CostIsNotNegativeSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Cost cannot be negative");	
	}
	
	@Test
	public void routeWithNegativeCostString() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		String cost = "-10";
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		CostIsNotNegativeSpecification spec = new CostIsNotNegativeSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Cost cannot be negative");	
	}
}
