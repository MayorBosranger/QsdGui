import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.List;

public class Verzoek extends JPanel{
    public JPanel verzoekPanel;
    private JLabel TitelLabel;
    private JTextField queryField;
    private JList history;
    private JButton SendButton, ResultaatButton, OorsprongButton;
    private JPanel ResponsePanel, HistoryPanel, DisplayPanel;
    private CardLayout cardLayout;
    private JTable Resultaat, Oorsprong;
    private Object ReturnedResultaat;
    private List<String> queryHistory;

    public Verzoek(){
        queryHistory = new ArrayList<String>();

        cardLayout = new CardLayout();
        SendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = queryField.getText();
                queryHistory.add(query);
                ReturnedResultaat = DoeApiRequest(query);
            }
        });
        ResultaatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchResponsePanelTo("Resultaat");
            }
        });
        OorsprongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchResponsePanelTo("Oorsprong");
            }
        });


        CardLayout card = (CardLayout)DisplayPanel.getLayout();
        card.show(DisplayPanel, "Resultaat");
        //UpdateHistory();
        history.addComponentListener(new ComponentAdapter() {
        });
    }

    public void UpdateHistory(){
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String s : queryHistory){
            listModel.addElement(s);
        }
        if(listModel.isEmpty()) listModel.addElement("geschiedenis is leeg");
        history.setModel(listModel);
    }

    public Object DoeApiRequest(String input){
        String s = input;
        //TODO maken
        return s;
    }

    public void SwitchResponsePanelTo(String panelNaam) {
        CardLayout card = (CardLayout)DisplayPanel.getLayout();
        switch (panelNaam) {
            case "Resultaat":
                card.show(DisplayPanel, "Resultaat");
                break;
            case "Oorsprong":
                card.show(DisplayPanel, "Oorsprong");
                break;
        }
    }
}
