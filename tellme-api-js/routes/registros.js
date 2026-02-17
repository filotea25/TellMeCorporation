const express = require('express')
const router = express.Router()
const fs = require('fs')
const path = require('path')

const dataPath = path.join(__dirname, '../data/registros.json')

// Leer datos
function readData() {
  return JSON.parse(fs.readFileSync(dataPath))
}

// Guardar datos
function writeData(data) {
  fs.writeFileSync(dataPath, JSON.stringify(data, null, 2))
}

// GET
router.get('/', (req, res) => {
  const registros = readData()
  res.json(registros)
})

// POST
router.post('/', (req, res) => {
  const registros = readData()

  const nuevo = {
    id: Date.now(),
    ...req.body,
    estado: 'NUEVO',
    fechaCreacion: new Date()
  }

  registros.push(nuevo)
  writeData(registros)

  res.status(201).json(nuevo)
})

// PUT cambiar estado
router.put('/:id/estado', (req, res) => {
  const registros = readData()
  const id = Number(req.params.id)

  const registro = registros.find(r => r.id === id)
  if (!registro) return res.status(404).json({ error: 'No encontrado' })

  registro.estado = req.query.estado
  writeData(registros)

  res.json(registro)
})

// DELETE
router.delete('/:id', (req, res) => {
  let registros = readData()
  const id = Number(req.params.id)

  registros = registros.filter(r => r.id !== id)
  writeData(registros)

  res.status(204).send()
})

module.exports = router
