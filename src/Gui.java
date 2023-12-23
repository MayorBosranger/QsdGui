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

    JMenuBar Menubar;
    JMenuItem verzoekItem;
    JMenuItem authorisatieItem;

    public Gui(){
        KadasterGui = new JFrame("KadasterGui");
        KadasterGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KadasterGui.setPreferredSize(new Dimension(1000,600));

        MakeMenuBar();
        KadasterGui.setJMenuBar(Menubar);

        setUpPanels();
        KadasterGui.pack();
//        KadasterGui.setLocationRelativeTo(null);
        KadasterGui.setVisible(true);
    }

    private void MakeMenuBar(){
        verzoekItem = new JMenuItem("Verzoek");
        authorisatieItem = new JMenuItem("Authorisatie");
        Menubar = new JMenuBar();
        menubarLayout();
        verzoekItem.addActionListener(this);
        authorisatieItem.addActionListener(this);
        Menubar.add(verzoekItem);
        Menubar.add(authorisatieItem);
    }

    private void menubarLayout(){
        verzoekItem.setMaximumSize(new Dimension(150, 1000));
        authorisatieItem.setMaximumSize(new Dimension(150, 1000));
        authorisatieItem.setBackground(Color.black);
        authorisatieItem.setForeground(Color.white);
        verzoekItem.setBackground(Color.black);
        verzoekItem.setForeground(Color.white);
        Menubar.setOpaque(true);
        Menubar.setBackground(Color.black);
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
                pressed(verzoekItem);
                unPressed(authorisatieItem);
                cardLayout.show(mainPanel, "verzoek");
                break;
            case ("Authorisatie"):
                pressed(authorisatieItem);
                unPressed(verzoekItem);
                cardLayout.show(mainPanel, "authorisatie");
                break;
        }
    }


}
