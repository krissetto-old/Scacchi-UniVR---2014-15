package view;

import javax.swing.ImageIcon;
import java.awt.Color;

public class Alfiere extends Casella {
	

	public Alfiere(int valore, Color colore) {
		/* 
		 * Prima cifra:
		 *	 1 = Pedone
		 *	 2 = Torre
		 *	 3 = Cavallo
		 *	 4 = Alfiere
		 *	 5 = Re
		 *	 6 = Regina
		 *	
		 *	Seconda Cifra:
		 *	0 = Bianco
		 *	1 = Nero	
		 */
		
		if(valore==40)
		{
			this.setOpaque(true);
			this.setBackground(colore);
			this.setIcon(new ImageIcon("src/img/AlfiereBianco.png"));
		}
		else if(valore==41){
			this.setOpaque(true);
			this.setBackground(colore);
			this.setIcon(new ImageIcon("src/img/AlfiereNero.png"));
		}
	}
}
