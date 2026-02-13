/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tellme.buzon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tellme.buzon.model.Empleado;
import com.tellme.buzon.repository.EmpleadoRepository;

/**
 *
 * @author filot
 */

@Service
public class EmpleadoService {
     private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // Crear empleado
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Listar todos
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    // Buscar por ID
    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    // Desactivar empleado
    public Empleado desactivarEmpleado(Long id) {
        Empleado empleado = buscarPorId(id);
        empleado.setActivo(false);
        return empleadoRepository.save(empleado);
    }
}
