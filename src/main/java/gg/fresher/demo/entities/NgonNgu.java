package gg.fresher.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "ngonngu")
@Getter
@Setter
//@SuperBuilder
@NoArgsConstructor
public class NgonNgu extends AbstractAuditingEntity<Long>  {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tenngonngu", length = 255)
    private String tenNgonNgu;

    @Column(name = "mangonngu", length = 100)
    private String maNgonNgu;

    @Column(name = "description", length = 500)
    private String description;
    
//    @ManyToOne
//    @JoinColumn(name = "id_quoc_gia",referencedColumnName = "id")
//    private QuocGia quocGia;
    
    @ManyToMany(mappedBy = "ngonNgus")
    @JsonBackReference
    private List<QuocGia> quocGias = new ArrayList<>();
}
