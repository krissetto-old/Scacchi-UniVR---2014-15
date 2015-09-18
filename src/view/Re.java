package view;

import javax.swing.ImageIcon;
import java.awt.Color;

public class Re extends Casella {

	private static final long serialVersionUID = 1L;

	public Re(int valore, Color colore) {
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
		
		if(valore==50)
		{
			this.setOpaque(true);
			this.setBackground(colore);
			this.setIcon(new ImageIcon("src/img/ReBianco.png"));
		}
		else if(valore==51){
			this.setOpaque(true);
			this.setBackground(colore);
			this.setIcon(new ImageIcon("src/img/ReNero.png"));
		}
	}
}
