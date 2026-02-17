<script setup>
import { ref } from 'vue'
import RegistroForm from './components/RegistroForm.vue'
import PanelRegistros from './components/PanelRegistros.vue'

const tipoSeleccionado = ref(null)
const vistaActual = ref(null)

function abrirFormulario(tipo) {
  tipoSeleccionado.value = tipo
  vistaActual.value = 'FORM'
}

function abrirPanel() {
  vistaActual.value = 'PANEL'
}

function cerrarVista() {
  vistaActual.value = null
  tipoSeleccionado.value = null
}
</script>

<template>
  <header class="header">
    <div class="logo-text">
      TellMe<span style="color:green;">App</span>
    </div>


    <div class="nav">
      <button @click="abrirFormulario('SUGERENCIA')">
        Sugerencia
      </button>

      <button @click="abrirFormulario('INCIDENCIA')">
        Incidencia
      </button>

      <button @click="abrirFormulario('ENCARGO')">
        Encargo
      </button>

      <button @click="abrirPanel">
        📋 Registros
      </button>

      <button class="login-btn">
        👤 Login
      </button>
    </div>
  </header>

  <main class="main">


    <!-- HERO CON LOGO -->
    <div v-if="!vistaActual" class="hero-container">
      <img 
        src="../images/tellme_logo_black.jpg" 
        alt="TellMeApp Logo"
        class="hero-logo"
      />

      <h1 class="hero-title">
        La comunicación interna
        <br />
        impulsa el crecimiento empresarial
      </h1>

      <p class="hero-subtitle">
        Registra incidencias, sugerencias y encargos
        de forma estructurada y profesional.
      </p>
    </div>

    <!-- VISTAS -->
    <div v-if="vistaActual" class="vista-container">

      <RegistroForm 
        v-if="vistaActual === 'FORM'" 
        :tipo="tipoSeleccionado" 
      />

      <PanelRegistros 
        v-if="vistaActual === 'PANEL'" 
      />

      <button class="cerrar" @click="cerrarVista">
        Cerrar
      </button>

    </div>

  </main>
</template>

<style>
body {
  margin: 0;
  font-family: Arial, sans-serif;
  
}
.main {
  min-height: calc(100vh - 70px);
  padding: 40px 60px;
  background: #D8DBD3;
}



.header {
  background-color: #1e2a38;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 30px;
}

.logo-text {
  font-size: 20px;
  font-weight: bold;
}

.nav button {
  margin-left: 10px;
  padding: 8px 14px;
  border: none;
  background-color: #3b82f6;
  color: white;
  border-radius: 5px;
  cursor: pointer;
}

.nav button:hover {
  background-color: #2563eb;
}

.login-btn {
  background: transparent;
  border: 1px solid #4da3ff;
  color: #4da3ff;
  padding: 8px 14px;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 500;
}

.login-btn:hover {
  background: #4da3ff;
  color: white;
}

.main {
  min-height: calc(100vh - 70px);
  padding: 40px 60px;
  background: #f1f5f9;
}

/* HERO */
.hero-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 70vh;
  text-align: center;
}

.hero-logo {
  width: 200px;
  margin-bottom: 25px;
  animation: fadeSlide 1.2s ease forwards;
}

.hero-title {
  font-size: 42px;
  font-weight: 700;
  opacity: 0;
  animation: fadeSlide 1.2s ease forwards;
  animation-delay: 0.4s;
  color:green;
}
.hero-subtitle {
  margin-top: 20px;
  font-size: 18px;
  color: #475569;
  max-width: 600px;
}

/* Gradiente animado */
.gradient-text {
  background: linear-gradient(
    90deg,
    #065f46,
    #047857,
    #10b981,
    #047857,
    #065f46
  );
  background-size: 300% 300%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: gradientMove 6s ease infinite;
}

/* Animaciones */
@keyframes fadeSlide {
  from {
    transform: translateY(20px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}

@keyframes gradientMove {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

.vista-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.cerrar {
  margin-top: 15px;
  padding: 6px 12px;
  background-color: #ef4444;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.cerrar:hover {
  background-color: #b91c1c;
}

</style>
