
# ShopV8

This is a restaurant site written in Spring Boot which allows you to add new positions in your menu, and users to place orders.

## Technologies

* Java
* Maven
* Spring
* H2 database
* HTML
* CSS
* Thymeleaf

## Instalation

1. Clone the repository

```
git clone https://github.com/JanRuczynski/ShopV8.git
```

2. Build the project

```
mvn clean install
```

3. Start the application

```
mvn spring-boot:run
```

4. Type localhost:8019/admin/login in your web browser

## Usage

This application can serve as a website for a restaurant. From the client side, you can search through various categories, then you can preview your cart, manage it, and finally place an order.
From the administrator side, you can create categories and food, update and delete them, as well as view all the placed orders. In manage orders tab, you can see all details of every order.
Unfortunately, due to the way application saves food and categories photos in database, it is currently not possible to upload photos bigger than approx. 70 KB.

## Preview
![1](https://github.com/JanRuczynski/ShopV8/assets/100945601/8c06d2d6-1af3-4a17-8d37-582c227c05e1)
![2](https://github.com/JanRuczynski/ShopV8/assets/100945601/84533a45-c5f7-4478-a912-b5df09ae67d6)
![3](https://github.com/JanRuczynski/ShopV8/assets/100945601/cc795fb0-69da-4e80-9340-37b2d99d9b77)
![4](https://github.com/JanRuczynski/ShopV8/assets/100945601/a9178054-b5ed-4574-95c6-ed5c9c3241ea)
![5](https://github.com/JanRuczynski/ShopV8/assets/100945601/f395eb94-583e-4046-9965-2935f41c95ae)
![6](https://github.com/JanRuczynski/ShopV8/assets/100945601/776bd959-881c-40ee-bdd7-ab0510228ccb)
![7](https://github.com/JanRuczynski/ShopV8/assets/100945601/4d881f23-084e-4dec-9abb-f99f6510dace)
