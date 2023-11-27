# Welcome to Online Bookstore Project!

---

If you have always dreamed of being the owner of the most 
atmospheric business - a bookstore, or you already are, 
but you need to automate all processes and expand on the Internet?

`Welcome to the Book Store project! ` 

It is here that all the main **functions** and **opportunities** 
to **_successfully achieve the goal_** are developed! \
The project is designed in such a way that any of your wishes 
and new implementations would easily expand the functionality!

---

# Project Description

## in this project we used the following technologies:

<details>
  <summary><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZqRFNAis0vxGXeQDFA2thujnilvYO8eqTKDX5QgJ5APGtLTNQu0-d6rTkb8oSWOdyRyY&usqp=CAU" width="30"/> Java</summary>

`In this project, we used Java as the main programming language.`
</details>

<details>
  <summary><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwsq-7f5BWyog4cdeT1sQaYLVzhJ0o37Up8TjHvVU08WUgfyyMMRMHTVwJ5XReSjyhZa0&usqp=CAU" width="30"/> Spring Boot</summary>

`A powerful framework for building Java-based applications.`
</details>

<details>
  <summary><img src="https://www.baeldung.com/wp-content/uploads/2021/02/lsd-module-icon-1.png" width="30"/> Spring Data JPA</summary>

`Simplifies data access and persistence with JPA (Java Persistence API).`
</details>

<details>
  <summary><img src="https://www.javacodegeeks.com/wp-content/uploads/2014/07/spring-security-project.png" width="30"/> Spring Security</summary>

`Enables robust and secure authentication and authorization mechanisms.`
</details>

<details>
  <summary><img src="https://oddblogger.com/wp-content/uploads/2021/03/swagger-logo-2.png" width="30"/> Swager</summary>

`Provides API documentation.`
</details>

<details>
  <summary><img src="https://www.freepnglogos.com/uploads/logo-mysql-png/logo-mysql-mysql-logo-png-images-are-download-crazypng-21.png" width="30"/> MySQL </summary>

` Utilization of a relational database to store information about: `

` * accommodations;  ` \
` * users; ` \
` * addresses; ` \
` * bookings; ` \
` * payments. `

</details>

<details>
  <summary><img src="https://repository-images.githubusercontent.com/444861690/5f8b5fb8-fb79-447b-8e52-2ecd52743e41" width="30"/> Telegram API</summary>

` Used to send notifications to administrators via Telegram. `
</details>

<details>
  <summary><img src="https://cdn-icons-png.flaticon.com/512/5968/5968312.png" width="30"/> Stripe API</summary>

`Integrated with Stripe for secure payment processing.`
</details>

<details>
  <summary><img src="https://philiaweb.com/uploads/image/google-maps.png" width="30"/> Google Maps API</summary>

`Used to display the location of accommodation.`
</details>

<details>
  <summary><img src="https://cdn-icons-png.flaticon.com/512/919/919853.png" width="30"/> Docker</summary>

`Used for containerization of the application and database.`
</details>

<details>
  <summary><img src="https://user-images.githubusercontent.com/1204509/79262490-b2012a80-7e91-11ea-82fa-e791f8b4d177.jpg" width="30"/> Lombok</summary>

`Reduces boilerplate code with annotations.`
</details>

<details>
  <summary><img src="https://1.bp.blogspot.com/-C5lGqSQuCic/WX39mN-OhdI/AAAAAAAAALU/qUZQdUPTvmInwGSKAYfcZ-QA_PXxhXCXwCLcBGAs/s1600/mapstruct.png" width="30"/> Mapstruct</summary>

`Simplifies object mapping between DTOs and entities.`
</details>

<details>
  <summary><img src="https://www.liquibase.org/wp-content/themes/liquibase/assets/img/cta-icon.svg" width="30" height="30"/> Liquibase </summary>

`Ensures the application database is updated along with the application code.`
</details> 

## Database Architecture

![DbArchitecture](assets/img.png)


## Endpoints

### Endpoints for Registration and Authentication for Users

| Method | Endpoint               | Description                                           | Role   |
|--------|------------------------|-------------------------------------------------------|--------|
| POST   | /api/auth/register     | Register new user by providing required details       | Anyone |
| POST   | /api/auth/login        | Authenticate user by email and password, returning JWT token if valid credentials | Anyone |

### Endpoints for Managing Shopping Cart

| Method | Endpoint                            | Description                                                        | Role       |
|--------|-------------------------------------|--------------------------------------------------------------------|------------|
| GET    | /api/cart                           | Retrieve user's shopping cart                                      | USER       |
| POST   | /api/cart                           | Add a book to the shopping cart                                    | USER       |
| PUT    | /api/cart/cart-items/{id}           | Update quantity of a book in the shopping cart                     | USER       |
| DELETE | /api/cart/cart-items/{id}           | Remove a book from the shopping cart                               | USER       |

### Endpoints for Managing Orders

| Method | Endpoint                            | Description                                                        | Role       |
|--------|-------------------------------------|--------------------------------------------------------------------|------------|
| GET    | /api/orders                         | Retrieve all orders for the current user                           | USER       |
| POST   | /api/orders                         | Place an order from the user's shopping cart                        | USER       |
| PATCH  | /api/orders/{id}                    | Change the status of an order by specifying its id                 | ADMIN      |
| GET    | /api/orders/{id}/items              | Retrieve an order by specifying its id                             | USER       |
| GET    | /api/orders/{orderId}/items/{id}    | Retrieve an order item by specifying its id                        | USER       |

### Endpoints for Managing Categories

| Method | Endpoint                            | Description                                                        | Role       |
|--------|-------------------------------------|--------------------------------------------------------------------|------------|
| GET    | /api/categories                     | Get a list of available categories                                 | USER       |
| POST   | /api/categories                     | Create a new category                                              | ADMIN      |
| GET    | /api/categories/{id}                | Get a category by ID                                               | USER       |
| PUT    | /api/categories/{id}                | Update a category by ID                                            | ADMIN      |
| DELETE | /api/categories/{id}                | Delete a category by ID                                            | ADMIN      |
| GET    | /api/categories/{id}/books          | Receive a list of books by category id                             | USER       |

### Endpoints for Managing Books

| Method | Endpoint                            | Description                                                        | Role       |
|--------|-------------------------------------|--------------------------------------------------------------------|------------|
| GET    | /api/books                          | Get a list of available books                                      | USER       |
| GET    | /api/books/{id}                     | Get a book by ID                                                   | USER       |
| POST   | /api/books                          | Create a new book                                                  | ADMIN      |
| DELETE | /api/books/{id}                     | Delete a book by ID                                                | ADMIN      |
| PUT    | /api/books/{id}                     | Update a book by ID                                                | ADMIN      |

---

## Quick Start

1. **Install Docker:**
   [Install Docker](https://docs.docker.com/get-docker/)

2. **Clone this repository:**
   ```bash
   git clone https://github.com/mmosii/online-book-store.git
   cd online-book-store

3. **Create a .env file in the root of the project.
   Use .env.sample as a reference.
   Add necessary environment variables.**
4. **Build the application:**
   ```bash
   mvn clean package
5. **Build and start the Docker containers:**
   ```bash
   docker-compose build && docker-compose up

The application should be running locally at http://localhost:8080.
6. **Test with Swagger:
   Open http://localhost:8080/swagger-ui/index.html in your browser.**

## For additional information or inquiries, please contact:
- Email: salnikov.markiyan@gmail.com
- Telegram: https://t.me/resp1dK
- LinkedIn: [LinkedIn Profile](https://www.linkedin.com/in/markiyan-salnikov/)