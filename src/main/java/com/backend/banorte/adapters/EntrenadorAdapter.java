package com.backend.banorte.adapters;

import com.backend.banorte.domain.dto.EntrenadorDto;
import com.backend.banorte.domain.entity.EntrenadorEntity;
import com.backend.banorte.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EntrenadorAdapter {

    @Autowired
    EntrenadorRepository entrenadorRepository;


    public EntrenadorDto obtenerEntrenador (String nombre){
        EntrenadorEntity entity = entrenadorRepository.obtenerNombreEntrenador(nombre);
        if (entity == null){
            return null;
        }
        EntrenadorDto entrenadorDto = new EntrenadorDto ();
        entrenadorDto.setId(entity.getId());
        entrenadorDto.setNombre(entity.getNombre());
        entrenadorDto.setPassword(entity.getPassword());
        return  entrenadorDto;
    }

    public void agregarNameYPassEntrenador (String nombre,String password){
        entrenadorRepository.agregarEntrenador(nombre, password);
    }

    public List<EntrenadorDto> obtenerListEntrenador (String nombre){
        List<EntrenadorEntity> entityList = entrenadorRepository.obtenerListNombreEntrenador(nombre);
        if (entityList == null){
            return null;
        }
        List<EntrenadorDto> entrenadorDtoList = new ArrayList<>();
        for (EntrenadorEntity entrenadorEntity: entityList) {
            EntrenadorDto entrenadorDto = new EntrenadorDto();
            entrenadorDto.setId(entrenadorEntity.getId());
            entrenadorDto.setNombre(entrenadorEntity.getNombre());
            entrenadorDto.setPassword(entrenadorEntity.getPassword());
            entrenadorDtoList.add(entrenadorDto);
        }
        return entrenadorDtoList;
    }

}
