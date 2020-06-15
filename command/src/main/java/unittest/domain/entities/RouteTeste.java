package unittest.domain.entities;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.entities.Route;
import domain.specifications.SpecificationResult;

public class RouteTeste {
	
	@Test
	public void RotaWithRightElements() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		
		final Route route = new Route(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertTrue(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "");
	}
	
	@Test
	public void RotaWithRightElementsCostInteger() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final Integer cost = 50;
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertTrue(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "");
	}
	
	@Test
	public void RotaWithNullDepartureAirport() {
		final Airport departureAirport = null;
		final Airport arrivalAirport = new Airport("ORL");
		
		final Route route = new Route(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Rota não possui aeroporto de partida");
	}
	
	@Test
	public void RotaWithNullArrivalAirport() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = null;
		
		final Route route = new Route(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Rota não possui aeroporto de chegada");
	}
	
	@Test
	public void RotaWithNullAirports() {
		final Airport departureAirport = null;
		final Airport arrivalAirport = null;
		
		final Route route = new Route(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
	}
	
	@Test
	public void RotaWithInvalidCost() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "Olá156615";
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser nulo");
	}
	
	@Test
	public void RotaWithInvalidCostFloat() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "56.30";
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser nulo");
	}
	
	@Test
	public void RotaWithInvalidCostNull() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = null;
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser nulo");
	}
	
	@Test
	public void RotaWithInvalidCostNegative() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "-10";
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser negativo");
	}
	
	@Test
	public void RotaWithInvalidCostNegativeInteger() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final Integer cost = -10;
		
		final Route route = new Route(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = route.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser negativo");
	}
	
	
}
