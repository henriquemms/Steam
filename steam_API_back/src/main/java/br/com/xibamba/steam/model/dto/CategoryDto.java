package br.com.xibamba.steam.model.dto;

import br.com.xibamba.steam.model.entity.Category;
import lombok.Data;

@Data
public class CategoryDto {
	
	private Long id;
	private String name;
	
	public Category transformDtoCategoryWithId() {
		
		Category category = new Category();
		category.setId(this.getId());
		category.setName(this.getName());
		
		return category;
	}
	
	public Category transformDtoCategoryWithoutId() {
		
		Category category = new Category();
		category.setName(this.getName());
		
		return category;
	}

}
