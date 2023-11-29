package com.evaluacion.prueba.tec.util;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * Se crea anotacion para ver si con este enfoque se logra leer la propiedad desde el archivo  y se lo puedo inyectar al atributo de la entidad
 * @author Alex
 *
 */
public class PatronClaveValidator implements ConstraintValidator<PatronClave, String> {

    private String patronClave;

    @Override
    public void initialize(PatronClave constraintAnnotation) {
        this.patronClave = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Implementa tu lógica de validación aquí usando el patrón cargado desde el archivo de propiedades
        return value != null && value.matches(patronClave);
    }
}
