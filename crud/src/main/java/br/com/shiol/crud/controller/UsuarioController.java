package br.com.shiol.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.shiol.crud.model.Usuario;
import br.com.shiol.crud.repository.UsuarioRepository;

@RestController
@RequestMapping({ "/users" })
public class UsuarioController {

    private UsuarioRepository repository;

    UsuarioController(UsuarioRepository contactRepository) {
        this.repository = contactRepository;
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario contact) {
        return repository.save(contact);
    }

    @GetMapping
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<Usuario> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") long id,
            @RequestBody Usuario car) {
        return repository.findById(id)
                .map(record -> {
                    record.setBirthday(car.getBirthday());
                    record.setEmail(car.getEmail());
                    record.setFirstName(car.getFirstName());
                    record.setLastName(car.getLastName());
                    record.setLogin(car.getLogin());
                    record.setPassword(car.getPassword());
                    record.setPhone(car.getPhone());
                    Usuario updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}