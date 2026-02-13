/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tellme.buzon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author filot
 */
@Entity
@Table(name="empleados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
     @NotBlank
    private String nombre;

    @Column(nullable = false, unique = true)
     @NotBlank
    private String email;

    @Column(nullable=false)
    @NotBlank
    private String departamento;

    
    @Column(nullable=false)
    @NotNull
    private boolean activo = true;

    // Relación: Un empleado tiene muchos registros
    // mappedBy indica que la relación se gestiona en el campo 'empleado' de la clase Registro
    @OneToMany(mappedBy = "empleado")
    @JsonIgnore
    private List<Registro> registros;
}
