import jdk.jshell.Snippet;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class Authorisatie extends JPanel{
    JTextField text;
    Preferences prefs = Preferences.userNodeForPackage(Authorisatie.class);
    public Authorisatie(){
        add(new JLabel("Key:"));
        text = new JTextField(prefs.get("Key", "Leeg"));
        text.setPreferredSize(new Dimension(400,20));
        add(text);
    }

    public void SaveKey(){
        prefs.put("Key", text.getText());
    }

    public String GetKey(){
        SaveKey();
        return prefs.get("Key", "Default");
    }

}
