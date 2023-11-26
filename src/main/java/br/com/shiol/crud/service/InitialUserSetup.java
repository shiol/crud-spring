package br.com.shiol.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.shiol.crud.model.Usuario;
import br.com.shiol.crud.repository.UsuarioRepository;

import java.util.Calendar;
import java.util.Optional;

@Component
public class InitialUserSetup implements ApplicationRunner {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitialUserSetup(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        initializeUser();
    }

    private void initializeUser() {
        String username = "admin";
        Optional<Usuario> existingUser = userRepository.findByUsername(username);

        if (existingUser.isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode("admin"));
            user.setFirstName("Administrator");
            user.setLastName("Master");
            user.setEmail("admin@email.com");
            user.setBirthday(Calendar.getInstance().getTime());
            user.setPhone("988887777");
            user.setLogin(username);
            userRepository.save(user);
        }
    }
}
