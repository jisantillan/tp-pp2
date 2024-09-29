package org.domingus.polling;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

    private final HttpClient httpClient;
    private final DataFactory factory;

    public HttpService() {
        this.httpClient = HttpClient.newHttpClient();
        this.factory = new DataFactory();
    }

    public AcademicData sendGet(String url, String dataType ) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return factory.createData(dataType, response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
