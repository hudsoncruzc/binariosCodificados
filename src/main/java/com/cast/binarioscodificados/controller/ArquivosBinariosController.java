package com.cast.binarioscodificados.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cast.binarioscodificados.model.ArquivosBinarios;
import com.cast.binarioscodificados.model.ObjetoJson;
import com.cast.binarioscodificados.repository.ArquivosBinariosRepository;

@Controller
@RequestMapping("/v1/diff/{id}")
public class ArquivosBinariosController {
	
	@Autowired
	private ArquivosBinariosRepository arquivoDAO;

	
	private ArquivosBinarios saveDados(Long id, String lado, ObjetoJson dadosJson) {
		
		ArquivosBinarios arqDados = arquivoDAO.findById(id);
		
		if ("right".equals(lado)) {
			arqDados.setRight(dadosJson.getDados());
		}else {
			arqDados.setLeft(dadosJson.getDados());
		}
		return arqDados;
	}
	
	
	@PostMapping(value="/right")
	public ResponseEntity<?> saveRight(@PathVariable Long id, @RequestBody ObjetoJson dados){
		
		ArquivosBinarios arqDados = saveDados(id, "right", dados);
		return new ResponseEntity<>(arquivoDAO.save(arqDados), HttpStatus.OK);
		
	}

	@PostMapping(value="/left")
	public ResponseEntity<?> salvaEsquerda(@PathVariable Long id, @RequestBody ObjetoJson dados){
		
		ArquivosBinarios arqDados = saveDados(id, "left", dados);
		return new ResponseEntity<>(arquivoDAO.save(arqDados), HttpStatus.OK);
		
	}

	@GetMapping(value="/diff")
	private String diffArquivo(@PathVariable Long id) {
		
		ArquivosBinarios arqDados = arquivoDAO.findById(id);		
		return diffBase64(arqDados);		
		
	}

	
	
	private String diffBase64(ArquivosBinarios arqDados) {
		
		byte[] bytesLeft = arqDados.getLeft().getBytes();
		byte[] bytesRight = arqDados.getRight().getBytes();

		boolean difference = Arrays.equals(bytesLeft, bytesRight);

		String caracter = "";

		if (difference) {
			return "True";
			
		} else if (bytesLeft.length != bytesRight.length) {
			return "Arquivos não possuem o mesmo tamanho.";
			
		} else {
			
			byte differ = 0;
			
			for (int index = 0; index < bytesLeft.length; index++) {
				
				differ = (byte) (bytesLeft[index] ^ bytesRight[index]);
				
				if (differ != 0) {
					caracter = caracter + " " + index;
				}
			}
		}
		
		return "Foram encontrados os seguintes caracteres diferentes entre os dois arquivos --> " + caracter;
	}

}
