# 🚀 Guía de Inicio Rápido - Foro Técnico

## ⚡ Ejecución en 5 minutos

### 1️⃣ Prerrequisitos
- ☕ Java 21+
- 🗄️ MySQL 8.0+ ejecutándose
- 🔧 Git instalado

### 2️⃣ Clonar y configurar
```bash
git clone https://github.com/tu-usuario/proyecto-foro.git
cd proyecto-foro
```

### 3️⃣ Configurar base de datos
```sql
-- En MySQL Workbench o consola MySQL
CREATE DATABASE foro_db;
```

### 4️⃣ Ejecutar aplicación
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac  
./mvnw spring-boot:run
```

### 5️⃣ Abrir frontend
Navega a la carpeta del proyecto y abre:
```
frontend/login.html
```

### 6️⃣ Iniciar sesión
**Credenciales por defecto:**
- 👤 **Usuario**: `admin`
- 🔑 **Contraseña**: `123456`

---

## 🔗 Enlaces Importantes

| Servicio | URL | Descripción |
|----------|-----|-------------|
| 🎨 **Login** | `file:///[ruta]/frontend/login.html` | Página de inicio |
| 🏠 **Dashboard** | `file:///[ruta]/frontend/dashboard.html` | Lista de topics |
| 📝 **Topic Detail** | `file:///[ruta]/frontend/topic-detail.html` | Detalles y comentarios |
| 🔧 **API** | http://localhost:8081 | Backend REST API |
| 📚 **Swagger** | http://localhost:8081/swagger-ui.html | Documentación API |
| 🗄️ **Base de datos** | localhost:3306/foro_db | MySQL Database |

---

## 🎯 Flujo de Prueba

1. **Login** → Usa `admin` / `123456`
2. **Crear Topic** → Selecciona categoría (Java, Spring, etc.)
3. **Ver Topics** → Lista con vistas, estados y fechas
4. **Hacer clic en título** → Ve detalles del topic
5. **Agregar comentarios** → Interactúa con otros usuarios
6. **Explorar API** → Swagger UI para testing
7. **Base de datos** → Verifica datos en MySQL

---

## 🆘 Solución de Problemas

### Puerto 8081 ocupado
```bash
# Windows
netstat -ano | findstr :8081
taskkill /PID [numero_proceso] /F

# Linux/Mac
lsof -ti:8081 | xargs kill -9
```

### Error de conexión MySQL
- Verifica que MySQL esté ejecutándose
- Confirma credenciales en `application.properties`
- Asegúrate de que la base de datos `foro_db` exista

### CORS Error en frontend
- Usa la aplicación desde `file://` directamente
- O ejecuta un servidor local: `python -m http.server 3000`

---

## ✅ Verificación de Funcionamiento

Si todo funciona correctamente deberías ver:

- ✅ Aplicación iniciada en puerto 8081
- ✅ Login exitoso con credenciales
- ✅ Dashboard con formulario de creación
- ✅ Lista de topics con enlaces clickeables
- ✅ Página de detalle con comentarios
- ✅ Sistema de comentarios funcional
- ✅ Swagger UI accesible
- ✅ Base de datos con 3 tablas: users, topics, comments

¡Listo para usar! 🎉