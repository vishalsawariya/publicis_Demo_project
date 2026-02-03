# XYZ Movie Booking (Java / Spring Boot)

A starter implementation of a two-sided **movie ticket booking platform** for **B2C customers** and **B2B theatre partners**.

## Tech Stack
- Java 17
- Spring Boot 3 (Web, JPA, Validation, Security)
- H2 (in-memory dev database)
- OpenAPI/Swagger UI (for API exploration)

## Run locally
```bash
mvn spring-boot:run
```
Then open:
- Swagger UI: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:moviedb`)

## Seeded sample data
On startup we seed **cities (Pune, Mumbai)**, movies, theatres, screens, shows. Try the discovery and booking endpoints.

## Quick API tour
- **Public Discovery**
  - `GET /api/public/cities`
  - `GET /api/public/movies?city=Pune`
  - `GET /api/public/shows?city=Pune&movieId=1`
- **Booking (customer)**
  - `POST /api/bookings/lock` – lock seats for 5 minutes
  - `POST /api/bookings/confirm` – confirm & create booking
  - `GET /api/bookings/{id}` – fetch booking
- **Partner (theatre owner)**
  - Basic Auth users are provisioned:
    - admin / admin123 (ROLE_PARTNER)
    - user  / user123  (ROLE_USER)
  - `POST /api/partner/theatres`
  - `POST /api/partner/screens`
  - `POST /api/partner/shows`

> For production: replace H2 with Postgres/MySQL, add JWT-based auth, move seat locks to Redis, add payment gateway integration, and harden validations.