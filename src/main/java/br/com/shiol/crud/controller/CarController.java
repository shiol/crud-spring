package br.com.shiol.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.shiol.crud.model.Car;
import br.com.shiol.crud.model.Usuario;
import br.com.shiol.crud.repository.CarRepository;
import br.com.shiol.crud.repository.UsuarioRepository;
import br.com.shiol.crud.security.JwtTokenUtil;

@RestController
@RequestMapping({ "/api/cars" })
public class CarController {

    private CarRepository repository;
    private UsuarioRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    CarController(CarRepository contactRepository, UsuarioRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.repository = contactRepository;
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping
    public Car create(@RequestBody Car car,
            @RequestHeader(name = "", value = "Authorization", defaultValue = "") String authorizationHeader) {
        Usuario user = jwtTokenUtil.extractUserDetails(authorizationHeader.substring(7));
        Usuario userFromBase = userRepository.findByLogin(user.getLogin()).get();
        car.setUser(userFromBase);
        return repository.save(car);
    }

    @GetMapping
    public List<Car> findAll(
            @RequestHeader(name = "", value = "Authorization", defaultValue = "") String authorizationHeader) {
        Usuario user = jwtTokenUtil.extractUserDetails(authorizationHeader.substring(7));
        return repository.findAllAndUser(user.getUsername());
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<Car> findById(@PathVariable long id,
            @RequestHeader(name = "", value = "Authorization", defaultValue = "") String authorizationHeader) {
        Usuario user = jwtTokenUtil.extractUserDetails(authorizationHeader.substring(7));
        return repository.findByIdAndUser(id, user.getUsername())
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Car> update(@PathVariable("id") long id,
            @RequestBody Car car) {
        return repository.findById(id)
                .map(record -> {
                    record.setColor(car.getColor());
                    record.setLicensePlate(car.getLicensePlate());
                    record.setModel(car.getModel());
                    record.setYearManufacture(car.getYearManufacture());
                    Car updated = repository.save(record);
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