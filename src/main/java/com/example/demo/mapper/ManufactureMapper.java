package com.example.demo.mapper;

import com.example.demo.dto.ManufactureDTO;
import com.example.demo.model.Manufacture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManufactureMapper {
//    ManufactureMapper INSTANCE = Mappers.getMapper( ManufactureMapper.class );
    ManufactureDTO ManuToManuDTO(Manufacture manufacture);
    Manufacture ManuDTOToManu(ManufactureDTO manufactureDTO);
    @Mapping ( target = "id", ignore = true )
    List<ManufactureDTO> ManuListToManuDTOList(List<Manufacture> manufactureList);
}
