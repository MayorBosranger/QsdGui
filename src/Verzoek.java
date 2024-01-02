import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Verzoek implements GuiPanel{
    public JPanel verzoekPanel;
    private JLabel TitelLabel;
    private JTextField queryField;
    private JList history;
    private JButton SendButton, ResultaatButton, OorsprongButton;
    private JPanel ResponsePanel, HistoryPanel, DisplayPanel, DisplayInnerPanel;
    private CardLayout cardLayout;
    private JTable Resultaat, Oorsprong;
    private Object ReturnedResultaat;
    private List<String> queryHistory;

    public Verzoek(){
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
        Resultaat = new JTable();
        Oorsprong = new JTable();
        cardLayout = new CardLayout();
        DisplayInnerPanel = new JPanel(cardLayout);
        DisplayPanel.add(DisplayInnerPanel);

        DisplayInnerPanel.add(Resultaat, "Resultaat");
        DisplayInnerPanel.add(Oorsprong, "Oorsprong");
        cardLayout.show(DisplayInnerPanel, "Resultaat");
        UpdateHistory();
    }

    public void UpdateHistory(){
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String s : queryHistory){
            listModel.addElement(s);
        }
        if(listModel.isEmpty()) listModel.addElement("geschiedenis is leeg");
        history.setModel(listModel);
    }

    @Override
    public JPanel getPanel() {
        return verzoekPanel;

    }

    @Override
    public GuiPanel getSoort() {
        return this;
    }

    public Object DoeApiRequest(String input){
        String s = input;
        return s;
    }

    public void SwitchResponsePanelTo(String panelNaam){
        switch (panelNaam) {
            case "Resultaat":
                cardLayout.show(DisplayInnerPanel, "Resultaat");
                break;
            case "Oorsprong":
                cardLayout.show(DisplayInnerPanel, "Oorsprong");
                break;
        }
    }
}
