package org.domingus.polling;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public HttpService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Data sendGet(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, Object> dataMap = objectMapper.readValue(response.body(), Map.class);

            return DataFactory.createDataFromMap(url.toString(), dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
