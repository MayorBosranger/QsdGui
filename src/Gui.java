import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame implements ActionListener{
    private CardLayout cardLayout;
    private Verzoek verzoek;
    private Authorisatie authorisatie;
    private Instellingen instellingen;
    private JPanel mainPanel;

    JMenuBar Menubar;
    JMenuItem verzoekItem = new JMenuItem("Verzoek");
    JMenuItem authorisatieItem = new JMenuItem("Authorisatie");
    JMenuItem instellingenItem = new JMenuItem("Instellingen");
    JMenuItem closeItem = new JMenuItem("x");
    JMenuItem minimizeItem = new JMenuItem("-");
    private int mouseX, mouseY;
    public Gui(){
        setUndecorated(true);
        draggable();
        setPreferredSize(new Dimension(1000,600));

        MakeMenuBar();
        setJMenuBar(Menubar);

        setUpPanels();
        pack();
        setVisible(true);

    }

    private void draggable(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - mouseX;
                int deltaY = e.getY() - mouseY;

                setLocation(getLocation().x + deltaX, getLocation().y + deltaY);
            }
        });

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                getContentPane().setSize(getWidth(), getHeight());
            }
        });
    }
    private void MakeMenuBar(){
        Menubar = new JMenuBar();
        menubarLayout();
        verzoekItem.addActionListener(this);
        authorisatieItem.addActionListener(this);
        instellingenItem.addActionListener(this);
        closeItem.addActionListener(this);
        minimizeItem.addActionListener(this);
        Menubar.add(verzoekItem);
        Menubar.add(authorisatieItem);
        Menubar.add(instellingenItem);
        Menubar.add(Box.createHorizontalGlue());
        Menubar.add(minimizeItem);
        Menubar.add(closeItem);
    }

    private void menubarLayout(){
        verzoekItem.setMaximumSize(new Dimension(150, 100));
        authorisatieItem.setMaximumSize(new Dimension(150, 100));
        instellingenItem.setMaximumSize(new Dimension(150, 100));
        closeItem.setMaximumSize(new Dimension(10, 100));
        minimizeItem.setMaximumSize(new Dimension(10, 100));
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
        add(mainPanel);
        mainPanel.add(verzoek, "verzoek");
        mainPanel.add(authorisatie, "authorisatie");
        mainPanel.add(instellingen, "instellingen");
        cardLayout.show(mainPanel, "verzoek");
        pressed(verzoekItem);
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
            case("x"):
                authorisatie.SaveKey();
                dispose();
                break;
            case("-"):
                setState(Frame.ICONIFIED);;
                break;
        }
    }


}
