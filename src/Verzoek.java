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
    private JTextArea QueryField, ResultaatArea;
    private JList History;
    private JButton SendButton, ClearButton, SelectButton;
    private JPanel ResponsePanel, DisplayPanel;
    private CardLayout cardLayout;
    private List<String> queryHistory, displayedQueryHistory;
    private ApiController apiController = new ApiController();

    public Verzoek(){
        queryHistory = new ArrayList<String>();
        displayedQueryHistory = new ArrayList<String>();
        cardLayout = new CardLayout();
        initializeButtons();

        CardLayout card = (CardLayout)DisplayPanel.getLayout();
        card.show(DisplayPanel, "Resultaat");

        UpdateHistory();
    }

    public void UpdateHistory(){
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String s : queryHistory){
            String query = s;
            if(query.startsWith("query MyQuery")) query = query.substring(13);
            if(query.length() >= 100) query = query.substring(0, 100);
            listModel.addElement(query);
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
                if(queryHistory.isEmpty()){
                    QueryField.setText("");
                    return;
                }

                QueryField.setText(queryHistory.get(History.getSelectedIndex()));
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
        if(s == null || s.isEmpty()) return;
        if(queryHistory.isEmpty())
        {
            queryHistory.add(s);
            displayedQueryHistory.add(cleanQueryForDisplay(s));
            return;
        }
        String previous = queryHistory.get(queryHistory.size()-1);

        if(Objects.equals(previous, s)) return;
        queryHistory.add(s);
        String displayQuery = cleanQueryForDisplay(s);

        System.out.println("s : " + s.length() + " : " + s);
        System.out.println("query : " + displayQuery.length() + " : " + displayQuery);
    }

    private String cleanQueryForDisplay(String s) {
        String query = s.replaceAll("\n", " ");
        query = query.replaceAll(" +", " ");// " +" is voor meerdere whitespacen
        if(query.startsWith("query MyQuery ")) query = query.substring(14);
        if(query.length() >= 100) query = query.substring(0, 100) + "...";
        return query;
    }

    private String queryResultFormat(String format){
        if(format == null || format.isEmpty()) return "";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(format);
        return gson.toJson(je);
    }
}