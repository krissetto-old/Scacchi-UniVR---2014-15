package controller;

import java.util.*;

import model.Scacchiera;
import controller.Casella;
	
public class Gioco extends Scacchiera {
	
	// (riga decresce, colonna decresce)
	public final int NORD_OVEST = 1;
	// (riga decresce, colonna uguale)
	public final int NORD = 2;	
	// (riga decresce, colonna cresce)
	public final int NORD_EST = 3;
	// (riga uguale, colonna decrescente)
	public final int OVEST = 4;	
	// (riga uguale, colonna crescente)
	public final int EST = 6;
	// (riga cresce, colonna decresce)
	public final int SUD_OVEST = 7;	
	// (riga crescente, colonna uguale)
	public final int SUD = 8;	
	// (riga cresce, colonna cresce)
	public final int SUD_EST = 9;
	// pedina ferma
	public final int FERMA = 0;	
	public final int C_NORD_O = 10;
	public final int C_NORD_E = 11;
	public final int C_EST_N = 12;
	public final int C_EST_S = 13;
	public final int C_SUD_E = 14;
	public final int C_SUD_O = 15;
	public final int C_OVEST_S = 16;
	public final int C_OVEST_N = 17;
     
	public final LinkedList<Mossa>[] possibilitaScacco = new LinkedList[12];
	public int aChiTocca = BIANCO;
	boolean mossaValida=true;
	Casella chiHaFattoScacco;
	Casella re_bianco= new Casella(7,4);
	Casella re_nero= new Casella(0,4);
	int regolaX = 0;
	int regolaY = 0;
	
	//costruttore
	public Gioco() {
		super();
	}

//-----------------------------------------------------------------------------------------------------			
	
	// Ritorna il colore opposto a quello dato: bianco per nero e nero per	 
	public int coloreOpposto(int colore) {
		switch (colore) {
		case BIANCO:
			return NERO;
		case NERO:
			return BIANCO;
		}
		return NON_COLORE;
	}

//-----------------------------------------------------------------------------------------------------		
	
	// Ritorna la casella adiacente a quella data, secondo la direzione	 
	public Casella casellaAdiacente(Casella c, int direz) {
		Casella c2 = null;
		switch (direz) {
			case NORD_OVEST:
				c2 = new Casella(c.riga - 1, c.colonna - 1);
				break;
			case NORD:
				c2 = new Casella(c.riga - 1, c.colonna);
				break;
			case NORD_EST:
				c2 = new Casella(c.riga - 1, c.colonna + 1);
				break;
			case OVEST:
				c2 = new Casella(c.riga, c.colonna - 1);
				break;
			case EST:
				c2 = new Casella(c.riga, c.colonna + 1);
				break;
			case SUD_OVEST:
				c2 = new Casella(c.riga + 1, c.colonna - 1);
				break;
			case SUD:
				c2 = new Casella(c.riga + 1, c.colonna);
				break;
			case SUD_EST:
				c2 = new Casella(c.riga + 1, c.colonna + 1);
				break;
			case C_NORD_O:
				c2 = new Casella(c.riga - 2, c.colonna - 1);
				break;
			case C_NORD_E:
				c2 = new Casella(c.riga - 2, c.colonna + 1);
				break;
			case C_EST_N:
				c2 = new Casella(c.riga - 1, c.colonna + 2);
				break;
			case C_EST_S:
				c2 = new Casella(c.riga + 1, c.colonna + 2);
				break;	
			case C_SUD_O:
				c2 = new Casella(c.riga + 2, c.colonna - 1);
				break;
			case C_SUD_E:
				c2 = new Casella(c.riga + 2, c.colonna + 1);
				break;
			case C_OVEST_N:
				c2 = new Casella(c.riga - 1, c.colonna - 2);
				break;
			case C_OVEST_S:
				c2 = new Casella(c.riga + 1, c.colonna - 2 );
				break;
		}
			if ((c2 != null) && ! controllaDentro(c2))
				c2 = null;
			return c2;
	}
		
//-----------------------------------------------------------------------------------------------------
	
