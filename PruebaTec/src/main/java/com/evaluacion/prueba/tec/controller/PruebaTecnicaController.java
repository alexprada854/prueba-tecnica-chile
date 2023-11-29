package com.evaluacion.prueba.tec.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.evaluacion.prueba.tec.model.Registro;
import com.evaluacion.prueba.tec.repository.RegistroRepository;
import com.evaluacion.prueba.tec.util.GenerateJWT;
import com.evaluacion.prueba.tec.util.InvalidUserDataException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RestController
@Validated
@RequestMapping("/api/prueba")
public class PruebaTecnicaController {
	private final RegistroRepository repository;
	private final String respuesta = "{\"mensaje\": \"respuesta\"}";
	
	@Autowired
	public PruebaTecnicaController(RegistroRepository repository) {
		this.repository=repository;
	}
	@PostMapping("/saveReg")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> GuardarRegistro(@RequestBody Registro registro) {
		String ans=respuesta.replace("mensaje", "INSERT").replace("respuesta", "OK");
		System.out.println("Llego "+registro.toString());
		try {
			registro.setToken(GenerateJWT.createJWT("1", registro.getName(), registro.getPassword(), 1000l));
			String validacion = validar(registro);
			if(validacion.equals("")) {
				Registro reg = repository.save(registro);
				//List<Registro> res = repository.findByName(registro.getName());
				//if(res !=null && res.size()>0) {
					ObjectMapper objectMapper = new ObjectMapper();
					String resJson = objectMapper.writeValueAsString(reg);
					ans=respuesta.replace("mensaje", "INSERT").replace("respuesta", resJson);
					return ResponseEntity.ok(ans);
				/*} else {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("Error interno");
				}*/
			} else {
				throw new InvalidUserDataException(validacion, new Exception());
			}
		} catch(DataIntegrityViolationException e) {
			ans = respuesta.replace("mensaje", "INSERT ERROR").replace("respuesta","Correo ya existe");
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(ans);
		} catch(ConstraintViolationException e) {
			ans = respuesta.replace("mensaje", "INSERT ERROR").replace("respuesta",e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ans);
		} 
		catch(Exception e) {
			ans = respuesta.replace("mensaje", "INSERT").replace("respuesta",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ans);
		}
	}
	
	/**
	 * Recorrer las validaciones que se configuraron en la definicion de la entidad 
	 * @param reg
	 * @return Devueleve la primera restriccion que se encuentre
	 */
	private String validar(Registro reg) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		String ans="";

		// Ejecutamos validación
		Set<ConstraintViolation<Registro>> violations = validator.validate(reg);

		for (ConstraintViolation<Registro> violation : violations) {
		    System.out.println(violation.getMessage());
		    if(ans.equals("")) {
		    	ans=violation.getMessage();
		    }
		}
		return ans;
	}

}
