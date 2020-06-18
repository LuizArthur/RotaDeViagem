package unittest.domain.entities;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.entities.Route;
import domain.specifications.SpecificationResult;

public class RouteTeste {
	
	@Test
	public void rotaWithRightElements() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		
		final Route route = new Route(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertTrue(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "");
	}
	
	@Test
	public void routeWithRightElementsCostInteger() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final Integer cost = 50;
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertTrue(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "");
	}
	
	@Test
	public void routeWithNullDepartureAirport() {
		final Airport departureAirport = null;
		final Airport arrivalAirport = new Airport("ORL");
		
		final Route route = new Route(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Route does not have departure airport");
	}
	
	@Test
	public void routeWithNullArrivalAirport() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = null;
		
		final Route route = new Route(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Route does not have arrival airport");
	}
	
	@Test
	public void routeWithNullAirports() {
		final Airport departureAirport = null;
		final Airport arrivalAirport = null;
		
		final Route route = new Route(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
	}
	
	@Test
	public void routeWithInvalidCost() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "Olá156615";
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Cost is not valid");
	}
	
	@Test
	public void routeWithInvalidCostFloat() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "56.30";
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Cost is not valid");
	}
	
	@Test
	public void routeWithInvalidCostNull() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = null;
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Cost is not valid");
	}
	
	@Test
	public void routeWithInvalidCostNegative() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "-10";
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Cost cannot be negative");
	}
	
	@Test
	public void routeWithInvalidCostNegativeInteger() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final Integer cost = -10;
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Cost cannot be negative");
	}
	
	
}
