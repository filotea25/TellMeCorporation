/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tellme.buzon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tellme.buzon.model.Empleado;
import com.tellme.buzon.model.EstadoRegistro;
import com.tellme.buzon.model.Registro;
import com.tellme.buzon.model.TipoRegistro;
import com.tellme.buzon.repository.RegistroRepository;

/**
 *
 * @author filot
 */

@Service
public class RegistroService {
  private final RegistroRepository registroRepository;
    private final EmpleadoService empleadoService;

    public RegistroService(RegistroRepository registroRepository,
                           EmpleadoService empleadoService) {
        this.registroRepository = registroRepository;
        this.empleadoService = empleadoService;
    }

    // Crear registro
    public Registro crearRegistro(Long empleadoId, Registro registro) {

        Empleado empleado = empleadoService.buscarPorId(empleadoId);

        registro.setEmpleado(empleado);
        return registroRepository.save(registro);
    }

    // Listar todos
    public List<Registro> listarTodos() {
        return registroRepository.findAll();
    }

    // Buscar por tipo
    public List<Registro> buscarPorTipo(TipoRegistro tipo) {
        return registroRepository.findByTipo(tipo);
    }

    // Buscar por estado
    public List<Registro> buscarPorEstado(EstadoRegistro estado) {
        return registroRepository.findByEstado(estado);
    }

    // Cambiar estado
    public Registro cambiarEstado(Long id, EstadoRegistro nuevoEstado) {

        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        registro.setEstado(nuevoEstado);
        return registroRepository.save(registro);
    }
}
