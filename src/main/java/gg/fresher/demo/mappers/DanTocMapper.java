package gg.fresher.demo.mappers;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gg.fresher.demo.dtos.DantocDto;
import gg.fresher.demo.entities.DanToc;
import gg.fresher.demo.service.model.DanTocModel;

@Mapper
public interface DanTocMapper {
	
	@Mapping(source = "quocGia.id", target = "quocGiaId")
	@Mapping(source = "chauLuc.id", target = "chauLucId")
	DantocDto toDto(DanToc entity);
	
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "tenDanToc", target = "tenDanToc")
    @Mapping(source = "maDanToc", target = "maDanToc")
    @Mapping(source = "soLuongNguoi", target = "soLuongNguoi")
    @Mapping(source = "description", target = "description")
    
    DanToc toEntity(DanTocModel model);

}
