package infra.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.entities.Airport;
import domain.entities.Route;
import infra.services.FileService;

public class RouteRepository implements IRouteRepository{

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
    public List<Route> getAll(final String inputsPath) {
        try {
            final List<String> linesList = FileService.read(inputsPath);

            return linesToRotas(linesList);
        } catch (final Exception e) {
            return null;
        }
    }

}
