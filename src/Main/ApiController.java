package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiController {

    public static String runJsonQuery(String query){
        String result = null;
        try {
            HttpURLConnection connection = startConnection();
            String jsonRequest = prepareJsonQuery(query);
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequest.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            result = sendQuery(connection);
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
        return result;
    }

    public static HttpURLConnection startConnection(){
        HttpURLConnection connection;
        try {
            URL apiUrl = new URL("http://localhost:8081/graphql");
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static String sendQuery(HttpURLConnection connection){
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String prepareJsonQuery(String query) {
        String json = "{\"query\": \"" + query.replace("\"", "\\\"").replace("\n", "\\n") + "\",\"operationName\": \"MyQuery\"}";
        return json;
    }
}
