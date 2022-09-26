## Architecture
### Frontend
The frontend is located underneath `/frontend` and is React with Typescript and TailwindCSS.

### Backend
#### API
The API is located underneath `/backend/api` and is a Spring Boot application using Hibernate.

#### Database
The database is located underneath `/backend/database` and is running Postgres 13. Alongside it is flyway located at `backend/database/sql` which runs our database migrations. If you would like to add a new database migration increment the version number by 1, append *two* underscores and give it a camel case name. E.g. `v2__newColumn.sql`.

### Docker
All of the infrastructure is containerized and run inside of docker containers, orchestrated by docker compose. *If you make changes to any components, running `docker-compose up --build -d` will restart and rebuild any needed containers.* If you have your local docker permissions set up correctly, the frontend is mounted into its container and should rebuild automatically upon change.
