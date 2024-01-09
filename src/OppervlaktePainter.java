import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

public class OppervlaktePainter implements Painter<JXMapViewer> {
    private final List<GeoPosition> areaCoordinates;

    public OppervlaktePainter(List<GeoPosition> areaCoordinates) {
        this.areaCoordinates = areaCoordinates;
    }

    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
        g = (Graphics2D) g.create();

        // converteert van GEO naar wereld bitmap in pixel
        Path2D path = new Path2D.Double();
        boolean first = true;

        // Huidige viewport om de offset aanpassen
        Rectangle rect = map.getViewportBounds();

        for (GeoPosition gp : areaCoordinates) {
            // Converteert naar wereld bitmap pixel
            Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());

            // past de punt aan naar de huidige viewpoint.
            double x = pt.getX() - rect.getX();
            double y = pt.getY() - rect.getY();

            if (first) {
                path.moveTo(x, y);
                first = false;
            } else {
                path.lineTo(x, y);
            }
        }
        path.closePath();

        g.setColor(new Color(255, 0, 0, 100));
        g.fill(path);
        g.setColor(Color.RED);
        g.draw(path);

        g.dispose();
    }
}
