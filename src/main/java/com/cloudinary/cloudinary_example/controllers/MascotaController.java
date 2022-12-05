package com.cloudinary.cloudinary_example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.cloudinary_example.models.dto.MascotaDto;
import com.cloudinary.cloudinary_example.models.dto.PostMascotaDto;
import com.cloudinary.cloudinary_example.models.entitys.ApiResponse;
import com.cloudinary.cloudinary_example.services.IMascotaService;

@RestController()
@RequestMapping("api")
public class MascotaController {
  @Autowired
  IMascotaService _mascotaService;

  @PostMapping("AddMascota")
  public ResponseEntity<ApiResponse<MascotaDto>> AddMascota(PostMascotaDto mascota){

    var response = _mascotaService.AddMascota(mascota);

    if (!response.isSuccess())
      return new ResponseEntity<ApiResponse<MascotaDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    return new ResponseEntity<ApiResponse<MascotaDto>>(response, HttpStatus.CREATED);

  }

  @GetMapping("GetAllMascotas")
  public ResponseEntity<ApiResponse<List<MascotaDto>>> GetAllMascotas() {

    ApiResponse<List<MascotaDto>> response = _mascotaService.GetAllMascotas();

    if (!response.isSuccess())
      return new ResponseEntity<ApiResponse<List<MascotaDto>>>(response, HttpStatus.BAD_REQUEST);

    return new ResponseEntity<ApiResponse<List<MascotaDto>>>(response, HttpStatus.OK);
  }

  @GetMapping("GetMascotaById/{id}")
  public ResponseEntity<ApiResponse<MascotaDto>> GetMascotaById(@PathVariable Long id) {

    ApiResponse<MascotaDto> response = _mascotaService.GetMascotaById(id);

    if (!response.isSuccess())
      return new ResponseEntity<ApiResponse<MascotaDto>>(response, HttpStatus.NOT_FOUND);

    return new ResponseEntity<ApiResponse<MascotaDto>>(response, HttpStatus.OK);
  }

  @PutMapping("UpdateMascotaById/{id}")
  public ResponseEntity<ApiResponse<MascotaDto>> UpdateMascotaById(@PathVariable Long id, PostMascotaDto mascota) {

    ApiResponse<MascotaDto> response = _mascotaService.UpdateMascotaByID(id, mascota);

    if (!response.isSuccess())
      return new ResponseEntity<ApiResponse<MascotaDto>>(response, HttpStatus.NOT_FOUND);

    return new ResponseEntity<ApiResponse<MascotaDto>>(response, HttpStatus.OK);
  }

  @DeleteMapping("DeleteMascotaById/{id}")
  public ResponseEntity<ApiResponse<MascotaDto>> DeleteMascotaById(@PathVariable Long id) {

    ApiResponse<MascotaDto> response = _mascotaService.DeleteMascota(id);

    if (!response.isSuccess())
      return new ResponseEntity<ApiResponse<MascotaDto>>(response, HttpStatus.NOT_FOUND);

    return new ResponseEntity<ApiResponse<MascotaDto>>(response, HttpStatus.OK);
  }

}
