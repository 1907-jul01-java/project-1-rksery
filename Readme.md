# Web-Based Art Gallery
A Mavenized Java 8 web application connected to a PostgreSQL database.

### Tools & APIs
- [x] Java SE 8
- [x] Maven 3+
- [x] JDBC 4+
- [x] PostgreSQL 9+
- [x] Servlet 2.5+
- [x] Tomcat 7+
- [x] HTML/JS/CSS
- [x] AJAX
- [] JUnit 4
- [] log4j 
- [] Optional:
    - [x] Docker, Docker-Compose
    - [] Angular 8
    - [] Bootstrap
    - [] Remote hosting (AWS EC2/RDS)
    - [] Jenkins CI/Travis CI
    - [] Mockito

### Architecture
- [] Anemic/DDD package & class structure
- [] Design Patterns:
    - [] Dependency Injection
    - [] Data Access Object
    - [] Business Delegate
    - [] Model-View-Controller
    - [] Front Controller
- [] SQL Normalization
- [] PL/pgSQL
- [] Optional:
    - [] Single Page Application

### Functionality
- [x] CRUD - Create, Read, Update, Delete
- [x] Web App dashboard interface
- [] Asynchronous interface updates
- [x] Login - Authentication & Authorization
- [x] Database persistance
- [x] Session management

### Presentation
- [] Prepare a demonstration of functionality requirements through a browser
- [] Prepare visual aides (slides) introducing the project requirements and features

## Example: Expense Reimbursement System
- An Artist...
    - [x] can login
    - [x] can view the Artist Homepage
    - [x] can logout
    - [x] can submit a display request
    - [x] can upload an image of his/her artwork as part of the display request
    - [x] can view their pending display requests
    - [x] can view their resolved display requests
    - [] can view their information
    - [] can update their information
    - [] receives an email when one of their display requests is resolved (optional)

- A Curator...
    - [x] can login
    - [x] can view the Curator Homepage
    - [x] can logout
    - [x] can approve/deny pending display requests
    - [x] can view all pending requests from all artists
    - [x] can view images of the artworks from reimbursement requests
    - [] can view all resolved requests from all artists and see which curator resolved it
    - [] can view all artists
    - [] can view display requests from a single artist 