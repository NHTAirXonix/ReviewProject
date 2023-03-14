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
public class NgonNguModel {

    @NotBlank(message = "Vui lòng điền 2 kí tự trở lên và không được điền khoảng trắng")
    @Size(min = 2, max = 255,message = "Vui lòng điền 2 kí tự trở lên và không được điền khoảng trắng")
    private String tenNgonNgu;
    
    @NotBlank
    @Size(min = 5, max = 255)
    private String maNgonNgu;

    @Size(max = 500)
    private String description;
    
    private Long quocGiaId;
}
