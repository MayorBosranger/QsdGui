import jdk.jshell.Snippet;

import javax.swing.*;

public class Authorisatie implements GuiPanel{
    JPanel authorisatiePanel;
    public Authorisatie(){
        authorisatiePanel = new JPanel();

        JLabel label = new JLabel("Authorisatie");

        authorisatiePanel.add(label);
    }

    @Override
    public JPanel getPanel() {
        return authorisatiePanel;
    }

    @Override
    public GuiPanel getSoort() {
        return this;
    }

}
