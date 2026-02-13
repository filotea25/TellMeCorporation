/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.tellme.buzon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tellme.buzon.model.EstadoRegistro;
import com.tellme.buzon.model.Registro;
import com.tellme.buzon.model.TipoRegistro;

/**
 *
 * @author filot
 */
public interface RegistroRepository extends JpaRepository<Registro, Long> {
// Spring Data JPA crea estas consultas automáticamente por el nombre del método
    List<Registro> findByTipo(TipoRegistro tipo);
    List<Registro> findByEstado(EstadoRegistro estado);
}