	//Ritorna vero se un pezzo può andare in quella direzione partendo da quella casella
	public boolean puoAndare(Mossa m0, int direz, LinkedList<Mossa> mossa, Casella cas) {
	   
	    Casella c0 = cas; 									// casella di partenza
	    Casella c2 = casellaAdiacente(c0, direz);			// prima casella adiacente in direzione direz
	
		int pezzo = contenuto(m0.caselleToccate.getFirst());	// contenuto casella di partenza
		int col = colore(pezzo);								// colore di quel pezzo
		
		// controllo l'adiacente in direzione direz è fuori dalla scacchiera
		if (c2 == null) {	
			return false;
		}
		
		// Se la casella adiacente in direzione direz e' libera la aggiungo alla lista
		if ((pezzo==PEDONE_BIANCO && contenuto(c2) == VUOTA && direz == NORD)
			|| (pezzo==PEDONE_NERO && contenuto(c2) == VUOTA && direz == SUD)
			|| (controlloPezzo(pezzo)) && (contenuto(c2)==VUOTA))		{
				
			m0.caselleToccate.addLast(c2);
			
			// aggiungo tutte le mosse possibili ai pezzi le cui mosse sono estendibili
			if(	  (pezzo == TORRE_BIANCA ||
					pezzo == TORRE_NERA ||
					pezzo == ALFIERE_BIANCO ||
					pezzo == ALFIERE_NERO ||
					pezzo == REGINA_BIANCA ||
					pezzo == REGINA_NERA)
					) 	  {
				if(casellaAdiacente(c2, direz) != null){
					Mossa m1 = new Mossa(m0.caselleToccate.getFirst());
					
					if(!puoAndare(m1, direz, mossa, casellaAdiacente(cas,direz))) {
						mossa.addLast(m0);
						return true;
					}	
				}
				mossa.addLast(m0);
				
			}else if (controlloPedone(pezzo) || pezzo==CAVALLO_NERO || pezzo== CAVALLO_BIANCO || pezzo==RE_NERO || pezzo== RE_BIANCO){
				mossa.addLast(m0);	
				if ( bordoOpposto(c2, col))
					m0.fattaDama = true;
				return true;
			}else {
				return false;
			}
				// i pedoni possono andare solo in una direzione
		}else if((pezzo==PEDONE_BIANCO && direz==NORD)||(pezzo==PEDONE_NERO && direz==SUD )){
			return false;
		}
		
		
		// la casella adiacente in direzione direz e' occupata da un pezzo, controllo se lo puo' mangiare
		else if (colore(contenuto(c2)) == coloreOpposto(col)) {
			m0.caselleToccate.addLast(c2);
			if (controlloPedone(pezzo) && bordoOpposto(c2, col)){
				m0.fattaDama = true;	
			}
			mossa.add(m0);
			return true;
		}
		// se nella casella da mangiare c'e' stesso colore allora no
		else 
			return false;
		return false;
	}

//-----------------------------------------------------------------------------------------------------
	
	//Ritorna vero se un pezzo ha fatto scacco ad un re
	public boolean Scacco(int pezzo,Mossa m){
		

		Casella c0 = (Casella) m.caselleToccate.getFirst();	// casella di partenza di pezzo
		Casella c1 = (Casella) m.caselleToccate.getLast();	// casella di arrivo di pezzo in questo momento in c1 c'è il pezzo
		int pos=switchone(pezzo); 							// chi può fare scacco
			
			metti(c0, VUOTA);   
			
			if(pos > -1 && pos < 12){
				possibilitaScacco[pos] = new LinkedList<Mossa>(suggerisciMosse(c1));
			}
					
			//Cicla per le liste di mosse dei vari pezzi e controlla se nella prossima mossa putrebbe mangiare il re
			if(pos!=12){
				for(int i = 0; i < possibilitaScacco[pos].size(); i++){
					Mossa mossa = possibilitaScacco[pos].get(i);			
					for(int j = 0; j < mossa.caselleToccate.size(); j++) {
						if(pos > -1 && pos < 6 && contenuto(mossa.caselleToccate.get(j)) == RE_NERO){ 
							view.Scacchi.stampaMessaggio("SCACCO AL RE NERO");
							chiHaFattoScacco=c1;
							return true;			
						}
						else if(pos > 5 && pos < 12 && contenuto(mossa.caselleToccate.get(j)) == RE_BIANCO){
							view.Scacchi.stampaMessaggio("SCACCO AL RE BIANCO");
							chiHaFattoScacco=c1;
							return true;				
						}
					}
				}
			}
			return false;
	}

//-----------------------------------------------------------------------------------------------------
	
