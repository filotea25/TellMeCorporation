<script setup>
import { ref, computed } from 'vue'

const props = defineProps({
  tipo: String
})

const titulo = ref('')
const descripcion = ref('')
const nombre = ref('')
const departamento = ref('')

const mostrarMensaje = ref(false)
const mensajeExito = ref('')

/* PRIORIDAD AUTOMÁTICA SEGÚN TIPO */
const prioridad = computed(() => {
  if (props.tipo === 'SUGERENCIA') return 'BAJA'
  if (props.tipo === 'INCIDENCIA') return 'ALTA'
  if (props.tipo === 'ENCARGO') return 'MEDIA'
  return 'MEDIA'
})

const crearRegistro = async () => {
  try {
    const response = await fetch(
      'http://localhost:8080/api/registros?empleadoId=1',
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          titulo: titulo.value,
          descripcion: descripcion.value,
          tipo: props.tipo,
          prioridad: prioridad.value
        })
      }
    )

    if (!response.ok) {
      const errorText = await response.text()
      alert("Error backend: " + errorText)
      return
    }

    mensajeExito.value = `${props.tipo} guardada correctamente`
    mostrarMensaje.value = true

    titulo.value = ''
    descripcion.value = ''

  } catch (error) {
    alert("Backend no conectado")
    console.error(error)
  }
}


</script>

<template>
  <div class="form-card">

    <h2>Nuevo {{ tipo }}</h2>

    <div v-if="mostrarMensaje" class="success-message">
      {{ mensajeExito }}
    </div>

    <!-- Nombre y departamento solo si NO es incidencia -->
    <div class="field" v-if="tipo !== 'INCIDENCIA'">
      <label>Nombre</label>
      <input v-model="nombre" type="text" />
    </div>

    <div class="field" v-if="tipo !== 'INCIDENCIA'">
      <label>Departamento</label>
      <input v-model="departamento" type="text" />
    </div>

    <div class="field">
      <label>Título</label>
      <input v-model="titulo" type="text" />
    </div>

    <div class="field">
      <label>Descripción</label>
      <textarea v-model="descripcion"></textarea>
    </div>

    <button class="btn-primary" @click="crearRegistro">
      Crear {{ tipo }}
    </button>

  </div>
</template>

<style>
.form-card {
  background: white;
  padding: 40px;
  border-radius: 12px;
  width: 450px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
}

.form-card h2 {
  margin-bottom: 25px;
  text-align: center;
  color: #1e293b;
}

.field {
  display: flex;
  flex-direction: column;
  margin-bottom: 18px;
}

label {
  margin-bottom: 6px;
  font-weight: 600;
  font-size: 14px;
  color: #475569;
}

input,
textarea {
  padding: 10px;
  border: 1px solid #cbd5e1;
  border-radius: 6px;
  font-size: 14px;
}

textarea {
  min-height: 100px;
  resize: none;
}

.btn-primary {
  width: 100%;
  margin-top: 15px;
  padding: 12px;
  background: #2563eb;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
}

.btn-primary:hover {
  background: #1e40af;
}

.success-message {
  background: #dcfce7;
  color: #166534;
  padding: 12px;
  border-radius: 8px;
  margin-bottom: 20px;
  text-align: center;
  font-weight: 600;
}
</style>

