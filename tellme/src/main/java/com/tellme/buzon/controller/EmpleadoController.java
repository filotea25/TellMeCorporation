/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tellme.buzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tellme.buzon.model.Empleado;
import com.tellme.buzon.service.EmpleadoService;

/**
 *
 * @author filot
 */
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
@Autowired
private EmpleadoService es;


    // POST /api/empleados
    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return es.crearEmpleado(empleado);
    }

    // GET /api/empleados
    @GetMapping
    public List<Empleado> listarTodos() {
        return es.listarTodos();
    }

    // PUT /api/empleados/{id}/desactivar
    @PutMapping("/{id}/desactivar")
    public Empleado desactivar(@PathVariable Long id) {
        return es.desactivarEmpleado(id);
    }

}