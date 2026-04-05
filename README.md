# 💳 Payment Processing Service

Spring Boot REST API application that simulates a simple payment processing system.  
It supports creating payments, checking status, confirming, canceling, and retrieving client payments.

---

# 🚀 Features

- Create payment
- Get payment by ID
- Confirm payment
- Cancel payment
- Get all payments of a client
- Validation of input data
- Proper error handling (404, 400)
- Swagger/OpenAPI documentation

---

# 📡 REST API

## 1. Create Payment

### `POST /payments`

Creates a new payment with default status `PENDING`.

### Request:
```json
{
  "amount": 1000,
  "currency": "KZT",
  "description": "Оплата заказа №123",
  "clientId": "12345"
}
