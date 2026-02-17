package com.tellme.buzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tellme.buzon.model.EstadoRegistro;
import com.tellme.buzon.model.Registro;
import com.tellme.buzon.model.TipoRegistro;
import com.tellme.buzon.service.RegistroService;

@RestController
@RequestMapping("api/registros")
@CrossOrigin(origins = "http://localhost:5173")
public class RegistroController {
    @Autowired
    private RegistroService res;

     // POST /api/registros?empleadoId=1
    @PostMapping
    public Registro crearRegistro(@RequestParam Long empleadoId,
                                  @RequestBody Registro registro) {
        return res.crearRegistro(empleadoId, registro);
    }

    // GET /api/registros
    @GetMapping
    public List<Registro> listarTodos() {
        return res.listarTodos();
    }

    // GET /api/registros/tipo/{tipo}
    @GetMapping("/tipo/{tipo}")
    public List<Registro> buscarPorTipo(@PathVariable TipoRegistro tipo) {
        return res.buscarPorTipo(tipo);
    }

    // GET /api/registros/estado/{estado}
    @GetMapping("/estado/{estado}")
    public List<Registro> buscarPorEstado(@PathVariable EstadoRegistro estado) {
        return res.buscarPorEstado(estado);
    }

   
//cambiar el estado del registro
    @PutMapping("/{id}/estado")
    public ResponseEntity<Registro> cambiarEstado(
        @PathVariable Long id,
        @RequestParam EstadoRegistro estado) {

    Registro actualizado = res.cambiarEstado(id, estado);
    return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    res.deleteById(id);
    return ResponseEntity.noContent().build();
    }

}
