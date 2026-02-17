<script setup>
import { ref, onMounted, computed } from 'vue'

const registros = ref([])
const filtroTipo = ref('TODOS')
const cargando = ref(false)

const cargarRegistros = async () => {
  cargando.value = true

  try {
    const response = await fetch('http://localhost:3000/api/registros')

    if (!response.ok) {
      throw new Error('Error HTTP')
    }

    registros.value = await response.json()

  } catch (error) {
    console.error(error)
    alert('Error cargando registros')
  } finally {
    cargando.value = false
  }
}

const cambiarEstado = async (id, nuevoEstado) => {
  try {
    await fetch(
      `http://localhost:3000/api/registros/${id}/estado?estado=${nuevoEstado}`,
      { method: 'PUT' }
    )

    cargarRegistros()

  } catch (error) {
    alert("Error actualizando estado")
    console.error(error)
  }
}

const eliminarRegistro = async (id) => {
  if (!confirm("¿Seguro que quieres eliminar este registro?")) return

  try {
    const response = await fetch(
      `http://localhost:3000/api/registros/${id}`,
      { method: 'DELETE' }
    )

    if (!response.ok) throw new Error("Error eliminando")

    cargarRegistros()

  } catch (error) {
    alert("Error eliminando registro")
    console.error(error)
  }
}

onMounted(() => {
  cargarRegistros()
})

const registrosFiltrados = computed(() => {
  if (filtroTipo.value === 'TODOS') return registros.value
  return registros.value.filter(r => r.tipo === filtroTipo.value)
})
</script>

<template>
  <div class="panel">

    <h2>📋 Panel de Registros</h2>

    <div class="filtro">
      <label>Filtrar:</label>
      <select v-model="filtroTipo">
        <option value="TODOS">Todos</option>
        <option value="SUGERENCIA">Sugerencias</option>
        <option value="INCIDENCIA">Incidencias</option>
        <option value="ENCARGO">Encargos</option>
      </select>
    </div>

    <div v-if="cargando" class="loading">
      Cargando registros...
    </div>

    <div v-else class="grid">

      <div 
        v-for="registro in registrosFiltrados" 
        :key="registro.id"
        class="card"
      >
        <div class="top">
          <span class="badge tipo">{{ registro.tipo }}</span>
          <span class="badge estado">{{ registro.estado }}</span>
        </div>

        <h3>{{ registro.titulo }}</h3>

          <p class="autor">
            Creado por: {{ registro.nombre }}
          </p>

          <p class="departamento">
            Departamento: {{ registro.departamento }}
          </p>

          <p>{{ registro.descripcion }}</p>

        <div class="bottom">
          <span 
            class="badge"
            :class="'prioridad-' + registro.prioridad"
          >
            {{ registro.prioridad }}
          </span>

          <span class="fecha">
            {{ new Date(registro.fechaCreacion).toLocaleString() }}
          </span>
        </div>

        <!-- ACCIONES -->
        <div class="acciones">

          <select 
            class="select-estado"
            @change="cambiarEstado(registro.id, $event.target.value)"
          >
            <option disabled selected>Gestionar</option>
            <option value="EN_PROCESO">En proceso</option>
            <option value="RESUELTO">Resuelto</option>
            <option value="RECHAZADO">Rechazado</option>
          </select>

          <button 
            class="btn-delete-small"
            @click="eliminarRegistro(registro.id)"
          >
            🗑
          </button>

        </div>

      </div>

      <div 
        v-if="registrosFiltrados.length === 0" 
        class="empty"
      >
        No hay registros disponibles
      </div>

    </div>

  </div>
</template>

<style scoped>

.panel {
  max-width: 1100px;
  margin: 0 auto;
}

.panel h2 {
  margin-bottom: 25px;
  font-size: 26px;
  color: #1e293b;
}

.filtro {
  margin-bottom: 30px;
}

.filtro select {
  margin-left: 10px;
  padding: 8px 12px;
  border-radius: 6px;
  border: 1px solid #cbd5e1;
}

.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 25px;
}

.card {
  background: white;
  padding: 22px;
  border-radius: 16px;
  box-shadow: 0 8px 25px rgba(0,0,0,0.06);
  transition: 0.25s ease;
  display: flex;
  flex-direction: column;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 15px 35px rgba(0,0,0,0.1);
}

.top {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.bottom {
  margin-top: 15px;
  display: flex;
  justify-content: space-between;
  font-size: 13px;
}

.badge {
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 11px;
  font-weight: bold;
  text-transform: uppercase;
}

.tipo {
  background: #dbeafe;
  color: #1d4ed8;
}

.estado {
  background: #dcfce7;
  color: #166534;
}

.prioridad-ALTA {
  background: #fee2e2;
  color: #991b1b;
}

.prioridad-MEDIA {
  background: #fef3c7;
  color: #92400e;
}

.prioridad-BAJA {
  background: #dcfce7;
  color: #166534;
}

.fecha {
  color: #64748b;
}

.loading,
.empty {
  text-align: center;
  margin-top: 50px;
  font-size: 18px;
}
.departamento {
  font-size: 13px;
  color: #475569;
  margin-bottom: 8px;
}


.acciones {
  display: flex;
  gap: 10px;
  margin-top: 15px;
}

.acciones {
  display: flex;
  gap: 10px;
  margin-top: 15px;
  align-items: center;
}

/* Select de gestionar */
.select-estado {
  flex: 1;
  padding: 8px 10px;
  border-radius: 8px;
  border: 1px solid #cbd5e1;
  font-size: 13px;
  background: white;
  cursor: pointer;
  transition: 0.2s ease;
}

.select-estado:hover {
  border-color: #2563eb;
}

/* Botón eliminar pequeño */
.btn-delete-small {
  width: 42px;
  height: 36px;
  border: none;
  border-radius: 8px;
  background: #ef4444;
  color: white;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: 0.2s ease;
}

.btn-delete-small:hover {
  background: #b91c1c;
}

.autor {
  font-size: 13px;
  color: #64748b;
  margin-bottom: 4px;
}




</style>

