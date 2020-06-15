package unittest.domain.services;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.entities.Airport;
import domain.entities.Route;
import domain.exceptions.DomainRuleException;
import domain.services.IRouteService;
import domain.services.RouteService;

public class RouteServiceTest {
	
	@Test
	public void getAllRoutesCorrectly() {
		final IRouteService routeService = new RouteService();
		final String projectDirectory = System.getProperty("user.dir");
		final String inputsPath = Paths.get(projectDirectory, "testdocuments", "input-file.txt").toString();
		
		try {
			List<Route> routes = routeService.getAll(inputsPath);
			Assert.assertEquals(routes.get(0).getDepartureAirport().getIataCode(), "GRU");
			Assert.assertEquals(routes.get(0).getArrivalAirport().getIataCode(), "BRC");
			Assert.assertEquals(routes.get(0).getCost(), (Integer) 10);
			
			Assert.assertEquals(routes.get(1).getDepartureAirport().getIataCode(), "BRC");
			Assert.assertEquals(routes.get(1).getArrivalAirport().getIataCode(), "SCL");
			Assert.assertEquals(routes.get(1).getCost(), (Integer) 5);
			
			Assert.assertEquals(routes.get(2).getDepartureAirport().getIataCode(), "GRU");
			Assert.assertEquals(routes.get(2).getArrivalAirport().getIataCode(), "CDG");
			Assert.assertEquals(routes.get(2).getCost(), (Integer) 75);
			
			Assert.assertEquals(routes.get(3).getDepartureAirport().getIataCode(), "GRU");
			Assert.assertEquals(routes.get(3).getArrivalAirport().getIataCode(), "SCL");
			Assert.assertEquals(routes.get(3).getCost(), (Integer) 20);
			
			Assert.assertEquals(routes.get(4).getDepartureAirport().getIataCode(), "GRU");
			Assert.assertEquals(routes.get(4).getArrivalAirport().getIataCode(), "ORL");
			Assert.assertEquals(routes.get(4).getCost(), (Integer) 56);
			
			Assert.assertEquals(routes.get(5).getDepartureAirport().getIataCode(), "ORL");
			Assert.assertEquals(routes.get(5).getArrivalAirport().getIataCode(), "CDG");
			Assert.assertEquals(routes.get(5).getCost(), (Integer) 5);
			
			Assert.assertEquals(routes.get(6).getDepartureAirport().getIataCode(), "SCL");
			Assert.assertEquals(routes.get(6).getArrivalAirport().getIataCode(), "ORL");
			Assert.assertEquals(routes.get(6).getCost(), (Integer) 20);
		} catch (DomainRuleException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void getAllRoutesWithNullPath() {
		final IRouteService routeService = new RouteService();
		final String inputsPath = null;
		
		try {
			final List<Route> routes = routeService.getAll(inputsPath);
		} catch (DomainRuleException e) {
			Assert.assertEquals(e.getMessage(), "O caminho do input não foi fornecido corretamente");
		}
	}
	
	@Test
	public void getAllRoutesWithInvalidPath() {
		final IRouteService routeService = new RouteService();
		final String projectDirectory = System.getProperty("user.dir");
		final String inputsPath = Paths.get(projectDirectory, "input-file.txt").toString();
		
		try {
			final List<Route> routes = routeService.getAll(inputsPath);
		} catch (DomainRuleException e) {
			Assert.assertEquals(e.getMessage(), "Não foi possível fazer a leitura do arquivo input");
		}
	}
	
	@Test
	public void getAllRoutesWithInvalidFile() {
		final IRouteService routeService = new RouteService();
		final String projectDirectory = System.getProperty("user.dir");
		final String inputsPath = Paths.get(projectDirectory, "testdocuments", "inputsWrongFormat.txt").toString();
		
		try {
			final List<Route> routes = routeService.getAll(inputsPath);
		} catch (DomainRuleException e) {
			Assert.assertEquals(e.getMessage(), "O arquivo de input possue dados inconsistentes");
		}
	}
	
	@Test
	public void getAllRoutesWithEmptyFile() {
		final IRouteService routeService = new RouteService();
		final String projectDirectory = System.getProperty("user.dir");
		final String inputsPath = Paths.get(projectDirectory, "testdocuments", "inputsEmpty.txt").toString();		
		
		try {
			final List<Route> routes = routeService.getAll(inputsPath);
			
			Assert.assertEquals(routes.size(), 0);
		} catch (DomainRuleException e) {
			e.printStackTrace();
		}
	}	
}
