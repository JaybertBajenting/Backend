# Question Management API

This repository contains a backend API for managing questions. The API supports creating, retrieving, updating, and deleting questions. It also provides structured JSON retrieval for integration with external systems such as Unity.

---

## Features
- **CRUD Operations**: Create, Read, Update, and Delete questions.
- **JSON Output**: Retrieve structured question data for Unity or other integrations.

---

## Technologies Used
- **Backend**: Java Spring Boot
- **Database**: ( PostgreSQL)
- **REST API**: Endpoints for managing questions

---

## Getting Started

### Prerequisites
- **Java 17+**: Ensure Java is installed on your system.
- **Database**: (PostgreSQL).
- **Postman**: Optional for testing API endpoints.

### Installation

#### Backend
1. Clone the repository:
   ```bash
   git clone https://github.com/JaybertBajenting/Backend.git
   cd backend
   ```
2. Install dependencies:
   ```bash
   ./mvnw clean install
   ```
3. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://dpg-ctkgl8rqf0us739ht1jg-a.singapore-postgres.render.com/backend_yy6c
   spring.datasource.username=backend_yy6c_user
   spring.datasource.password=ovrbmTBXAOOSacr4UeFAFCS8opvqOHLH


   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## API Endpoints

### Add a Question
**POST** `/api/addQuestion/`
- **Description**: Add a new question with options, tags, steps, and an optional image.
- **Request Body** (form-data):
  | Key            | Type    | Description                                |
  |----------------|---------|--------------------------------------------|
  | question       | String  | The question text                         |
  | solution       | String  | The solution description                  |
  | correctAnswer  | String  | The correct answer                        |
  | options        | Text    | JSON array of options (e.g., ["A", "B"]) |
  | tags           | Text    | JSON array of tags (e.g., ["MATH"])      |
  | difficulty     | String  | The difficulty level (e.g., EASY)         |
  | steps          | Text    | JSON array of steps                       |
  | image          | File    | An optional image file                    |

- **Example Request**:
  ```json
  {
    "question": "What is 2 + 2?",
    "solution": "Add the numbers together.",
    "correctAnswer": "4",
    "options": ["2", "3", "4", "5"],
    "tags": ["MATH"],
    "difficulty": "EASY",
    "steps": [
      { "title": "Step 1", "result": "Understand addition", "imageUrl": "" }
    ]
  }
  ```

### Get All Questions
**GET** `/api/getAllQuestion`
- **Description**: Retrieve all questions.
- **Response**: A list of all questions.

### Get a Question by ID
**GET** `/api/getQuestionById/{id}`
- **Description**: Retrieve a specific question by ID.
- **Path Parameter**: `id` (Question ID)

### Update a Question
**PUT** `/api/updateQuestionById/{id}`
- **Description**: Update an existing question.
- **Path Parameter**: `id` (Question ID)
- **Request Body** (JSON):
  Similar to `Add a Question`.

### Delete a Question
**DELETE** `/api/deleteQuestionById/{id}`
- **Description**: Delete a specific question by ID.

### Retrieve Questions for Unity
**GET** `/api/questions/json`
- **Description**: Retrieve questions in a structured JSON format suitable for Unity.

---

## Using Postman to Test the API

### Testing the `addQuestion` Endpoint
1. **Create a new request**:
    - Method: **POST**
    - URL: `http://localhost:8080/api/addQuestion/`

2. **Set Body to `form-data`**:
    - Add fields based on the `QuestionRequestDTO` structure:
      | Key            | Type  | Example Value                          |
      |----------------|-------|----------------------------------------|
      | question       | Text  | "What is 2 + 2?"                      |
      | solution       | Text  | "Add the numbers together."           |
      | correctAnswer  | Text  | "4"                                   |
      | options        | Text  | ["2", "3", "4", "5"]                |
      | tags           | Text  | ["MATH"]                             |
      | difficulty     | Text  | EASY                                  |
      | steps          | Text  | [{"title":"Step 1","result":"..."}] |
      | image          | File  | (Upload an image file, e.g., `img.jpg`) |

3. **Send the Request**:
    - Click **Send**.
    - Check the response for confirmation (e.g., "Added").

---




## Deployment
If deployed, include the live URL here:
- **Live Demo**: "https://hammerhead-app-cnoce.ondigitalocean.app"
---

## License
This project is licensed under the MIT License. See `LICENSE` for more details.

---

For further queries, contact [jaybertbajenting99@gmail.com].

