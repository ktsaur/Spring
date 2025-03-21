package ru.kpfu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HttpClientService {

    private static final Logger log = LoggerFactory.getLogger(HttpClientService.class);

    public String get(String url, Map<String, String> headers, Map<String, String> params) throws IOException {
        URL getUrl = new URL(getUrl(url, params));
        log.info("Запрос URL: {}", getUrl);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", headers.get("Content-Type"));
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        return readResponse(connection);
    }

    public String post(String url, Map<String, String> headers, Map<String, String> data) throws IOException {
        URL postUrl = new URL(url);
        HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", headers.get("Content-Type"));
        postConnection.setRequestProperty("Accept", headers.get("Accept"));
        postConnection.setRequestProperty("Authorization", headers.get("Authorization"));
        postConnection.setDoOutput(true);
        String jsonInput = mapToJsonFile(data);
        try (OutputStream outputStream = postConnection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
        System.out.println(postConnection.getResponseCode());
        return readResponse(postConnection);
    }

    public String put(String url, Map<String, String> headers, Map<String, String> data) throws IOException {
        URL putUrl = new URL(url);
        HttpURLConnection putConnection = (HttpURLConnection) putUrl.openConnection();
        putConnection.setRequestMethod("PUT");
        putConnection.setRequestProperty("Content-Type", headers.get("Content-Type"));
        putConnection.setRequestProperty("Accept", headers.get("Accept"));
        putConnection.setRequestProperty("Authorization", headers.get("Authorization"));
        putConnection.setDoOutput(true);
        String jsonInput = mapToJsonFile(data);
        try (OutputStream outputStream = putConnection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
        return readResponse(putConnection);
    }

    public String delete(String url, Map<String, String> headers, Map<String, String> data) throws IOException {
        URL deleteUrl = new URL(url);
        HttpURLConnection deleteConnection = (HttpURLConnection) deleteUrl.openConnection();
        deleteConnection.setRequestMethod("DELETE");
        deleteConnection.setRequestProperty("Content-Type", headers.get("Content-Type"));
        deleteConnection.setConnectTimeout(5000);
        deleteConnection.setReadTimeout(5000);
        return readResponse(deleteConnection);

    }

    public String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append("\t").append(input).append("\n");
                }
                return content.toString();
            }
        }
        return null;
    }

    public String getUrl(String url, Map<String, String> param) {
        if (param != null) {
            List<String> keys = new ArrayList<>(param.keySet());
            StringBuilder getUrl = new StringBuilder(url);
            for (int i = 0; i < keys.size(); i++) {
                getUrl.append(keys.get(i)).append("=").append(param.get(keys.get(i))).append("&");
            }
            return getUrl.toString();
        } else {
            return null;
        }
    }

    public String mapToJsonFile(Map<String, String> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(map);
    }

}
