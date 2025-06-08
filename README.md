# Spring Boot TestContainers PostgreSQL POC

This project demonstrates how to use TestContainers with Spring Boot and PostgreSQL for integration testing. It shows how to set up and run integration tests with a real PostgreSQL database in a Docker container, which is automatically managed by TestContainers.

## Project Overview

This proof of concept (POC) demonstrates:
- Integration of Spring Boot with TestContainers
- JPA Entity and Repository implementation
- Integration testing with a real PostgreSQL database
- Automated container lifecycle management
- Native SQL query usage in Spring Data JPA

## Prerequisites

- Java 23
- Maven
- Docker Desktop (or Docker Engine for Linux)
- IDE (IntelliJ IDEA recommended)

## Technology Stack

- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- TestContainers 1.21.1
- JUnit

## Project Structure
```
src ├── main │ └── java │ └── org/example │ ├── Main.java # Spring Boot application class │ ├── entity │ │ └── User.java # JPA entity │ └── repository │ └── UserRepository.java # Spring Data JPA repository │ └── resources │ └── application.properties # Main application properties ├── test │ └── java │ └── org/example │ └── UserRepositoryTCLiveTest.java # Integration test │ └── resources │ ├── application-tc.properties # Test profile properties │ └── testcontainers.properties # TestContainers configuration
``` 

## Key Components

### User Entity
The `User` entity represents a user in the system with the following fields:
- id (Long): Primary key
- name (String): User's name
- dateCreated (LocalDate): User creation date
- email (String): User's email
- status (String): User's status

### UserRepository
The repository interface extends `JpaRepository` and includes:
- Basic CRUD operations (inherited from JpaRepository)
- Custom native query for updating user status by name

### Test Configuration
The integration test demonstrates:
- TestContainers setup with PostgreSQL
- Spring Boot test configuration
- Transaction management
- Data manipulation and verification

## Configuration Files

### application-tc.properties
```
properties spring.datasource.driver-class-name=org.postgresql.Driver spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect spring.jpa.hibernate.ddl-auto=create spring.jpa.show-sql=true spring.datasource.url=jdbc:tc:postgresql:11.1:///integration-tests-db spring.datasource.username=sa spring.datasource.password=sa
``` 

### testcontainers.properties
```
properties docker.host=unix:///var/run/docker.sock testcontainers.reuse.enable=true
``` 

## Running Tests

To run the integration tests:
```
bash mvn test
``` 

The test will:
1. Start a PostgreSQL container
2. Create the database schema
3. Execute the test cases
4. Automatically clean up the container

## Test Cases

The integration test (`UserRepositoryTCLiveTest`) demonstrates:
1. Saving multiple users to the database
2. Executing a native query to update user status
3. Verifying the number of updated records

## Best Practices Demonstrated

1. **Container Management**: Using TestContainers for automated container lifecycle management
2. **Test Isolation**: Each test runs in its own transaction
3. **Profile Separation**: Using separate test profile for integration tests
4. **Native Queries**: Demonstrating how to use native SQL queries with JPA
5. **Clean Code**: Proper separation of concerns and clear structure

## Troubleshooting

1. **Docker Issues**
   - Ensure Docker is running
   - Check Docker socket permissions
   - Verify Docker daemon is accessible

2. **Database Connection**
   - Check Docker network connectivity
   - Verify database credentials
   - Ensure correct port mappings

3. **Test Execution**
   - Verify Maven configuration
   - Check Java version compatibility
   - Ensure all dependencies are resolved

## Additional Notes

- The project uses PostgreSQL 11.1 for testing
- Container configurations are handled automatically by TestContainers
- Database schema is created automatically through JPA entity definitions
- Tests run in isolation with their own database instance

## Future Enhancements

1. Add more complex entity relationships
2. Implement additional test scenarios
3. Add database migration tools (e.g., Flyway)
4. Include test coverage reports
5. Add CI/CD pipeline configuration
