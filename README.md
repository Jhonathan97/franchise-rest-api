# Franquicias API - NEQUI 🚀

API REST para gestionar **franquicias**, **sucursales** y **productos** con stock, construida con **Spring Boot 3**, **Java 21** y **MySQL**.

---

## 🛠️ Endpoints principales

### Franquicias
- `POST /api/v1/franchises` → crear franquicia
- `GET /api/v1/franchises?q=...&page=0&size=20&sort=name,asc` → listar franquicias (con búsqueda y paginación)
- `PATCH /api/v1/franchises/{id}` → renombrar franquicia

### Sucursales
- `POST /api/v1/franchises/{franchiseId}/branches` → crear sucursal
- `GET /api/v1/franchises/{franchiseId}/branches?page=0&size=20&sort=name,asc` → listar sucursales de una franquicia
- `PATCH /api/v1/franchises/{fid}/branches/{bid}` → renombrar sucursal

### Productos
- `POST /api/v1/franchises/{fid}/branches/{bid}/products` → crear producto
- `GET /api/v1/franchises/{fid}/branches/{bid}/products?page=0&size=20&sort=name,asc` → listar productos en sucursal
- `PATCH /api/v1/franchises/{fid}/branches/{bid}/products/{pid}/stock` → actualizar stock
- `PATCH /api/v1/franchises/{fid}/branches/{bid}/products/{pid}/name` → renombrar producto
- `DELETE /api/v1/franchises/{fid}/branches/{bid}/products/{pid}` → eliminar producto

### Reportes
- `GET /api/v1/franchises/{fid}/branches/top-stock-product` → producto top por sucursal

### list
- `GET /api/v1/franchises?filter=...&page=0&size=20&sort=name,asc` → List franchises  paginated
- `GET /api/v1/franchises/{franchiseId}/branches?page=0&size=20&sort=name,asc` → List Branch by franciseId
- `GET /api/v1/franchises/{franchiseId}/branches/{branchId}/products?page=0&size=20&sort=name,asc` → List produc  by branchId
### Health
- `GET /health` → Health check de la API

---

## 🚀 Cómo correr localmente

### Pre-requisitos
- JDK 21
- Maven 3.9+
- Docker y docker-compose

### 1. Levantar MySQL local con Docker
```bash
docker compose -f docker-compose.local.yml up -d
```

### 2. Ejecutar la aplicación en modo local
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Swagger UI: [https://franquicias-api-latest-5ka4.onrender.com/swagger-ui/index.html](https://franquicias-api-latest-5ka4.onrender.com/swagger-ui/index.html)

---

## 🐳 Docker
- Image docker: https://hub.docker.com/r/jhonathan0297/franquicias-api
### Build imagen
```bash
docker build -t franquicias-api:latest .
```

### Correr apuntando a RDS (prod)
```bash
docker run --rm -p 8080:8080   -e SPRING_PROFILES_ACTIVE=prod   -e SPRING_DATASOURCE_URL="jdbc:mysql://franquicias-mysql.cc3ageyeowas.us-east-1.rds.amazonaws.com:3306/franchises?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"   -e SPRING_DATASOURCE_USERNAME=franchise   -e SPRING_DATASOURCE_PASSWORD=franchise   franquicias-api:latest
```

### URL  API desplegada en la nube

- https://franquicias-api-latest-5ka4.onrender.com/
- https://franquicias-api-latest-5ka4.onrender.com/swagger-ui/index.html



---

## ☁️ Despliegue en la nube

1. **RDS MySQL** en AWS (t3.micro, MySQL 8)
2. Imagen Docker publicada en Docker Hub
3. Despliegue en **Elastic Beanstalk** o **Render**

Ejemplo Elastic Beanstalk:
```bash
eb init -p docker franquicias-api --region us-east-1
eb create franquicias-api-env
eb setenv SPRING_PROFILES_ACTIVE=prod \
SPRING_DATASOURCE_URL="jdbc:mysql://<franquicias-mysql.cc3ageyeowas.us-east-1.rds.amazonaws.com>:3306/franchises" \
SPRING_DATASOURCE_USERNAME=franchise \
SPRING_DATASOURCE_PASSWORD=franchise
eb deploy
```

---

## 🧰 Infraestructura como código

Directorio `/iac/terraform` con definición mínima para provisionar **RDS MySQL**:
- `provider.tf`
- `main.tf`
- `variables.tf`
- `outputs.tf`

Uso:
```bash
cd iac/terraform
terraform init
terraform plan
terraform apply
```

---

## 📖 Notas de entrega

- Repositorio público en GitHub
- Flujo de ramas con PRs
- Documentación en este README.md
