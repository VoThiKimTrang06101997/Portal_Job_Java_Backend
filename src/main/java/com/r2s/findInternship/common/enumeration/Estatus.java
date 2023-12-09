package com.r2s.findInternship.common.enumeration;

public enum Estatus {
	Active("Active"), 		
	Not_Active("Not Active"), 	
	Lock("Lock"),			
	Disable("Disable"),		
	Delete("Delete");		

	private final String NAME;

	Estatus(String string) {
		this.NAME = string;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
