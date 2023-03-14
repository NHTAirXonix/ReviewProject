package gg.fresher.demo.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "chauluc")
@Getter
@SuperBuilder
@NoArgsConstructor
public class ChauLuc  extends AbstractAuditingEntity<Long> {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tenchauluc", length = 100)
    private String tenChauLuc;
    
    @Column(name = "soluongnguoi", length = 100)
    private String soLuongNguoi;

    @Column(name = "description", length = 500)
    private String description;
    
    @OneToMany(mappedBy = "chauLuc")
    @JsonIgnore
    private Set<QuocGia> quocGiaSet;
	
    
}
