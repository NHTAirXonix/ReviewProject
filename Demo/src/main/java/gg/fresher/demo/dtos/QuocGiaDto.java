package gg.fresher.demo.dtos;

import java.util.Set;

import gg.fresher.demo.entities.DanToc;
import gg.fresher.demo.entities.NgonNgu;
import lombok.Data;

@Data
public class QuocGiaDto {

	    private Long id;

	    private String tenQuocGia;
	    
	    private String thuDo;
	    
	    private String description;
	    
	    private Set<DantocDto> danTocs;
	    
	    private Set<NgonNguDto> ngonNgus;
	    
	    private Long chauLucId;
}
