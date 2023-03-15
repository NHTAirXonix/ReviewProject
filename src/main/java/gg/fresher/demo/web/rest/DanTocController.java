package gg.fresher.demo.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gg.fresher.demo.dtos.DantocDto;
import gg.fresher.demo.service.DanTocService;
import gg.fresher.demo.service.model.DanTocModel;
import gg.fresher.demo.utils.Paging;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/dantoc")
public class DanTocController {
	
	@Autowired
	private DanTocService danTocService;
	
    @Operation(summary = "get list of dantoc")
    @GetMapping
    public ResponseEntity<Paging<DantocDto>> getListDanToc(@ParameterObject Pageable pageable,
    		@RequestParam(name = "quocgia", defaultValue = "") String quocgia,
    		@RequestParam(name = "chauluc", defaultValue = "") String chauluc) {
        return ResponseEntity.ok().body(danTocService.getList(pageable, quocgia,chauluc));
    }
	    
    @Operation(summary = "Create dantoc")
    @PostMapping("/create")
    public ResponseEntity<List<Object>> createDanToc(@RequestBody @Valid DanTocModel model) {
        return danTocService.create(model);
    }
	    
    @Operation(summary = "Update dantoc")
    @PutMapping("/update")
    public ResponseEntity<List<Object>> updateDanToc(@RequestBody @Valid DanTocModel model,
    		@RequestParam(name = "id", defaultValue = "") Long id) {
        return danTocService.update(model, id);
    }
    @Operation(summary = "Detail dantoc")
    @GetMapping("/chitiet")
	public ResponseEntity<List<Object>> xemChiTiet(@RequestParam(name = "id") Long id) {
    	return danTocService.detail(id);
    }
    
    @Operation(summary = "Delete dantoc")
    @DeleteMapping("/delete")
    public ResponseEntity<List<Object>> deleteDanToc(@RequestParam(name = "id", defaultValue = "") Long id) {
        return danTocService.delete(id);
    }
}
