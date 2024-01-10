import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;
import java.util.List;

public class GeoController {
    public ArrayList<GeoPosition> ParseGeoPosition(String input){
        String[] positions = input.split(",");
        ArrayList<GeoPosition> result = new ArrayList<GeoPosition>();
        for(String s : positions){
            String[] coords = s.trim().split(" ");
            result.add(new GeoPosition(Double.parseDouble(coords[1]), Double.parseDouble(coords[0])));
        }
        return result;
    }

    public double BerekenOppervlakte(double[] xCoordinaten, double[] yCoordinaten) throws Exception {
        if(xCoordinaten.length != yCoordinaten.length) throw new Exception("lengte van arrays verschilt");

        double oppervlakte = 0;
        int aantalCoordinaten = xCoordinaten.length;

        for (int i = 0; i < aantalCoordinaten; i++) {
            int nextI = (i+1)%aantalCoordinaten;
            oppervlakte += xCoordinaten[i]*yCoordinaten[nextI] - yCoordinaten[i]*xCoordinaten[nextI];
        }

        if(oppervlakte < 0) oppervlakte = -oppervlakte;
        return oppervlakte/2;
    }
}