package infra.repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import domain.entities.Airport;
import infra.data.DataBaseFactory;
import infra.data.IDataBaseFactory;
import infra.services.FileService;

public class AirportRepository implements IAirportRepository{
	
	final private IDataBaseFactory dataBaseFactory;
	
	public AirportRepository() {
		this.dataBaseFactory = new DataBaseFactory();
	}
	
	private IDataBaseFactory getDataBaseFactory() {
		return dataBaseFactory;
	}
	
	private List<Airport> linesToAirports(final List<String> linesList) {
		try {
			final List<Airport> airports = new ArrayList<Airport>();
			final List<String[]> linesFormated = linesList.stream().map(x -> x.replaceAll(" ", "").split(","))
                    .collect(Collectors.toList());
			
			final HashSet<String> iataCodeSet = new HashSet<String>();
			linesFormated.forEach(x -> {
				iataCodeSet.add(x[0].toUpperCase());
				iataCodeSet.add(x[1].toUpperCase());
			});
			
			iataCodeSet.forEach(x -> airports.add(new Airport(x)));
			
			return airports;
			
		} catch(final Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Airport> getAll() {
		String inputsPath = this.getDataBaseFactory().getDataSource();
		
		try {
            final List<String> linesList = FileService.read(inputsPath);

            return linesToAirports(linesList);
        } catch (final Exception e) {
            return null;
        }
	}

}
