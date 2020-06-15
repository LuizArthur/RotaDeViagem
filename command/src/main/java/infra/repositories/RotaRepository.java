package infra.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Airport;
import domain.Rota;
import utils.FileUtils;

public class RotaRepository implements IRotaRepository {

    private List<Rota> linesToRotas(final List<String> linesList) {
        try {
            final List<Rota> rotas = new ArrayList<Rota>();
            final List<String[]> linesFormated = linesList.stream().map(x -> x.replaceAll(" ", "").split(","))
                    .collect(Collectors.toList());

            linesFormated.forEach(x -> rotas.add(new Rota(new Airport(x[0]), new Airport(x[1]), x[2])));

            return rotas;
        } catch (final Exception e) {
            return null;
        }
    }

    @Override
    public List<Rota> getAll(final String inputsPath) {
        try {
            final List<String> linesList = FileUtils.read(inputsPath);

            return linesToRotas(linesList);
        } catch (final Exception e) {
            return null;
        }
    }

    @Override
    public boolean insert(final Rota rota, final String inputsPath) {
        final String text = String
                .format(
            "\n%s,%s,%s",
            rota.getDepartureAirport().getIataCode(),
            rota.getArrivalAirport().getIataCode(),
            rota.getCost());

        
        final boolean isWritten = FileUtils.write(text, true, inputsPath);
        
        return isWritten;
    }
    
}