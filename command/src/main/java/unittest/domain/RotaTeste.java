package unittest.domain;

import org.junit.Assert;
import org.junit.Test;

import domain.Airport;
import domain.Rota;
import domain.specifications.SpecificationResult;

public class RotaTeste {
	
	@Test
	public void RotaWithRightElements() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertTrue(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "");
	}
	
	@Test
	public void RotaWithRightElementsCostInteger() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final Integer cost = 50;
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertTrue(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "");
	}
	
	@Test
	public void RotaWithNullDepartureAirport() {
		final Airport departureAirport = null;
		final Airport arrivalAirport = new Airport("ORL");
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Rota não possui aeroporto de partida");
	}
	
	@Test
	public void RotaWithNullArrivalAirport() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = null;
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Rota não possui aeroporto de chegada");
	}
	
	@Test
	public void RotaWithNullAirports() {
		final Airport departureAirport = null;
		final Airport arrivalAirport = null;
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, "50");
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
	}
	
	@Test
	public void RotaWithInvalidCost() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "Olá156615";
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser nulo");
	}
	
	@Test
	public void RotaWithInvalidCostFloat() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "56.30";
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser nulo");
	}
	
	@Test
	public void RotaWithInvalidCostNull() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = null;
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser nulo");
	}
	
	@Test
	public void RotaWithInvalidCostNegative() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final String cost = "-10";
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser negativo");
	}
	
	@Test
	public void RotaWithInvalidCostNegativeInteger() {
		final Airport departureAirport = new Airport("GRU");
		final Airport arrivalAirport = new Airport("ORL");
		final Integer cost = -10;
		
		final Rota rota = new Rota(departureAirport, arrivalAirport, cost);
		SpecificationResult rotaSpec = rota.isValid();
		
		Assert.assertFalse(rotaSpec.isValid());
		Assert.assertEquals(rotaSpec.getMessage(), "Custo não pode ser negativo");
	}
	
	
}
