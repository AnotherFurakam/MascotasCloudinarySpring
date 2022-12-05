package com.cloudinary.cloudinary_example.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cloudinary.cloudinary_example.models.entitys.Mascota;

public interface MascotaDao extends CrudRepository<Mascota,Long> {

  @Query("select m from Mascota m where isDeleted = 0")
  public List<Mascota> getAllNoDeleted();

  @Query("select m from Mascota m where email = ?1")
  public Mascota findByEmail(String email);

  /*  Se usa cuando queremos lanzar un error en caso no encontremos ningún valor
  default Mascota findByEmailOrThrow(String email){
    return Optional.ofNullable(findByEmail(email)).orElseThrow();
  } 
  */
}
