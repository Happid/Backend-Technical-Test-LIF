## Database Schema
### 1. Table `users`
Menyimpan data pengguna aplikasi.

| Column   | Type         | Constraints                 | Description        |
|----------|--------------|-----------------------------|--------------------|
| id       | BIGINT       | PRIMARY KEY, AUTO_INCREMENT | ID unique user     |
| email    | VARCHAR(255) | NOT NULL, UNIQUE            | Email user         |
| username | VARCHAR(255) | NOT NULL                    | Username           |
| password | VARCHAR(255) | NOT NULL                    | Password with hash |


### 2. Table `todo`
Menyimpan data pengguna aplikasi.

| Column      | Type         | Constraints                      | Description                  |
|-------------|--------------|----------------------------------|------------------------------|
| id          | BIGINT       | PRIMARY KEY, AUTO_INCREMENT      | ID unique todo               |
| completed   | BOOLEAN      | NOT NULL                         | Status todo                  |
| description | VARCHAR(255) |                                  | Description todo             |
| title       | VARCHAR(255) | NOT NULL                         | Title todo                   |
| user_id     | BIGINT       | FOREIGN KEY references users(id) | ID user who created the todo |


### 3. Relationships
- **user** to **todo**: One-to-Many  
  Every user can have many todo task.

---

## Documentation API Usage
- api include: `login`, `register`, `todo (CRUD)`
- if you run in local
  `{root_url}` : `http://localhost:8080`

- if you run in live demo (railway)
  `{root_url}` : `http://localhost:8080`

## 1. Register User
**Endpoint:** `POST {root_url}/api/auth/register`  
**Description:** Create new account.

- Request Body
```json
{
  "username": "My Admin",
  "email": "admin@mail.com",
  "password": "1234"
}
```
- Response Body
```json
{
  "success": true,
  "message": "User registered successfully",
  "data": null
}
```

## 2. Login Account
**Endpoint:** `POST {root_url}/api/auth/login`  
**Description:** Login to your account to take token (JWT).

- Request Body
```json
{
  "email": "admin@mail.com",
  "password": "1234"
}
```
- Response Body
```json
{
    "success": true,
    "message": "Login successful",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJhZG1pbjFAbWFpbC5jb20iLCJpYXQiOjE3NjY1NTM2ODEsImV4cCI6MTc2NzE1ODQ4MX0.zJ2xfRR2-EVLhSdGG_StcvP5r-Q0cLr8hYl80i11KRo",
        "username": "My Admin1",
        "email": "admin1@mail.com"
    }
}
```

## 3. Todo Get All
**Endpoint:** `GET {root_url}/api/todos`  
**Description:** Get all the todo list by automatically using id user.
- AUTH Need: Auth Type `Bearer Token` + `token (in response login if successfully logedin)`
  example: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJhZG1pbjFAbWFpbC5jb20iLCJpYXQiOjE3NjY1NTM2ODEsImV4cCI6MTc2NzE1ODQ4MX0.zJ2xfRR2-EVLhSdGG_StcvP5r-Q0cLr8hYl80i11KRo`

- Response Body
```json
{
    "success": true,
    "message": "Todo list retrieved",
    "data": [
        {
            "id": 1,
            "title": "Judul 1",
            "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It h",
            "completed": false
        }
    ]
}
```

## 4. Todo Create
**Endpoint:** `POST {root_url}/api/todos`  
**Description:** Add new Todo Task.
- AUTH Need: Auth Type `Bearer Token` + `token (in response login if successfully logedin)`
  example: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJhZG1pbjFAbWFpbC5jb20iLCJpYXQiOjE3NjY1NTM2ODEsImV4cCI6MTc2NzE1ODQ4MX0.zJ2xfRR2-EVLhSdGG_StcvP5r-Q0cLr8hYl80i11KRo`

- Request Body
```json
{
  "title": "Title No-1",
  "description": "This my description..."
}
```

- Response Body
```json
{
  "success": true,
  "message": "Todo created",
  "data": {
    "id": 2,
    "title": "Title No-",
    "description": "This my description...",
    "completed": false
  }
}
```

## 5. Todo Update
**Endpoint:** `PUT {root_url}/api/todos/{id_todo}`  
**Description:** Update information Todo Task.
- AUTH Need: Auth Type `Bearer Token` + `token (in response login if successfully logedin)`
  example: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJhZG1pbjFAbWFpbC5jb20iLCJpYXQiOjE3NjY1NTM2ODEsImV4cCI6MTc2NzE1ODQ4MX0.zJ2xfRR2-EVLhSdGG_StcvP5r-Q0cLr8hYl80i11KRo`

- Request Body
```json
{
  "title": "Title No-1 Updated",
  "description": "This my description updated...",
  "completed": true
}
```

- Response Body
```json
{
  "success": true,
  "message": "Todo updated",
  "data": {
    "id": 2,
    "title": "Title No-1 Updated",
    "description": "This my description updated...",
    "completed": true
  }
}
```

## 6. Todo Delete
**Endpoint:** `DELETE {root_url}/api/todos/{id_todo}`  
**Description:** Add new Todo List.
- AUTH Need: Auth Type `Bearer Token` + `token (in response login if successfully logedin)`
  example: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJhZG1pbjFAbWFpbC5jb20iLCJpYXQiOjE3NjY1NTM2ODEsImV4cCI6MTc2NzE1ODQ4MX0.zJ2xfRR2-EVLhSdGG_StcvP5r-Q0cLr8hYl80i11KRo`

- Response Body
```json
{
  "success": true,
  "message": "Todo deleted",
  "data": null
}
```