import static org.junit.Assert.*;

import org.junit.Test;

import controller.*;


public class TestScacchi {

	@Test
	public void testMetti(){
		
		Gioco test = new Gioco();
		
		Casella casella = new Casella(4,4);
		
		assertTrue(test.metti(casella, 51));
		
	}
	
	// TEST COLORE OPPOSTO ----------------------------------------------------------------------------------------	

	
	@Test
	public void testColoreOpposto(){
		Gioco test = new Gioco();	
		assertEquals(1, test.coloreOpposto(2));
		assertEquals(2, test.coloreOpposto(1));
		assertEquals(0, test.coloreOpposto(0));
		
	}
	
	// TEST CASELLA ADIACENTE ----------------------------------------------------------------------------------------	

	
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
	
	
	
	
	// TEST PUOANDARE INIZIO PARTITA --------------------------------------------------------------------------------------------

	/*
	@Test
	public void testPuoAndarePedoneBianco(){
		
		//Test sul pedone bianco all'inizio della partita
		Gioco test = new Gioco();
		
		boolean bool = true;
	//	Mossa m0 = new Mossa(new Casella(6,0));
		
		for(int pos = 0; pos < test.possibilitaScacco.length; pos++)
			if(!test.puoAndare(test.possibilitaScacco[0].get(pos), 
					test.NORD, 
					test.possibilitaScacco[0], 
					test.possibilitaScacco[0].get(pos).caselleToccate.getFirst()))
					bool = false;

			assertEquals(true, bool);
	}
	*/
	
	@Test
	public void testPuoAndareTorreBianca(){
		
		//Test sulla torre bianca all'inizio della partita
		Gioco test = new Gioco();
		
		Mossa m0 = new Mossa(new Casella(7,1));
		
		assertEquals(true, test.puoAndare(m0, test.C_NORD_O, test.possibilitaScacco[2], new Casella(7,1)));

	}
	
	
	
	
	
	/*
	@Test
	public void testPuoAndarePedone(){
		
		//Testa una mossa valida ed una non valida di un pedone nella posizione iniziale

		Gioco test = new Gioco();
		Mossa m0 = test.possibilitaScacco[0].getFirst();
		
		assertEquals(true, test.puoAndare(m0, 2 , test.possibilitaScacco[0], test.possibilitaScacco[0].getFirst().caselleToccate.getFirst()));
		assertFalse(test.puoAndare(m0, 1 , test.possibilitaScacco[0], test.possibilitaScacco[0].getFirst().caselleToccate.getFirst()));
		
	}
	
	
	@Test
	public void testPuoAndareCavallo(){
		
		//Testa una mossa valida ed una non valida di un cavallo nella posizione iniziale

		Gioco test = new Gioco();
		Mossa m0 = test.possibilitaScacco[2].getFirst();
		
		assertEquals(true, test.puoAndare(m0, 11 , test.possibilitaScacco[2], test.possibilitaScacco[2].getFirst().caselleToccate.getFirst()));
		assertFalse(test.puoAndare(m0, 17 , test.possibilitaScacco[2], test.possibilitaScacco[2].getFirst().caselleToccate.getFirst()));
		
	}
	
	*/
	
	
}
