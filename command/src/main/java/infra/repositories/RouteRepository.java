package infra.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.entities.Airport;
import domain.entities.Route;
import infra.data.DataBaseFactory;
import infra.data.IDataBaseFactory;
import infra.services.FileService;

public class RouteRepository implements IRouteRepository {
	
	final private IDataBaseFactory dataBaseFactory;
	
	public RouteRepository() {
		this.dataBaseFactory = new DataBaseFactory();
	}
	
	private IDataBaseFactory getDataBaseFactory() {
		return this.dataBaseFactory;
	}

    private List<Route> linesToRotas(final List<String> linesList) {
        try {
            final List<Route> routes = new ArrayList<Route>();
            final List<String[]> linesFormated = linesList.stream().map(x -> x.replaceAll(" ", "").split(","))
                    .collect(Collectors.toList());

            linesFormated.forEach(x -> routes.add(new Route(new Airport(x[0]), new Airport(x[1]), x[2])));

            return routes;
        } catch (final Exception e) {
            return null;
        }
    }

    @Override
    public List<Route> getAll() {
    	String inputsPath = this.getDataBaseFactory().getDataSource();
    	
        try {
            final List<String> linesList = FileService.read(inputsPath);

            return linesToRotas(linesList);
        } catch (final Exception e) {
            return null;
        }
    }

    @Override
    public boolean insert(final Route route) {
    	String inputsPath = this.getDataBaseFactory().getDataSource(); 
    	
        final String text = String
                .format(
            "\n%s,%s,%s",
            route.getDepartureAirport().getIataCode(),
            route.getArrivalAirport().getIataCode(),
            route.getCost());
        
        final boolean isWritten = FileService.write(text, true, inputsPath);
        
        return isWritten;
    }
    
}