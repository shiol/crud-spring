package br.com.shiol.crud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Carro {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private int yearManufacture;
   private String licensePlate;
   private String model;
   private String color;
}