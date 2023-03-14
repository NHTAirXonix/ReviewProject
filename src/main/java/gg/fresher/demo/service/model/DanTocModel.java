package gg.fresher.demo.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DanTocModel {

	@NotBlank
    @Size(min = 5, max = 255)
    private String tenDanToc;

	@NotBlank
    @Size(min = 5, max = 255)
    private String maDanToc;
    
	@NotBlank
    @Size(min = 5, max = 255)
    private String soLuongNguoi;

    @Size(max = 500)
    private String description;

    private Long quocGiaId;
    
    private Long chauLucId;
    
}
