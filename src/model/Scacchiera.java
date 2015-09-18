package model;

import controller.Casella;

public class Scacchiera {
	
	public final int DIM_LATO = 8;
	public final int VUOTA = 0;
	
	public final int NON_COLORE = 0;
	public final int BIANCO = 1;
	public final int NERO = 2;
	
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
	public final int PEDONE_BIANCO = 10;
	public final int PEDONE_NERO = 11;
	public final int TORRE_BIANCA = 20;
	public final int TORRE_NERA = 21;
	public final int CAVALLO_BIANCO = 30;
	public final int CAVALLO_NERO = 31;
	public final int ALFIERE_BIANCO = 40;
	public final int ALFIERE_NERO = 41;
	public final int RE_BIANCO = 50;
	public final int RE_NERO = 51;
	public final int REGINA_BIANCA = 60;
	public final int REGINA_NERA = 61;
	
	protected int[][] contenutoCaselle;

	// Costruttore  
	public Scacchiera(){
		contenutoCaselle = new int[DIM_LATO][DIM_LATO];
		statoIniziale();
	}
	  
	// Controlla, date riga e colonna, se una casella e' nera.  
	  public boolean controlloCasellaNera(int riga, int colonna) {  
		  return ( (riga % 2) == (colonna % 2) );  
	  }

	public void statoIniziale() {
		
		int r, c;
	
		for (r=0; r<DIM_LATO; r++)
			for (c=0; c<DIM_LATO; c++) {
					if (r == 0) { // La prima riga in alto
						switch (c) {
							case 0: 
								contenutoCaselle[r][c] = TORRE_NERA;
								break;
							case 1:
								contenutoCaselle[r][c] = CAVALLO_NERO;
								break;
							case 2:
								contenutoCaselle[r][c] = ALFIERE_NERO;
								break;
							case 3:
								contenutoCaselle[r][c] = REGINA_NERA;
								break;
							case 4:
								contenutoCaselle[r][c] = RE_NERO;
								break;
							case 5:
								contenutoCaselle[r][c] = ALFIERE_NERO;
								break;
							case 6:
								contenutoCaselle[r][c] = CAVALLO_NERO;
								break;
							case 7:
								contenutoCaselle[r][c] = TORRE_NERA;
								break;
							default:
								break;
						}
					}
					else if (r == 1) {
						contenutoCaselle[r][c] = PEDONE_NERO;
					}
					
					else if (r == 6) { // l'ultima riga in basso
						contenutoCaselle[r][c] = PEDONE_BIANCO;
					}
					
					else if (r == 7) { // Penultima riga in basso
						switch (c) {
						case 0: 
							contenutoCaselle[r][c] = TORRE_BIANCA;
							break;
						case 1:
							contenutoCaselle[r][c] = CAVALLO_BIANCO;
							break;
						case 2:
							contenutoCaselle[r][c] = ALFIERE_BIANCO;
							break;
						case 3:
							contenutoCaselle[r][c] = REGINA_BIANCA;
							break;
						case 4:
							contenutoCaselle[r][c] = RE_BIANCO;
							break;
						case 5:
							contenutoCaselle[r][c] = ALFIERE_BIANCO;
							break;
						case 6:
							contenutoCaselle[r][c] = CAVALLO_BIANCO;
							break;
						case 7:
							contenutoCaselle[r][c] = TORRE_BIANCA;
							break;
						default:
							break;
						}
					}
					else contenutoCaselle[r][c] = VUOTA; // caselle bianche
				
			}
	
	}
	  // Ritorna il colore di un pezzo, dato il codice del pezzo. 
	  public int colore(int pezzo){
	    switch(pezzo){
	      case PEDONE_BIANCO: 
	      case TORRE_BIANCA:
	      case CAVALLO_BIANCO:
	      case ALFIERE_BIANCO:
	      case RE_BIANCO:
	      case REGINA_BIANCA:
	    	  return BIANCO;
	      case PEDONE_NERO: 
	      case TORRE_NERA:
	      case CAVALLO_NERO:
	      case ALFIERE_NERO:
	      case RE_NERO:
	      case REGINA_NERA:
	    	  return NERO;
	    }
	    return NON_COLORE;
	  }  
	
	// Controlla se il pezzo dato e' un pedone. 
	  public boolean controlloPedone(int pezzo) {  
		  return ( (pezzo==PEDONE_BIANCO) || (pezzo==PEDONE_NERO) );  
		  }
	  
	// Controlla se c'è un pezzo. 
	  public boolean controlloPezzo(int pezzo) {  
		  return ( (pezzo==CAVALLO_NERO || pezzo==CAVALLO_BIANCO || pezzo==TORRE_NERA 
				  || pezzo==TORRE_BIANCA || pezzo==RE_NERO || pezzo==RE_BIANCO || pezzo==REGINA_NERA || 
				  pezzo==REGINA_BIANCA || pezzo==ALFIERE_NERO || pezzo==ALFIERE_BIANCO) );  
		  }
		 
	  // Controlla il contenuto di una casella
	  public int contenuto(int r, int c) {
		  return contenutoCaselle[r][c];  
	  }
	  
	  public int contenuto(Casella cas) {  
		  return contenutoCaselle[cas.riga][cas.colonna];
	  }
	  
	  // Posiziona un pezzo su una casella
	  public boolean metti(int r, int c, int pezzo) {
		  
		  if ( (pezzo>=0) && (pezzo<=99) ) {  
			  contenutoCaselle[r][c] = pezzo; 
			  return true;  
		  }else 
			  return false;
	  }  
	  
	  public boolean metti(Casella cas, int pezzo) {  
		  return metti(cas.riga, cas.colonna, pezzo);  
	  }
	  
	  // Controlla, date riga e colonna, se una casella e' dentro i limiti della scacchiera.
	   
	  public boolean controllaDentro(int riga, int colonna) {  
		  return ((riga>=0) && 
				  (riga<DIM_LATO) && 
				  (colonna>=0) && 
				  (colonna<DIM_LATO));
	  }
	  
	  public boolean controllaDentro(Casella c) {  
		  return controllaDentro(c.riga,c.colonna);  
	  }
	  
	  // Controlla se un pezzo ha raggiunto il bordo opposto
	  public boolean bordoOpposto(Casella c, int col) {
		  switch (col) {  
		  	case NERO: 
		  		return ( c.riga==(DIM_LATO-1) );
		  	case BIANCO: 
		  		return ( c.riga==0 );
		  }
		  return false;
	  }
	  
	  //Promuove il pedone in regina
	  
	  public int promozionePedone(int pezzo){
	    switch (pezzo){
	      
	    case PEDONE_NERO: 
	    case REGINA_NERA: 
	    	return REGINA_NERA;
	    case PEDONE_BIANCO: 
	    case REGINA_BIANCA: 
	    	return REGINA_BIANCA;
	    }
	    return VUOTA;
	  }
}