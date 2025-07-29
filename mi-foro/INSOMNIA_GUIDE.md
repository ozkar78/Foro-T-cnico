# Guía de Conexión con Insomnia

## 1. Ejecutar la Aplicación
```bash
cd mi-foro
mvn spring-boot:run
```
La aplicación estará disponible en: http://localhost:8080

## 2. Endpoints Disponibles

### Autenticación

#### Registrar Usuario
- **POST** `http://localhost:8080/auth/register`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "username": "testuser",
  "password": "123456",
  "email": "test@example.com"
}
```

#### Login
- **POST** `http://localhost:8080/auth/login`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "username": "admin",
  "password": "123456"
}
```
- **Respuesta**: `{"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9..."}`

### Topics (Requieren Autenticación)

#### Listar Topics
- **GET** `http://localhost:8080/topics`
- **Headers**: `Authorization: Bearer {tu_token_aqui}`

#### Obtener Topic por ID
- **GET** `http://localhost:8080/topics/1`
- **Headers**: `Authorization: Bearer {tu_token_aqui}`

#### Crear Topic
- **POST** `http://localhost:8080/topics`
- **Headers**: 
  - `Content-Type: application/json`
  - `Authorization: Bearer {tu_token_aqui}`
- **Body**:
```json
{
  "title": "Mi primer topic",
  "message": "Este es el contenido de mi primer topic en el foro"
}
```

#### Actualizar Topic
- **PUT** `http://localhost:8080/topics/1`
- **Headers**: 
  - `Content-Type: application/json`
  - `Authorization: Bearer {tu_token_aqui}`
- **Body**:
```json
{
  "title": "Título actualizado",
  "message": "Mensaje actualizado"
}
```

#### Eliminar Topic
- **DELETE** `http://localhost:8080/topics/1`
- **Headers**: `Authorization: Bearer {tu_token_aqui}`

## 3. Pasos en Insomnia

1. **Crear Workspace**: Nuevo workspace llamado "Mi Foro API"

2. **Registrar Usuario**:
   - Nueva request POST a `/auth/register`
   - Agregar body JSON con datos del usuario

3. **Login**:
   - Nueva request POST a `/auth/login`
   - Copiar el token de la respuesta

4. **Configurar Environment**:
   - Crear environment variable `token` con el valor obtenido
   - Crear variable `base_url` con valor `http://localhost:8080`

5. **Usar Token**:
   - En requests protegidas, agregar header:
   - `Authorization: Bearer {{ _.token }}`

## 4. Documentación Swagger
Visita: http://localhost:8080/swagger-ui.html

## 5. Base de Datos H2 Console
Visita: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (vacío)

## 6. Usuarios de Prueba
- **Username**: admin, **Password**: 123456
- **Username**: user1, **Password**: 123456