package com.evaluacion.prueba.tec.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.evaluacion.prueba.tec.util.GenerateJWT;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
/**
 * Entidad principal que con los atributos necesarios para cargar y manipular el registro de usuario
 * @author Alex
 *
 */
@Entity
@JsonPropertyOrder({"id","name","email","password","phones"})
public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String patronClave= "^(?=.*[0-9])(?=.*[A-Z])[A-Za-z0-9]{5,}$";
	//@Value("${patron.clave:^(?=.*[0-9])(?=.*[A-Z])[A-Za-z0-9]{5,}$}")
    //private final String patronClave="${patron.clave:^(?=.*[0-9])(?=.*[A-Z])[A-Za-z0-9]{5,}$}";
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	//@Email(message = "El formato del correo electrónico no es válido")
    @NotBlank(message = "El correo electrónico no puede estar en blanco")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El formato del correo electrónico no es válido")
    @Column(unique = true) // Garantiza que el correo electrónico sea único en la base de datos
	private String email;
	@Pattern(regexp = patronClave, message = "La clave no cumple las condiciones minimas")
	//@PatronClave(value = "#{Registro.patronClave}")
	private String password;
//@OneToMany(cascade= {CascadeType.ALL}, mappedBy="registro")
//@JsonIgnoreProperties({"registro"})
	@OneToMany(cascade= {CascadeType.ALL}, orphanRemoval= true)
	private List<Telefono> phones;
	
	// campos adicionales
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	private String token;
	private boolean isActive;	
	
	public Registro() {
		phones = new ArrayList<Telefono>();
		created = new Date();
		modified = new Date();
		lastLogin = new Date();
		//token = GenerateJWT.createJWT(String.valueOf(this.id), this.name, this.password, 1000l);
	}
	
	public Registro(Integer id, String name, String email, String password/*, List<Phone> phones*/) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = new ArrayList<Telefono>();
		created = new Date();
		modified = new Date();
		lastLogin = new Date();
		token = GenerateJWT.createJWT(String.valueOf(this.id), this.name, this.password, 1000l);
		
	}

	public Registro(Integer id, String name, String email, String password, List<Telefono> phones) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = new ArrayList<Telefono>(phones);
		created = new Date();
		modified = new Date();
		lastLogin = new Date();
		token = GenerateJWT.createJWT(String.valueOf(this.id), this.name, this.password, 1000l);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Telefono> getPhones() {
		return phones;
	}

	public void setPhones(List<Telefono> phones) {
		this.phones = phones;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Registro [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phones="
				+ String.valueOf(phones.size()) + "]";
	}
	@Entity
	public static class Telefono {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		private String number;
		@JsonProperty("citycode")
		private int cityCode;
		@JsonProperty("contrycode")
		private String countryCode;
		
		
		public Telefono() {}
		public Telefono(String number, int cityCode, String countryCode) {
			this.number = number;
			this.cityCode = cityCode;
			this.countryCode = countryCode;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public int getCityCode() {
			return cityCode;
		}
		public void setCityCode(int cityCode) {
			this.cityCode = cityCode;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		

	}
	
}
