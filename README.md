# Order Management API

This is a simple API to manage orders. It supports the following features:

- Create, read, update, delete orders, items, and stock movements.
- Send email notifications when an order is complete.

## Requirements

- Java 8
- Spring Boot
- PostgreSQL

## Setup

1. Clone the repository
2. Update `application.properties` with your PostgreSQL credentials.
3. Update `application.properties` with your mail client credentials.
4. Build the project: `mvn clean install` (Build without tests: `mvn clean install -DskipTests`)
5. Run the project: `mvn spring-boot:run`

- Run tests: `mvn test`

## API Endpoints

### USERS
#### POST /api/users
Create a new user.

#### Request:
```json
{
  "name": "User Name",
  "email": "user@email.com"
}
```

#### PUT /api/users/{userId}
Update a user.

#### Request:
```json
{
  "name": "User Name",
  "email": "user@email.com"
}
```

#### GET /api/users
Get all users.


#### GET /api/orders/{orderId}
Get a user  by id.


#### DELETE /api/users/{userId}
Delete a user.

### ITEMS
#### POST /api/items
Create a new item.

#### Request:
```json
{
  "name": "Item Name"
}
```

#### PUT /api/items/{itemId}
Update an item.

#### Request:
```json
{
  "name": "Item Name"
}
```

#### GET /api/items
Get all items.


#### GET /api/items/{itemId}
Get an item by id.


#### DELETE /api/items/{itemId}
Delete an item.

### ORDERS
#### POST /api/orders
Create a new order.

#### Request:
```json
{
  "creationDate": "2024-12-19T23:40:00",
  "item": {
    "name": "Item Name"
  },
  "quantity": 15,
  "user": {
    "name": "User Name",
    "email": "user@email.com"
  },
  "isComplete": true
}
```

#### PUT /api/orders/{orderId}
Update an order.

```json
{
  "creationDate": "2024-12-19T23:40:00",
  "item": {
    "name": "Item Name"
  },
  "quantity": 15,
  "user": {
    "name": "User Name",
    "email": "user@email.com"
  },
  "isComplete": true
}
```

#### GET /api/orders
Get all orders.

#### GET /api/orders/{orderId}
Get an order by id.

#### DELETE /api/orders/{orderId}
Delete an order.

### STOCK MOVEMENT
#### POST /api/stock-movements
Create a new stock movement.

#### Request:
```json
{
  "creationDate": "2024-12-19T23:40:00",
  "item": {
    "name": "Item Name"
  },
  "quantity": 15
}
```

#### PUT /api/stock-movements/{stockMovementId}
Update an stock movement.

#### Request:
```json
{
  "creationDate": "2024-12-19T23:40:00",
  "item": {
    "name": "Item Name"
  },
  "quantity": 15
}
```

#### GET /api/stock-movements
Get all stock movements.

#### GET /api/stock-movements/{stockMovementId}
Get a stock movement by id.

#### DELETE /api/stock-movements/{stockMovementId}
Delete a stock movement.
