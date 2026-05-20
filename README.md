# CouriGistics JavaFX

A desktop logistics and courier management application built with JavaFX, providing multi-role platform for customers, couriers, and administrators.

## Features

- **Multi-role System**: Separate dashboards for Customers, Couriers, and Admins
- **Real-time Tracking**: Google Maps integration for delivery tracking
- **Shipment Management**: Create, track, and manage deliveries
- **Courier Management**: Approval system and delivery assignment
- **Depot Inventory**: Stock and parcel management for administrators

## Technology Stack

| Component | Technology |
|-----------|------------|
| UI Framework | JavaFX 21.0.6 |
| UI Controls | ControlsFX 11.2.1 |
| Validation | ValidatorFX 0.6.1 |
| Icons | Ikonli 12.3.1 |
| JSON Processing | Jackson Databind 2.21.3 |
| Build Tool | Maven |
| Java Version | 21 |

## Project Structure

```
src/main/java/com/courigistics/courigisticsjavafx/
├── app/                    # Application entry points
├── core/                   # Core infrastructure (HTTP, navigation, session)
├── components/             # Reusable UI components
├── controllers/            # FXML controllers (admin/, auth/, courier/, customer/)
├── maps/                   # Google Maps integration
└── services/               # Business logic services
```

## Build & Run

```bash
# Build
./mvnw clean compile

# Run
./mvnw clean javafx:run

# Package
./mvnw clean package
```

## Configuration

API and Google Maps settings are configured in `AppConfig.java`. Default API endpoint: `http://localhost:8080/api/v1`

## Screens

- Login / Register
- Customer: Dashboard, New Shipment, Tracking
- Courier: Dashboard, Delivery Detail, Map
- Admin: Dashboard, Courier Approval, Depot Inventory