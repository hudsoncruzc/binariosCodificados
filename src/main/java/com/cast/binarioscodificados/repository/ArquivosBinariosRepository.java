package com.cast.binarioscodificados.repository;

import org.springframework.data.repository.CrudRepository;

import com.cast.binarioscodificados.model.ArquivosBinarios;

/**
 * 
 * Repository ArquivosBinarios
 *
 * @author <a href="mailto:hcamposcruz@gmail.com">Hudson de Campos Cruz</a>.
 */

public interface ArquivosBinariosRepository extends CrudRepository<ArquivosBinarios, String>{

	/**
	 * 
	 * Metodo pesquiva dados arquivo binário.
	 *
	 * @author <a href="mailto:hcamposcruz@gmail.com">Hudson de Campos Cruz</a>.
	 * @param id
	 * @return
	 * ArquivosBinarios
	 */

	ArquivosBinarios findById(Long id);
	
}
