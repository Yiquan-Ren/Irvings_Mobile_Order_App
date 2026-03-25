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
1.  Update `pom.xml` dependencies:
    
    <!-- Remove H2 Database dependency -->
    <!-- Add PostgreSQL Driver dependency -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>

2.  Update `application.properties` connection configuration:
    
    spring.datasource.url=jdbc:postgresql://db.[YOUR_PROJECT_ID].supabase.co:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=[YOUR_SUPABASE_DATABASE_PASSWORD]
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

3.  Initialize schema and test data:

    Open the Supabase SQL Editor
    Run the contents of schema.sql and data.sql to create tables and insert test data    

## Full API Specification