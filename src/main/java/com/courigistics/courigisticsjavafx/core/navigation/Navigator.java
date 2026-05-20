package com.courigistics.courigisticsjavafx.core.navigation;

import com.courigistics.courigisticsjavafx.app.AppConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton handling scene transitions and window management.
 * Responsibility: Loading FXML files, switching between screens, and managing stage properties.
 */

@Getter
public class Navigator {

    private static Navigator instance;

    private Stage primaryStage;

    // Cache loaded views so they aren't reloaded on every navigation
    private final Map<Screen, Parent> viewCache = new HashMap<>();

    private Navigator() {}

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    /** Must be called once from App.start() */
    public void init(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle(AppConfig.APP_TITLE);
        primaryStage.setMinWidth(AppConfig.MIN_WIDTH);
        primaryStage.setMinHeight(AppConfig.MIN_HEIGHT);
    }

    /** Navigate to a screen. Caches the view after first load. */
    public void navigateTo(Screen screen) {
        try {
            Parent view = viewCache.computeIfAbsent(screen, this::loadView);
            if (primaryStage.getScene() == null) {
                primaryStage.setScene(new Scene(view));
            } else {
                primaryStage.getScene().setRoot(view);
            }
            primaryStage.show();
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to: " + screen, e);
        }
    }

    /** Navigate and clear the cache for that screen (forces fresh load) */
    public void navigateFresh(Screen screen) {
        viewCache.remove(screen);
        navigateTo(screen);
    }

    /** Clear all cached views — call on logout */
    public void clearCache() {
        viewCache.clear();
    }

    private Parent loadView(Screen screen) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(screen.getFxmlPath())
            );
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Could not load FXML: " + screen.getFxmlPath(), e);
        }
    }

}
