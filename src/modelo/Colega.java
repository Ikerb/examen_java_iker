package modelo;

import java.util.Date;

public class Colega {

	private int id;
	private String nombre;
	private String ciudad;
	private Date fecha;	
	
	
	public Colega(int id, String nombre, String ciudad, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.fecha = fecha;
	}
	
	// constructor sin id
	public Colega(String nombre, String ciudad, Date fecha) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.fecha = fecha;
	}
	// Getters and Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
	//ToString
	
	@Override
	public String toString() {
		return "Colega [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", fecha=" + fecha + "]";
	}
	
	//Hashcode
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colega other = (Colega) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
}