	//Ritorna vero se un re può andare nella casella cliccata
	public boolean mossaValida(int pezzo, Casella c1, Casella c0, int pezzoDest,boolean sonoUnReBianco,boolean sonoUnReNero){
	
		
		// c0 è la casella di partenza ed è vuota al momento
		// c1 è la casella di destinazione ( da controllare se è giusta) e c'è dentro PEZZO
		
		// ricalcolo tutte le possibili mosse di ogni pezzo
		
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++) {
				int pos = switchone(contenuto(new Casella(x,y)));
				if (pos != 12){
					possibilitaScacco[pos] = suggerisciMosse(new Casella(x,y));		
				}
			}
		}
		
		if(pezzo==RE_BIANCO){
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++) {
					int pos = switchone(contenuto(new Casella(x,y)));
					if (pos != 12){
						possibilitaScacco[pos] = suggerisciMosse(new Casella(x,y));		
						
						for(int i = 6; i < 12; i++){  // controlla se i pezzi avversari possono fargli scacco in quella posizione
							for(int j = 0; j < possibilitaScacco[i].size(); j++){	
															
								if(possibilitaScacco[i].get(j) == null)
									break;
								Mossa mossa = possibilitaScacco[i].get(j);
								// se la casella dove il re vorrebbe andare coincide con una possibile mangiata di un nero la mossa non è valida
								
								if(contenuto(c1) ==  contenuto(mossa.caselleToccate.getLast())){
									
									//se non sono un re ma sto solo iterando per vedere se lo scacco è matto non devo spostare nulla
									if(sonoUnReNero || sonoUnReBianco){
										view.Scacchi.stampaMessaggio("Mossa non valida. Scacco al re.");
										metti(c0, RE_BIANCO);					
										metti(c1, pezzoDest);
									}
									return false;
								}
							}
						}
						
					}
				}
			}
			
		}else if(pezzo==RE_NERO){
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++) {
					int pos = switchone(contenuto(new Casella(x,y)));
					if (pos != 12){
						possibilitaScacco[pos] = suggerisciMosse(new Casella(x,y));		
						
						for(int i = 0; i < 6; i++){  // controlla se i pezzi avversari possono fargli scacco in quella posizione
							for(int j = 0; j < possibilitaScacco[i].size(); j++){	
															
								if(possibilitaScacco[i].get(j) == null)
									break;
								Mossa mossa = possibilitaScacco[i].get(j);
								// se la casella dove il re vorrebbe andare coincide con una possibile mangiata di un nero la mossa non è valida

								if(contenuto(c1) ==  contenuto(mossa.caselleToccate.getLast())){
									
									//se non sono un re ma sto solo iterando per vedere se lo scacco è matto non devo spostare nulla
									if(sonoUnReNero || sonoUnReBianco){
										view.Scacchi.stampaMessaggio("Mossa non valida. Scacco al re.");
										metti(c0, RE_NERO);					
										metti(c1, pezzoDest);
									}
									return false;
								}
							}
						}
						
					}
				}
			}
			
		}
		if(sonoUnReNero || sonoUnReBianco)
			metti(c0,VUOTA);
		return true;
	}
	
//-----------------------------------------------------------------------------------------------------
	
	//Calcola le caselle tra il re e chi gli ha fatto scacco
	public boolean mosseComprese(LinkedList<Mossa> mosseIntermedie, Casella chiHaFattoScacco, int regolaX, int regolaY){
		
		boolean exist = false;
		// se la prossima casella esce dalla scacchiera rompi la ricorsione
		if (chiHaFattoScacco.riga+regolaX > 7 ||chiHaFattoScacco.riga+regolaX < 0 ||chiHaFattoScacco.colonna+regolaY>7 || chiHaFattoScacco.colonna+regolaY<0){
			return exist;
		}
		// se la casella intermedia è vuota la aggiunge alla lista e si chiama ricorsivamente per trovare la prossima
		Casella casella = new Casella(chiHaFattoScacco.riga+regolaX, chiHaFattoScacco.colonna+regolaY);
		if( contenuto(casella) == VUOTA){
			exist = true;
			mosseIntermedie.add(new Mossa(chiHaFattoScacco));
			
			mosseIntermedie.getLast().caselleToccate.add(casella);
			
			mosseComprese(mosseIntermedie, casella, regolaX, regolaY);
		}
		return exist;
	}

