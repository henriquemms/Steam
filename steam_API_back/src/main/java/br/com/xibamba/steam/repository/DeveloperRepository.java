package br.com.xibamba.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xibamba.steam.model.entity.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long>{

	
	
}
