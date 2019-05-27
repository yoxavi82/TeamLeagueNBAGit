package com.example.teamleaguebagit.pojos;
// Generated 24-may-2019 19:04:20 by Hibernate Tools 5.1.10.Final

import java.util.Date;

/**
 * Plantillas generated by hbm2java
 */
public class Plantillas implements java.io.Serializable {

	private PlantillasId id;
	private EquiposUsuarios equiposUsuarios;
	private Jugadores jugadores;
	private Ligas ligas;
	private int precio;
	private Date fechaCompra;
	private Integer puja;
	private int titular;

	public Plantillas() {
	}

	public Plantillas(PlantillasId id, EquiposUsuarios equiposUsuarios, Jugadores jugadores, Ligas ligas, int precio,
			Date fechaCompra, int titular) {
		this.id = id;
		this.equiposUsuarios = equiposUsuarios;
		this.jugadores = jugadores;
		this.ligas = ligas;
		this.precio = precio;
		this.fechaCompra = fechaCompra;
		this.titular = titular;
	}

	public Plantillas(PlantillasId id, EquiposUsuarios equiposUsuarios, Jugadores jugadores, Ligas ligas, int precio,
			Date fechaCompra, Integer puja, int titular) {
		this.id = id;
		this.equiposUsuarios = equiposUsuarios;
		this.jugadores = jugadores;
		this.ligas = ligas;
		this.precio = precio;
		this.fechaCompra = fechaCompra;
		this.puja = puja;
		this.titular = titular;
	}

	public PlantillasId getId() {
		return this.id;
	}

	public void setId(PlantillasId id) {
		this.id = id;
	}

	public EquiposUsuarios getEquiposUsuarios() {
		return this.equiposUsuarios;
	}

	public void setEquiposUsuarios(EquiposUsuarios equiposUsuarios) {
		this.equiposUsuarios = equiposUsuarios;
	}

	public Jugadores getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(Jugadores jugadores) {
		this.jugadores = jugadores;
	}

	public Ligas getLigas() {
		return this.ligas;
	}

	public void setLigas(Ligas ligas) {
		this.ligas = ligas;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Integer getPuja() {
		return this.puja;
	}

	public void setPuja(Integer puja) {
		this.puja = puja;
	}

	public int getTitular() {
		return this.titular;
	}

	public void setTitular(int titular) {
		this.titular = titular;
	}

}
