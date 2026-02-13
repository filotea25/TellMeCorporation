<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const irDashboard = () => {
  router.push('/dashboard')
}
</script>

<template>
  <div class="container">
    <div class="left">
      <h1>TellMe</h1>
      <button @click="irDashboard">
        Empleado
      </button>

      <p>La comunicación es la base del éxito</p>
    </div>
  </div>
</template>

<style>
.container {
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
}
</style>
