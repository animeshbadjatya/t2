#
# **E-Commerce-Application**

- The E-Commerce Application has been constructed using Java 17 and Spring Boot. In the backend, Spring Data JPA is utilized to communicate with a Postgres database, simplifying the management and storage of vital entities like products, orders, orderitems.

- The application is deployed with Dokcer using docker-compose, allowing for the containerization of the application for enhanced efficiency and manageability.

- The APIs come with comprehensive documentation and can be conveniently accessed via Swagger UI, facilitating developers in testing and comprehending the diverse endpoints. In summary, this project offers secure REST APIs, establishing a scalable platform for businesses to market their products to customers.

#
# **Features**

## **Product Management**

- Add new products to the inventory with extra details
- Update an existing product and its details
- Managing quantity of products
- Deleting a product entity
- Fetching product details on demand

## **Order Management**

- Creating a new order
- Adding products to the new order with price
- Deleting/Updating order items on demand
- Fetching order details with respect to customer

#
# **Technologies:**

- Java 17
- Spring Boot 3.0
- PostgreSQL
- Spring Data JPA
- OpenAPI
- Docker
- Mockito
- JUnit
- Swagger UI

#
# **Running the app**

1. Download the folder and start the terminal.
2. Make sure docker is enabled on the system.
3. To run the app, a JAR file should be created first - *mvn clean package*
4. EXPORT the following variable below or a .env file is also present in the folder - 
export POSTGRESDB_USER=postgres
export POSTGRESDB_ROOT_PASSWORD=postgres
export POSTGRESDB_DATABASE=ecommerce
5. Once everything is in place, run the following command - *docker-compose up*
6. After the app is loaded, run the following on any browser - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
7. This will redirect to a SwaggerUI page where all the exposed API's can be accessed.
8. A sql script will run automatically on docker run to add products and orders for testing the application. (init.sql)

#
# **API documentation**

- API documentation is available via Swagger UI - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

#
# **ER-Diagram**

![ER-Diagram - Showing relation between tables](/images/picture1.png)

#
# **Swagger-ui**

![Swagger-Ui](/images/picture7.png)

#
# **API Controllers**

![CRUD REST APIs for Products](/images/picture2.png)

![CRUD REST APIs for Products](/images/picture3.png)

![CRUD REST APIs for Products](/images/picture4.png)

![Schemas](/images/picture5.png)