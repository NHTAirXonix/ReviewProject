package gg.fresher.demo.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;

import gg.fresher.demo.dtos.QuocGiaDto;
import gg.fresher.demo.entities.QuocGia;
import gg.fresher.demo.service.model.QuocGiaModel;

public interface QuocGiaMapper {
	
//	@Mapping(source = "quocGia.id", target = "quocGiaId")
    QuocGiaDto toDto(QuocGia entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "tenQuocGia", target = "tenQuocGia")
    @Mapping(source = "thudo", target = "thudo")
    @Mapping(source = "description", target = "description")
    
//    @Mapping(source = "quocGiaId", target = "quocGia")
    QuocGia toEntity(QuocGiaModel model);
}
