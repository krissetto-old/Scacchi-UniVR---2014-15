import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Test;
import controller.*;

public class TestScacchi {
	
	@Test
	public void testMetti(){
		
		Gioco test = new Gioco();
		Casella casella = new Casella(4,4);
		assertTrue(test.metti(casella, 51));
		
	}
	
	@Test
	public void testColoreOpposto(){
		Gioco test = new Gioco();	
		assertEquals(1, test.coloreOpposto(2));
		assertEquals(2, test.coloreOpposto(1));
		assertEquals(0, test.coloreOpposto(0));
		
	}
	
	// TEST CASELLA ADIACENTE -------------------------------------------------------------------------	
	
	@Test
	public void testCasellaAdiacenteNordOvest(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 1).colonna, 3);
		assertEquals(test.casellaAdiacente(casella, 1).riga, 3);
		
	}
	
	@Test
	public void testCasellaAdiacenteNord(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 2).colonna, 4);
		assertEquals(test.casellaAdiacente(casella, 2).riga, 3);
		
	}
	
	@Test
	public void testCasellaAdiacenteNordEst(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 3).colonna, 5);
		assertEquals(test.casellaAdiacente(casella, 3).riga, 3);
		
	}
	
	@Test
	public void testCasellaAdiacenteOvest(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 4).colonna, 3);
		assertEquals(test.casellaAdiacente(casella, 4).riga, 4);
		
	}
	
	@Test
	public void testCasellaAdiacenteEst(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 6).colonna, 5);
		assertEquals(test.casellaAdiacente(casella, 6).riga, 4);
		
	}
		
	@Test
	public void testCasellaAdiacenteSudOvest(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 7).colonna, 3);
		assertEquals(test.casellaAdiacente(casella, 7).riga, 5);
	
	}

	@Test
	public void testCasellaAdiacenteSud(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 8).colonna, 4);
		assertEquals(test.casellaAdiacente(casella, 8).riga, 5);
		
	}
	
	@Test
	public void testCasellaAdiacenteSudEst(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 9).colonna, 5);
		assertEquals(test.casellaAdiacente(casella, 9).riga, 5);
		
	}
	
	@Test
	public void testCasellaAdiacenteCavalloNordEst(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 10).colonna, 3);
		assertEquals(test.casellaAdiacente(casella, 10).riga, 2);
		
	}
	
	@Test
	public void testCasellaAdiacenteCavalloNordOvest(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 11).colonna, 5);
		assertEquals(test.casellaAdiacente(casella, 11).riga, 2);
		
	}
	
	@Test
	public void testCasellaAdiacenteCavalloEstNord(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 12).colonna, 6);
		assertEquals(test.casellaAdiacente(casella, 12).riga,3);
		
	}
	
	@Test
	public void testCasellaAdiacenteCavalloEstSud(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 13).colonna, 6);
		assertEquals(test.casellaAdiacente(casella, 13).riga, 5);
		
	}
	
	@Test
	public void testCasellaAdiacenteCavalloSudEst(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 14).colonna, 5);
		assertEquals(test.casellaAdiacente(casella, 14).riga, 6);
		
	}
	
	@Test
	public void testCasellaAdiacenteCavalloSudOvest(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 15).colonna, 3);
		assertEquals(test.casellaAdiacente(casella, 15).riga, 6);
		
	}
	
	@Test
	public void testCasellaAdiacenteCavalloOvestSud(){
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 16).colonna, 2);
		assertEquals(test.casellaAdiacente(casella, 16).riga, 5);
		 
	}
	
	@Test
	public void testCasellaAdiacenteCavalloOvestNord(){
		Gioco test = new Gioco();
		Casella casella = new Casella(4,4);
		assertEquals(test.casellaAdiacente(casella, 17).colonna, 2);
		assertEquals(test.casellaAdiacente(casella, 17).riga, 3);
	}
	
	// TEST PUOANDARE ---------------------------------------------------------------------------------
	
	@Test
	public void testPuoAndarePedone(){
		
		//Testa una mossa valida ed una non valida di un pedone nella posizione iniziale
		
		Gioco test = new Gioco();
		
		Mossa mossa = new Mossa(new Casella(6,0));
		
		for(int i = 0; i < 12; i++)
			test.possibilitaScacco[i] = new LinkedList<Mossa>();
		
		test.possibilitaScacco[0].addFirst(mossa);
		
		
		Mossa m0 = test.possibilitaScacco[0].getFirst();
		
		assertEquals(true, test.puoAndare(m0, 2 , test.possibilitaScacco[0], test.possibilitaScacco[0].getFirst().caselleToccate.getFirst()));
		assertFalse(test.puoAndare(m0, 1 , test.possibilitaScacco[0], test.possibilitaScacco[0].getFirst().caselleToccate.getFirst()));
		
	}
	
	@Test
	public void testPuoAndareCavallo(){
		
		//Testa una mossa valida ed una non valida di un cavallo nella posizione iniziale

		Gioco test = new Gioco();
		Mossa mossa = new Mossa(new Casella(7,1));
		
		for(int i = 0; i < 12; i++)
			test.possibilitaScacco[i] = new LinkedList<Mossa>();
		
		test.possibilitaScacco[2].addFirst(mossa);
		
		Mossa m0 = test.possibilitaScacco[2].getFirst();
		
		assertEquals(true, test.puoAndare(m0, 11 , test.possibilitaScacco[2], test.possibilitaScacco[2].getFirst().caselleToccate.getFirst()));
		assertFalse(test.puoAndare(m0, 17 , test.possibilitaScacco[2], test.possibilitaScacco[2].getFirst().caselleToccate.getFirst()));
		
	}
	
	@Test
	public void testPuoAndareTorre(){
		
		// Testa se un pezzo con mosse ricorsive può andare più lontano di una casella e può mangiare
		Gioco test = new Gioco();
		boolean pappe = false;
		
		test.metti(new Casella(5,7), test.TORRE_BIANCA );

		test.possibilitaScacco[1] = test.suggerisciMosse(new Casella(5,7));
		
		for(int i = 0; i < test.possibilitaScacco[1].size(); i++)
			if(Casella.stessa(test.possibilitaScacco[1].get(i).caselleToccate.getLast(), new Casella(1,7)))
				pappe = true;
		
		assertEquals(true, pappe);
	}
	
	@Test
	public void testPuoAndareFattaDama(){
		
		// Testa se un la variabile FattaDama viene impostata correttamente quando un pedone arriva al bordo opposto
		Gioco test = new Gioco();
		
		test.metti(new Casella(1,7), test.PEDONE_BIANCO );
		
		Mossa mossa = new Mossa(new Casella(1,7));
	
		for(int i = 0; i < 12; i++)
			test.possibilitaScacco[i] = new LinkedList<Mossa>();
		
		test.possibilitaScacco[0].addFirst(mossa);
		
		assertEquals(true, test.puoAndare(mossa, 1, test.possibilitaScacco[0], new Casella(1,7)));
		
		assertEquals(true, mossa.fattaDama);
	}
	
	// TEST SCACCO ------------------------------------------------------------------------------------
	
	@Test
	public void testScaccoConCavallo(){
			
		//Testa se un Cavallo bianco fa scacco
		
		Gioco test = new Gioco();
		
		
		test.metti(new Casella(4,6), test.CAVALLO_BIANCO );
		
		Mossa mossa = new Mossa(new Casella(4,6));
		mossa.caselleToccate.add(new Casella(2,5));
		test.esegui(mossa);
		
		assertEquals(true, test.Scacco(test.contenuto(2,5), mossa));
		
	}
	
	@Test
	public void testScaccoConAlfiere(){
			
		//Testa se una Torre fa scacco
		Gioco test = new Gioco();
		
		test.metti(new Casella(3,1), test.ALFIERE_BIANCO );
		test.metti(new Casella(1,3), test.VUOTA );
		
		Mossa mossa = new Mossa(new Casella(3,1));
		mossa.caselleToccate.add(new Casella(2,2));
		test.esegui(mossa);

		assertEquals(true, test.Scacco(test.contenuto(2,2), mossa));
		
	}
	
	@Test
	public void testScaccoConRegina(){
			
		//Testa se una Regina fa scacco
		Gioco test = new Gioco();
		
		test.metti(new Casella(3,1), test.REGINA_BIANCA );
		test.metti(new Casella(1,3), test.VUOTA );
		
		Mossa mossa = new Mossa(new Casella(3,1));
		mossa.caselleToccate.add(new Casella(2,2));
		test.esegui(mossa);

		assertEquals(true, test.Scacco(test.contenuto(2,2), mossa));
		
	}
	
	@Test
	public void testScaccoFalso(){
		
		//Testa se la funzione ritorna falso il caso di un falso scacco ( pedone di fronte al re )
		Gioco test = new Gioco();
		
		test.metti(new Casella(1,4), test.VUOTA );
		test.metti(new Casella(2,4), test.PEDONE_BIANCO );

		Mossa mossa = new Mossa(new Casella(2,4));
		mossa.caselleToccate.add(new Casella(1,4));
		test.esegui(mossa);

		assertFalse(test.Scacco(test.contenuto(1,4), mossa));
		
	}

	// TEST SCACCO MATTO ------------------------------------------------------------------------------
	
	 @Test
	 public void testScaccoMattoCavallo(){
		//Testa se un Cavallo bianco fa scacco matto
			
			Gioco test = new Gioco();

			test.metti(new Casella(4,6), test.CAVALLO_BIANCO );
			Casella casella= new Casella(4,6);
			Mossa mossa = new Mossa(casella);
			mossa.caselleToccate.add(new Casella(2,5));
			test.esegui(mossa);
			
			assertEquals(true, test.scaccoMatto());
	 }
	
	 @Test
	 public void testScaccoMattoRegina(){
		 //Testa se una Regina fa scacco matto
			Gioco test = new Gioco();
			
			test.metti(new Casella(3,1), test.REGINA_BIANCA );
			test.metti(new Casella(1,3), test.VUOTA );
			Casella casella=new Casella(3,1);
			Mossa mossa = new Mossa(casella);
			mossa.caselleToccate.add(new Casella(2,2));
			test.esegui(mossa);

			assertEquals(true, test.scaccoMatto());
	 }
	 
	 // TEST MOSSA VALIDA ------------------------------------------------------------------------------

	@Test
	public void testMossaValidaReBianco(){
		
		// Testa se mettendo il re al centro della scacchiera da 
		// falso quando prova ad andare avanti ( mangiato dai pedoni)
		// falso quando va in alto a destra e vero quando torna indietro
		Gioco test = new Gioco();

		test.metti(new Casella(2,2), test.RE_BIANCO );
		assertFalse(test.mossaValida(test.RE_BIANCO, new Casella(2,2), new Casella(3,2), test.VUOTA, false, false ));
		test.metti(new Casella(2,1), test.RE_BIANCO );
		assertFalse(test.mossaValida(test.RE_BIANCO, new Casella(2,1), new Casella(3,2), test.VUOTA, false, false ));
	
	}
	
	@Test
	public void testMossaValidaReNero(){
		
		// Testa se mettendo il re al centro della scacchiera da 
		// falso quando prova ad andare avanti ( mangiato dai pedoni)
		// falso quando va in alto a destra e vero quando torna indietro
		Gioco test = new Gioco();

		test.metti(new Casella(5,2), test.RE_NERO );
		assertFalse(test.mossaValida(test.RE_NERO, new Casella(5,2), new Casella(4,2), test.VUOTA, false, false ));
		test.metti(new Casella(5,1), test.RE_NERO );
		assertFalse(test.mossaValida(test.RE_NERO, new Casella(5,1), new Casella(4,2), test.VUOTA, false, false ));

	}

	// TEST CONTROLLO MANGATA RE ----------------------------------------------------------------------

	@Test
	public void testControlloMangiataReBianco(){
		//Controlla se il re cade in scacco mangiando qualcosa
		Gioco test = new Gioco();
		
		test.metti(new Casella(3,3), test.RE_BIANCO);
		test.metti(new Casella(2,3), test.PEDONE_NERO);
		
		Mossa m = new Mossa(new Casella(3,3));
		m.caselleToccate.add(new Casella(2,3));
		
		assertEquals(false, test.controlloMangiataRe(test.RE_BIANCO, new Casella(2,3), new Casella(3,3), m, false, false, true, false));
		
			//	int pezzo, Casella c0, Casella c1, )Mossa m, boolean scaccoNuovo, boolean mossaValida, boolean sonoUnReBianco, boolean sonoUnReNero);
	
	}
	
	@Test
	public void testControlloMangiataReNero(){
		//Controlla se il re cade in scacco mangiando qualcosa
		Gioco test = new Gioco();
		
		test.metti(new Casella(4,3), test.RE_NERO);
		test.metti(new Casella(5,3), test.PEDONE_BIANCO);
		
		Mossa m = new Mossa(new Casella(4,3));
		m.caselleToccate.add(new Casella(5,3));
		
		assertEquals(false, test.controlloMangiataRe(test.RE_NERO, new Casella(5,3), new Casella(4,3), m, false, false, false, true));
		
			//	int pezzo, Casella c0, Casella c1, )Mossa m, boolean scaccoNuovo, boolean mossaValida, boolean sonoUnReBianco, boolean sonoUnReNero);
	
	}
	
	// TEST CHI HA VINTO ------------------------------------------------------------------------------
	
	@Test
	public void testchiHaVinto() {
		Gioco test = new Gioco();
		
		
		assertEquals(false, test.chiHaVinto(false)); 	//Test di falsita` con entrambi i re presenti
		
		test.metti(new Casella(0,4), test.VUOTA);
		
		assertEquals(true, test.chiHaVinto(false)); 	//Test di verita` con solo re nero assente
		
		test.metti(new Casella(0,4), test.RE_NERO);

		
		test.metti(new Casella(7,4), test.VUOTA);
		
		assertEquals(true, test.chiHaVinto(false));		//Test di verita` con solo re bianco assente
		
		test.metti(new Casella(7,4), test.RE_BIANCO);

		assertEquals(true, test.chiHaVinto(true));		//Test di verita` con scacco matto ed entrambi i re presenti

	}
}
