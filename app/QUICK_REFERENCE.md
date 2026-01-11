# Spring Boot + Thymeleaf - Quick Reference

## 🚀 Acesso Rápido

```bash
# Acessar localmente
http://localhost:8081

# Acessar em dev (Maven)
http://localhost:8080

# APIs conectadas
- Command: http://localhost:3000
- Query: http://localhost:3001
```

## 🐳 Docker Commands

```bash
# Build
docker compose build spring

# Run
docker compose up -d spring

# Logs
docker logs -f spring-thymeleaf

# Stop
docker compose stop spring

# Remove
docker compose down --remove-orphans
```

## 📁 Projeto Structure

```
front/spring/first/
├── pom.xml                 # Maven config
├── Dockerfile              # Multi-stage build
├── src/main/java/com/zeferini/
│   ├── ZeferiniApplication.java
│   ├── config/ApiConfig.java
│   ├── controller/PersonController.java
│   ├── model/Person.java
│   └── service/PersonService.java
└── src/main/resources/
    ├── application.yml
    ├── static/css/styles.css
    └── templates/
        ├── layout.html
        └── persons/
            ├── list.html
            └── form.html
```

## 🔌 Controllers & Routes

| Method | URL | Action |
|--------|-----|--------|
| GET | / | Redirect to /persons |
| GET | /persons | List all |
| GET | /persons/new | Create form |
| POST | /persons | Create |
| GET | /persons/{id}/edit | Edit form |
| POST | /persons/{id} | Update |
| POST | /persons/{id}/delete | Delete |

## 🎨 CSS Classes

```css
.app-shell            /* Main container */
.app-shell__navbar    /* Top navigation */
.app-shell__drawer    /* Sidenav drawer */
.app-shell__backdrop  /* Overlay background */
.app-shell__content   /* Main content area */

.btn                  /* Button base */
.btn-primary          /* Primary action */
.btn-secondary        /* Secondary action */
.btn-danger           /* Delete action */
.btn-icon             /* Icon button */

.card                 /* Card container */
.table                /* Data table */
.form                 /* Form container */
.form-group           /* Form group */

.snackbar             /* Notification */
.dialog               /* Modal dialog */
.empty-state          /* Empty state */
```

## 🧩 Java Classes

### PersonController
```java
GET  /             → index()
GET  /persons      → listPersons()
GET  /persons/new  → newPerson()
POST /persons      → createPerson()
GET  /persons/{id}/edit → editPerson()
POST /persons/{id} → updatePerson()
POST /persons/{id}/delete → deletePerson()
```

### PersonService
```java
List<Person> getAllPersons()
Person getPersonById(String id)
Person createPerson(Person person)
Person updatePerson(String id, Person person)
void deletePerson(String id)
```

### Person Model
```java
String id
String name
String email
String createdAt
String updatedAt
```

## ⚙️ Configuration

### application.yml
```yaml
spring:
  application:
    name: zeferini-thymeleaf
  thymeleaf:
    cache: true        # Production: true, Dev: false
    prefix: classpath:/templates/
    suffix: .html

server:
  port: ${SERVER_PORT:8080}

api:
  command:
    url: ${API_COMMAND_URL:http://localhost:3000}
  query:
    url: ${API_QUERY_URL:http://localhost:3001}
```

## 🔗 API Calls (WebFlux)

```java
// GET request
queryClient.get()
  .uri("/persons")
  .retrieve()
  .bodyToMono(String.class)
  .block();

// POST request
commandClient.post()
  .uri("/persons")
  .bodyValue(person)
  .retrieve()
  .bodyToMono(Person.class)
  .block();

// PATCH request
commandClient.patch()
  .uri("/persons/{id}", id)
  .bodyValue(person)
  .retrieve()
  .bodyToMono(Person.class)
  .block();

// DELETE request
commandClient.delete()
  .uri("/persons/{id}", id)
  .retrieve()
  .toBodilessEntity()
  .block();
```

## 🎯 JavaScript Functions

```javascript
// Menu toggle
function toggleDrawer()
function closeDrawer()

// Form validation
function validateForm()
function isValidEmail(email)
function showError(elementId, message)
function clearError(elementId)

// Delete dialog
function showDeleteConfirm(button)
function closeDeleteDialog()
```

## 📦 Maven Commands

```bash
# Build
mvn clean package -DskipTests

# Run
mvn spring-boot:run

# Install dependencies
mvn install

# Check updates
mvn versions:display-dependency-updates
```

## 🔍 Troubleshooting

| Problem | Solution |
|---------|----------|
| Port 8080 in use | Change SERVER_PORT or kill process |
| API connection error | Check if 3000/3001 are running |
| CORS error | Add origin to CORS whitelist |
| Template not found | Check file path in /templates |
| Static CSS not loading | Check /static folder |

## 📊 Project Stack

- **Java 17** - Runtime
- **Spring Boot 3.2.0** - Framework
- **Thymeleaf 3.1.2** - Template engine
- **WebFlux** - Async HTTP client
- **Lombok** - Boilerplate reduction
- **Maven 3.9.4** - Build tool
- **Docker** - Containerization
- **Nginx** - (Future: reverse proxy)

## 🔐 CORS Configuration

Whitelist in `main.ts`:
```typescript
origin: [
  'http://localhost:4200',   // Vue dev
  'http://localhost:4201',   // Angular
  'http://localhost:4202',   // Vue Docker
  'http://localhost:3201',   // React
  'http://localhost:8081',   // Spring Boot
  'http://localhost:8000',   // NPM balancer
]
```

## 📱 Responsive Breakpoints

```css
@media (max-width: 900px) {
  /* Mobile adjustments */
  /* Navbar padding */
  /* Flex layout changes */
  /* Font size reductions */
}
```

## ✅ Checklist

- [x] Spring Boot scaffold
- [x] Thymeleaf templates
- [x] Controllers & services
- [x] CSS styling
- [x] API integration
- [x] Form validation
- [x] CRUD operations
- [x] Docker setup
- [x] CORS configuration
- [x] Container running
- [x] Tests passing

---

**Quick Start:**
```bash
docker compose up -d spring
open http://localhost:8081
```
