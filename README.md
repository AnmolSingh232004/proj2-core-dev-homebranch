### Setup

- Install Docker Desktop on Windows
- Run docker-compose.yml (core/infrastructure/spring-based-app/spring-application/src/main/resources/docker-compose.yml)

### Build project

- mvn clean install

### Run project

- devDockerDb profile is activated (check application.properties)
- Run org.tasos.proj2.onionarchitecture.springapplication.Application class
- This triggers schema.sql to initialize and populate the database with test data.


### Connect to docker container database

- Install MySQLWorkbench on Windows
- New connection with:
  - Host: 127.0.0.1
  - Port: 3305
  - Username: user
  - Password: pass

- Or use Database tool of Intellij if you have paid version

### Postman setup

- Install postman locally
- Import proj2 collection
- No auth/jwt needed. We hardcode user "user" for dev purposes."# proj2-core-dev-homebranch" 
"# proj2-core-dev-homebranch" 
