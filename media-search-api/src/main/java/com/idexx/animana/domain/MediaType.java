package com.idexx.animana.domain;

public enum MediaType {
	
	BOOK(1),
	ALBUM(2);
	
	Integer typeId;
	
	MediaType(Integer typeId) {
		this.typeId = typeId;
	}

}
