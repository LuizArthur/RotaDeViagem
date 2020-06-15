package domain.vobjects;

import java.util.List;

import domain.entities.Airport;

public class BestRoute {
	private List<Airport> airports;
	private Integer cost;
	
	private BestRoute(List<Airport> airports, Integer cost) {
		setAirports(airports);
		setCost(cost);
	}
	
	public List<Airport> getAirports() {
		return airports;
	}
	
	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
}
