module com.courigistics.courigisticsjavafx {
    // ==========================================
    // DEPENDENCIES (requires)
    // Tells Java which modules this project needs to run
    // ==========================================

    // Core JavaFX modules for UI components, FXML layouts, and web views
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    // Third-party UI libraries for advanced controls, validation, and icons
    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;

    // Core data and networking dependencies
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    // ==========================================
    // REFLECTION ACCESS (opens ... to)
    // Grants the JavaFX FXML loader private/reflective access
    // to inject @FXML variables and trigger controller methods
    // ==========================================
    opens com.courigistics.courigisticsjavafx.app to javafx.fxml;
    opens com.courigistics.courigisticsjavafx.controllers.auth to javafx.fxml;
    opens com.courigistics.courigisticsjavafx.controllers.customer to javafx.fxml;
    opens com.courigistics.courigisticsjavafx.controllers.courier to javafx.fxml;
    opens com.courigistics.courigisticsjavafx.controllers.admin to javafx.fxml;
    opens com.courigistics.courigisticsjavafx.components to javafx.fxml;

    // ==========================================
    // PUBLIC ACCESSIBILITY (exports)
    // Exposes public classes in these packages to external modules
    // ==========================================

    // Required so the JavaFX runtime can launch the main Application class
    exports com.courigistics.courigisticsjavafx.app;
    exports com.courigistics.courigisticsjavafx.core.session;
    exports com.courigistics.courigisticsjavafx.core.navigation;

    // dto packages for data transfer
    exports com.courigistics.courigisticsjavafx.dto.responses;
    exports com.courigistics.courigisticsjavafx.dto.requests;

}