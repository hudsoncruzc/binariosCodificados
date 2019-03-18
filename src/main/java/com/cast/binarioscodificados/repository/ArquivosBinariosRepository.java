package com.cast.binarioscodificados.repository;

import org.springframework.data.repository.CrudRepository;

import com.cast.binarioscodificados.model.ArquivosBinarios;

public interface ArquivosBinariosRepository extends CrudRepository<ArquivosBinarios, String>{

	ArquivosBinarios findById(Long id);
	

}
