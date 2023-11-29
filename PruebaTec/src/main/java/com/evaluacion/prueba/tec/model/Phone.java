package com.evaluacion.prueba.tec.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonPropertyOrder({"number","citycode","contrycode"})
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String number;
	@Column(name="citycode", nullable=true, length=10)
	@JsonProperty("citycode")
	private int cityCode;
	@Column(name="contrycode", nullable=true, length=10)
	@JsonProperty("contrycode")
	private String countryCode;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_phone")
	@JsonIgnoreProperties({"phone"})
	private Registro registro;

	
	public Phone() {}
	public Phone(String number, int cityCode, String countryCode,Registro registro) {
		super();
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.registro = registro;
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
	public Registro getRegistro() {
		return registro;
	}
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	@Override
	public String toString() {
		return "Phones [number=" + number + ", cityCode=" + cityCode + ", countryCode=" + countryCode + "]";
	}
	
	
	
}