//-----------------------------------------------------------------------------------------------------

	//Funzione che calcola i numeri da sommare ad una casella per trovare quelle tra re e chi ha fatto scacco
	public void impostaRegole(Casella c0){

        // controlla in che direzione deve andare  per calcolare le caselle intermedie confrontando la posizione 
		// di chi ha fatto scacco e quella del re
		if(contenuto(chiHaFattoScacco)==ALFIERE_NERO || contenuto(chiHaFattoScacco)==ALFIERE_BIANCO ){
			if((chiHaFattoScacco.riga - c0.riga)>0)
				regolaX=1;
			else
				regolaX=-1;
			if((chiHaFattoScacco.colonna - c0.colonna)>0)
				regolaY=1;
			else
				regolaY=-1;
		}else if(contenuto(chiHaFattoScacco)==TORRE_NERA || contenuto(chiHaFattoScacco)==TORRE_BIANCA){
			if((chiHaFattoScacco.riga - c0.riga)>0){
				regolaX = 1;
				regolaY = 0;
			}
			else{
				regolaX = -1;
				regolaY = 0;
			}
			if((chiHaFattoScacco.colonna - c0.colonna)>0){
				regolaY = 1;
				regolaX = 0;
			}
			else{
				regolaY = -1;
				regolaX = 0;
			}
		}else if(contenuto(chiHaFattoScacco)==REGINA_NERA || contenuto(chiHaFattoScacco)==REGINA_BIANCA){
			if((chiHaFattoScacco.riga == c0.riga) || (chiHaFattoScacco.colonna ==c0.colonna)){
				if((chiHaFattoScacco.riga - c0.riga)>0){
					regolaX = 1;
					regolaY = 0;
				}
				else{
					regolaX = -1;
					regolaY = 0;
				}
				if((chiHaFattoScacco.colonna - c0.colonna)>0){
					regolaY = 1;
					regolaX = 0;
				}
				else{
					regolaY = -1;
					regolaX = 0;
				}
			}else 
				if((chiHaFattoScacco.riga - c0.riga)>0)
					regolaX=1;
				else
					regolaX=-1;
				if((chiHaFattoScacco.colonna - c0.colonna)>0)
					regolaY=1;
				else
					regolaY=-1;
		}
		
	}

//-----------------------------------------------------------------------------------------------------

	//Funzione che controlla se qualche pezzo può mettersi tra il re e chi ha fatto scacco
	public boolean scaccoMatto(Casella c0){

		for (int x = 0; x < 8; x++) {								//scorre le righe
			for (int y = 0; y < 8; y++) { 							//scorre le colonne

				if(colore(contenuto(x,y))==aChiTocca){				// se il colore del pezzo in quella casella è opposto a quello del pezzo cliccato
					int pos=switchone(contenuto(x,y));								// pos = numero pezzo in quella casella (del colore del re)
					int palinka=switchone(contenuto(chiHaFattoScacco));				// palinka = numero pezzo che ho cliccato
				
					if(pos!=12 && palinka!=12){
						possibilitaScacco[pos]= new LinkedList<Mossa>(suggerisciMosse(new Casella(x,y)));
						
						for(int mossa=0;mossa<possibilitaScacco[pos].size();mossa++){   // scorre le mosse del pezzo nella casella x,y
							
							if(contenuto(possibilitaScacco[pos].get(mossa).caselleToccate.getLast())== VUOTA){  // se il pezzo può mettersi tra il re ed il pezzo che ha fatto scacco
										
								Mossa tempMossa = possibilitaScacco[pos].get(mossa);    // mossa del pedone che potrebbe mettersi inmezzo
								
								for(int avversario = 0; avversario < possibilitaScacco[palinka].size(); avversario++){   // scorre le mosse del pezzo che ha fatto scacco
									LinkedList<Mossa> mosseIntermedie=new LinkedList<Mossa>();
									if ((palinka == 7 || palinka == 1 || palinka == 11 || palinka == 5 || palinka == 9 || palinka == 3)){	// se chi ha fatto scacco può estende le proprie mosse	
									
										if(colore(aChiTocca)==NERO)
											impostaRegole(re_bianco);									// imposta le regole per calcolare le caselle intermedie
										else
											impostaRegole(re_nero);
										
										mosseComprese(mosseIntermedie, chiHaFattoScacco, regolaX, regolaY);  // calcola le caselle tra chi ha fatto scacco ed il re
			
										for(int gianni = 0; gianni < mosseIntermedie.size(); gianni++)
											//se la casella di destinazione della mossa del pezzo che si potrebbe mettere inmezzo E' UGUALE a la casella di destinazione di una  delle caselle intermedie
											for(int i=0;i<mosseIntermedie.size();i++){
												if(Casella.stessa(tempMossa.caselleToccate.getLast(), mosseIntermedie.get(i).caselleToccate.getLast())){
													return false;
												}
											}
									}
								}
							}
						}
					}	
				}
			}
		}
		return true;
	}

