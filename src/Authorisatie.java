import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class Authorisatie{
    Preferences prefs = Preferences.userNodeForPackage(Authorisatie.class);
    private JTextField keyTextField;
    public JPanel AuthorisatiePanel;
    private JButton startAPIButton;

    public Authorisatie(){
        keyTextField.setText(prefs.get("Key", "Leeg"));
        keyTextField.setPreferredSize(new Dimension(400,20));
        startAPIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void SaveKey(){
        prefs.put("Key", keyTextField.getText());
    }

    public String GetKey(){
        SaveKey();
        return prefs.get("Key", "Default");
    }

}
