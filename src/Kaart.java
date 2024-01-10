import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.List;

public class Kaart extends JPanel {
    private JXMapViewer mapViewer;

    public Kaart(){
        mapViewer = new JXMapViewer();
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Use 8 threads in parallel to load the tiles
        tileFactory.setThreadPoolSize(8);

        // Set the focus
        GeoPosition frankfurt = new GeoPosition(52.5, 5.0);

        mapViewer.setZoom(10);
        mapViewer.setAddressLocation(frankfurt);

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        List<GeoPosition> areaCoordinates = Arrays.asList(
                new GeoPosition(54, 5.0),
                new GeoPosition(53.6, 5.4),
                new GeoPosition(52.1, 4.4),
                new GeoPosition(52.3, 5.0),
                new GeoPosition(55.2,6.5),
                new GeoPosition(55.3,5.6)
        );

        OppervlaktePainter areaPainter = new OppervlaktePainter(areaCoordinates);
        mapViewer.setOverlayPainter(areaPainter);

        this.setLayout(new BorderLayout());
        this.add(mapViewer, BorderLayout.CENTER);

        // Resize listener
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                mapViewer.setSize(Kaart.this.getSize());
            }
        });
    }
}
