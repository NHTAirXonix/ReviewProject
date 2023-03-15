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

import gg.fresher.demo.dtos.DantocDto;
import gg.fresher.demo.entities.DanToc;
import gg.fresher.demo.entities.QDanToc;
import gg.fresher.demo.mappers.DanTocMapper;
import gg.fresher.demo.repositories.ChauLucJpaRepository;
import gg.fresher.demo.repositories.DanTocJpaRepository;
import gg.fresher.demo.repositories.QuocGiaJpaRepository;
import gg.fresher.demo.service.DanTocService;
import gg.fresher.demo.service.model.DanTocModel;
import gg.fresher.demo.utils.Paging;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DanTocServiceImpl implements DanTocService {

	@PersistenceContext
	EntityManager entityManager;
	private final DanTocJpaRepository danTocRepository;
	private final QuocGiaJpaRepository quocGiaRepository;
	private final ChauLucJpaRepository chauLucRepository;
    private final DanTocMapper mapper;
	
    @Override
	public Paging<DantocDto> getList(Pageable pageable, String quocgia, String chauluc) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QDanToc qDanToc = QDanToc.danToc;
		List<DanToc> danTocs = queryFactory.selectFrom(qDanToc)
				.where(qDanToc.deleted.eq(false)
				.and(qDanToc.quocGia.tenQuocGia.like("%" + quocgia + "%"))
				.and(qDanToc.chauLuc.tenChauLuc.like("%" + chauluc + "%")))
				.fetch();
		Page<DanToc> pages = new PageImpl<DanToc>(danTocs, pageable, pageable.getPageSize());
		Page<DantocDto> result = pages.map(mapper::toDto);
		return Paging.of(result);
	}
    
	@Override
	public ResponseEntity<List<Object>> create(DanTocModel model) {
		try {
			DanToc danToc = mapper.toEntity(model);
			if (quocGiaRepository.findById(model.getQuocGiaId()).isPresent()) {
				danToc.setQuocGia(quocGiaRepository.findById(model.getQuocGiaId()).get());
			} else {danToc.setQuocGia(null);}
			if (chauLucRepository.findById(model.getChauLucId()).isPresent()) {
				danToc.setChauLuc(chauLucRepository.findById(model.getChauLucId()).get());
			} else {danToc.setChauLuc(null);}
			DanToc newEntity = danTocRepository.save(danToc);
			List<Object> list = new ArrayList<>();
			list.add("Tạo mới thành công");
			list.add(newEntity);
			return new ResponseEntity<List<Object>>(list,HttpStatus.OK);
		} 
		catch(Exception e) {
			List<Object> list = new ArrayList<>();
			list.add("Tạo mới không thành công");
			return new ResponseEntity<List<Object>>(list,HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Object>> update(DanTocModel model, Long id) {
		List<Object> list = new ArrayList<>();
		try {
			DanToc danToc = mapper.toEntity(model);
			danToc.setId(id);
			danToc.setChauLuc(chauLucRepository.findById(model.getChauLucId()).get());
			danToc.setQuocGia(quocGiaRepository.findById(model.getQuocGiaId()).get());
			danTocRepository.save(danToc);
			list.add("Chỉnh sửa thành công");
			list.add(danToc);
			return new ResponseEntity<>(list,HttpStatus.OK);
		} catch(Exception e) {			
			list.add("Chỉnh sửa không thành công");
			return new ResponseEntity<List<Object>>(list,HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Object>> delete(Long id) {
		List<Object> list = new ArrayList<>();
		if (danTocRepository.findById(id).isPresent()) {
			DanToc danToc = danTocRepository.findById(id).get();
			danToc.setId(id);
			danToc.setDeleted(true);
			danTocRepository.save(danToc);
			list.add("Xoá thành công");
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			list.add("Xoá không thành công");
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Object>> detail(Long id) {
		List<Object> list = new ArrayList<>();
		if (danTocRepository.findById(id).isPresent()) {
			DanToc danToc = danTocRepository.findById(id).get();
			list.add("Lấy dữ liệu thành công");
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			list.add("Lấy dữ liệu không thành công");
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
	}
}
