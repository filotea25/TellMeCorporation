<script setup>
import { ref, onMounted } from 'vue'

const registros = ref([])

onMounted(async () => {
  const res = await fetch('http://localhost:8080/api/registros')
  registros.value = await res.json()
})
</script>

<template>
  <div>
    <h1>Dashboard</h1>

    <ul>
      <li v-for="r in registros" :key="r.id">
        {{ r.titulo }} - {{ r.tipo }} - {{ r.estado }}
      </li>
    </ul>
  </div>
</template>
