import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    private JFrame KadasterGui;
    private CardLayout cardLayout;
    private Verzoek verzoek;
    private Authorisatie authorisatie;
    private JPanel mainPanel;

    JMenuBar Menubar = new JMenuBar();
    JMenuItem VerzoekItem = new JMenuItem("Verzoek");
    JMenuItem AuthorisatieItem = new JMenuItem("Authorisatie");

    public Gui(){
        KadasterGui = new JFrame("KadasterGui");
        KadasterGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KadasterGui.setPreferredSize(new Dimension(1000,600));

        KadasterGui.setJMenuBar(MakeMenuBar());

        setUpPanels();
        KadasterGui.pack();
        KadasterGui.setLocationRelativeTo(null);
        KadasterGui.setVisible(true);
    }

    private JMenuBar MakeMenuBar(){
        VerzoekItem.addActionListener(this);
        AuthorisatieItem.addActionListener(this);
        Menubar.add(VerzoekItem);
        Menubar.add(AuthorisatieItem);
        return Menubar;
    }

    private void setUpPanels(){
        verzoek = new Verzoek();
        authorisatie = new Authorisatie();
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        KadasterGui.add(mainPanel);
        mainPanel.add(verzoek, "verzoek");
        mainPanel.add(authorisatie, "authorisatie");
        cardLayout.show(mainPanel, "verzoek");
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case("Verzoek"):
                VerzoekItem.setEnabled(false);
                AuthorisatieItem.setEnabled(true);
                cardLayout.show(mainPanel, "verzoek");
                break;
            case ("Authorisatie"):
                AuthorisatieItem.setEnabled(false);
                VerzoekItem.setEnabled(true);
                cardLayout.show(mainPanel, "authorisatie");
                break;
        }
    }


}
