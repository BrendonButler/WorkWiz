## Tools, Frameworks, and Technologies 

### Frontend
- **React**: A JavaScript library for building user interfaces. It allows you to create reusable UI components. 
- **TypeScript**: A superset of JavaScript that adds static types, making your code more robust and easier to maintain. 
- **npm**: A package manager for JavaScript, used to manage dependencies and scripts for your React application. 

### Backend
- **Spring Boot**: A framework for building Java-based applications. It simplifies the development of RESTful APIs and integrates well with various databases.
- **Java**: The programming language you'll use with Spring Boot for backend development. 
- **Maven**: A build automation tool used primarily for Java projects. It helps manage project dependencies and build processes. 

### Database
- **MySQL**: A relational database management system. It's widely used and supports complex queries and transactions. 

### Configuration and Data Management
- **YAML**: A human-readable data serialization standard. You'll use it for configuration files in Spring Boot. 
- **JSON**: A lightweight data interchange format. You'll use it for data files that are programmatically changed. 

### Containerization and Deployment
- **Docker**: A platform for developing, shipping, and running applications in containers. It ensures consistency across different environments. 
- **AWS**: Amazon Web Services, a cloud platform that provides various services for hosting and managing applications. You'll use services like EC2 and RDS for deployment. ### Brief Description of the Application Your application is an educational tool designed to help your nephews learn how to run a cash register, scan items with a hand scanner, and manage inventory and prices.

### Cash Register Interface
- A user-friendly interface where users can simulate transactions. 
- Users can scan items, calculate totals, and process payments. 

### Inventory Management
- A system to manage inventory, including adding, updating, and deleting items. 
- Users can view current stock levels and update prices. 

### Hand Scanner Simulation
- A feature that allows users to scan items using a simulated hand scanner. 
- The scanned items are added to the transaction list in the cash register interface. 

### Backend Services
- RESTful APIs to handle CRUD operations for inventory and transactions. 
- Integration with a MySQL database to store and retrieve data. 

### Configuration and Data Management
- YAML files for configuring environment-specific settings in Spring Boot.
- JSON files for storing data that can be programmatically changed. 

### Deployment
- The application can be self-hosted on a local server using Docker.
- It can also be deployed to AWS for scalability and reliability.

## Running the Application
1. `docker-compose up -d` to start the MySQL database container in detached mode.
2. `mvn spring-boot:run` to start the Spring Boot application.
3. Use curl, Postman/Insomnia/Bruno, or the frontend application to interact with the APIs.
