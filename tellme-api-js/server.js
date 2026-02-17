const express = require('express')
const cors = require('cors')
const fs = require('fs')
const path = require('path')

const app = express()
const PORT = 3000

app.use(cors())
app.use(express.json())

const DATA_FILE = path.join(__dirname, 'registros.json')

/* =========================
   FUNCIONES AUXILIARES
========================= */

function leerRegistros() {
  if (!fs.existsSync(DATA_FILE)) {
    fs.writeFileSync(DATA_FILE, JSON.stringify([]))
  }
  return JSON.parse(fs.readFileSync(DATA_FILE))
}

function guardarRegistros(data) {
  fs.writeFileSync(DATA_FILE, JSON.stringify(data, null, 2))
}

/* =========================
   GET - Obtener registros
========================= */

app.get('/api/registros', (req, res) => {
  const registros = leerRegistros()
  res.json(registros)
})

/* =========================
   POST - Crear registro
========================= */

app.post('/api/registros', (req, res) => {
  const registros = leerRegistros()

  const nuevoRegistro = {
    id: Date.now(),
    titulo: req.body.titulo,
    descripcion: req.body.descripcion,
    tipo: req.body.tipo,
    prioridad: req.body.prioridad,
    estado: req.body.estado || 'NUEVO',
    nombre: req.body.nombre,
    departamento: req.body.departamento,
    fechaCreacion: new Date().toISOString()
  }

  registros.push(nuevoRegistro)
  guardarRegistros(registros)

  res.status(201).json(nuevoRegistro)
})

/* =========================
   PUT - Cambiar estado
========================= */

app.put('/api/registros/:id/estado', (req, res) => {
  const registros = leerRegistros()
  const id = Number(req.params.id)
  const nuevoEstado = req.query.estado

  const registro = registros.find(r => r.id === id)

  if (!registro) {
    return res.status(404).json({ error: 'Registro no encontrado' })
  }

  registro.estado = nuevoEstado
  guardarRegistros(registros)

  res.json(registro)
})

/* =========================
   DELETE - Eliminar registro
========================= */

app.delete('/api/registros/:id', (req, res) => {
  const registros = leerRegistros()
  const id = Number(req.params.id)

  const nuevosRegistros = registros.filter(r => r.id !== id)

  guardarRegistros(nuevosRegistros)

  res.json({ message: 'Registro eliminado correctamente' })
})

/* =========================
   INICIAR SERVIDOR
========================= */

app.listen(PORT, () => {
  console.log(`🚀 API funcionando en http://localhost:${PORT}`)
})
