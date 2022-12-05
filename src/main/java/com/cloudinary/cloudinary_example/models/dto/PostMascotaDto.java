package com.cloudinary.cloudinary_example.models.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostMascotaDto {
  private String name;
  private String especies;
  private String age;
  private MultipartFile image;
  private String ownername;
  private String email;
}
