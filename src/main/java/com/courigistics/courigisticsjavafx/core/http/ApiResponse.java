package com.courigistics.courigisticsjavafx.core.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

/**
 * Generic wrapper for HTTP responses.
 * Responsibility: Capturing status codes, response headers, and deserialized body data or error messages.
 *
 */
public class ApiResponse{

    @Getter
    private final int statusCode;
    @Getter
    private final String body;
    private final ObjectMapper objectMapper;

    public ApiResponse(int statusCode, String body, ObjectMapper objectMapper) {
        this.statusCode = statusCode;
        this.body = body;
        this.objectMapper = objectMapper;
    }

    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 300;
    }

    public boolean isUnauthorized() { return statusCode == 401; }
    public boolean isForbidden()    { return statusCode == 403; }
    public boolean isConflict()     { return statusCode == 409; }
    public boolean isNotFound()     { return statusCode == 404; }
    public boolean isServerError()  { return statusCode >= 500; }
    public boolean isNetworkError() { return statusCode == 0; }

    /** Parse body into any class */
    public <T> T as(Class<T> clazz) {
        try {
            return objectMapper.readValue(body, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize response body into " + clazz.getSimpleName(), e);
        }
    }

    /** Get a single field from the JSON body */
    public String getField(String fieldName) {
        try {
            JsonNode node = objectMapper.readTree(body);
            JsonNode field = node.get(fieldName);
            return field != null ? field.asText() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /** Get the "message" field — used for error display */
    public String getMessage() {
        return getField("message");
    }

}
