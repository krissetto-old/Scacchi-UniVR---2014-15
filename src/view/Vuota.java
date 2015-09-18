package view;

import java.awt.Color;

public class Vuota extends Casella {

	private static final long serialVersionUID = 1L;

	public Vuota() {
		this.setBackground(Color.gray);
	}
	
	public Vuota(Color colore) {
		this.setBackground(colore);
	}
}
