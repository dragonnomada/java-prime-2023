package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Fruta {

	@Id
	private Long id;
	@NotNull
	private String nombre;
	@NotNull
	@Min(0L)
	private Double precio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificado;
	
}
