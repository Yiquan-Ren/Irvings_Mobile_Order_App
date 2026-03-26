# Irving's Order App - Backend Development Documentation
Technical details, database migration guide, and full API specification for backend development.

## Technical Details
### Component Scanning Rules
The Spring Boot main application class is in the `com.irvings` base package. **All business code must be placed in subpackages of `com.irvings`** (e.g., `com.irvings.controller`, `com.irvings.user`). Annotations like `@Service` and `@RestController` will not be detected by Spring if placed outside this base package.

### Global Exception Handling
All custom exceptions are centrally handled in `com.irvings.exception.GlobalExceptionHandler`, which maps exceptions to standard HTTP status codes and human-readable error messages.

### CORS Configuration
Cross-Origin Resource Sharing (CORS) is configured in `com.irvings.config.CorsConfig`. The current configuration allows all origins for development purposes. For production deployment, replace the wildcard with your specific frontend origin URL.

## Database Migration Guide
### Switch from H2 to Supabase PostgreSQL
This guide walks you through migrating from the local H2 in-memory database to a cloud-hosted Supabase PostgreSQL database. This migration preserves your existing codebase, requires minimal changes to your business logic, and enables shared team access to persistent data.

#### Prerequisites
Before starting the migration, complete these setup steps:
1. Create a free account at supabase.com (GitHub SSO recommended for team access)
2. Create a new Supabase project:
   - Project Name: irvings-mobile-order-app
   - Database Password: Create a secure password (save this securely, you will need it for configuration)
   - Region: Select the region closest to your location (e.g., US East for Penn State students)
3. Wait 2-3 minutes for Supabase to fully provision your database instance

#### Step 1: Update Maven Dependencies
First, replace the H2 database dependency with the official PostgreSQL JDBC driver in your root pom.xml file:
1. **Remove** the H2 dependency block
2. **Add** the PostgreSQL driver dependency in its place
```xml
<!-- REMOVE THIS BLOCK -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- PostgreSQL JDBC Driver for Supabase -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```
3. Refresh your Maven project in VS Code:
   - Right-click the `pom.xml` file in the VS Code Explorer
   - Select **Reload** Projects to apply the dependency changes

#### Step 2: Update Spring Boot Application Configuration
Replace the H2 configuration in `src/main/resources/application.properties` with the Supabase PostgreSQL configuration. We have included optimized settings for your project, with comments for development vs. production use.
```properties
# Server Port (unchanged from your existing configuration)
server.port=8080

# --------------------------
# SUPABASE POSTGRESQL CONFIG
# --------------------------
# Full JDBC Connection String: replace [YOUR_PROJECT_ID] and [YOUR_DB_PASSWORD]
# Find your Project ID in Supabase > Project Settings > General > Reference ID
spring.datasource.url=jdbc:postgresql://db.[YOUR_PROJECT_ID].supabase.co:5432/postgres
# Default Supabase username is always "postgres"
spring.datasource.username=postgres
# The password you created when setting up your Supabase project
spring.datasource.password=[YOUR_DB_PASSWORD]
# PostgreSQL JDBC Driver
spring.datasource.driver-class-name=org.postgresql.Driver

# --------------------------
# JPA / HIBERNATE CONFIG
# Optimized for PostgreSQL and your project
# --------------------------
# Development: use "update" to auto-create tables on startup (matches your H2 setup)
# Production: use "validate" to prevent accidental schema changes
spring.jpa.hibernate.ddl-auto=update
# Enable SQL logging in the console for debugging (disable in production)
spring.jpa.show-sql=true
# PostgreSQL dialect for Hibernate (critical for compatibility)
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
# Improve schema compatibility with Supabase
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

# --------------------------
# DISABLE H2 CONSOLE (no longer needed)
# --------------------------
spring.h2.console.enabled=false
```

> **Critical Security Note**: Never commit the `application.properties` file with your plaintext password to Git. For team collaboration, use environment variables or a separate `application-dev.properties` file added to your `.gitignore`.

#### Step 3: Initialize Database Schema & Test Data
You have two validated options to set up your database schema and test data, both compatible with your existing project code.

