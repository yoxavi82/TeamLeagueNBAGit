package com.example.teamleaguebagit.pojos;
// Generated 24-may-2019 19:04:20 by Hibernate Tools 5.1.10.Final

/**
 * PlantillasId generated by hbm2java
 */
public class PlantillasId implements java.io.Serializable {

	private String idJugador;
	private String idLiga;
	private int idEquipo;

	public PlantillasId() {
	}

	public PlantillasId(String idJugador, String idLiga, int idEquipo) {
		this.idJugador = idJugador;
		this.idLiga = idLiga;
		this.idEquipo = idEquipo;
	}

	public String getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(String idJugador) {
		this.idJugador = idJugador;
	}

	public String getIdLiga() {
		return this.idLiga;
	}

	public void setIdLiga(String idLiga) {
		this.idLiga = idLiga;
	}

	public int getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PlantillasId))
			return false;
		PlantillasId castOther = (PlantillasId) other;

		return ((this.getIdJugador() == castOther.getIdJugador()) || (this.getIdJugador() != null
				&& castOther.getIdJugador() != null && this.getIdJugador().equals(castOther.getIdJugador())))
				&& ((this.getIdLiga() == castOther.getIdLiga()) || (this.getIdLiga() != null
						&& castOther.getIdLiga() != null && this.getIdLiga().equals(castOther.getIdLiga())))
				&& (this.getIdEquipo() == castOther.getIdEquipo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIdJugador() == null ? 0 : this.getIdJugador().hashCode());
		result = 37 * result + (getIdLiga() == null ? 0 : this.getIdLiga().hashCode());
		result = 37 * result + this.getIdEquipo();
		return result;
	}

}
