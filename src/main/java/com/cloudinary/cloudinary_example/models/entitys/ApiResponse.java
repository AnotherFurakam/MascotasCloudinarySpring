package com.cloudinary.cloudinary_example.models.entitys;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResponse<T>{
  private T data;
  private boolean success = true; 
  private String message = "";
}
