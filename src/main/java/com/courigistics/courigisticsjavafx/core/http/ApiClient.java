package com.courigistics.courigisticsjavafx.core.http;

import com.courigistics.courigisticsjavafx.app.AppConfig;
import com.courigistics.courigisticsjavafx.core.session.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Singleton wrapper for HttpClient to handle backend communication.
 * Responsibility: Executing HTTP requests, handling JSON serialization/deserialization, and managing global headers like JWT.
 */
public class ApiClient {
    private static ApiClient instance;

    private final HttpClient httpClient;
    @Getter
    private final ObjectMapper objectMapper;

    private ApiClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules(); // handles LocalDateTime etc.
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    /** GET — no auth */
    public ApiResponse get(String endpoint) {
        return execute(buildRequest(endpoint).GET().build());
    }

    /** GET — with JWT auth */
    public ApiResponse getAuth(String endpoint) {
        return execute(buildAuthRequest(endpoint).GET().build());
    }

    /** POST — no auth */
    public ApiResponse post(String endpoint, Object body) {
        return execute(buildRequest(endpoint)
                .POST(jsonBody(body))
                .header("Content-Type", "application/json")
                .build());
    }

    /** POST — with JWT auth */
    public ApiResponse postAuth(String endpoint, Object body) {
        return execute(buildAuthRequest(endpoint)
                .POST(jsonBody(body))
                .header("Content-Type", "application/json")
                .build());
    }

    /** PATCH — with JWT auth */
    public ApiResponse patchAuth(String endpoint, Object body) {
        return execute(buildAuthRequest(endpoint)
                .method("PATCH", jsonBody(body))
                .header("Content-Type", "application/json")
                .build());
    }

    /** DELETE — with JWT auth */
    public ApiResponse deleteAuth(String endpoint) {
        return execute(buildAuthRequest(endpoint).DELETE().build());
    }

    // --- Helpers ---

    private HttpRequest.Builder buildRequest(String endpoint) {
        return HttpRequest.newBuilder()
                .uri(URI.create(AppConfig.BASE_URL + endpoint))
                .timeout(Duration.ofSeconds(15));
    }

    private HttpRequest.Builder buildAuthRequest(String endpoint) {
        return buildRequest(endpoint)
                .header("Authorization", SessionManager.getInstance().getBearerToken());
    }

    private HttpRequest.BodyPublisher jsonBody(Object body) {
        try {
            return HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize request body", e);
        }
    }

    private ApiResponse execute(HttpRequest request) {
        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString()
            );
            return new ApiResponse(response.statusCode(), response.body(), objectMapper);
        } catch (Exception e) {
            // Return a synthetic error response so callers don't need to catch
            return new ApiResponse(0, "{\"message\":\"" + e.getMessage() + "\"}", objectMapper);
        }
    }

}
