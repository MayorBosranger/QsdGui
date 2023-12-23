import jdk.jshell.Snippet;

import javax.swing.*;
import java.awt.*;

public class Authorisatie extends JPanel{
    JTextField text;
    public Authorisatie(){
        setBackground(Color.white);
        add(new JLabel("Key:"));
        text = new JTextField();
        text.setPreferredSize(new Dimension(400,20));
        add(text);
    }

    public String GetKey(){
        return text.getText();
    }

}
