package com.cloudinary.cloudinary_example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.cloudinary_example.models.dao.MascotaDao;
import com.cloudinary.cloudinary_example.models.dto.MascotaDto;
import com.cloudinary.cloudinary_example.models.dto.PostMascotaDto;
import com.cloudinary.cloudinary_example.models.entitys.ApiResponse;
import com.cloudinary.cloudinary_example.models.entitys.Mascota;
import com.cloudinary.utils.ObjectUtils;

@Service
public class MascotaService implements IMascotaService {

  private Cloudinary _cloudinary = Singleton.getCloudinary();

  @Autowired
  private MascotaDao _mascotaDao;

  @Autowired
  private ModelMapper _mapper;

  @Override
  public ApiResponse<MascotaDto> AddMascota(PostMascotaDto newMascota) {

    ApiResponse<MascotaDto> response = new ApiResponse<MascotaDto>();

    try {
      //Verifying if images file is empty
      if (newMascota.getImage().isEmpty()) throw new Exception("Debe enviar una imagen de su mascota");

      //Verifying if the email is already registered in the database
      Mascota isEmailExist = _mascotaDao.findByEmail(newMascota.getEmail());

      //Throw error if email exist
      if(isEmailExist != null) throw new Exception("Email alredy exist");

      // Mapping <PostMascotaDto>newMascota to <Mascota>mascota
      Mascota mascota = _mapper.map(newMascota, Mascota.class);

      // Image upload to Cloudinary
      var uploadedImg = this._cloudinary.uploader().upload(newMascota.getImage().getBytes(),
          ObjectUtils.asMap("resourse_type", "auto","folder","Mascotas"));

      // Set urlImage in mascota.urlImage from uploadedImg(Cloudinary response)
      mascota.setUrlImage((String) uploadedImg.get("secure_url")); // sucure_url is a response property of cloudinary

      Mascota mascotaResponse = _mascotaDao.save(mascota); // Saving mascota in database

      // Mapping <Mascota>mascota to <MascotaDto> in response.Data
      response.setData(_mapper.map(mascotaResponse, MascotaDto.class));

    } catch (Exception e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }

    return response;
  }

  @Override
  public ApiResponse<MascotaDto> DeleteMascota(Long id) {
    ApiResponse<MascotaDto> response = new ApiResponse<MascotaDto>();
    try {
      Mascota mascota = _mascotaDao.findById(id).orElseThrow(()-> new Exception("Mascota not found"));

      mascota.setDeleted(true);

      Mascota mascotaResponse = _mascotaDao.save(mascota);

      response.setData(_mapper.map(mascotaResponse, MascotaDto.class));

    } catch (Exception e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }
    return response;
  }

  @Override
  public ApiResponse<List<MascotaDto>> GetAllMascotas() {
    ApiResponse<List<MascotaDto>> response = new ApiResponse<List<MascotaDto>>();

    try {
      List<Mascota> mascotas = (List<Mascota>) _mascotaDao.getAllNoDeleted();
      response.setData(
          mascotas.stream().map(mascota -> _mapper.map(mascota, MascotaDto.class)).collect(Collectors.toList()));

    } catch (Exception e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }
    return response;
  }

  @Override
  public ApiResponse<MascotaDto> GetMascotaById(Long id) {
    ApiResponse<MascotaDto> response = new ApiResponse<MascotaDto>();
    try {

      // Oteniendo la mascota de la base de datos
      Mascota mascota = (Mascota) _mascotaDao.findById(id)
          .orElseThrow(() -> new Exception("Mascota with id: " + id + " not found"));
      response.setData(_mapper.map(mascota, MascotaDto.class));

    } catch (Exception e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }
    return response;
  }

  @Override
  public ApiResponse<MascotaDto> UpdateMascotaByID(Long id, PostMascotaDto mascota) {
    ApiResponse<MascotaDto> response = new ApiResponse<MascotaDto>();
    try {

      Mascota mascotaToUpdate = _mascotaDao.findById(id).orElseThrow(()-> new Exception("Mascota not found"));

      //Configurando el automapper para mapear de un modelo a otro (Actualizar) 
      _mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

      //Ejecutando actualización con automapper
      _mapper.map(mascota, mascotaToUpdate);

      if (mascota.getImage() != null) {
        // Image upload to Cloudinary
        var uploadedImg = this._cloudinary.uploader().upload(mascota.getImage().getBytes(),
            ObjectUtils.asMap("resourse_type", "auto","folder","Mascotas"));

        mascotaToUpdate.setUrlImage((String)uploadedImg.get("secure_url"));
      }

      _mascotaDao.save(mascotaToUpdate);

      response.setData(_mapper.map(mascotaToUpdate, MascotaDto.class));

    } catch (Exception e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }
    return response;
  }
}
