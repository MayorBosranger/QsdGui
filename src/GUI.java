import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private Request request;
    private Authorisation authorisation;
    private Map map;
    private JPanel mainPanel;

    JMenuBar Menubar;
    JMenuItem RequestItem = new JMenuItem("Verzoek");
    JMenuItem AuthorisationItem = new JMenuItem("Authorisatie");
    JMenuItem MapItem = new JMenuItem("Kaart");

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
        RequestItem.addActionListener(this);
        AuthorisationItem.addActionListener(this);
        MapItem.addActionListener(this);
        Menubar.add(RequestItem);
        Menubar.add(AuthorisationItem);
        Menubar.add(MapItem);
        Menubar.add(Box.createHorizontalGlue());
    }

    private int close(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                authorisation.SaveKey();
                super.windowClosing(e);
            }
        });
        return JFrame.EXIT_ON_CLOSE;
    }

    private void menubarLayout(){
        RequestItem.setMaximumSize(new Dimension(150, 100));
        AuthorisationItem.setMaximumSize(new Dimension(150, 100));
        MapItem.setMaximumSize(new Dimension(150, 100));
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
        unPressed(RequestItem);
        unPressed(AuthorisationItem);
        unPressed(MapItem);
    }

    private void setUpPanels(){
        authorisation = new Authorisation();
        map = new Map();
        request = new Request(map);
        cardLayout = new CardLayout();

        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
        mainPanel.add(request.requestPanel, "request");
        mainPanel.add(authorisation.AuthorisationPanel, "authorisation");
        mainPanel.add(map, "map");
        cardLayout.show(mainPanel, "request");
        pressed(RequestItem);
    }

    public void actionPerformed(ActionEvent e) {
        unPressAll();
        switch (e.getActionCommand()){
            case("Verzoek"):
                pressed(RequestItem);
                cardLayout.show(mainPanel, "request");
                break;
            case ("Authorisatie"):
                pressed(AuthorisationItem);
                cardLayout.show(mainPanel, "authorisation");
                break;
            case("Kaart"):
                pressed(MapItem);
                cardLayout.show(mainPanel, "map");
                break;
        }
    }


}
