package br.com.shiol.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.shiol.crud.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.user.login = :login")
    List<Car> findAllAndUser(String login);

    @Query("SELECT c FROM Car c WHERE c.id = :id AND c.user.login = :login")
    Optional<Car> findByIdAndUser(Long id, String login);
}