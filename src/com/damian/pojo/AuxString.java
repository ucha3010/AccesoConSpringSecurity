package com.damian.pojo;

public class AuxString {
	
	private String campo;
	

	public AuxString() {
	}

	/**
	 * @param campo
	 */
	public AuxString(String campo) {
		this.campo = campo;
	}

	/**
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuxString [campo=" + campo + "]";
	}

}
