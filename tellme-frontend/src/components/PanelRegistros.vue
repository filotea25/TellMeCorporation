<script setup>
import { ref, onMounted, computed } from 'vue'

const registros = ref([])
const filtroTipo = ref('TODOS')
const cargando = ref(false)

const cargarRegistros = async () => {
  cargando.value = true

  try {
    const response = await fetch('http://localhost:8080/api/registros')

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

</style>

