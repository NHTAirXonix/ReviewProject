package gg.fresher.demo.entities.relationship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "quocgia_ngonngu")
@SuperBuilder
@NoArgsConstructor
public class NgonNguQuocGia{
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "quocgia_id")
	private Long quocGiaId;
	
	@Column(name = "ngonngu_id")
    private Long ngonNguId;

	
}
