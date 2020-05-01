package br.com.xibamba.steam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xibamba.steam.model.dto.DeveloperDto;
import br.com.xibamba.steam.model.entity.Developer;
import br.com.xibamba.steam.service.DeveloperService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/developer")
public class DeveloperController {
	
	@Autowired
	private DeveloperService developerService;
	
	@GetMapping("/list-all") //Abre o método para a requisição get na url descrita
	@ApiOperation("Lista todas as desenvolvedoras")
	public List<Developer> listAll(){	
		return developerService.getDevelopers();
	}
	
	@PostMapping("/create") //Abre o método para a requisição get na url descrita
	@ApiOperation("Cria uma nova desenvolvedora")
	public Developer createDevelopers(@RequestBody DeveloperDto developerDto){	
		
		Developer developer = developerDto.transformDtoDeveloperWithoutId();
		
		this.developerService.create(developer);
		return developer;
	}
	
	@PostMapping("/update") //Abre o método para a requisição get na url descrita
	@ApiOperation("Atualiza desenvolvedora existente")
	public Developer updateDevelopers(@RequestBody DeveloperDto developerDto){	
		
		Developer developer = developerDto.transformDtoDeveloperWithId();

		
		this.developerService.update(developer);
		return developer;
	}
	
	@PostMapping("/deleteLogical") //Abre o método para a requisição get na url descrita
	@ApiOperation("Apaga desenvolvedora existente")
	public Boolean deleteLogicalDevelopers(@RequestParam Long developerId){	
		return this.developerService.deleteLogical(developerId);
	}
	
	@PostMapping("/deletePhysical/{id}") //Abre o método para a requisição get na url descrita
	@ApiOperation("Apaga desenvolvedora existente")
	public Boolean deletePhysicalDevelopers(@PathVariable(name = "id") Long developerId){	
		return this.developerService.deletePhysical(developerId);
	}
	
	
}
