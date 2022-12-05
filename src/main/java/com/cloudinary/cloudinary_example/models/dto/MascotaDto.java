package com.cloudinary.cloudinary_example.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MascotaDto {
  private Long id;
  private String name;
  private String especies;
  private String age;
  private String urlImage;
  private String ownername;
  private String email;
}
