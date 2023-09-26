package com.fobal.model;

import java.util.Date;

public class PicadoDTO {

	Integer idCancha;
	Date fecha;
	
	public Integer getIdCancha() {
		return idCancha;
	}
	public void setIdCancha(Integer idCancha) {
		this.idCancha = idCancha;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public PicadoDTO(Integer idCancha, Date fecha) {
		
		this.idCancha = idCancha;
		this.fecha = fecha;
	}
	
}
