import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    private JFrame KadasterGui;
    private Verzoek verzoek;
    private Authorisatie authorisatie;
    private JPanel activePanel;

    JMenuBar Menubar = new JMenuBar();
    JMenuItem VerzoekItem = new JMenuItem("Verzoek");
    JMenuItem AuthorisatieItem = new JMenuItem("Authorisatie");

    public Gui(){
        KadasterGui = new JFrame("KadasterGui");
        KadasterGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KadasterGui.setPreferredSize(new Dimension(1000,600));

        KadasterGui.setJMenuBar(MakeMenuBar());

        activePanel = getAuthorisatiePanel();
        KadasterGui.add(activePanel);
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

    private JPanel getVerzoekPanel(){
        if (verzoek == null){
            verzoek = new Verzoek();
            return verzoek.getPanel();
        }else return verzoek.getPanel();
    }

    private JPanel getAuthorisatiePanel(){
        if (authorisatie == null){
            authorisatie = new Authorisatie();
            return authorisatie.getPanel();
        }else return authorisatie.getPanel();
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case("Verzoek"):
                VerzoekItem.setEnabled(false);
                AuthorisatieItem.setEnabled(true);
                activePanel = getVerzoekPanel();
                activateActivePanel();
                break;
            case ("Authorisatie"):
                AuthorisatieItem.setEnabled(false);
                VerzoekItem.setEnabled(true);
                activePanel = getAuthorisatiePanel();
                activateActivePanel();
                break;
        }
    }

    private void activateActivePanel(){
        KadasterGui.getContentPane().removeAll();
        KadasterGui.add(activePanel);
    }

}
