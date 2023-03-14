package gg.fresher.demo.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gg.fresher.demo.dtos.NgonNguDto;
import gg.fresher.demo.dtos.simpleDto.NgonNguSDto;
import gg.fresher.demo.entities.NgonNgu;
import gg.fresher.demo.service.model.NgonNguModel;

@Mapper
public interface NgonNguMapper {
	
//	@Mapping(source = "quocGia.id", target = "quocGiaId")
    NgonNguDto toDto(NgonNgu entity);
    
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "tenNgonNgu", target = "tenNgonNgu")
    @Mapping(source = "maNgonNgu", target = "maNgonNgu")
    @Mapping(source = "description", target = "description")
    NgonNguSDto toSDto(NgonNgu entity);
    
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "tenNgonNgu", target = "tenNgonNgu")
    @Mapping(source = "maNgonNgu", target = "maNgonNgu")
    @Mapping(source = "description", target = "description")
    
//  @Mapping(source = "quocGiaId", target = "quocGia")
    NgonNgu toEntity(NgonNguModel model);
    

    default NgonNgu fromId(Long id) {
        if (id == null) {
            return null;
        }
        NgonNgu ngonNgu = new NgonNgu();
        ngonNgu.setId(id);
        return ngonNgu;
    }
}
