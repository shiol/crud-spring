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

import br.com.shiol.crud.model.Carro;
import br.com.shiol.crud.repository.CarroRepository;

@RestController
@RequestMapping({ "/cars" })
public class CarroController {

    private CarroRepository repository;

    CarroController(CarroRepository contactRepository) {
        this.repository = contactRepository;
    }

    @PostMapping
    public Carro create(@RequestBody Carro contact) {
        return repository.save(contact);
    }

    @GetMapping
    public List<Carro> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<Carro> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Carro> update(@PathVariable("id") long id,
            @RequestBody Carro car) {
        return repository.findById(id)
                .map(record -> {
                    record.setColor(car.getColor());
                    record.setLicensePlate(car.getLicensePlate());
                    record.setModel(car.getModel());
                    record.setYear(car.getYear());
                    Carro updated = repository.save(record);
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