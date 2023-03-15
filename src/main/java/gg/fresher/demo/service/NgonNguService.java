package gg.fresher.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import gg.fresher.demo.dtos.NgonNguDto;
import gg.fresher.demo.dtos.simpleDto.NgonNguSDto;
import gg.fresher.demo.service.model.NgonNguModel;
import gg.fresher.demo.utils.Paging;

public interface NgonNguService {

    /**
     * @param model
     * @return
     */
	ResponseEntity<List<Object>> create(NgonNguModel model, List<Long> quocGias);
    
	ResponseEntity<NgonNguDto> update(NgonNguModel model, Long id);
	
	ResponseEntity<NgonNguDto> delete(Long id);

	ResponseEntity<List<Object>> detail(Long id);
    /**
     * @param pageable
     * @return
     */
    Paging<NgonNguSDto> getList(Pageable pageable, String quocgia);
}
