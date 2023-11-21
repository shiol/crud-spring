package br.com.shiol.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.shiol.crud.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}