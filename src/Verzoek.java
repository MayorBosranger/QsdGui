import javax.management.Query;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
        runJsonQuery();
    }

    private void runJsonQuery(){
        try {
            // Replace the URL with the actual GraphQL endpoint
            URL apiUrl = new URL("http://localhost:8081/graphql");

            // Replace this with your custom GraphQL query
            String jsonRequest = "{\"query\": \"" + escapeDoubleQuotes(queryTextArea.getText()) + "\",\"operationName\": \"MyQuery\"}";


            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Enable input and output streams
            connection.setDoOutput(true);


            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequest.getBytes("utf-8");
                os.write(input, 0, input.length);
            }


            // Read the response from the API
                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    result.setText(response.toString());
                }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String escapeDoubleQuotes(String input) {
        return input.replace("\"", "\\\"").replace("\n", "\\n");
    }
}
