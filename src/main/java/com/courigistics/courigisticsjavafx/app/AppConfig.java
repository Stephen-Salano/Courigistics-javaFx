package com.courigistics.courigisticsjavafx.app;

/**
 * Application-wide configuration and constants.
 * Responsibility: Storing API keys, base URLs, and other global settings.
 */
public class AppConfig {
    // API Configuration
    // Backend base URL — change to deployed URL when ready
    public static final String BASE_URL = "http://localhost:8080/api/v1";

    // Google Maps
    public static final String GOOGLE_MAPS_API_KEY = "YOUR_API_KEY_HERE";
    public static final String PLACES_AUTOCOMPLETE_URL =
            "https://places.googleapis.com/v1/places:autocomplete";
    public static final String PLACES_DETAILS_URL =
            "https://places.googleapis.com/v1/places/";
    public static final String ROUTES_API_URL =
            "https://routes.googleapis.com/directions/v2:computeRoutes";

    // App window
    public static final String APP_TITLE = "CouriGistics";
    public static final double MIN_WIDTH = 1100;
    public static final double MIN_HEIGHT = 700;
}
