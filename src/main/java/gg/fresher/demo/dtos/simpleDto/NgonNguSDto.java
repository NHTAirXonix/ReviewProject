package gg.fresher.demo.dtos.simpleDto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class NgonNguSDto {
	
    private Long id;
    
    private String tenNgonNgu;

    private String maNgonNgu;

    private String description;
    
}
