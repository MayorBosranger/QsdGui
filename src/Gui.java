import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    private JFrame KadasterGui;
    private CardLayout cardLayout;
    private Verzoek verzoek;
    private Authorisatie authorisatie;
    private Instellingen instellingen;
    private JPanel mainPanel;

    JMenuBar Menubar;
    JMenuItem verzoekItem = new JMenuItem("Verzoek");
    JMenuItem authorisatieItem = new JMenuItem("Authorisatie");
    JMenuItem instellingenItem = new JMenuItem("Instellingen");

    public Gui(){
        KadasterGui = new JFrame("KadasterGui");
        KadasterGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KadasterGui.setPreferredSize(new Dimension(1000,600));

        MakeMenuBar();
        KadasterGui.setJMenuBar(Menubar);

        setUpPanels();
        KadasterGui.pack();
        KadasterGui.setVisible(true);
    }

    private void MakeMenuBar(){
        Menubar = new JMenuBar();
        menubarLayout();
        verzoekItem.addActionListener(this);
        authorisatieItem.addActionListener(this);
        instellingenItem.addActionListener(this);
        Menubar.add(verzoekItem);
        Menubar.add(authorisatieItem);
        Menubar.add(instellingenItem);
    }

    private void menubarLayout(){
        verzoekItem.setMaximumSize(new Dimension(150, 1000));
        authorisatieItem.setMaximumSize(new Dimension(150, 1000));
        instellingenItem.setMaximumSize(new Dimension(150, 1000));
        Menubar.setOpaque(true);
    }

    private void pressed(JMenuItem item){
        item.setForeground(new Color(200,200,200));
        item.setBackground(new Color(100,100,100));
    }

    private void unPressed(JMenuItem item){
        item.setForeground(Color.black);
        item.setBackground(Color.white);
    }

    private void setUpPanels(){
        verzoek = new Verzoek();
        authorisatie = new Authorisatie();
        instellingen = new Instellingen();
        cardLayout = new CardLayout();

        mainPanel = new JPanel(cardLayout);
        KadasterGui.add(mainPanel);
        mainPanel.add(verzoek, "verzoek");
        mainPanel.add(authorisatie, "authorisatie");
        mainPanel.add(instellingen, "instellingen");
        cardLayout.show(mainPanel, "verzoek");
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case("Verzoek"):
                pressed(verzoekItem);
                unPressed(authorisatieItem);
                unPressed(instellingenItem);
                cardLayout.show(mainPanel, "verzoek");
                break;
            case ("Authorisatie"):
                pressed(authorisatieItem);
                unPressed(verzoekItem);
                unPressed(instellingenItem);
                cardLayout.show(mainPanel, "authorisatie");
                break;
            case("Instellingen"):
                pressed(instellingenItem);
                unPressed(verzoekItem);
                unPressed(authorisatieItem);
                cardLayout.show(mainPanel, "instellingen");
                break;
        }
    }


}
