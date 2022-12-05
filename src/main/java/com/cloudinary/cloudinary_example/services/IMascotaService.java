package com.cloudinary.cloudinary_example.services;

import java.util.List;

import com.cloudinary.cloudinary_example.models.dto.MascotaDto;
import com.cloudinary.cloudinary_example.models.dto.PostMascotaDto;
import com.cloudinary.cloudinary_example.models.entitys.ApiResponse;

public interface IMascotaService {
  public ApiResponse<List<MascotaDto>> GetAllMascotas();

  public ApiResponse<MascotaDto> GetMascotaById(Long id);

  public ApiResponse<MascotaDto> AddMascota(PostMascotaDto newMascota);

  public ApiResponse<MascotaDto> UpdateMascotaByID(Long id, PostMascotaDto mascota);

  public ApiResponse<MascotaDto> DeleteMascota(Long id);
}
