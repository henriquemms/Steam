package br.com.xibamba.steam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xibamba.steam.model.dto.CategoryDto;
import br.com.xibamba.steam.model.entity.Category;
import br.com.xibamba.steam.service.CategoryService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list-all")
	@ApiOperation("Lista todas as categorias")
	public List<Category> listAll(){
		return categoryService.getCategory();
	}
	
	@PostMapping("/create")
	@ApiOperation("Cria novas categorias")
	public Category create(@RequestBody CategoryDto categoryDto) {
		
		Category category = categoryDto.transformDtoCategoryWithoutId();
		
		this.categoryService.createCategory(category);
		
		return category;
	}
	
	@PostMapping("/update") //Abre o método para a requisição get na url descrita
	@ApiOperation("Atualiza categoria existente")
	public Category updateDevelopers(@RequestBody CategoryDto categoryDto){	
		
		Category category = categoryDto.transformDtoCategoryWithId();
		
		this.categoryService.update(category);
		return category;
	}
	
	@PostMapping("/deleteLogical") //Abre o método para a requisição get na url descrita
	@ApiOperation("Apaga categoria existente")
	public Boolean deleteLogicalCategory(@RequestBody Long developerId){	
		return this.categoryService.deletePhysical(developerId);
	}
	
	@PostMapping("/deletePhysical") //Abre o método para a requisição get na url descrita
	@ApiOperation("Apaga categoria existente")
	public Boolean deletePhysicalCategory(@RequestBody Long developerId){	
		return this.categoryService.deletePhysical(developerId);
	}

}
