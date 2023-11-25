package br.com.shiol.crud.controller;

import java.util.ArrayList;
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

import br.com.shiol.crud.model.Carro;
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
    public Usuario create(@RequestBody Usuario usuario) {

        List<Carro> carros = new ArrayList<>();

        for (Carro car : usuario.getCars()) {
            Carro carro = new Carro();
            carro.setColor(car.getColor());
            carro.setLicensePlate(car.getLicensePlate());
            carro.setModel(car.getModel());
            carro.setYearManufacture(car.getYearManufacture());
            carro.setUser(usuario);
            carros.add(carro);
        }

        usuario.setCars(carros);

        Usuario user = repository.save(usuario);
        return user;
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
            @RequestBody Usuario usuario) {
        return repository.findById(id)
                .map(record -> {
                    record.setBirthday(usuario.getBirthday());
                    record.setEmail(usuario.getEmail());
                    record.setFirstName(usuario.getFirstName());
                    record.setLastName(usuario.getLastName());
                    record.setLogin(usuario.getLogin());
                    record.setPassword(usuario.getPassword());
                    record.setPhone(usuario.getPhone());
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