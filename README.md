[![codecov](https://codecov.io/github/BrendonButler/WorkWiz/branch/main/graph/badge.svg?token=NXO6H2K0NJ&label=Coverage)](https://codecov.io/github/BrendonButler/WorkWiz)

WorkWiz is an educational application designed to teach children various skills through simulated jobs. One of the featured games, "GooberMart," enhances the classic play of using cash register toys by integrating a scanner and touch screen. This allows the web UI to be added to a touch screen atop a toy (or real) cash drawer, making the experience as real as possible while keeping it fun.

### Features
- **GooberMart**: A game where children can simulate running a cash register.
- **Customizable Items**: Parents can add items with prices, names, descriptions, etc., to help children practice reading, math, and more.


## Running the Application
1. `docker-compose up -d` to start the MySQL database container in detached mode.
2. `mvn spring-boot:run` to start the Spring Boot application.
3. Use curl, Postman/Insomnia/Bruno, or the frontend application to interact with the APIs.

### Deployment
- The application can be self-hosted on a local server using Docker.
- It can also be deployed to AWS for scalability and reliability.
