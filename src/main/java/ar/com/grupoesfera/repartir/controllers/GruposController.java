package ar.com.grupoesfera.repartir.controllers;

import ar.com.grupoesfera.repartir.exceptions.GrupoInvalidoException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.repositories.GruposRepository;
import ar.com.grupoesfera.repartir.exceptions.GrupoNoEncontradoException;
import ar.com.grupoesfera.repartir.services.GruposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GruposController {

    @Autowired
    GruposService grupos;

    @GetMapping
    public ResponseEntity<List<Grupo>> listar() {

        ResponseEntity<List<Grupo>> response;

        try {

            response = ResponseEntity.ok(grupos.listarGrupos());

        } catch (Exception e) {

            response = ResponseEntity.internalServerError().build();
        }

        return  response;
    }

    @PostMapping
    public ResponseEntity<Grupo> crear(@RequestBody Grupo grupo) {

        ResponseEntity<Grupo> response;

        try {

            response = ResponseEntity.ok(grupos.crear(grupo));

        } catch (GrupoInvalidoException e) {

            response = ResponseEntity.badRequest().build();

        } catch (Exception e) {

            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> recuperar(@PathVariable Long id) {

        ResponseEntity<Grupo> response;

        try {

            response = ResponseEntity.ok(grupos.recuperar(id));

        } catch (GrupoNoEncontradoException e) {

            response = ResponseEntity.notFound().build();

        } catch (Exception e) {

            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> actualizar(@PathVariable Long id, @RequestBody Grupo grupo) {
        System.out.println("recibiendo id: " + id);
        System.out.println("recibiendo grupo: " + grupo);
        grupo.setId(id);
        System.out.println("actualizando: " + grupo);
        ResponseEntity<Grupo> response;

        try {

            response = ResponseEntity.ok(grupos.actualizar(grupo));

        } catch (GrupoInvalidoException e) {

            response = ResponseEntity.badRequest().build();

        } catch (Exception e) {

            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }

    @PostMapping("/{id}/gastos")
    public ResponseEntity<Grupo> agregarGasto(@PathVariable Long id, @RequestBody Gasto gasto) {

        ResponseEntity<Grupo> response;

        try {

            Grupo grupo = grupos.agregarGasto(id, gasto);

            response = ResponseEntity.ok(grupo);

        } catch (GrupoNoEncontradoException e) {

            response = ResponseEntity.notFound().build();

        } catch (Exception e) {

            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }
}
