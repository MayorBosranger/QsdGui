import javax.swing.*;
import java.awt.*;

public class Verzoek implements GuiPanel{
    JPanel verzoekPanel;
    public Verzoek(){
        verzoekPanel = new JPanel();

        JLabel label = new JLabel("Kadapter");
        verzoekPanel.add(label);
    }

    @Override
    public JPanel getPanel() {
        return verzoekPanel;

    }

    @Override
    public GuiPanel getSoort() {
        return this;
    }
}
