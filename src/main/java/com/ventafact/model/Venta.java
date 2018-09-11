package com.ventafact.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.*;

@Entity
@Table(name="venta")
public class Venta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVenta;
	@JsonSerialize(using=ToStringSerializer.class)
	private LocalDateTime fecha;
	@ManyToOne
	@JoinColumn(name="id_persona",nullable=false)
	private Persona persona;
	private double importe;
	@OneToMany(mappedBy = "venta", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<DetalleVenta> detalleVenta=new ArrayList<DetalleVenta>();
	
	 
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		if (idVenta != other.idVenta)
			return false;
		return true;
	}
	@Override
    public int hashCode() {
        return 31;
    }
	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}
	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	@Override
	public String toString() {
		System.out.println(this.getClass().getCanonicalName()+" 123 "+getIdVenta());
	    return this.getClass().getCanonicalName()+" 123 "+getIdVenta();	}
	
}
