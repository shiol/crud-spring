package br.com.shiol.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.shiol.crud.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByLogin(String login);
}