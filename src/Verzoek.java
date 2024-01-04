import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Verzoek extends JPanel implements ActionListener {
    public JPanel verzoekPanel;
    private JLabel TitelLabel;
    private JTextField QueryField;
    private JList History;
    private JButton SendButton, ResultaatButton, OorsprongButton, ClearButton, SelectButton;
    private JPanel ResponsePanel, DisplayPanel;
    private CardLayout cardLayout;
    private JTable Resultaat, Oorsprong;
    private Object ReturnedResultaat;
    private List<String> queryHistory;
    JTextArea queryTextArea = new JTextArea(20,30);
    JButton run = new JButton("Run");
    JTextArea result = new JTextArea(20,30);
    ApiController apiController = new ApiController();

    public Verzoek(){
        add(queryTextArea);
        add(run);
        run.addActionListener(this);
        result.setEnabled(false);
        add(result);
        queryHistory = new ArrayList<String>();
        cardLayout = new CardLayout();
        initiateButtons();

        CardLayout card = (CardLayout)DisplayPanel.getLayout();
        card.show(DisplayPanel, "Resultaat");

        UpdateHistory();
    }

    public void UpdateHistory(){
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String s : queryHistory){
            listModel.addElement(s);
        }
        if(listModel.isEmpty()) listModel.addElement("geschiedenis is leeg");
        History.setModel(listModel);
    }

    public void addToHistory(String s){
        if(queryHistory.isEmpty()) queryHistory.add(s);
        String previous = queryHistory.get(queryHistory.size()-1);

        if(Objects.equals(previous, s)) return;
        queryHistory.add(s);
    }

    private void initiateButtons(){
        SendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = QueryField.getText();
                addToHistory(query);
                UpdateHistory();
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
        SelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //niks geselecteerd is index -1
                int index = History.getSelectedIndex();
                String s = (String) History.getSelectedValue();
                System.out.println("Index: " + index + " - Value: "+ s);
                QueryField.setText(s);
            }
        });
        ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryHistory.clear();
                UpdateHistory();
            }
        });
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

    public void actionPerformed(ActionEvent e) {
        result.setText(apiController.runJsonQuery(queryTextArea.getText()));
    }
}
