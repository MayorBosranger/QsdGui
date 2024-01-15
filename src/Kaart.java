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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kaart extends JPanel {
    private JXMapViewer mapViewer;
    private final int maximumZoomLevel = 17; // max zoomniveau


    public Kaart(){
        mapViewer = new JXMapViewer();
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Use 8 threads in parallel to load the tiles
        tileFactory.setThreadPoolSize(8);

        // Set the focus
        GeoPosition frankfurt = new GeoPosition(52.5, 5.0);

        mapViewer.setZoom(7);
        mapViewer.setPanEnabled(true);

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        List<GeoPosition> areaCoordinates = Arrays.asList(
                new GeoPosition(52.338276,5.174459),
                new GeoPosition(52.378324,5.173828),
                new GeoPosition(52.394249,5.247269),
                new GeoPosition(52.371617,5.287764)
        );

        OppervlaktePainter areaPainter = new OppervlaktePainter(areaCoordinates);
        mapViewer.setOverlayPainter(areaPainter);
        mapViewer.setAddressLocation(areaPainter.berekenMiddelpunt());


        this.setLayout(new BorderLayout());
        this.add(mapViewer, BorderLayout.CENTER);

        // Resize listener
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                mapViewer.setSize(Kaart.this.getSize());
            }
        });

        mapViewer.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getPreciseWheelRotation() > 0) {
                    if(mapViewer.getZoom() > maximumZoomLevel)
                        mapViewer.setZoom(17);
                }
            }
        });
    }

    public void SetAreaPainter(ArrayList<GeoPosition> points){
        OppervlaktePainter areaPainter = new OppervlaktePainter(points);
        mapViewer.setOverlayPainter(areaPainter);
        mapViewer.setAddressLocation(areaPainter.berekenMiddelpunt());
    }
}
