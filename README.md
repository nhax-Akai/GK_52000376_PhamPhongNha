## Midterm take-home Assignment
### ID: 52000376, Full name: Phạm Phong Nhã
### 1. Software development principles, patterns and practices being applied.
Dependency Injection: The HomeController class uses Dependency Injection to inject instances of the ProductServiceImpl, CategoryServiceImpl, and CartItemServiceImpl classes into its constructor. This makes the class more modular and easier to test, as it can be easily swapped out with mock or stub implementations.

Model-View-Controller (MVC) Pattern: The HomeController class follows the MVC pattern, where the getAllProducts() method retrieves a list of products from the ProductService and adds them to the Model, which is then passed to the View (home.html) for rendering.

Separation of Concerns: The HomeController class separates concerns by having separate methods for handling login, filtering products, adding products to the cart, updating cart items, and performing checkout. This makes the code easier to read, maintain, and test.

Single Responsibility Principle (SRP): The ProductService, CategoryService, and CartItemService classes follow the SRP by having a single responsibility of handling their respective entities, and not being responsible for anything else.

Open-Closed Principle (OCP): The code follows the OCP by being open to extension (e.g., the ProductService can be easily extended to add new filtering methods) but closed to modification (e.g., the ProductService does not need to be modified to add new filtering methods).

Don't Repeat Yourself (DRY) Principle: The filter() method avoids code duplication by using a series of if-else statements to handle different filter combinations, instead of creating separate methods for each combination.

Test-Driven Development (TDD): The code follows TDD principles by having separate test classes for each service class, and using mock objects to test the methods in isolation.

### 2. The code structure.
The code structure follows a standard Spring MVC architecture, where the main components are the Model, View, and Controller.

The Model includes the entities (Product and CartItem) and the services (ProductService, CategoryService, and CartItemService) that handle the logic for retrieving, filtering, and modifying the entities.

The View includes the HTML templates (home.html, cart.html, checkout.html, addproduct.html, details.html, and editproduct.html) that render the data from the Model.

The Controller includes the HomeController class, which handles the user requests and interacts with the Model and View. The HomeController has methods for retrieving all products, logging in, filtering products, adding products to the cart, updating cart items, performing checkout, adding a new product, searching for products, viewing product details, and editing product details.

The code structure also includes the necessary configurations for Spring Boot, such as the application.properties file for setting up the database connection and the pom.xml file for managing dependencies.

### 3. All required steps in order to get the application run on a local computer.
1. Install Java 17 or higher and Maven on your computer.

2. Download the source code of the application from the repository.

3. Extract the downloaded zip file or clone the repository to your local machine.

4. Open the application.properties file located in the src/main/resources directory.

  - Update the database URL, username, and password to match your MySQL database configuration:

    - spring.datasource.url=jdbc:mysql://localhost:3306/spring-commerce
    - spring.datasource.username=your_username
    - spring.datasource.password=your_password
  - Save the changes to the application.properties file.

5. Create a new MySQL database named spring-commerce using the following command in the MySQL command line:

  - CREATE DATABASE spring-commerce;
6. Launch the application by running the button Run App in IntelliJ IDEA

7. Open a web browser and go to the following URL to access the application:
http://localhost:8080/

### 4. Entity-relationship diagram for the database
Link picture: [https://github.com](https://drive.google.com/file/d/1wUF3njTJzIA4mhCplRXZfUwzRfJA09Js/view?usp=sharing)

### 5. Video demo 
Link video: 

