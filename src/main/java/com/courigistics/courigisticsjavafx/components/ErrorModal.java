package com.courigistics.courigisticsjavafx.components;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.util.Objects;

/**
 * Standardized modal for displaying error messages.
 * Responsibility: Providing a consistent way to inform users about API failures, validation errors, or system issues.
 */
public class ErrorModal {
    public static void show(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void styleAlert(Alert alert) {
        DialogPane pane = alert.getDialogPane();
        pane.getStylesheets().add(
                Objects.requireNonNull(ErrorModal.class.getResource("/css/components.css")).toExternalForm()
        );
        pane.getStyleClass().add("error-modal");
    }
}
