# CustomerCRUDApplication
INTEND: This project is a part of a assignment-->
DESCRIPTION: 
1. This project explains Customer CRUD (**C**reate, **R**ead, **U**pdate, **D**elete) operations using spring boot and H2 in-memory database.
2. In this app we are using Spring Data JPA for built-in methods to do Customer CRUD operations.     
3. `@EnableJpaRepositories` annotation is used on main class to Enable H2 DB related configuration, which will read properties from `application.properties` file.

Also, recently added **Spring Reactive programming** support with the help of **Spring Webflux** in this application. All reactive classes/interfaces are added with prefix as `Reactive*`.

## Prerequisites 
- Java (version 17)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/guides/index.html)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Lombok](https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok)


## Tools
- Eclipse or IntelliJ IDEA (or any preferred IDE) with embedded Maven like Spring Tool Suite
- Maven (version >= 3.6.0)
- Postman (or any RESTful API testing tool)

<br/>


###  Build and Run application
_GOTO >_ **~/absolute-path-to-directory/customer-service**  
and try below command in terminal
> **```mvn spring-boot:run```** it will run application as spring boot application

or
> **```mvn clean install```** it will build application and create **jar** file under target directory 

Run jar file from below path with given command
> **```java -jar ~/path-to-customer-service/target/customer-service-0.0.1-SNAPSHOT.jar```**

Or
> run main method from `CustomerCRUDApplication.java` as spring boot application.  


||
|  ---------    |
| **_Note_** : In `CustomerCRUDApplication.java` class we have autowired both Cusromer repository. <br/>If there is no record present in DB for any one of that module class (Customer), static data is getting inserted in DB from `HelperUtil.java` class when we are starting the app for the first time.| 



### Code Snippets
1. #### Maven Dependencies
    Need to add below dependencies to enable H2 DB related config in **pom.xml**. Lombok's dependency is to get rid of boiler-plate code.   
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
   
    <!-- update -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
   
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
   
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    ```
   
   Added Reactive spring support with below dependencies in this application.
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
   
    <dependency>
        <groupId>io.projectreactor</groupId>
        <artifactId>reactor-test</artifactId>
        <scope>test</scope>
    </dependency>
    ```
    
   
   
2. #### Properties file
    Reading H2 DB related properties from **application.properties** file and configuring JPA connection factory for H2 database.  

    **src/main/resources/application.properties**
     ```
     server.port=8088
    
     spring.datasource.url=jdbc:h2:mem:sampledb;DB_CLOSE_ON_EXIT=TRUE
     spring.datasource.driverClassName=org.h2.Driver
     spring.datasource.username=sa
     spring.datasource.password=password
     spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    
     spring.h2.console.enabled=true
     spring.h2.console.path=/h2-console
     spring.h2.console.settings.trace=false

     spring.data.rest.defaultPageSize=10
     spring.data.rest.maxPageSize=20

     springdoc.version=1.0.0
     springdoc.show-actuator=true
     springdoc.swagger-ui.path=/swagger-ui-custom.html 
     ```
   
   
3. #### Model class
    Below are the model classes which we will store in H2 DB and perform CRUD operations.  
    **Customer.java**  
    ```
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Entity
    @Table
    public class Customer implements Serializable {
    
        @Id
        @GeneratedValue
        private int id;
    
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private boolean isActive;
    
        // Constructor, Getter and Setter
    }
    ```

    #    
    #### **Update**: New annotations are added in this application to print log smartly with the help of Aspect (LoggerAspect class from aop package).
    **@LogObjectBefore** - annotation created to print log before method execution <br/>
    **@LogObjectAfter** - annotation created to print the returned value from method
    #   
   
   
4. #### CRUD operation for Customers

    In **CustomerController.java** class, 
    we have exposed 5 endpoints for basic CRUD operations
    - GET All Customer
    - GET by ID
    - POST to store Customer in DB
    - PUT to update Customer
    - DELETE by ID
    
    ```
    @RestController
    @RequestMapping("/customers")
    public class CustomerController {
        
        @LogObjectAfter
        @GetMapping
        public ResponseEntity<List<?>> findAll();
    
        @LogObjectAfter
        @GetMapping("/{id}")
        public ResponseEntity<?> findById(@PathVariable String id);
    
        @LogObjectBefore
        @LogObjectAfter
        @PostMapping
        public ResponseEntity<?> save(@RequestBody Customer customer);
    
        @LogObjectBefore
        @LogObjectAfter
        @PutMapping("/{id}")
        public ResponseEntity<?> update(@PathVariable int id, @RequestBody Customer customer);
    
        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable String id);
    }
    ```
   
   In **CustomerServiceImpl.java**, we are autowiring above interface using `@Autowired` annotation and doing CRUD operation.
    
    <br/>
    <br/>
    
    In **ReactiveCustomerController.java** class, 
    we have exposed 5 endpoints for basic CRUD operations with spring reactive feature
    - GET All Customer
    - GET by ID
    - POST to Customer in DB
    - PUT to update Customer
    - DELETE by ID
       
    ```
    
### API Endpoints

- #### Customer CRUD Operations
    > **GET Mapping** http://localhost:8088/customers  - Get all Customers
    
    > **GET Mapping** http://localhost:8088/customers/1  - Get Customer by ID
       
    > **POST Mapping** http://localhost:8088/customers  - Add new Customer in DB  
    
     Request Body  
     ```
        {
            "firstName": "Tony",
            "lastName": "Iron Man",
            "dateOfBirth": "12/12/2002",
            "isActive": true
        }
     ```
    
    > **PUT Mapping** http://localhost:8088/customers/3  - Update existing Customers for given ID 
                                                       
     Request Body  
     ```
        {
            "id": "3"
            "firstName": "Tony",
            "lastName": "Iron Man",
            "dateOfBirth": "12/12/2002",
            "isActive": true
        }
     ```
    
    > **DELETE Mapping** http://localhost:8088/customers/4  - Delete Customer by ID

- #### Reactive Customers CRUD Operations
    > ###### **GET Mapping** http://localhost:8088/reactive/customers  - Get all Customers
    
     ## **(Try above endpoint in Chrome to see magic, Postman currently not supporting reactive programming)**
    
    > **GET Mapping** http://localhost:8088/reactive/customers/1  - Get Customer by ID
       
    > **POST Mapping** http://localhost:8088/reactive/customers  - Add new Customer in DB  
    
     Request Body  
     ```
        {
            "firstName": "Tony",
            "lastName": "Iron Man",
            "dateOfBirth": "12/12/2002",
            "isActive": true
        }
     ```
    
    > **PUT Mapping** http://localhost:8088/reactive/customers/3  - Update existing Customers for given ID 
                                                       
     Request Body  
     ```
        {
            "id": "3"
            "firstName": "Tony",
            "lastName": "Iron Man",
            "dateOfBirth": "12/12/2002",
            "isActive": true
        }
     ```
    
    > **DELETE Mapping** http://localhost:8088/reactive/customers/4  - Delete Customer by ID
