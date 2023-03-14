package gg.fresher.demo.dtos;

import lombok.Data;

@Data
public class DantocDto {

	private Long id;

    private String tenDanToc;

    private String maDanToc;
    
    private String soLuongNguoi;

    private String description;

    private Long quocGiaId;
    
    private Long chauLucId;
}
