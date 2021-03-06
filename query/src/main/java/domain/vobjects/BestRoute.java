package domain.vobjects;

import java.util.List;

import domain.entities.Airport;

public class BestRoute {
	private List<Airport> airports;
	private Integer cost;
	
	public BestRoute(List<Airport> airports, Integer cost) {
		this.airports = airports;
		this.cost = cost;
	}
	
	public List<Airport> getAirports() {
		return airports;
	}
	
	public Integer getCost() {
		return cost;
	}
}
