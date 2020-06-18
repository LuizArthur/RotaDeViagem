package unittest.domain.specifications;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.specifications.AirportIataCodePatternSpecification;
import domain.specifications.SpecificationResult;

public class AirportIataCodePatternSpecificationTest {
	@Test
	public void airportPatternCorrect() {
		Airport airport = new Airport("GRU");
		AirportIataCodePatternSpecification spec = new AirportIataCodePatternSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(airport);
		
		Assert.assertTrue(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "");
	}
	
	@Test
	public void airportPatternCorrectWithLowerCaseIataCreation() {
		Airport airport = new Airport("gru");
		AirportIataCodePatternSpecification spec = new AirportIataCodePatternSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(airport);
		
		Assert.assertTrue(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "");
	}
	
	@Test
	public void airportIataCodeWithNumbers() {
		Airport airport = new Airport("G1U");
		AirportIataCodePatternSpecification spec = new AirportIataCodePatternSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(airport);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Airport code does not obey IATA pattern");
	}
	
	@Test
	public void AiportIataCodeWithLengthBiggerThan3() {
		Airport airport = new Airport("GRUA");
		AirportIataCodePatternSpecification spec = new AirportIataCodePatternSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(airport);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Airport code does not obey IATA pattern");
	}
	
	@Test
	public void AiportIataCodeWithLengthLessThan3() {
		Airport airport = new Airport("GR");
		AirportIataCodePatternSpecification spec = new AirportIataCodePatternSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(airport);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Airport code does not obey IATA pattern");
	}
	
	@Test
	public void AiportIataCodeWithLength0() {
		Airport airport = new Airport("");
		AirportIataCodePatternSpecification spec = new AirportIataCodePatternSpecification();
		
		SpecificationResult actual = spec.isSatisfiedBy(airport);
		
		Assert.assertFalse(actual.isValid());
		Assert.assertEquals(actual.getMessage(), "Airport code does not obey IATA pattern");
	}
}
