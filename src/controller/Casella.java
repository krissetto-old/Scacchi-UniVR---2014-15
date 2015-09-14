package controller;

public class Casella{
	// Istanze
	public int riga;
	public int colonna;

	// Costruttore
	public Casella(int r, int c) { 
		riga = r; 
		colonna = c; 
	}

	// Controlla se due caselle sono la stessa, cioe' hanno uguale riga e colonna
	public static boolean stessa(Casella c1, Casella c2) {  
		return ((c1.riga==c2.riga) && (c1.colonna==c2.colonna));  
	}  

}