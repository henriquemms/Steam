package br.com.xibamba.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xibamba.steam.model.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
