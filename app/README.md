# Zeferini - Spring Boot com Thymeleaf

Aplicação web equivalente ao projeto Angular desenvolvida com **Spring Boot 3.2.0** e **Thymeleaf** como template engine.

## Características

- ✅ Interface idêntica ao projeto Angular (navbar, sidenav, estilos)
- ✅ CRUD completo de Pessoas com integração a APIs REST
- ✅ Responsivo (mobile-first com breakpoint em 900px)
- ✅ Integração com NestJS Command API (port 3000)
- ✅ Integração com MongoDB Sync Query API (port 3001)
- ✅ Material Design com Material Symbols icons
- ✅ Confirmação de exclusão com modal
- ✅ Formulário com validação
- ✅ Docker multi-stage build

## Tecnologias

- **Java 17** - Runtime
- **Spring Boot 3.2.0** - Framework
- **Thymeleaf** - Template Engine
- **WebFlux** - HTTP Client para REST calls
- **Lombok** - Boilerplate reduction
- **Maven** - Build tool

## Estrutura de Pastas

```
front/spring/first/
├── pom.xml                          # Maven configuration
├── Dockerfile                       # Multi-stage Docker build
├── src/main/
│   ├── java/com/zeferini/
│   │   ├── ZeferiniApplication.java # Main application class
│   │   ├── config/
│   │   │   └── ApiConfig.java       # API endpoints configuration
│   │   ├── controller/
│   │   │   └── PersonController.java # MVC controller
│   │   ├── model/
│   │   │   └── Person.java          # Entity model
│   │   └── service/
│   │       └── PersonService.java   # Business logic
│   └── resources/
│       ├── application.yml          # Spring configuration
│       ├── static/css/
│       │   └── styles.css           # Global styles
│       └── templates/
│           ├── layout.html          # Base layout
│           └── persons/
│               ├── list.html        # Person list page
│               └── form.html        # Person form page
```

## Endpoints

### Web Pages
- `GET /` → Redirect to `/persons`
- `GET /persons` → List all persons
- `GET /persons/new` → Create person form
- `POST /persons` → Create person
- `GET /persons/{id}/edit` → Edit person form
- `POST /persons/{id}` → Update person
- `POST /persons/{id}/delete` → Delete person

### APIs integradas
- **Command API** (3000): `http://localhost:3000/persons`
- **Query API** (3001): `http://localhost:3001/persons`

## Configuração

### Environment Variables (Docker)
```yaml
SERVER_PORT: 8080
API_COMMAND_URL: http://nestjs-app:3000
API_QUERY_URL: http://mongo-sync:3001
```

### Local Development
```yaml
# application.yml
api:
  command:
    url: http://localhost:3000
  query:
    url: http://localhost:3001
```

## Como Executar

### Local com Maven
```bash
cd front/spring/first
mvn clean package -DskipTests
mvn spring-boot:run
# Acessa em http://localhost:8080
```

### Docker Compose
```bash
cd TesteFull
docker compose up -d --build spring
# Acessa em http://localhost:8081
```

## Features Implementadas

### Navbar
- Logo "Zeferini"
- Botão hamburger para abrir sidenav
- Sticky ao topo
- Gradient background (#3f51b5 → #2b3990)

### Sidenav
- Menu overlay fixo
- Seção "Principal" com link para Pessoas
- Seção "APIs" com links externos
- Backdrop clicável para fechar
- Smooth animations

### Lista de Pessoas
- Tabela com colunas: Nome, Email, Criado em, Ações
- Botão "Adicionar Pessoa"
- Ações: Editar, Deletar
- Empty state quando vazio
- Snackbar para feedback (sucesso/erro)
- Diálogo de confirmação para exclusão

### Formulário de Pessoa
- Campos: Nome, Email (obrigatórios)
- Validação client-side com mensagens de erro
- Botões: Cancelar, Criar/Salvar
- Redirecionamento após sucesso

### Design & Styling
- Color Scheme: Primário (#3f51b5), Danger (#c62828)
- Spacing System: 8px base unit
- Font: Space Grotesk (weights: 300-700)
- Icons: Material Symbols Rounded
- Responsive breakpoint: 900px

## CORS Configuration

O projeto foi configurado para aceitar requisições de:
- `localhost:4200` (Vue dev)
- `localhost:4201` (Angular)
- `localhost:4202` (Vue Docker)
- `localhost:3201` (React)
- `localhost:8081` (Spring Boot)
- `localhost:8000` (Load Balancer)

## Logs

```bash
# Ver logs do container
docker logs spring-thymeleaf -f

# Ver logs da aplicação
tail -f /var/log/spring/application.log
```

## Status do Projeto

✅ **Completo e Deployado**
- Todas as funcionalidades implementadas
- CRUD totalmente funcional
- Estilos idênticos ao Angular
- Integração com APIs funcionando
- Container rodando em produção

## Contatos & Roadmap

### Phase 1 (Atual) - MVP
- [x] Scaffold Spring Boot com Thymeleaf
- [x] Controllers e Services
- [x] Templates HTML
- [x] Estilos CSS
- [x] Integração com APIs
- [x] Docker e Docker Compose

### Phase 2 - Melhorias Futuras
- [ ] Validação server-side com Bean Validation
- [ ] Pagination na lista
- [ ] Search/Filter
- [ ] Exportação (CSV, PDF)
- [ ] Autenticação com Spring Security
- [ ] Testes automatizados (JUnit, Mockito)
