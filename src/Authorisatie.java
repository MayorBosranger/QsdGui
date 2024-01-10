import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import gateway.src.main.java.nl.geostandaarden.imx.orchestrate.gateway.*;

public class Authorisatie{
    Preferences prefs = Preferences.userNodeForPackage(Authorisatie.class);
    private JTextField keyTextField;
    public JPanel AuthorisatiePanel;
    private JButton startAPIButton;

    public Authorisatie(){
        keyTextField.setText(prefs.get("ApiKey", ""));
        keyTextField.setPreferredSize(new Dimension(400,20));

        startAPIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetKey();
//                try {
//                    Process proc = Runtime.getRuntime().exec("java -jar \\.Orchestrate\\imx-orchestrate-quadaster.jar");
//                    proc.waitFor();
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//                ProcessBuilder pb = new ProcessBuilder("imx-orchestrate-quadaster.jar", "-jar", "imx-orchestrate-quadaster.jar");
//                pb.directory(new File("C:\\Users\\lucmb\\Desktop\\school\\jaar 3\\GuiQsd\\QsdGui\\Orchestrate\\"));
//                try {
//                    Process p = pb.start();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
            }
        });
    }

    public void SaveKey(){
        prefs.put("ApiKey", keyTextField.getText());
    }

    public String GetKey(){
        SaveKey();
        return prefs.get("ApiKey", "");
    }

}
