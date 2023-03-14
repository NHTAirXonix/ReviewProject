package gg.fresher.demo.dtos;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class NgonNguDto {

    private Long id;
    
    private String tenNgonNgu;

    private String maNgonNgu;

    private String description;
    
    private Long quocGiaId;
    
    private List<QuocGiaDto> quocGias;
}
