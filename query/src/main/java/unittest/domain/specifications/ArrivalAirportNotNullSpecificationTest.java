package unittest.domain.specifications;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.entities.Route;
import domain.specifications.ArrivalAirportNotNullSpecification;
import domain.specifications.SpecificationResult;

public class ArrivalAirportNotNullSpecificationTest {
	@Test
	public void routeWithArrivalAirport() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		Integer cost = (Integer) 10;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		ArrivalAirportNotNullSpecification spec = new ArrivalAirportNotNullSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertTrue(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "");	
	}
	
	@Test
	public void routeWithNullArrivalAirport() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = null;
		Integer cost = (Integer) 10;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		ArrivalAirportNotNullSpecification spec = new ArrivalAirportNotNullSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Route does not have arrival airport");	
	}
}
