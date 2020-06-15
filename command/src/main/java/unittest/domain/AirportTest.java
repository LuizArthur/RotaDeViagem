package unittest.domain;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.specifications.SpecificationResult;

public class AirportTest {
    
	@Test
    public void AirportWithRightIataPattern() {
        final Airport airport = new Airport("LAX");
        final SpecificationResult airportSpec = airport.isValid();

        Assert.assertTrue(airportSpec.isValid());
        Assert.assertEquals(airportSpec.getMessage(), "");
    }
	
	@Test
	public void AirportIataCodeWithNumbers() {
		final Airport airport = new Airport("LA2");
		final SpecificationResult airportSpec = airport.isValid();
		
		Assert.assertFalse(airportSpec.isValid());
		Assert.assertEquals(airportSpec.getMessage(), "Código do aerporto não obedece o padrão IATA");
	}
	
	@Test
	public void AiportIataCodeWithLengthBiggerThan3() {
		final Airport airport = new Airport("LAXD");
		final SpecificationResult airportSpec = airport.isValid();
		
		Assert.assertFalse(airportSpec.isValid());
		Assert.assertEquals(airportSpec.getMessage(), "Código do aerporto não obedece o padrão IATA");
	}
	
	@Test
	public void AiportIataCodeWithLengthLessThan3() {
		final Airport airport = new Airport("LA");
		final SpecificationResult airportSpec = airport.isValid();
		
		Assert.assertFalse(airportSpec.isValid());
		Assert.assertEquals(airportSpec.getMessage(), "Código do aerporto não obedece o padrão IATA");
	}
	
	@Test
	public void AiportIataCodeWithLength0() {
		final Airport airport = new Airport("");
		final SpecificationResult airportSpec = airport.isValid();
		
		Assert.assertFalse(airportSpec.isValid());
		Assert.assertEquals(airportSpec.getMessage(), "Código do aerporto não obedece o padrão IATA");
	}
	
	@Test
	public void AiportIataCodeNull() {
		final Airport airport = new Airport(null);
		final SpecificationResult airportSpec = airport.isValid();
		
		Assert.assertFalse(airportSpec.isValid());
		Assert.assertEquals(airportSpec.getMessage(), "O Código do aeroporto fornecido é nulo");
	}
	
}