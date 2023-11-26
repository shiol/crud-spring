package br.com.shiol.crud.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String firstName;
   private String lastName;
   private String email;
   private Date birthday;
   private String login;
   private String password;
   private String phone;
   private String username;

   @CreationTimestamp
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "created_at", updatable = false)
   private Date createdAt;
   private Date lastLogin;

   public Usuario(Usuario usuario) {
      this.setFirstName(usuario.getFirstName());
      this.setLastName(usuario.getLastName());
      this.setEmail(usuario.getEmail());
      this.setBirthday(usuario.getBirthday());
      this.setLogin(usuario.getLogin());
      this.setPassword(usuario.getPassword());
      this.setPhone(usuario.getPhone());
      this.setUsername(usuario.getUsername());
   }

   public Usuario(String username, String password) {
      this.username = username;
      this.password = password;
   }

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Car> cars;

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

}