package br.com.xibamba.steam.model.dto;

import java.util.Date;

import br.com.xibamba.steam.model.entity.Developer;
import lombok.Data;

@Data
public class DeveloperDto {
	
	private Long id;
	private String name;
	private Date foundationDate;
	
	public Developer transformDtoDeveloperWithId() {
		
		Developer developer = new Developer();
		developer.setId(this.getId());
		developer.setName(this.getName());
		developer.setFoundationDate(this.getFoundationDate());
		
		return developer;	
	}
	
	public Developer transformDtoDeveloperWithoutId() {
		
		Developer developer = new Developer();
		developer.setName(this.getName());
		developer.setFoundationDate(this.getFoundationDate());
		
		return developer;	
	}
	
	
}
