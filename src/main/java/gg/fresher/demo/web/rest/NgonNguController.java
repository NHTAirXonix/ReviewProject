/**
 * 
 */
package gg.fresher.demo.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gg.fresher.demo.dtos.NgonNguDto;
import gg.fresher.demo.entities.NgonNgu;
import gg.fresher.demo.service.NgonNguService;
import gg.fresher.demo.service.model.NgonNguModel;
import gg.fresher.demo.utils.Paging;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/ngonngu")
public class NgonNguController {

    @Autowired
    private NgonNguService service;

    @Operation(summary = "get list of ngonngu")
    @GetMapping
    public ResponseEntity<Paging<NgonNguDto>> getListNgonNgu(@ParameterObject Pageable pageable,
    		@RequestParam(name = "quocgia", defaultValue = "") String quocgia) {
        return ResponseEntity.ok().body(service.getList(pageable, quocgia));
    }
    
    @Operation(summary = "Create ngonngu")
    @PostMapping("/create")
    public ResponseEntity<List<Object>> createNgonNgu(@RequestBody @Valid NgonNguModel model ,
    		@RequestParam(name = "quocGias", defaultValue = "") List<Long> quocGias) {
    	try {
    		return service.create(model, quocGias);
    	} catch (Exception  ex) {
    	      BindingResult bindingResult = ((BindException) ex).getBindingResult();
    	      String errorMessage = bindingResult.getAllErrors().stream()
    	          .map(DefaultMessageSourceResolvable::getDefaultMessage)
    	          .collect(Collectors.joining(", "));
    	      List<Object> list = new ArrayList<>();
    	list.add(errorMessage);
    	list.add("Tạo mới không thành công - Sai dữ liệu đầu vào");
    	return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
    	}
    }
    
    @Operation(summary = "Update ngonngu")
    @PostMapping("/update")
    public ResponseEntity<NgonNguDto> updateNgonNgu(@RequestBody @Valid NgonNguModel model,
    		@RequestParam(name = "id", defaultValue = "") Long id) {
        return service.update(model, id);
    }
    
    @Operation(summary = "Delete ngonngu")
    @PostMapping("/delete")
    public ResponseEntity<NgonNguDto> deleteNgonNgu(@RequestParam(name = "id", defaultValue = "") Long id) {
        return service.delete(id);
    }
}
