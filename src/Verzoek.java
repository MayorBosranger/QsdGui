import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Verzoek{
    public JPanel verzoekPanel;
    private JTextArea QueryField;
    private JList History;
    private JButton SendButton, ClearButton, SelectButton;
    private JPanel ResponsePanel, DisplayPanel;
    private CardLayout cardLayout;
    private JTextArea ResultaatArea;
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
                UpdateHistory();
                String resultaat = queryResultFormat(apiController.runJsonQuery(query));
                ResultaatArea.setText(resultaat);
            }
        });
        SelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //niks geselecteerd is index -1
                if(queryHistory.isEmpty()){
                    QueryField.setText("");
                    return;
                }
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

    private String queryResultFormat(String format){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(format);
        String returnString = gson.toJson(je);
        return returnString;
    }
}