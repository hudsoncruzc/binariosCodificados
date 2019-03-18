package com.cast.binarioscodificados.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * 
 * Classe Entity ArquivosBinarios
 *
 * @author <a href="mailto:hcamposcruz@gmail.com">Hudson de Campos Cruz</a>.
 */

@Entity
public class ArquivosBinarios {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Lob
	private String right;
	
	@Lob
	private String left;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}
	
	
	
}
