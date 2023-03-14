package gg.fresher.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import gg.fresher.demo.dtos.DantocDto;
import gg.fresher.demo.service.model.DanTocModel;
import gg.fresher.demo.utils.Paging;

public interface DanTocService {

    /**
     * @param model
     * @return
     */
	ResponseEntity<List<Object>> create(DanTocModel model);
    
	ResponseEntity<List<Object>> update(DanTocModel model, Long id);
	
	ResponseEntity<List<Object>> delete(Long id);

    /**
     * @param pageable
     * @return
     */
    Paging<DantocDto> getList(Pageable pageable, String quocgia, String chauluc);
	
}
