import javax.management.Query;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Verzoek extends JPanel implements ActionListener {

    JTextArea queryTextArea = new JTextArea(20,30);
    JButton run = new JButton("Run");
    JTextArea result = new JTextArea(20,30);
    public Verzoek(){
        add(queryTextArea);
        add(run);
        run.addActionListener(this);
        result.setEnabled(false);
        add(result);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        runQuery();
    }

    private void runQuery(){
        URL url;
        try {
            url = new URL("http://localhost:8081/graphql");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Read the response from the API
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = queryTextArea.getText();
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Print the API response
                System.out.println("API Response:\n" + response.toString());
            } else {
                System.out.println("Error: Unable to fetch data from the API.");
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
