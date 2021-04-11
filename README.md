# 💡 General Info

 Project performed with accordance to modern solutions, designing patterns and Clean Code principles. Entire app based on hexagonal architecture and designed with REST API standards. With use of the JUnit and Mockito frameworks to ensure tests coverage. In addition connected to PostgreSQL database.

The Board Game Rental app covers the basic CRUD operations and provides additional features. It focuses mainly on searching for board games by users and allows them to be rented. It also prepared an entire panel for maintaining a stock of board games. In addition gives possibility to check rent status and renty history list for each user. 

## ⚙️ Pre-requisites

- Java 8
- Gradle 6+
- PostgreSQL Database *(not necessary, created in-memory-database version, mainly to perform fast unit tests)*

## 🕛 Getting Started

Clone repo straightforward to destination directory and open through your IDE.

`git clone [https://github.com/zborowskibartek/Board-Game-Rental.git](https://github.com/zborowskibartek/Board-Game-Rental.git)`

Thanks to Spring Boot (web starter) you can start app on [localhost:8080](http://localhost:8080) and execute all endpoints via HTTP. For example: 

- GET all board games (filtered and sorted in every combination)

`localhost:8080/boardgames?sort=NAME_ASC&type=CARD_GAME&category=COOPERATIVE`

- GET particular board game (according to REST specification)

`localhost:8080/boardgames/1`

- POST rent board game

`localhost:8080/rent?userId=1&boardGameId=1`

- GET retrive user rent history

`localhost:8080/history?userId=1`

## ⌨️ Technology Stack

The project uses the following technologies:

- REST: **Spring Boot**
- Persistence: **Spring Data JPA**
- Databse: **PostgreSQL**
- Tests: **JUnit 5, Mockito**

## ⚠️ Project Status

The project is consistently developed, so far I assume the implementation of additional functionalities. In addition, I want to create integration tests as soon as possible. Recently the main goal of this project is to constantly improve technical skills and learn about new technologies.
