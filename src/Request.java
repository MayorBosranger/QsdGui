import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Request {
    public JPanel requestPanel;
    private JTextArea QueryField, ResultArea;
    private JList History;
    private JButton SendButton, ClearButton, SelectButton;
    private JPanel ResponsePanel, DisplayPanel;
    private CardLayout cardLayout;
    private List<String> queryHistory, displayedQueryHistory;
    private Map map;

    public Request(){
        BaseInitialise();
    }

    public Request(Map map){
        BaseInitialise();
        this.map = map;
    }

    private void BaseInitialise(){
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
        for (String query : displayedQueryHistory){
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
                if(query == null || query.isEmpty()) return;
                addToHistory(query);
                UpdateHistory();
                String QueryResult = ApiController.runJsonQuery(query);
                try {
                    if (QueryResult.contains("\"geometrie\"") || QueryResult.contains("\"geometry\"")) {
                        String cordString = QueryResult.substring(QueryResult.indexOf("((") + 2);
                        cordString = cordString.substring(0, cordString.indexOf("))"));
                        UpdateMap(GeoController.ParseGeoPosition(cordString));
                    }
                } catch (java.lang.NumberFormatException error) {
                    QueryResult = "[{ \"Kon coordinaten niet goed parsen\": 1 }]";
                }
                String result = queryResultFormat(QueryResult);
                ResultArea.setText(result);
            }
        });
        SelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(queryHistory.isEmpty()){
                    QueryField.setText("");
                    return;
                }
                if(History.getSelectedIndex() == -1) return;
                QueryField.setText(queryHistory.get(History.getSelectedIndex()));
            }
        });
        ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryHistory.clear();
                displayedQueryHistory.clear();
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
        displayedQueryHistory.add(cleanQueryForDisplay(s));
    }

    private String cleanQueryForDisplay(String s) {
        String query = s.replaceAll("\n", " ");
        query = query.replaceAll(" +", " ");// " +" is voor meerdere whitespacen
        if(query.startsWith("query ")) query = query.substring(6);
        if(query.length() >= 100) query = query.substring(0, 100) + "...";
        return query;
    }

    private String queryResultFormat(String format){
        if(format == null || format.isEmpty()) return "";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(format);
        return gson.toJson(je);
    }

    private void UpdateMap(ArrayList<GeoPosition> points){
        map.SetAreaPainter(points);
    }
}