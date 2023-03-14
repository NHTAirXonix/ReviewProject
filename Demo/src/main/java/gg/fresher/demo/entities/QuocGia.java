package gg.fresher.demo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @created Mar 2, 2023
 *
 */
@Entity
@Table(name = "quocgia")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class QuocGia  extends AbstractAuditingEntity<Long> {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenquocgia", length = 255)
    private String tenQuocGia;
    
    @Column(name = "thudo", length = 255)
    private String thuDo;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @OneToMany(mappedBy = "quocGia")
    @JsonIgnore
    private Set<DanToc> danTocs;
    
//    @OneToMany(mappedBy = "quocGia")
//    @JsonIgnore
//    private Set<NgonNgu> ngonNguSet;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "quocgia_ngonngu",
            joinColumns = @JoinColumn(name = "quocgia_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ngonngu_id", referencedColumnName = "id"))
    @JsonBackReference
    private List<NgonNgu> ngonNgus = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "id_chau_luc",referencedColumnName = "id")
    private ChauLuc chauLuc;
}
