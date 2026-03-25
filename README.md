# Irving's Mobile Order App
A full-stack mobile ordering system built with Object-Oriented Design (OOD) principles, including a Spring Boot backend API, frontend user interface, and automated integration tests.

## Update Time
All program code files update till Mid-Semester Progress
- **Time Stamp**:2026/3/25

## Project Overview
This project implements a complete bagel shop mobile ordering workflow for Irving's, supporting core business functions: customer menu browsing, cart management, order placement, payment processing, pickup status tracking, as well as staff order management and kitchen order status synchronization.

## Tech Stack
### Backend
- **Core Framework**: Spring Boot 3.x
- **Database**: H2 In-Memory Database (current development environment) / Supabase PostgreSQL (future demo/production environment)
- **ORM**: Spring Data JPA
- **Build Tool**: Apache Maven

### Frontend
- **Markup**: HTML5
- **Styling**: CSS3
- **Interactivity**: JavaScript (ES6+)
- **Dev Tool**: VS Code + Live Server Extension

### Testing
- **Framework**: JUnit 5
- **Coverage**: End-to-end user flow integration tests

## Project Structure
IRVINGS_MOBILE_ORDER_APP/
├── src/
│ ├── main/
│ │ ├── java/com/irvings/
│ │ │ ├── IrvingsApplication.java # Spring Boot Main Application Entry
│ │ │ ├── config/ # Configuration (CORS, etc.)
│ │ │ ├── controller/ # REST API Layer
│ │ │ ├── exception/ # Global Exception Handler
│ │ │ ├── user/ # User Module (OOD Domain Model)
│ │ │ ├── menu/ # Menu Module (OOD Domain Model)
│ │ │ ├── order/ # Order Module (OOD Domain Model)
│ │ │ ├── payment/ # Payment Module (OOD Domain Model)
│ │ │ ├── pickup/ # Pickup Module (OOD Domain Model)
│ │ │ └── kitchen/ # Kitchen Module (OOD Domain Model)
│ │ └── resources/
│ │ ├── application.properties # Spring Boot Configuration
│ │ ├── schema.sql # Database Schema Definition (Optional)
│ │ └── data.sql # Test Data Initialization (Optional)
│ └── test/
│ └── java/com/irvings/
│ └── testharness/ # JUnit 5 Integration Test Suite
├── frontend/ # Frontend Source Code
│ ├── index.html # App Entry Page
│ ├── css/ # Stylesheets
│ ├── js/ # JavaScript Logic
│ ├── assets/ # Static Assets (Images, Icons)
│ └── README.md # Frontend Development Documentation
├── pom.xml # Maven Project Configuration
└── README.md # This Document


## Quick Start
### Prerequisites
- JDK 17 or higher
- Visual Studio Code (VS Code)
- Required VS Code Extensions: Extension Pack for Java, Live Server

### 1. Start the Backend
1.  Open the project root directory in VS Code
2.  Navigate to `src/main/java/com/irvings/IrvingsApplication.java`
3.  Click the ▶️ Run button on the left side of the class definition
4.  Confirm successful startup when you see `Irving's Order App API started successfully!` in the console
5.  Backend API Base URL: `http://localhost:8080/api`

### 2. Access the H2 Database Console (Development Environment)
1.  Ensure the backend is running
2.  Open your browser and go to: `http://localhost:8080/h2-console`
3.  Enter the following connection details:
    - **JDBC URL**: `jdbc:h2:mem:irvingsdb`
    - **User Name**: `sa`
    - **Password**: (leave blank)
4.  Click **Connect** to view database tables and test data

### 3. Start the Frontend
1.  Navigate to the `frontend/` folder in the VS Code Explorer
2.  Right-click on `index.html`
3.  Select **Open with Live Server**
4.  The frontend will automatically open in your default browser

### 4. Run Integration Tests
1.  Navigate to `src/test/java/com/irvings/testharness/IrvingsAppTest.java`
2.  Right-click on the class name or any test method
3.  Select **Run 'IrvingsAppTest'**
4.  The IDE will display pass/fail results for all test cases

## Database Information
### Current Development Environment: H2 In-Memory Database
- Zero-installation, natively supported by Spring Boot
- Auto-creates table schema on startup, customizable via `schema.sql` and `data.sql`
- Data resets on application restart by default (persist to local file by changing `spring.datasource.url` in `application.properties` to `jdbc:h2:file:./data/irvings`)

### Future Demo/Production Environment: Supabase PostgreSQL
- Free cloud-hosted PostgreSQL database for team data sharing
- Full PostgreSQL feature set with a web-based management console
- Migration only requires changes to `pom.xml` dependencies and `application.properties` connection configuration
- Full migration guide available in the backend development documentation

## Core API Endpoints
### User Module
- `POST /api/user/register` - Register a new user
- `POST /api/user/login` - Login and retrieve session ID
- `GET /api/user/validate` - Validate session validity
- `POST /api/user/logout` - Logout and invalidate session

### Menu Module
- `GET /api/menu/list` - Retrieve full menu
- `GET /api/menu/{itemId}/customization` - Get customization options for a menu item

### Cart Module
- `POST /api/cart/create` - Create a new shopping cart
- `POST /api/cart/{cartId}/item` - Add item to cart
- `GET /api/cart/{cartId}/items` - List all items in cart
- `GET /api/cart/{cartId}/total` - Calculate cart total price

### Order Module
- `POST /api/order/create` - Create order from cart
- `GET /api/order/{orderId}/status` - Get order status

### Pickup Module
- `GET /api/pickup/receipt/{orderId}` - Generate pickup receipt and code

### Kitchen Module
- `POST /api/kitchen/receive` - Kitchen receives new order
- `PUT /api/kitchen/{orderId}/ready` - Mark order ready for pickup


## Related Resources
- Frontend Development Docs: [frontend/README.md](frontend/README.md)
- Spring Boot Official Docs: https://spring.io/projects/spring-boot
- H2 Database Official Docs: https://www.h2database.com
- Supabase Official Docs: https://supabase.com/docs

## Team Contributions at M02-A03
| Member ID + Name         | Contribution                               |
|--------------------------|--------------------------------------------|
| Julia Johnson            | Implemented `user` and `menu` modules      |
| Anika Humad              | Implemented `order` module                 |
| Chanyah johnson          | Implemented `payment`, `kitchen` modules   |
| Yiquan Ren               | Implemented integration tests; project integration |

## Team Contributions
| Team Member | Contributed Modules |
|-------------|---------------------|
| [Name 1]    | User Module, Menu Module |
| [Name 2]    | Order Module, Payment Module |
| [Name 3]    | Pickup Module, Kitchen Module |
| [Name 4]    | API Layer, Database Integration |