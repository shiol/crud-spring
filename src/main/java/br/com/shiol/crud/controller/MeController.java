package br.com.shiol.crud.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.shiol.crud.model.Usuario;
import br.com.shiol.crud.repository.UsuarioRepository;

@RestController
public class MeController {

    private UsuarioRepository repository;

    MeController(UsuarioRepository contactRepository) {
        this.repository = contactRepository;
    }

    @GetMapping(path = { "/api/me" })
    public ResponseEntity<Usuario> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElseThrow(() -> new EntityNotFoundException());
    }
}