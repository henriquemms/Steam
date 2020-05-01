package br.com.xibamba.steam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.xibamba.steam.model.entity.Developer;
import br.com.xibamba.steam.model.entity.Game;
import br.com.xibamba.steam.repository.DeveloperRepository;
import br.com.xibamba.steam.repository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private DeveloperRepository developerRepository;
	
	public List<Game> listAll(){
		
		Game game = new Game();
		game.getDataControl().setDeleted(false);
		
		Example<Game> example = Example.of(game);
		
		return this.gameRepository.findAll(example);
	}
	
	public Game create(Game game){	
		
		if(game.getId() != null) {
			throw new ServiceException("Não é possível salvar, pois o id está preenchido");
		}
		
		validDeveloper(game.getDeveloper());
		
		game.getDataControl().markCreated(new Date());
		this.gameRepository.save(game);
	
		return game;
	}
	
	public Game update(Game game) {
		if(game.getId() == null) {
			throw new ServiceException("Não é possível salvar, pois o não id está preenchido");
		}
		
		validDeveloper(game.getDeveloper());
		
		Optional<Game> currentGame = this.gameRepository.findById(game.getId());
		
		game.setDataControl(currentGame.get().getDataControl());
		game.getDataControl().markUpdated(new Date());
		this.gameRepository.save(game);
		
		return game;	
	}
	
	public Boolean deleteLogical(Long gameId) {
		Optional<Game> currentGame = this.gameRepository.findById(gameId);
		
		if(!currentGame.isPresent()) {
			throw new ServiceException("Jogo não existe. Impossível deletar.");
		}
		
		currentGame.get().getDataControl().markDeleted(new Date());
		this.gameRepository.save(currentGame.get());
		
		return true;	
	}	
	
	public Boolean deletePhysical(Long gameId) {
		Optional<Game> currentGame = this.gameRepository.findById(gameId);
		
		if(!currentGame.isPresent()) {
			throw new ServiceException("Jogo não existe. Impossível deletar.");
		}
		
		this.gameRepository.delete(currentGame.get());
		
		return true;	
	}
	
	public Boolean validDeveloper(Developer developer) {
		
		if(developer == null || developer.getId() == null) {
			throw new ServiceException("Não é possível salvar, pois não foi informada a desenvolvedora do jogo.");
		}
		
		Optional<Developer> developerOpinal = this.developerRepository.findById(developer.getId());
		
		if(!developerOpinal.isPresent()) {
			throw new ServiceException("Desenvolvedora informada para o Game não existe. Impossível salvar o jogo.");
		}
		
		
		return true;
	}

}
