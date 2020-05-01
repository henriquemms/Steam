package br.com.xibamba.steam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.xibamba.steam.model.entity.Developer;
import br.com.xibamba.steam.repository.DeveloperRepository;


@Service
public class DeveloperService {

	@Autowired
	private DeveloperRepository developerRepository;
	
	public List<Developer> getDevelopers(){	
		
		Developer developer = new Developer();
		
		developer.getDataControl().setDeleted(false);
		
		Example<Developer> example = Example.of(developer);
				
		return this.developerRepository.findAll(example);
	}
	
	public Developer create(Developer developer){	
	
		if(developer.getId() != null) {
			throw new ServiceException("Não é possível salvar, pois o id está preenchido");
		}
		
		developer.getDataControl().markCreated(new Date());
		this.developerRepository.save(developer);
	
		return developer;
	}

	/**
	 * Atualiza uma desenvolvedora
	 * @param developer Informa um objeto developer.
	 * @return retorna o objeto developer alterado.
	 */
	public Developer update(Developer developer){	
		
		if(developer.getId() == null) {
			throw new ServiceException("Não é possível salvar, pois o id não está preenchido");
		}
		
		Optional<Developer> currentDeveloper = this.developerRepository.findById(developer.getId());
		
		if(!currentDeveloper.isPresent()) {
			throw new ServiceException("Desenvolvedora não existe. Impossível atualizar.");
			
		}
		
		developer.setDataControl(currentDeveloper.get().getDataControl());
		developer.getDataControl().markUpdated(new Date());
		this.developerRepository.save(developer);
		
		return developer;
	}
		
	/**
	 * Faz a exclusão lógica da da desenvoldora.
	 * @param developerId : (Long) Informar o identificador da desenvolvedora.
	 * @return Retorna true para sucesso.
	 */
	public boolean deleteLogical(Long developerId) {
		
		Optional<Developer> currentDeveloper = this.developerRepository.findById(developerId);
		
		if(!currentDeveloper.isPresent()) {
			throw new ServiceException("Desenvolvedora não existe. Impossível apagar.");	
		}
		
		currentDeveloper.get().getDataControl().markDeleted(new Date());
		this.developerRepository.save(currentDeveloper.get());
		
		return  true;
	}

	
	/**
	 * Apaga do banco a desenvolvedora (exclusão física).
	 * @param developerId : (Long) Informar o identificador da desenvolvedora.
	 * @return Retorna true para sucesso.
	 */
	public Boolean deletePhysical(Long developerId){	
		
		Optional<Developer> currentDeveloper = this.developerRepository.findById(developerId);
		
		if(!currentDeveloper.isPresent()) {
			throw new ServiceException("Desenvolvedora não existe. Impossível deletar.");
			
		}
		
		this.developerRepository.delete(currentDeveloper.get());
	
		return true;
	}
	
}
