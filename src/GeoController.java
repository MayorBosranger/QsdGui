import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;

public class GeoController {
    public static ArrayList<GeoPosition> ParseGeoPosition(String input){
        ArrayList<GeoPosition> result = new ArrayList<GeoPosition>();
        if(input == null || input.isEmpty()) return result;
        String[] positions = input.split(",");
        for(String position : positions){
            if(position.isEmpty()) continue;
            String[] coords = position.trim().split(" ");
            result.add(new GeoPosition(Double.parseDouble(coords[1]), Double.parseDouble(coords[0])));
        }
        return result;
    }

    public double CalculateSurfaceArea(double[] xCoordinates, double[] yCoordinates) throws Exception {
        if(xCoordinates.length != yCoordinates.length) throw new Exception("lengte van arrays verschilt");

        double surface = 0;
        int amountOfCoordinates = xCoordinates.length;

        for (int i = 0; i < amountOfCoordinates; i++) {
            int nextI = (i+1)%amountOfCoordinates;
            surface += xCoordinates[i]*yCoordinates[nextI] - yCoordinates[i]*xCoordinates[nextI];
        }

        if(surface < 0) surface = -surface;
        return surface/2;
    }
}