//-----------------------------------------------------------------------------------------------------		

	public int switchone(int pezzo){
		switch (pezzo){
		case PEDONE_BIANCO:
			return 0;
		case TORRE_BIANCA:
			return 1;		
		case CAVALLO_BIANCO:
			return 2;		
		case ALFIERE_BIANCO:
			return 3;		
		case RE_BIANCO:
			return 4;		
		case REGINA_BIANCA:
			return 5;		
		case PEDONE_NERO:
			return 6;			
		case TORRE_NERA:
			return 7;			
		case CAVALLO_NERO:
			return 8;		
		case ALFIERE_NERO:
			return 9;			
		case RE_NERO:
			return 10;		
		case REGINA_NERA:
			return 11;			
		default:
			return 12;				
		}
}

//-----------------------------------------------------------------------------------------------------		

	//Controlla se il re cade in scacco mangiando qualcosa
	public boolean controlloMangiataRe(int pezzo, Casella c0, Casella c1, Mossa m, boolean scaccoNuovo, boolean mossaValida, boolean sonoUnReBianco, boolean sonoUnReNero ){
		
		
		int temp = contenuto(c1);
	
		metti(c1, VUOTA);
	
		// ricalcolo tutte le possibili mosse di tutti
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++) {
				int pos = switchone(contenuto(x,y));
				if (pos != 12)
					possibilitaScacco[pos] = suggerisciMosse(new Casella(x,y));		
			}

		scaccoNuovo = Scacco(pezzo,m);
		mossaValida = mossaValida(pezzo,c1,c0,temp,sonoUnReBianco,sonoUnReNero);
	
		if(!mossaValida){
			if(sonoUnReBianco)
				metti(c0,RE_BIANCO);
			else if (sonoUnReNero)
				metti(c0,RE_NERO);
			metti(c1, temp);
			return false;
		}
		
		metti(c0,VUOTA);
		if(sonoUnReBianco)
			metti(c1,RE_BIANCO);
		else if (sonoUnReNero)
			metti(c1,RE_NERO);	

		return true;
}

