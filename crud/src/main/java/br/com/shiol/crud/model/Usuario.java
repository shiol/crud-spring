package br.com.shiol.crud.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario {

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

   @OneToMany(fetch = FetchType.LAZY)
   private List<Carro> cars;
}