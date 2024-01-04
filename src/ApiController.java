import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiController {

    public String runJsonQuery(String query){
        String result = null;
        try {
            // Replace the URL with the actual GraphQL endpoint
            URL apiUrl = new URL("http://localhost:8081/graphql");

            // Replace this with your custom GraphQL query
            String jsonRequest = "{\"query\": \"" + escapeDoubleQuotes(query) + "\",\"operationName\": \"MyQuery\"}";


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
                result = response.toString();
            }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String escapeDoubleQuotes(String input) {
        return input.replace("\"", "\\\"").replace("\n", "\\n");
    }
}
