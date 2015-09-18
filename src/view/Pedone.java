package view;

import java.awt.Color;

import javax.swing.ImageIcon;


public class Pedone extends Casella {
	
	public Pedone(int valore, Color colore) {
		
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
		
		// Pedoni
		if(valore==10)
		{
			this.setOpaque(true);
			this.setBackground(colore);
			this.setIcon(new ImageIcon("src/img/PedoneBianco.png"));
		}
		else if(valore==11){
			this.setOpaque(true);
			this.setBackground(colore);
			this.setIcon(new ImageIcon("src/img/PedoneNero.png"));
		}
		
		// Pedoni che si "trasformano" in altri pezzi una volta raggiunta la parte opposta della scacchiera
		
/*		else if(valore==20){
			this.setIcon(new ImageIcon("src/img/TorreBianca.png"));
		}
		else if(valore==21){
			this.setIcon(new ImageIcon("src/img/TorreNera.png"));
		}
		else if(valore==30){
			this.setIcon(new ImageIcon("src/img/CavalloBianco.png"));
		}
		else if(valore==31){
			this.setIcon(new ImageIcon("src/img/CavalloNero.png"));
		}
		else if(valore==40){
			this.setIcon(new ImageIcon("src/img/AlfiereBianco.png"));
		}
		else if(valore==41){
			this.setIcon(new ImageIcon("src/img/AlfiereNero.png"));
		}
		else if(valore==50){
			this.setIcon(new ImageIcon("src/img/ReBianco.png"));
		}
		else if(valore==51){
			this.setIcon(new ImageIcon("src/img/ReNero.png"));
		}
		else if(valore==60){
			this.setIcon(new ImageIcon("src/img/ReginaBianca.png"));
		}
		else if(valore==61){
			this.setIcon(new ImageIcon("src/img/ReginaNera.png"));
		}*/
	}
	
}

