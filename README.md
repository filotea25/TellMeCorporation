# 🚀 TellMeApp | Aplicación Full-Stack con API REST

<p align="center">
  <strong>Proyecto Full-Stack desarrollado con Vue 3 + Node.js + Express</strong><br>
  Comunicación real cliente-servidor mediante arquitectura REST
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Vue-3-42b883?style=for-the-badge&logo=vue.js&logoColor=white"/>
  <img src="https://img.shields.io/badge/Node.js-Backend-339933?style=for-the-badge&logo=node.js&logoColor=white"/>
  <img src="https://img.shields.io/badge/Express-REST_API-black?style=for-the-badge&logo=express&logoColor=white"/>
  <img src="https://img.shields.io/badge/Estado-FullStack-blue?style=for-the-badge"/>
</p>

---

## 🌍 Sobre el Proyecto

**TellMeApp** es una aplicación web que simula un sistema interno de gestión empresarial.

Permite:

- 📩 Crear sugerencias  
- ⚠️ Reportar incidencias  
- 📦 Gestionar encargos  
- 🔄 Actualizar estados  
- 🗑 Eliminar registros  

Este proyecto demuestra una **implementación completa de API REST**, junto con consumo real desde el frontend usando `fetch()` y JSON.

---

## 🧠 Arquitectura del Proyecto

```
Frontend (Vue 3)
        ⬇ HTTP (fetch + JSON)
Backend (Node.js + Express)
        ⬇
Persistencia en JSON
```

✔ Separación clara entre frontend y backend  
✔ Uso correcto de métodos HTTP  
✔ Gestión de estados y flujo de datos  
✔ Comunicación cliente-servidor real  

---

## 🔌 Endpoints Implementados (CRUD)

| Método | Endpoint | Función |
|--------|----------|----------|
| GET | `/api/registros` | Obtener todos los registros |
| POST | `/api/registros` | Crear un nuevo registro |
| PUT | `/api/registros/:id/estado` | Actualizar estado |
| DELETE | `/api/registros/:id` | Eliminar registro |

Se han implementado las operaciones básicas CRUD para la gestión completa de datos.

---

## 🛠 Tecnologías Utilizadas

### 💻 Frontend
- Vue 3 (Composition API)
- Fetch API
- CSS moderno
- Componentes reutilizables

### 🖥 Backend
- Node.js
- Express
- Arquitectura REST
- Middleware CORS
- Persistencia en archivo JSON

---

## 🚀 Instalación

### 1️⃣ Backend

```bash
cd tellme-api-js
npm install
node server.js
```

Servidor disponible en:
```
http://localhost:3000
```

---

### 2️⃣ Frontend

```bash
cd tellme-frontend
npm install
npm run dev
```

---

## 📂 Estructura del Proyecto

```
📦 TellMeApp
 ┣ 📂 tellme-api-js
 ┃ ┣ 📜 server.js
 ┃ ┗ 📜 registros.json
 ┗ 📂 tellme-frontend
   ┣ 📂 components
   ┃ ┣ 📜 RegistroForm.vue
   ┃ ┗ 📜 PanelRegistros.vue
   ┗ 📜 App.vue
```

---

## 🎯 Qué demuestra este proyecto

- Desarrollo de API REST funcional
- Consumo de API desde frontend
- Manejo de métodos HTTP
- Arquitectura cliente-servidor
- Proyecto full-stack estructurado
- Separación de responsabilidades

---

## 👩‍💻 Autora

**Filotea Mangue Mitogo**  
Estudiante de Desarrollo de Aplicaciones Web  

---

<p align="center">
  ✨ Proyecto enfocado en demostrar competencias Full-Stack Junior ✨
</p>
