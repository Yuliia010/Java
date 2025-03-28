import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Task {
    private static final String API_URL = "https://api.novaposhta.ua/v2.0/json/";
    private static final String API_KEY = "0c7e6b97e135589e7bec37fe92fd4a43";

    public static void main(String[] args) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);


            JSONObject requestJson = new JSONObject();
            requestJson.put("apiKey", API_KEY);
            requestJson.put("modelName", "Address");
            requestJson.put("calledMethod", "getAreas");
            requestJson.put("methodProperties", new JSONObject());


            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestJson.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                JSONObject responseJson = new JSONObject(response.toString());
                JSONArray areasJsonArray = responseJson.getJSONArray("data");


                List<OblastApiModel> oblasts = new ArrayList<>();
                for (int i = 1; i < areasJsonArray.length(); i++) {
                    JSONObject oblastJson = areasJsonArray.getJSONObject(i);
                    OblastApiModel oblast = new OblastApiModel(
                            oblastJson.getString("Ref"),
                            oblastJson.getString("Description")

                    );
                    oblasts.add(oblast);
                }


                for (OblastApiModel oblast : oblasts) {
                    System.out.println(oblast);
                }

            } else {
                System.out.println("POST request failed. Response Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

