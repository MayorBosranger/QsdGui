public class OppervlakteCalculator {
    public double Bereken(double[] xCoordinaten, double[] yCoordinaten) throws Exception {
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
