package br.com.xibamba.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xibamba.steam.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
