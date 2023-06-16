# Internet shop mockup

## Table of Contents

- [Introduction](#introduction)
- [Requirements](#requirements)
- [Installation](#installation)
- [Installing and Running the App from Docker](#installing-and-running-the-app-from-docker)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [H2 Database](#h2-database)
- [Contact Information](#contact-information)

## Introduction

The Project Name is a simple mockup of the backend web shop application. It aims to mock the behaviour if the shop,
especially receiving order and processing payment. This documentation will guide you through the
installation and usage of the Internet Shop Mockup.

## Requirements

Initial requirements received from the business team:
Napisać prostą aplikacje backendową w języku Java która będzie symulowała pewne zachowania sklepu internetowego.
Aplikacja powinna:

1. Przyjąć przez usługi REST koszyk produktów z frontendu (nie trzeba pisać frontu - załóżmy, że jest)
2. Utworzyć transakcje płatniczą i zapisać ją w bazie danych (może byc w pamięci np h2)
3. Wysłać transakcje za koszyk produktów do systemu płatniczego (nie trzeba go tworzyć, może być mock lub losowy adres)
4. Powiadomić klient o procesowaniu jego transakcji (zakładamy, że klient podał adres email przy tworzeniu koszyka
   produktów)
5. Odebrać status płatności z systemu płatniczego po przeprocesowaniu transakcji
6. Powiadomić klienta przez email, że jego płatnosć zakończyła się sukcesem i towar zostanie do niego wysłany

Uproszenia:

- wysyłka email nie musi polegać na rzeczywistym wysłaniu wiadomości, wystarczy mock lub klasa która potencjalnie wywoła
  taką akcję

Mile widziane technologie (niewymagane):

- java w wersji 8+
- framework Spring boot lub Java EE
- baza danych h2 lub podobna

## Installation

Before proceeding with the installation, make sure you have the following prerequisites installed on your system:

- Java Development Kit (JDK) 17 or later
- Maven

### Steps

1. Clone the repository or download the source code for the Internet Shop Mockup application.

2. Open a terminal or command prompt and navigate to the project's root directory.

3. Build the project using Maven by running the following command:

```
mvn clean install
```

4. Once the build is successful, navigate to the target directory:

```
cd target
```

5. Run the JAR file using the following command:

```
java -jar internet-shop-mockup-0.0.1-SNAPSHOT.jar
```

6. The app sets one endpoint at address

```
http://localhost:8080/cart
```

Note: Make sure port 8080 is not already in use by another application.

Congratulations! You have successfully installed and started the Internet Shop Mockup application. You can now explore
the various features and functionalities provided by the application.

## Installing and Running the App from Docker

If you prefer to run the Internet Shop Mockup application using Docker, follow the steps below:

### Prerequisites

Before proceeding with the Docker installation, ensure that you have Docker installed and configured on your system. You
can download and install Docker from the official Docker
website: [https://www.docker.com/get-started](https://www.docker.com/get-started)

### Steps

1. Open a terminal or command prompt.

2. Run the Docker image by running the following command:

```
docker run -p 8080:8080 internet-shop-mockup
```

This command starts a Docker container from the `internet-shop-mockup` image and maps port 8080 of the container to port
8080 of the host machine.

## Usage

To interact with the Internet Shop Mockup application, you can use the provided REST API endpoints. Below, you'll find
an example of how to use one of the endpoints.

### Sending a Cart Request

To send a cart request to the application, you need to make a POST request to the `/cart` endpoint with a JSON payload
containing the cart details.

The `CartRequest` object represents the cart details and includes the following properties:

```json
{
  "customerId": "1",
  "products": {
    "1": 2,
    "2": 3
  },
  "payment": {
    "paymentMethod": "creditCard"
  }
}
```

## API Documentation

The Internet Shop Mockup application provides a set of RESTful API endpoints for interacting with the system. This
section provides an overview of the available endpoints and their functionality.

### Endpoint 1: Cart Request

**Endpoint:** `/cart`
**HTTP Method:** POST
**Description:** Submit a cart request to the application.

**Request Body:**
The request body should contain a JSON payload representing the cart request. The payload should include the following
properties:

- `customerId` (String): A unique identifier for the customer.
- `products` (Map<String, Integer>): A map of product names and quantities.
- `payment` (PaymentRequest): An object representing the payment method.

**Response:**

- 200 OK: If the cart request was processed successfully.
- 500 Internal Server Error: If an error occurred while processing the cart request.

### Example Usage

To make a cart request, you can send a POST request to the `/cart` endpoint with a JSON payload in the request body.
Here's an example using cURL:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "customerId": "1",
  "products": {
    "1": 2,
    "2": 3
  },
  "payment": {
    "paymentMethod": "creditCard"
  }
}' http://localhost:8080/cart
```

## H2 Database

The Internet Shop Mockup application utilizes the H2 database, an in-memory database that provides a lightweight and
fast storage solution. This section provides an overview of the H2 database configuration and its usage within the
application.

### Configuration

The H2 database configuration can be found in the `application.properties` file.

## Contact Information

If you have any questions, feedback, or need assistance with the Internet Shop Mockup application, we are here to help.
Please feel free to reach out to us using the contact information provided below:

- **Email**: `wkuziow @ gmail.com`

Feel free to reach out to me with any inquiries or feedback you may have.