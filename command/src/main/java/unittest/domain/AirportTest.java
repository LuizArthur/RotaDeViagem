package unittest.domain;

import org.junit.Assert;
import org.junit.Test;

import domain.Airport;
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
}