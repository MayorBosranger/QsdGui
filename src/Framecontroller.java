import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Framecontroller {
    static int mouseX;
    static int mouseY;
    public static void draggable(JFrame frame){
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - mouseX;
                int deltaY = e.getY() - mouseY;

                frame.setLocation(frame.getLocation().x + deltaX, frame.getLocation().y + deltaY);
            }
        });

        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                frame.getContentPane().setSize(frame.getWidth(), frame.getHeight());
            }
        });
    }
}
