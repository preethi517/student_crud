# Student CRUD

A full-stack student management app with create, read, update, delete, search, and pagination.

## Live links

- **Frontend (Vercel):** https://student-crud-woad.vercel.app
- **Backend API (Render):** https://spring-backend-rp7a.onrender.com/api/students
- **Source code:** https://github.com/preethi517/student_crud

> The backend runs on Render's free tier and sleeps after about 15 minutes of inactivity. The first request after a break can take 1 to 2 minutes.

## Tech stack

| Layer | Technology |
|---|---|
| Frontend | Angular |
| Backend | Spring Boot 3.2.5, Java 17, Spring Data JPA, Spring Security |
| Database | PostgreSQL |
| Hosting | Vercel (frontend), Render (backend and database) |
| Containers | Docker, Docker Compose |

## Project structure

```
student_crud/
├── src/                 # Spring Boot backend
├── pom.xml
├── Dockerfile           # Backend image
├── docker-compose.yml   # Local setup (backend, frontend)
└── frontend/            # Angular app
    ├── Dockerfile
    └── nginx.conf
```

## API endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/students?search=&page=0&size=5` | List students (paged, searchable) |
| POST | `/api/students` | Create a student |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

## Run locally

### Backend

```bash
./mvnw spring-boot:run
```

Requires JDK 17 and PostgreSQL with a database named `preeti` (see `application.properties`).

### Frontend

```bash
cd frontend
npm install
npm start
```

Open http://localhost:4200.

### With Docker

```bash
docker compose up --build
```

- Frontend: http://localhost:4200
- Backend: http://localhost:8081

## Configuration

The backend reads these environment variables:

| Variable | Purpose |
|---|---|
| `SPRING_DATASOURCE_URL` | JDBC URL, e.g. `jdbc:postgresql://host:5432/dbname` |
| `SPRING_DATASOURCE_USERNAME` | Database user |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `CORS_ALLOWED_ORIGINS` | Comma-separated allowed frontend origins |
| `PORT` | Server port (set by Render) |

## Deployment

- **Frontend:** Vercel, Root Directory `frontend`, Output Directory `dist/frontend/browser`
- **Backend:** Render (Docker), using the root `Dockerfile`