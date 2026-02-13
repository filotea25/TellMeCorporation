/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.tellme.buzon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tellme.buzon.model.Empleado;

/**
 *
 * @author filot
 */
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

}
