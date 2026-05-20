package com.courigistics.courigisticsjavafx.app;

import com.courigistics.courigisticsjavafx.core.navigation.Navigator;
import com.courigistics.courigisticsjavafx.core.navigation.Screen;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point for the JavaFX application.
 * Responsibility: Initializing the primary stage and setting up the initial scene via the Navigator.
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        Navigator.getInstance().init(stage);
        Navigator.getInstance().navigateTo(Screen.LOGIN);
    }
}
