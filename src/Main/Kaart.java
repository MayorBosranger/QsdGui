package Main;

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

        tileFactory.setThreadPoolSize(8);

        mapViewer.setZoom(7);
        mapViewer.setPanEnabled(true);

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        List<GeoPosition> areaCoordinates = Arrays.asList(
                new GeoPosition(52.17984265967399, 5.9606851335416575),
                new GeoPosition(52.17984909559126, 5.959732632231972),
                new GeoPosition(52.17971555011681, 5.9597195123792215),
                new GeoPosition(52.17970911418019, 5.959971413552031),
                new GeoPosition(52.17960774805575, 5.959960917669831),
                new GeoPosition(52.1796061390678, 5.959743128114173),
                new GeoPosition(52.17946937487803, 5.959740504143623),
                new GeoPosition(52.17945650293264, 5.960687757512209),
                new GeoPosition(52.17959970311535, 5.960679885600558),
                new GeoPosition(52.1796061390678, 5.960451600162699),
                new GeoPosition(52.179713941132746, 5.96045422413325),
                new GeoPosition(52.1797123321486, 5.960682509571108)
        );

        OppervlaktePainter areaPainter = new OppervlaktePainter(areaCoordinates);
        mapViewer.setOverlayPainter(areaPainter);
        mapViewer.setZoom(1);
        mapViewer.setAddressLocation(areaPainter.berekenMiddelpunt());


        this.setLayout(new BorderLayout());
        this.add(mapViewer, BorderLayout.CENTER);

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
