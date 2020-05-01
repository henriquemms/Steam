package br.com.xibamba.steam.model.dto;

import java.util.Date;
import java.util.List;

import br.com.xibamba.steam.model.entity.Category;
import br.com.xibamba.steam.model.entity.Developer;
import br.com.xibamba.steam.model.entity.Game;
import lombok.Data;

@Data
public class GameDto {
	
	private Long id;
	private String name;
	private String description;
	private Date releaseDate;
	private Long idDeveloper;
	private List<CategoryDto> categories;
	
	public Game transformDtoGameWithId() {
		
		Game game = new Game();
		game.setId(this.getId());
		game.setName(this.getName());
		game.setDescription(this.getDescription());
		game.setReleaseDate(this.getReleaseDate());
		
		game.setDeveloper(new Developer());
		
		game.getDeveloper().setId(this.getIdDeveloper());
		
		//Pega a lista de category no DTO e transforma na entidade GAME
		for(CategoryDto category : categories) {
			game.getCategories().add(category.transformDtoCategoryWithId());
		}
		
		return game;
	}
	
	public Game transformDtoGameWithoytId() {
		
		Game game = new Game();
		game.setName(this.getName());
		game.setDescription(this.getDescription());
		game.setReleaseDate(this.getReleaseDate());
		
		game.setDeveloper(new Developer());
		
		game.getDeveloper().setId(this.getIdDeveloper());
		
		//Pega a lista de category no DTO e transforma na entidade GAME
		for(CategoryDto category : categories) {
			game.getCategories().add(category.transformDtoCategoryWithId());
		}
		
		return game;
	}

}
