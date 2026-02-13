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
    <div class="logo">TellMe Corporation</div>

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
  📋    Registros
      </button>
      <button class="login-btn">
        👤 Login
      </button>
    </div>
  </header>

  <main class="main">

  <h1 v-if="!vistaActual" class="hero-title">
  <span class="gradient-text">
    La comunicación nos hace mejores
  </span>
</h1>

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

.header {
  background-color: #1e2a38;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 30px;
}

.logo {
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

.login {
  background-color: #10b981;
}

.main {
  min-height: calc(100vh - 70px);
  padding: 40px 60px;
  background: #f1f5f9;
}


.form-container {
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
.hero-title {
  font-size: 42px;
  font-weight: 700;
  text-align: center;
  animation: fadeSlide 1.2s ease forwards;
  opacity: 0;
}

/* Gradiente verde elegante */
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

/* Animación entrada */
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

/* Movimiento suave del degradado */
@keyframes gradientMove {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

</style>
