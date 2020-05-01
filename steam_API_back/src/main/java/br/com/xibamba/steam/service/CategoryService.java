package br.com.xibamba.steam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.xibamba.steam.model.entity.Category;
import br.com.xibamba.steam.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getCategory(){
		
		Category category = new Category();
		
		category.getDataControl().setDeleted(false);
		
		Example<Category> example = Example.of(category);
		
		return this.categoryRepository.findAll(example);
	}
	
	public Category createCategory(Category category) {
		if(category.getId() != null) {
			throw new ServiceException("Não é possível salvar, pois o id está preenchido.");
		}
		
		category.getDataControl().markCreated(new Date());
		this.categoryRepository.save(category);
		
		return category;
	}
	
	public Category update(Category category){	
		
		if(category.getId() == null) {
			throw new ServiceException("Não é possível salvar, pois o id não está preenchido");
		}
		
		Optional<Category> currentDeveloper = this.categoryRepository.findById(category.getId());
		
		if(!currentDeveloper.isPresent()) {
			throw new ServiceException("Desenvolvedora não existe. Impossível atualizar.");
			
		}
		
		category.setDataControl(currentDeveloper.get().getDataControl());
		category.getDataControl().markUpdated(new Date());
		this.categoryRepository.save(category);
		
		return category;
	}
	
	public Boolean deleteLogical(Long categoryId){
		Optional<Category> currentCategory = this.categoryRepository.findById(categoryId);
		
		if(!currentCategory.isPresent()) {
			throw new ServiceException("Desenvolvedora não existe. Impossível deletar.");
			
		}
		
		currentCategory.get().getDataControl().markDeleted(new Date());
		this.categoryRepository.save(currentCategory.get());
	
		return true;
	}	
	
	public Boolean deletePhysical(Long categoryId){
		Optional<Category> currentCategory = this.categoryRepository.findById(categoryId);
		
		if(!currentCategory.isPresent()) {
			throw new ServiceException("Desenvolvedora não existe. Impossível deletar.");
			
		}
		
		this.categoryRepository.delete(currentCategory.get());
	
		return true;
	}

}
