package com.courigistics.courigisticsjavafx.core.navigation;

/**
 * Enumeration of all available screens in the application.
 * Responsibility: Mapping screen identifiers to their respective FXML file paths.
 */
public enum Screen {
    // Auth
    LOGIN("/fxml/auth/Login.fxml"),
    REGISTER_SELECTION("/fxml/auth/RegisterSelection.fxml"),
    REGISTER_CUSTOMER("/fxml/auth/RegisterCustomer.fxml"),
    REGISTER_COURIER("/fxml/auth/RegisterCourier.fxml"),
    ACCOUNT_SETUP("/fxml/auth/AccountSetup.fxml"),
    VERIFY_CUSTOMER("/fxml/auth/VerifyCustomer.fxml"),
    FORGOT_PASSWORD("/fxml/auth/ForgotPassword.fxml"),

    // Customer
    CUSTOMER_DASHBOARD("/fxml/customer/CustomerDashboard.fxml"),
    NEW_SHIPMENT("/fxml/customer/NewShipment.fxml"),
    TRACKING("/fxml/customer/Tracking.fxml"),

    // Courier
    COURIER_DASHBOARD("/fxml/courier/CourierDashboard.fxml"),
    DELIVERY_DETAIL("/fxml/courier/DeliveryDetail.fxml"),
    COURIER_MAP("/fxml/courier/CourierMap.fxml"),

    // Admin
    ADMIN_DASHBOARD("/fxml/admin/AdminDashboard.fxml"),
    COURIER_APPROVAL("/fxml/admin/CourierApproval.fxml"),
    DEPOT_INVENTORY("/fxml/admin/DepotInventory.fxml");

    private final String fxmlPath;

    Screen(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }
}
