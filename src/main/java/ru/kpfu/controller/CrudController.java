package ru.kpfu.controller;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.service.HttpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/crud")
public class CrudController {

    private final HttpClientService httpClientService;
    private static final Logger log = LoggerFactory.getLogger(HttpClientService.class);

    public CrudController(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @GetMapping(produces = "application/json")
    public String doGet() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/posts?";
        Map<String, String> params = new HashMap<>();
        params.put("userId", "2");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String response = httpClientService.get(url, headers, params);
        return response;
    }

    @PostMapping(produces = "application/json")
    public String doPost() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/posts";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer 58762cdab4e248c10d165f6bbe89d18a444dff00267b6cfcec49acf9dceb94b7");

        Map<String, String> data = new HashMap<>();
        data.put("userId", "78");
        data.put("id", "78");
        data.put("title", "title");
        data.put("body", "body");

        String response = httpClientService.post(url, headers, data);
        return response;
    }

    @PutMapping(produces = "application/json")
    public String doPut() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer 58762cdab4e248c10d165f6bbe89d18a444dff00267b6cfcec49acf9dceb94b7");

        Map<String, String> data = new HashMap<>();
        data.put("title", "new title");

        String response = httpClientService.put(url, headers, data);
        return response;
    }

    @DeleteMapping
    public void doDelete() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Map<String, String> data = new HashMap<>();

        log.info(httpClientService.delete(url, headers, data));
    }

}
