package unittest.domain.specifications;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.entities.Route;
import domain.specifications.CostIsNotNullSpecification;
import domain.specifications.SpecificationResult;

public class CostIsNotNullSpecificationTest {
	@Test
	public void routeWithRightCost() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		Integer cost = 10;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		CostIsNotNullSpecification spec = new CostIsNotNullSpecification();
		
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
		CostIsNotNullSpecification spec = new CostIsNotNullSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertTrue(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "");	
	}
	
	@Test
	public void routeWithRightCostNull() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		Integer cost = null;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		CostIsNotNullSpecification spec = new CostIsNotNullSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Cost is not valid");	
	}
	
	@Test
	public void routeWithRightCostNullString() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		String cost = null;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		CostIsNotNullSpecification spec = new CostIsNotNullSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Cost is not valid");	
	}
}
