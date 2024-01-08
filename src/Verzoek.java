import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Verzoek extends JPanel{
    public JPanel verzoekPanel;
    private JTextArea QueryField;
    private JList History;
    private JButton SendButton, ClearButton, SelectButton;
    private JPanel ResponsePanel, DisplayPanel;
    private CardLayout cardLayout;
    private JTextArea Resultaat;
    private Object ReturnedResultaat;
    private List<String> queryHistory;
private ApiController apiController = new ApiController();
    public Verzoek(){
        queryHistory = new ArrayList<String>();
        cardLayout = new CardLayout();
        initializeButtons();

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

    private void initializeButtons(){
        SendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = QueryField.getText();
                addToHistory(query);
                Resultaat.setText(apiController.runJsonQuery(query));
                UpdateHistory();
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

    public void addToHistory(String s){
        if(queryHistory.isEmpty()) queryHistory.add(s);
        String previous = queryHistory.get(queryHistory.size()-1);

        if(Objects.equals(previous, s)) return;
        queryHistory.add(s);
    }
}