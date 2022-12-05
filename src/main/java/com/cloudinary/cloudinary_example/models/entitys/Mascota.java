package com.cloudinary.cloudinary_example.models.entitys;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Mascotas", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mascota implements Serializable {

  // Serialización de id
  private static final long serialVersion = 1L;

  public static long getSerialversion() {
    return serialVersion;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Name no puede ser nulo")
  private String name;

  @NotNull(message = "Especie no puede ser nulo")
  private String especies;

  @NotNull(message = "Age no puede ser nulo")
  private String age;

  @NotNull(message = "UrlImage no puede ser nulo")
  private String urlImage;

  @NotNull(message = "Name no puede ser nulo")
  private String ownername;

  @NotNull(message = "Name no puede ser nulo")
  private String email;

  @Column(columnDefinition = "boolean default false")
  private boolean isDeleted;

}
