package Main;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private Verzoek verzoek;
    private Authorisatie authorisatie;
    private Kaart kaart;
    private JPanel mainPanel;

    JMenuBar Menubar;
    JMenuItem verzoekItem = new JMenuItem("Verzoek");
    JMenuItem authorisatieItem = new JMenuItem("Authorisatie");
    JMenuItem kaartItem = new JMenuItem("Kaart");

    public GUI(){
        FlatLightLaf.setup();
        setPreferredSize(new Dimension(1000,600));

        MakeMenuBar();
        setJMenuBar(Menubar);

        setUpPanels();
        pack();
        setDefaultCloseOperation(close());
        setVisible(true);
    }

    private void MakeMenuBar(){
        Menubar = new JMenuBar();
        menubarLayout();
        verzoekItem.addActionListener(this);
        authorisatieItem.addActionListener(this);
        kaartItem.addActionListener(this);
        Menubar.add(verzoekItem);
        Menubar.add(authorisatieItem);
        Menubar.add(kaartItem);
        Menubar.add(Box.createHorizontalGlue());
    }

    private int close(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                authorisatie.SaveKey();
                super.windowClosing(e);
            }
        });
        return JFrame.EXIT_ON_CLOSE;
    }

    private void menubarLayout(){
        verzoekItem.setMaximumSize(new Dimension(150, 100));
        authorisatieItem.setMaximumSize(new Dimension(150, 100));
        kaartItem.setMaximumSize(new Dimension(150, 100));
    }

    private void pressed(JMenuItem item){
        item.setForeground(new Color(200,200,200));
        item.setBackground(new Color(100,100,100));
    }

    private void unPressed(JMenuItem item){
        item.setForeground(Color.black);
        item.setBackground(Color.white);
    }

    private void unPressAll(){
        unPressed(verzoekItem);
        unPressed(authorisatieItem);
        unPressed(kaartItem);
    }

    private void setUpPanels(){
        authorisatie = new Authorisatie();
        kaart = new Kaart();
        verzoek = new Verzoek(kaart);
        cardLayout = new CardLayout();

        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
        mainPanel.add(verzoek.verzoekPanel, "verzoek");
        mainPanel.add(authorisatie.AuthorisatiePanel, "authorisatie");
        mainPanel.add(kaart, "kaart");
        cardLayout.show(mainPanel, "verzoek");
        pressed(verzoekItem);
    }

    public void actionPerformed(ActionEvent e) {
        unPressAll();
        switch (e.getActionCommand()){
            case("Verzoek"):
                pressed(verzoekItem);
                cardLayout.show(mainPanel, "verzoek");
                break;
            case ("Authorisatie"):
                pressed(authorisatieItem);
                cardLayout.show(mainPanel, "authorisatie");
                break;
            case("Kaart"):
                pressed(kaartItem);
                cardLayout.show(mainPanel, "kaart");
                break;
        }
    }


}
