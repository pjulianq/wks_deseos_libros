package com.co.lista_deseos.clients.modelsdto.apibooksgoogle;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GBItemsWrapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GBVolumeInfoWrapper volumeInfo;
	private String id;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GBVolumeInfoWrapper getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(GBVolumeInfoWrapper volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
	
	

}
