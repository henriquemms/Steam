package br.com.xibamba.steam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xibamba.steam.model.dto.GameDto;
import br.com.xibamba.steam.model.entity.Game;
import br.com.xibamba.steam.service.GameService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping("/list")
	public List<Game> getGames(){
		return this.gameService.listAll();
	}
	
	@PostMapping("/create")
	@ApiOperation("Cria novos jogos")
	public Game create(@RequestBody GameDto gameDto) {
		
		Game game = gameDto.transformDtoGameWithoytId();
		
		
		//validação de categoria existente e ativa 
		
		this.gameService.create(game);
		return game;
	}
	
	@PostMapping("/update")
	@ApiOperation("Atualiza jogos")
	public Game update(@RequestBody GameDto gameDto) {
		
		Game game = gameDto.transformDtoGameWithId();
		
		this.gameService.update(game);
		return game;
	}
	
	@PostMapping("/deleteLogical")
	@ApiOperation("Apaga jogos")
	public Boolean deleteLogicalGame(@RequestBody Long gameId) {
		return this.gameService.deletePhysical(gameId);
	}
	
	@PostMapping("/deletePhysical")
	@ApiOperation("Apaga jogos")
	public Boolean deletePhysicalGame(@RequestBody Long gameId) {
		return this.gameService.deletePhysical(gameId);
	}


}
