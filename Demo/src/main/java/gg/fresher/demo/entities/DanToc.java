package gg.fresher.demo.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "dantoc")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DanToc extends AbstractAuditingEntity<Long>  {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tendantoc", length = 255)
    private String tenDanToc;

    @Column(name = "madantoc", length = 100)
    private String maDanToc;
    
    @Column(name = "soluongnguoi", length = 100)
    private String soLuongNguoi;

    @Column(name = "description", length = 500)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "id_quoc_gia",referencedColumnName = "id")
    private QuocGia quocGia;
    
    @ManyToOne
    @JoinColumn(name = "id_chau_luc",referencedColumnName = "id")
    private ChauLuc chauLuc;
}