**Option A: Auto-Generate Schema via JPA (Recommended for our Project)**
This is the simplest option, with zero manual SQL work:
1. Ensure all your entity classes (e.g., `CustomerAccount`, `Order`, `MenuItem`) have the correct JPA annotations (`@Entity`, `@Id`, `@Table`)
2. Start your Spring Boot application
3. Hibernate will automatically create all tables, columns, and relationships in your Supabase database to match your entity classes
4. Verify the tables were created:
   - Go to your Supabase project dashboard
   - Open the **Table Editor** from the left sidebar
   - You will see all your tables under the `public` schema

**Option B: Manual Schema Initialization via SQL Scripts**
Use this option if you want full control over your database schema (matches our existing `schema.sql`/`data.sql` files):
1. Go to your Supabase project dashboard
2. Open the **SQL Editor** from the left sidebar
3. Click **New Query**
4. Paste the full contents of your `src/main/resources/schema.sql` file into the editor
5. Click **Run** to create all tables
6. Create a second new query, paste the contents of your `data.sql` file, and click **Run** to insert your test menu items and user accounts
7. Verify the data in the **Table Editor**

> **Compatibility Note**: H2 and PostgreSQL have nearly identical SQL syntax for your project's use case. The only common difference is boolean handling: PostgreSQL uses `TRUE`/`FALSE` (lowercase `true`/`false` also works) which is fully compatible with your existing scripts.

#### Step 4: Validate the Migration
Complete these checks to confirm the migration was successful:
1. **Start the Application**: Run `IrvingsApplication.java` and confirm there are no connection errors in the console
2. **Verify API Functionality**: Test your core API endpoints (e.g., `GET /api/menu/list`, `POST /api/user/login`) to confirm they work as expected
3. **Confirm Data Persistence**: Create a test order via the API, then restart your Spring Boot application. The order will remain in the Supabase database (unlike the H2 in-memory database, which resets on restart)
4. **Run Integration Tests**: Execute your full `IrvingsAppTest.java` test suite to confirm all business logic works with the new database
5. **Team Access Check**: Have your team members use the same `application.properties` configuration to confirm they can all connect to the shared database

#### Step 5: Rollback Plan (If Needed)
If you need to revert to the H2 database for any reason:
1. Restore the H2 dependency in `pom.xml`
2. Revert your `application.properties` to the original H2 configuration
3. Reload the Maven project and restart the application
4. All functionality will immediately return to the local H2 database, with no changes to your business logic

### Best Practices & Important Notes
1. **Credentials Security**: Never commit database passwords to Git. For team collaboration, use a `application-example.properties` file with placeholders, and have each team member create their own local `application-dev.properties` file (added to `.gitignore`).
2. **Schema Changes**: Always use `spring.jpa.hibernate.ddl-auto=validate` in production to prevent accidental table drops or schema changes.
3. **Backup**: Supabase automatically creates daily database backups, but you can manually export your schema and data via the **Table Editor** > **Export** button.
4. **Team Permissions**: Add your team members to the Supabase project via the **Settings** > **Team** menu to grant shared access to the database dashboard.

### Troubleshooting Common Issues
- Connection Timeout Error: Confirm your Supabase project is fully provisioned, check that your Project ID and password are correct, and ensure your network does not block port 5432.
- Table Not Found Error: Confirm spring.jpa.hibernate.ddl-auto=update is set in your configuration, or manually run your schema.sql script in the Supabase SQL Editor.
- Permission Denied Error: Ensure you are using the default postgres username, and that your database password matches the one you created during project setup.
- Data Type Mismatch Error: PostgreSQL is stricter about numeric and date types than H2. Ensure your entity class field types match your database column types (e.g., Double for prices, LocalDateTime for timestamps).
- CORS Error for Supabase API: This guide uses direct JDBC connection to the PostgreSQL database, so you do not need to configure Supabase CORS settings for the backend. Frontend CORS is handled by your existing Spring Boot CorsConfig.java.


## Full API Specification

### User Authentication
#### POST /api/user/login
Request Body includes email and password fields.
```json
{
    "email": "test@psu.edu",
    "password": "password"
}
```
Response returns a session ID string with 200 OK, or 404 Not Found if credentials are invalid.

#### POST /api/user/register
Request Body includes email and password fields.
```json
{
    "email": "newuser@psu.edu",
    "password": "userpassword"
}
```
Response returns true if registration is successful, or false if the email already exists.

### Test Credentials
| Account Type | Email | Password |
|--------------|-------|----------|
| Customer Account | `test@psu.edu` | `password` |
| Staff Account | `staff@irvings.com` | `staffpass` |