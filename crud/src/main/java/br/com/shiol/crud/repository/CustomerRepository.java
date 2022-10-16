package br.com.shiol.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.shiol.crud.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}