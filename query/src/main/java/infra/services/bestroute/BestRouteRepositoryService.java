package infra.services.bestroute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import domain.entities.Airport;
import domain.entities.Route;
import domain.vobjects.BestRoute;
import infra.services.bestroute.graph.DijkstraSP;
import infra.services.bestroute.graph.DirectedEdge;
import infra.services.bestroute.graph.EdgeWeightedDigraph;

public class BestRouteRepositoryService implements IBestRouteRepositoryService{
	
	private HashMap<String, Integer> getAirportToIntegerRelation(final List<Airport> airports) {
		final HashMap<String, Integer> relation = new HashMap<String, Integer>();
		
		for (int i = 0; i < airports.size(); i++) {
			relation.put(airports.get(i).getIataCode(), (Integer) i);
		}

		return relation;
	}
	
	private List<Integer> getOrigins(final List<Route> routes, final HashMap<String, Integer> relation) {
		final List<Integer> origins = new ArrayList<Integer>();
		
		for(int i = 0; i < routes.size(); i++) {
			String iataCode = routes.get(i).getDepartureAirport().getIataCode();
			origins.add(relation.get(iataCode));
		}		
		
		return origins;
	}
	
	private List<Integer> getDestinations(final List<Route> routes, final HashMap<String, Integer> relation) {
		final List<Integer> detinations = new ArrayList<Integer>();
		
		for(int i = 0; i < routes.size(); i++) {
			String iataCode = routes.get(i).getArrivalAirport().getIataCode();
			detinations.add(relation.get(iataCode));
		}		
		
		return detinations;
	}
	
	private List<Integer> getWeights(final List<Route> routes) {
		final List<Integer> weights = new ArrayList<Integer>();
		
		for(int i = 0; i < routes.size(); i++) {
			Integer cost = routes.get(i).getCost();
			weights.add(cost);
		}		
		
		return weights;
	}
	
	private Integer getVertex(Airport airport, HashMap<String, Integer> relation) {
		return relation.get(airport.getIataCode());
	}	
	
	private DijkstraSP calculateBestRoute(
		final HashMap<String, Integer> relation,
		final List<Integer> origins,
		final List<Integer> destinations,
		final List<Integer> weights,
		final Integer sourceVertex
	) {
		Integer numberVertices = relation.size();
		Integer numberOfEdges = origins.size();
				
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numberVertices, numberOfEdges, origins, destinations, weights);
		DijkstraSP dijkistra = new DijkstraSP(graph, sourceVertex);		
		
		return dijkistra;		
	}
	
	List<Airport> getAirports(Integer destinationVertex, List<Airport> airports, DijkstraSP dijkstra) {
		final boolean hasPath = dijkstra.hasPathTo(destinationVertex);
		if(!hasPath) {
			return new ArrayList<Airport>(0);
		}
		
		final List<Airport> routeAirports = new ArrayList<Airport>();
		final Iterable<DirectedEdge> edges = dijkstra.pathTo(destinationVertex);
		DirectedEdge lastEdge = null;
		
		for(DirectedEdge edge: edges) {
			routeAirports.add(airports.get(edge.to()));
			lastEdge = edge;
		}
		
		if(lastEdge != null) {
			routeAirports.add(airports.get(lastEdge.from()));
		}
		
		Collections.reverse(routeAirports);

		return routeAirports;		
	}
	
	Integer getCost(Integer destinationVertex, DijkstraSP dijkstra) {
		final boolean hasPath = dijkstra.hasPathTo(destinationVertex);
		if(!hasPath) {
			return null;
		}

		return (int) dijkstra.distTo(destinationVertex);
	}
	
	@Override
	public BestRoute bestRoute(
		final Airport departureAirport,
		final Airport arrivalAirport,
		final List<Airport> airports,
		final List<Route> routes) {
		
		HashMap<String, Integer> iataToIntRelation = getAirportToIntegerRelation(airports);
		List<Integer> origins = getOrigins(routes, iataToIntRelation);
		List<Integer> destinations = getDestinations(routes, iataToIntRelation);
		List<Integer> weights = getWeights(routes);
		Integer sourceVertex = getVertex(departureAirport, iataToIntRelation);
		Integer destinationVertex = getVertex(arrivalAirport, iataToIntRelation);
		
		DijkstraSP dijkstra = calculateBestRoute(iataToIntRelation, origins, destinations, weights, sourceVertex);
		
		List<Airport> routeAirports = getAirports(destinationVertex, airports, dijkstra);
		Integer cost = getCost(destinationVertex, dijkstra);
		
		return new BestRoute(routeAirports, cost);
	}
	
}
