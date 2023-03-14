package gg.fresher.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import gg.fresher.demo.dtos.NgonNguDto;
import gg.fresher.demo.dtos.simpleDto.NgonNguSDto;
import gg.fresher.demo.entities.NgonNgu;
import gg.fresher.demo.entities.QNgonNgu;
import gg.fresher.demo.entities.QuocGia;
import gg.fresher.demo.entities.relationship.NgonNguQuocGia;
import gg.fresher.demo.entities.relationship.QNgonNguQuocGia;
import gg.fresher.demo.mappers.NgonNguMapper;
import gg.fresher.demo.repositories.NgonNguJpaRepository;
import gg.fresher.demo.repositories.NgonNguQuocGiaJpaRepository;
import gg.fresher.demo.repositories.QuocGiaJpaRepository;
import gg.fresher.demo.service.NgonNguService;
import gg.fresher.demo.service.model.NgonNguModel;
import gg.fresher.demo.utils.Paging;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NgonNguServiceImpl implements NgonNguService {
	
	@PersistenceContext
	EntityManager entityManager;
	private final NgonNguJpaRepository repository;
	private final QuocGiaJpaRepository quocGiaJpaRepository;
	private final NgonNguQuocGiaJpaRepository ngonNguQuocGiaJpaRepository;
    private final NgonNguMapper mapper;
	
    
    //Lấy danh sách tất cả các đối tượng chưa xoá và thoả mãn theo yêu cầu tìm kiếm
	@Override
	public Paging<NgonNguSDto> getList(Pageable pageable, String quocgia) {

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		quocgia=quocgia.trim();
		QNgonNgu qNgonNgu = QNgonNgu.ngonNgu;
		QNgonNguQuocGia qNgonNguQuocGia = QNgonNguQuocGia.ngonNguQuocGia;
		List<NgonNgu> finalList = new ArrayList<NgonNgu>();
		if(quocgia.equals("")) {
			finalList = queryFactory.selectFrom(qNgonNgu)
					.where(qNgonNgu.deleted.eq(false))
					.fetch();
		} else {
			QuocGia quocGia = quocGiaJpaRepository.findByName(quocgia);
			
			List<NgonNgu> ngonNgus = queryFactory.selectFrom(qNgonNgu)
					.where(qNgonNgu.deleted.eq(false))
					.fetch();
			
			List<NgonNguQuocGia> ngonNguQuocGias = queryFactory.selectFrom(qNgonNguQuocGia)
					.where(qNgonNguQuocGia.quocGiaId.eq(quocGia.getId()))
					.fetch();
			
			for (NgonNguQuocGia ngonNguQuocGia : ngonNguQuocGias) {
				for (NgonNgu ngonNgu : ngonNgus) {
					if(ngonNguQuocGia.getNgonNguId()==ngonNgu.getId()) {
						finalList.add(ngonNgu);
					}
				}
			}
		}
		Page<NgonNguSDto> pages = new PageImpl<NgonNguSDto>(converList(finalList), pageable, pageable.getPageSize());
		return Paging.of(pages);
	}
	
	//Lưu đối tượng mới vào database
	@Override
	public ResponseEntity<List<Object>> create(NgonNguModel model, List<Long> quocGias) {
		List<Object> list = new ArrayList<>();
		try {
			NgonNgu ngonNgu = mapper.toEntity(model);
			if (CheckUniqueCode(ngonNgu.getMaNgonNgu())) {
				list.add("Tạo mới không thành công - Trùng mã định danh");
				return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
			} else {
				ngonNgu.setMaNgonNgu(ngonNgu.getMaNgonNgu().toUpperCase());
				ngonNgu.setQuocGias(getListQuocGia(quocGias));
				NgonNgu newEntity = repository.save(ngonNgu);
				for (QuocGia item : getListQuocGia(quocGias)) {
					NgonNguQuocGia ngonNguQuocGia = new NgonNguQuocGia(); 
					ngonNguQuocGia.setNgonNguId(ngonNgu.getId());
					ngonNguQuocGia.setQuocGiaId(item.getId());
					ngonNguQuocGiaJpaRepository.save(ngonNguQuocGia);
				}
				list.add("Tạo mới thành công");
				list.add(newEntity);
				return new ResponseEntity<>(list,HttpStatus.OK);
			}
		} catch (Exception e) {
			list.add("Tạo mới không thành công - Sai kiểu dữ liệu");
			return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	// Cập nhật lại dữ liệu cho đối tượng và lưu lại vào database
	@Override
	public ResponseEntity<NgonNguDto> update(NgonNguModel model, Long id) {
		NgonNgu ngonNgu = mapper.toEntity(model);
		ngonNgu.setId(id);
//		ngonNgu.setQuocGia(quocGiaJpaRepository.findById(model.getQuocGiaId()).get());
		repository.save(ngonNgu);
		return new ResponseEntity<>(mapper.toDto(ngonNgu),HttpStatus.OK);
	}
	
	
	
	
	// Xoá đối tượng trong database
	@Override
	public ResponseEntity<NgonNguDto> delete(Long id) {
		if (repository.findById(id).isPresent()) {
			NgonNgu ngonNgu = repository.findById(id).get();
			ngonNgu.setId(id);
			ngonNgu.setDeleted(true);
			repository.save(ngonNgu);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}	
	
	// Kiểm tra trùng mã định danh nhằm tránh lưu đối tượng có mã định danh giống nhau
	public Boolean CheckUniqueCode(String code) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QNgonNgu qNgonNgu = QNgonNgu.ngonNgu;
		List<NgonNgu> ngonNgus = queryFactory.selectFrom(qNgonNgu)
				.where(qNgonNgu.deleted.eq(false)).fetch();
		List<String> codes = new ArrayList<>();
		for (NgonNgu ngonNgu : ngonNgus) {
			codes.add(ngonNgu.getMaNgonNgu());
		}
		return codes.contains(code.toUpperCase());
	}
	
	public List<NgonNguSDto> converList(List<NgonNgu> beforeList) {
		List<NgonNguSDto> dtos = new ArrayList();
		for (NgonNgu item : beforeList) {
			NgonNguSDto dto = new NgonNguSDto();
			dto.setId(item.getId());
			dto.setMaNgonNgu(item.getMaNgonNgu());
			dto.setTenNgonNgu(item.getTenNgonNgu());
			dto.setDescription(item.getDescription());
			dtos.add(dto);
		}
		return dtos;
	}
	
	// Dùng để lấy danh sách đối tượng thông qua list mã định danh đã liên kết mối quan hệ trong database 
	public List<QuocGia> getListQuocGia(List<Long> listId) {
		List<QuocGia> quocGias = new ArrayList<>();
		for (Long quocGiaId : listId) {
			quocGias.add(quocGiaJpaRepository.findById(quocGiaId).get());
		}
		return quocGias;		
	}
	
	
	// Dùng để lọc ra danh sách những phần tử đã tồn tại trước kia trong database nhằm tránh lưu dữ liệu trùng lặp
	public List<QuocGia> checkListQuocGia(List<QuocGia> quocGias, Long id) {
		if(repository.findById(id).isPresent()) {
			List<QuocGia> finalList = new ArrayList<>();
			List<QuocGia> before = repository.findById(id).get().getQuocGias();
			for (QuocGia quocGia : quocGias) {
				if(!before.contains(quocGia)) {
					finalList.add(quocGia);
				}
			}
			return finalList;
		} else {
			return quocGias;
		}
	}
}
