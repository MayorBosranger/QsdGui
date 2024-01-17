import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiController {

    public static String runJsonQuery(String query){
        String result = null;
        try {
            URL apiUrl = new URL("http://localhost:8081/graphql");
            String jsonRequest = "{\"query\": \"" + escapeDoubleQuotes(query) + "\",\"operationName\": \"MyQuery\"}";
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequest.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                result = response.toString();
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
        return result;
    }

    private static String escapeDoubleQuotes(String input) {
        return input.replace("\"", "\\\"").replace("\n", "\\n");
    }
}
