import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class Authorisation {
    Preferences prefs = Preferences.userNodeForPackage(Authorisation.class);
    private JTextField APIkeyTextField;
    public JPanel AuthorisationPanel;
    private JButton startAPIButton;

    public Authorisation(){
        APIkeyTextField.setText(prefs.get("ApiKey", "Leeg"));
        APIkeyTextField.setPreferredSize(new Dimension(400,20));

        startAPIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetKey();
            }
        });
    }

    public void SaveKey(){
        prefs.put("ApiKey", APIkeyTextField.getText());
    }

    public String GetKey(){
        SaveKey();
        return prefs.get("ApiKey", "");
    }

}