//-----------------------------------------------------------------------------------------------------		

	//Controlla se il pezzo che ha fatto scacco può essere mangiato
	public boolean puoEssereMangiato(int colore,int pezzo){
		
		for (int x = 0; x < 8; x++) {											//scorre le righe
			for (int y = 0; y < 8; y++) { 										//scorre le colonne

				if((colore==contenuto(x,y)%2) && contenuto(x,y)!=0){	// se sono bianchi
					
					int pos=switchone(contenuto(x,y));					// pos = numero pezzo in quella casella (del colore del re)

					if(pos!=12 && pos!=10 && pos!=4){

						possibilitaScacco[pos]= new LinkedList<Mossa>(suggerisciMosse(new Casella(x,y)));
						
						for(int mossa=0;mossa<possibilitaScacco[pos].size();mossa++){   // scorre le mosse del pezzo nella casella x,y
							if(contenuto(possibilitaScacco[pos].get(mossa).caselleToccate.getLast())== pezzo ){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
					
	}

//-----------------------------------------------------------------------------------------------------		

	// Esegue la mossa assegnata, controlla se c'è scacco matto e cambia il giocatore a cui tocca.			
	public boolean esegui(Mossa m) {
	
		Casella c0 = (Casella) m.caselleToccate.getFirst();
		Casella c1 = (Casella) m.caselleToccate.getLast();
		mossaValida=true;
		boolean scacco=false;
		
		int pezzo = contenuto(c0);
		int pezzoDest = contenuto(c1);
		int conta = -1;
		int c=0;
		boolean possoMangiare = true;
		boolean sonoUnReBianco=false;
		boolean sonoUnReNero=false;
		boolean puoEssereMangiato = false;
		
	
		if (contenuto(c0)==RE_BIANCO){
			sonoUnReBianco=true; 
			re_bianco=c1;
		}
		else if (contenuto(c0)==RE_NERO){
			sonoUnReNero=true;
			re_nero=c1;
		}
		else{
			sonoUnReNero=false; 
			sonoUnReBianco=false; 
		}
		
		if(contenuto(c1) != VUOTA && (contenuto(c0) == RE_BIANCO || contenuto(c0) == RE_NERO)) //Se c'e` una casella da mangiare
			possoMangiare = controlloMangiataRe(pezzo, c0, c1, m, scacco, mossaValida,sonoUnReBianco,sonoUnReNero);
	
		else if ((contenuto(c0) == RE_BIANCO || contenuto(c0) == RE_NERO)){
			
			metti(c1, pezzo);
			metti(c0, VUOTA);
			// controlla se la mossa che sposta il re da c0 a c1 è valida
			mossaValida = mossaValida(pezzo,c1,c0,pezzoDest,sonoUnReBianco,sonoUnReNero);
					
		}
		
		else{	
			metti(c1, pezzo);
			scacco=Scacco(pezzo,m);	
		}

		// controlli per lo scacco matto
		if(colore(pezzo) == NERO){
			if(scacco){
				possibilitaScacco[4] = suggerisciMosse(re_bianco);
				for(conta=0; conta<possibilitaScacco[4].size();conta++){
					Mossa mossa = possibilitaScacco[4].get(conta);
					// se la mossa che sposta il re dalla sua casella a ogni sua possibile mossa è valida
				
					if( mossaValida(RE_BIANCO, mossa.caselleToccate.getLast(), re_bianco, pezzoDest, sonoUnReNero , sonoUnReBianco) )	
						
						if((possoMangiare) && (contenuto(m.caselleToccate.getLast()))==contenuto(mossa.caselleToccate.getLast()))
							c++;
						else
							break;					
					
				}
				puoEssereMangiato=puoEssereMangiato(0,pezzo);
	
				if ((conta + c) == possibilitaScacco[4].size() && !puoEssereMangiato ){
			
					if(scaccoMatto(c0)){
						view.Scacchi.stampaMessaggio("SCACCO MATTO. PARTITA TERMINATA. Hanno vinto i neri!");	
						return true;
					}
				}
			}	
		}
		else if(colore(pezzo) == BIANCO){

			if(scacco){
				possibilitaScacco[10] = suggerisciMosse(re_nero);
				for(conta=0; conta<possibilitaScacco[10].size();conta++){
					Mossa mossa = possibilitaScacco[10].get(conta);
					if( mossaValida(RE_NERO, mossa.caselleToccate.getLast(), re_nero, pezzoDest, sonoUnReNero, sonoUnReBianco))
						if((possoMangiare) && (contenuto(m.caselleToccate.getLast()))==contenuto(mossa.caselleToccate.getLast()))
							c++;
						else
							break;
					puoEssereMangiato=puoEssereMangiato(1,pezzo);
				}
				if( (conta + c) == possibilitaScacco[10].size() && !puoEssereMangiato){
					if(scaccoMatto(c0)){
						view.Scacchi.stampaMessaggio("SCACCO MATTO. PARTITA TERMINATA. Hanno vinto i bianchi!");	
						return true;
					}
				}
			}
		}
		if (m.fattaDama){
			pezzo = promozionePedone(pezzo);
			metti(c1,pezzo);
		}
	
		if(mossaValida == true )
			if ((pezzo==RE_BIANCO || pezzo==RE_NERO) && (possoMangiare==true))
				if (aChiTocca == BIANCO)
					aChiTocca = NERO;
				else
					aChiTocca = BIANCO;	
			else if ((pezzo!=RE_BIANCO && pezzo!=RE_NERO))
				if (aChiTocca == BIANCO)
					aChiTocca = NERO;
				else
					aChiTocca = BIANCO;
		
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++) {
				int pos = switchone(contenuto(x,y));
				if (pos != 12)
					possibilitaScacco[pos] = suggerisciMosse(new Casella(x,y));		
			}
		return false;
	}
				
//-----------------------------------------------------------------------------------------------------		

	// Ritorna vero se uno dei due giocatori ha vinto		 
	public boolean chiHaVinto(boolean scaccoMatto) {
				
				int r, c;
				boolean biancoHaPezzi = false;
				boolean neroHaPezzi = false;
	
				for (r = 0; r < DIM_LATO; r++)
					for (c = 0; c < DIM_LATO; c++) {
						if ((contenuto(r, c)) == RE_BIANCO) {
							biancoHaPezzi = true;
						} else if ((contenuto(r, c)) == RE_NERO) {
							neroHaPezzi = true;
						}
					}
				// almeno uno dei due non ha mosse, vince l'altro
				if (!biancoHaPezzi || scaccoMatto ||!neroHaPezzi )
					return true;
				return false;
			}
			
//-----------------------------------------------------------------------------------------------------		
		
	// Restituisce la lista dlle mosse possibili per il pezzo che si trova nella casella specificata.		
	public LinkedList<Mossa> suggerisciMosse(Casella cas) {
				LinkedList<Mossa> mossePossibili = new LinkedList<Mossa>();
				int pezzo = contenuto(cas);
				// nessuna mossa da casella vuota
				if (pezzo == VUOTA)
					return mossePossibili;
				// esplora l-albero di tutte le possibilita'
				Mossa m = new Mossa(cas);
				suggerisciMosseRic(m, mossePossibili,cas);
				
				return mossePossibili;
			}
		
//-----------------------------------------------------------------------------------------------------			

	// Funzione che trova le mosse possibili dalla casella specificata, e le aggiunge alla lista.		 
	protected void suggerisciMosseRic(Mossa m0, LinkedList<Mossa> mosse, Casella cas) {
				int i;
				Mossa[] mossePossibili = new Mossa[8];
				for (i = 0; i < 8; i++) {
					mossePossibili[i] = new Mossa(m0);
				}
	
				switch (contenuto(cas)) {
				 case PEDONE_BIANCO:
					puoAndare(mossePossibili[0], NORD_EST, mosse,  cas );
					puoAndare(mossePossibili[1], NORD_OVEST, mosse, cas );
					puoAndare(mossePossibili[2], NORD, mosse, cas );
					break;
				 case PEDONE_NERO:
					puoAndare(mossePossibili[0], SUD_EST, mosse, cas );
					puoAndare(mossePossibili[1], SUD_OVEST, mosse,  cas );
					puoAndare(mossePossibili[2], SUD, mosse,  cas );
					break;
				 case TORRE_BIANCA:
				 case TORRE_NERA:
				    puoAndare(mossePossibili[0], NORD, mosse, cas );
					puoAndare(mossePossibili[1], SUD, mosse,  cas );
					puoAndare(mossePossibili[2], EST, mosse, cas );
					puoAndare(mossePossibili[3], OVEST, mosse,  cas );
					break;
				 case CAVALLO_BIANCO:
				 case CAVALLO_NERO:
					puoAndare(mossePossibili[0], C_NORD_O, mosse, cas );
					puoAndare(mossePossibili[1], C_NORD_E, mosse, cas );
					puoAndare(mossePossibili[2], C_EST_N, mosse, cas );
					puoAndare(mossePossibili[3], C_EST_S, mosse,  cas );
					puoAndare(mossePossibili[4], C_SUD_E, mosse, cas );
					puoAndare(mossePossibili[5], C_SUD_O, mosse,  cas );
					puoAndare(mossePossibili[6], C_OVEST_S, mosse, cas );
					puoAndare(mossePossibili[7], C_OVEST_N, mosse,  cas );
					break;
				 case ALFIERE_BIANCO:
				 case ALFIERE_NERO:
					puoAndare(mossePossibili[0], NORD_OVEST, mosse,  cas );
					puoAndare(mossePossibili[1], NORD_EST, mosse,  cas );
					puoAndare(mossePossibili[2], SUD_OVEST, mosse, cas );
					puoAndare(mossePossibili[3], SUD_EST, mosse, cas );
					break;
				 case REGINA_BIANCA:
				 case REGINA_NERA:
				 case RE_BIANCO:
				 case RE_NERO:
					puoAndare(mossePossibili[0], NORD, mosse,  cas );
					puoAndare(mossePossibili[1], SUD, mosse,  cas );
					puoAndare(mossePossibili[2], EST, mosse, cas );
					puoAndare(mossePossibili[3], OVEST, mosse, cas );
					puoAndare(mossePossibili[4], NORD_OVEST, mosse,  cas );
					puoAndare(mossePossibili[5], NORD_EST, mosse,  cas );
					puoAndare(mossePossibili[6], SUD_OVEST, mosse,  cas );
					puoAndare(mossePossibili[7], SUD_EST, mosse, cas );
					break;
				 
				 }
			}
			
//-----------------------------------------------------------------------------------------------------			

	// Prova la mossa del giocatore, ovvero verifica se é valida e in tal caso la esegue.
	public String provaMossaGiocatore(int x1, int y1, int x2, int y2) {
				boolean trovata = false;
				boolean scaccoMatto;
				
				//ciclo per impostare l'array di LInkedList di mosse possibili di ogni pezzo
				for (int r=0; r<DIM_LATO; r++)
					for (int c=0; c<DIM_LATO; c++) {
						
							if (r == 0) { // La prima riga in alto
								switch (c) {
									case 0: 
										possibilitaScacco[7] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
										break;
									case 1:
										possibilitaScacco[8] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
										break;
									case 2:
										possibilitaScacco[9] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
										break;
									case 3:
										possibilitaScacco[11] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
										break;
									case 4:
										possibilitaScacco[10] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
										break;
									case 5:
										possibilitaScacco[9] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
										break;
									case 6:
										possibilitaScacco[8] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
										break;
									case 7:
										possibilitaScacco[7] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
										break;
									default:
										break;
								}
							}
							else if (r == 1) {
								possibilitaScacco[6] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
							}
							else if (r == 6) { // l'ultima riga in basso
								possibilitaScacco[0] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
							}
							else if (r == 7) { // Penultima riga in basso
								switch (c) {
								case 0: 
									possibilitaScacco[1] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
									break;
								case 1:
									possibilitaScacco[2] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
									break;
								case 2:
									possibilitaScacco[3] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
									break;
								case 3:
									possibilitaScacco[5] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
									break;
								case 4:
									possibilitaScacco[4] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
									break;
								case 5:
									possibilitaScacco[3] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
									break;
								case 6:
									possibilitaScacco[2] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
									break;								
								case 7:
									possibilitaScacco[1] = new LinkedList<Mossa>(suggerisciMosse(new Casella(r,c)));
									break;
								default:
									break;
								}
							}
						}
								
				//Suggerisce tutte le mosse della pedina inizialmente selezionata
				
				LinkedList<Mossa> mossePedina = suggerisciMosse(new Casella(x1, y1));
				
				//Controllo se la mossa � fattibile
				
				for (int i = 0; i < mossePedina.size(); i++) {
					Mossa pop = (Mossa) mossePedina.get(i);
					Casella casella = (Casella) pop.caselleToccate.getLast();
					
					//Corrispondenza trovata
					
					if (casella.riga == x2 && casella.colonna == y2) {
						trovata = true;
						//Quindi eseguo la mossa
							
						scaccoMatto=esegui(pop);
						
						//Controllo se qualcuno ha vinto
						
						if(chiHaVinto(scaccoMatto)){
							return "Gioco finito!";
						}						
					}
				}			
				//Mossa non valida
				if(!trovata)
					return "La mossa non e valida, riprova";
			
				return null;
			
		}			
}