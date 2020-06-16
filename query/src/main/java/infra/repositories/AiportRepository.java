package infra.repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import domain.entities.Airport;
import infra.services.FileService;

public class AiportRepository implements IAirportRepository{
	
	private List<Airport> linesToAirports(final List<String> linesList) {
		try {
			final List<Airport> airports = new ArrayList<Airport>();
			final List<String[]> linesFormated = linesList.stream().map(x -> x.replaceAll(" ", "").split(","))
                    .collect(Collectors.toList());
			
			final HashSet<String> setIataCode = new HashSet<String>();
			linesFormated.forEach(x -> {
				setIataCode.add(x[0].toUpperCase());
				setIataCode.add(x[1].toUpperCase());
			});
			
			setIataCode.forEach(x -> airports.add(new Airport(x)));
			
			return airports;
			
		} catch(final Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Airport> getAll(String inputsPath) {
		try {
            final List<String> linesList = FileService.read(inputsPath);

            return linesToAirports(linesList);
        } catch (final Exception e) {
            return null;
        }
	}

}
