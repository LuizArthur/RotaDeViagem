package unittest.domain.specifications;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.entities.Route;
import domain.specifications.DepartureAirportNotNullSpecification;
import domain.specifications.SpecificationResult;

public class DepartureAirportNotNullSpecificationTest {
	@Test
	public void routeWithDepartureAirport() {
		Airport departureAirport = new Airport("LAX");
		Airport arrivalAirport = new Airport("GRU");
		Integer cost = (Integer) 10;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		DepartureAirportNotNullSpecification spec = new DepartureAirportNotNullSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertTrue(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "");	
	}
	
	@Test
	public void routeWithNullArrivalAirport() {
		Airport departureAirport = null;
		Airport arrivalAirport = new Airport("GRU");
		Integer cost = (Integer) 10;
		
		Route route = new Route(departureAirport, arrivalAirport, cost);
		DepartureAirportNotNullSpecification spec = new DepartureAirportNotNullSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(route);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Route does not have departure airport");	
	}
}
