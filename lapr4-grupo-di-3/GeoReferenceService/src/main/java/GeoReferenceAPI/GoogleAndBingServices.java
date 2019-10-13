/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoReferenceAPI;

import DTO.DistanceBetweenPlacesDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Francisco Negrão
 */
public class GoogleAndBingServices {

    /**
     * Método que retorna a média da elevação num dado local, calculada entre a
     * microsoft service e a bing.
     *
     * @param latitude
     * @param longitude
     * @return
     * @throws ParseException
     */
    public double mediaElevation(double latitude, double longitude) throws ParseException {
        GoogleMapsServices g = new GoogleMapsServices();
        MicrosoftBingServices m = new MicrosoftBingServices();
        Double elevation = g.getElevation(latitude, longitude);
        Long elevation2 = m.getElevation(latitude, longitude);

        Double elevation3 = elevation2.doubleValue();
        return (elevation + elevation3) / 2;
    }

    /**
     * Método para retornar a lista de lugares qu se situam a um dado raio de
     * uma certa localização, sem repetições.
     *
     * @param latitude
     * @param longitude
     * @param tipo
     * @param tipo2
     * @param raio
     * @return
     * @throws ParseException
     */
    public List<String> listaFinalSemDuplicados(Double latitude, Double longitude, String tipo, String tipo2, Double raio) throws ParseException {
        GoogleMapsServices g = new GoogleMapsServices();
        MicrosoftBingServices m = new MicrosoftBingServices();
        List<String> listaStringsGoogle = g.getNearbyPlacesFromJson(latitude, longitude, tipo, raio);
        List<String> listaStringsBing = m.getNearbyPlacesFromJson(latitude, longitude, tipo2, raio);

        List<String> listaStringsFinal = new ArrayList();
        for (int i = 0; i < listaStringsGoogle.size(); i++) {
            listaStringsFinal.add(listaStringsGoogle.get(i));
        }

        for (int e = 0; e < listaStringsBing.size(); e++) {
            listaStringsFinal.add(listaStringsBing.get(e));
        }

        for (int i = 0; i < listaStringsFinal.size(); i++) {
            for (int j = i + 1; j < listaStringsFinal.size(); j++) {
                String[] teste = listaStringsFinal.get(i).split("/");

                String nome1 = teste[0];

                String[] teste2 = listaStringsFinal.get(j).split("/");

                String nome2 = teste2[0];

                if (nome1.equalsIgnoreCase(nome2)) {
                    listaStringsFinal.remove(i);
                }
            }

        }

        return listaStringsFinal;

    }

    /**
     * Método que retorna a distância entre dois dados locais, tendo em conta um
     * determinado tipo de viagem e que os resultados devolvidos são a média dos
     * obtidos através do GMS e do MBS.
     *
     * @param location1 Nome da primeira localização
     * @param location2 Nome da segunda localização
     * @param coordenadas1 Coordenadas da primeira localização
     * @param coordenadas2 Coordenadas da segunda localização
     * @param travelType Tipo de viagem
     * @return Lista com os valores
     * @throws ParseException
     */
    public DistanceBetweenPlacesDTO getAverageDistanceBetweenPlaces(String location1, String location2, String coordenadas1, String coordenadas2, String travelType) throws ParseException {

        GoogleMapsServices g = new GoogleMapsServices();
        MicrosoftBingServices m = new MicrosoftBingServices();
        List<Long> listaFinal = new ArrayList<>();

        DistanceBetweenPlacesDTO dto1 = g.getDistanceBetweenPlacesFromJson(location1, location2, travelType);

        DistanceBetweenPlacesDTO dto2 = m.getDistanceBetweenPlacesFromJson(coordenadas1, coordenadas2, travelType);

        Long sumDist = dto1.getDistance() + dto2.getDistance();
        Long sumDur = dto1.getDuration() + dto2.getDuration();

        double averageDistance = (double) sumDist / 2;
        double averageDuration = (double) sumDur / 2;

        long averageDistanceFinal = (int) Math.round(averageDistance);
        long averageDurationFinal = (int) Math.round(averageDuration);

        DistanceBetweenPlacesDTO dtoFinal = new DistanceBetweenPlacesDTO(averageDistanceFinal, averageDurationFinal);

        return dtoFinal;

    }

    public double getDifferences(String rua1, String localidade1, String endereco1, String rua2, String localidade2, String endereco2) throws ParseException, IOException {

        GoogleMapsServices g = new GoogleMapsServices();
        MicrosoftBingServices m = new MicrosoftBingServices();
        String servico = HTTPClient.getConfiguredMethodFromConfigFile("SG06");
        double diff = 0;
        double diffBing = 0;
        double diffGoogle = 0;

        if (servico.equalsIgnoreCase("mbs")) {
            diff = m.getDifferenceBing(rua1, localidade1, endereco1, rua2, localidade2, endereco2);
        } else if (servico.equalsIgnoreCase("gms")) {
            diff = g.getDifferenceGoogle(rua1, localidade1, endereco1, rua2, localidade2, endereco2);
        } else if (servico.equalsIgnoreCase("combined")) {
            diffBing = m.getDifferenceBing(rua1, localidade1, endereco1, rua2, localidade2, endereco2);
            diffGoogle = g.getDifferenceGoogle(rua1, localidade1, endereco1, rua2, localidade2, endereco2);
            diff = getDifferenceCombination(diffBing, diffGoogle);
        }

        return diff;

    }

    /**
     * Método que retorna a média da combinação entre a diferença obtida por
     * Bing e por Google
     *
     * @param diffBing
     * @param diffGoogle
     * @return
     */
    public double getDifferenceCombination(double diffBing, double diffGoogle) {
        double media = 0;

        media = (diffBing + diffGoogle) / 2;

        return media;
    }

    public DistanceBetweenPlacesDTO getDistanceBetweenPlacesDefault(String location1, String location2, String coordinates1, String coordinates2, String travelType) throws IOException, ParseException {

        String result = HTTPClient.getConfiguredMethodFromConfigFile("SG05");
        DistanceBetweenPlacesDTO finalResults = null;
        MicrosoftBingServices m = new MicrosoftBingServices();
        GoogleMapsServices g = new GoogleMapsServices();

        if (result.equalsIgnoreCase("mbs")) {
            finalResults = m.getDistanceBetweenPlacesFromJson(coordinates1, coordinates2, travelType);
        } else if (result.equalsIgnoreCase("gms")) {
            finalResults = g.getDistanceBetweenPlacesFromJson(location1, location2, travelType);
        } else if (result.equalsIgnoreCase("combined")) {
            finalResults = getAverageDistanceBetweenPlaces(location1, location2, coordinates1, coordinates2, travelType);
        }

        return finalResults;

    }

}
