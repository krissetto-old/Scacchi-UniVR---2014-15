package controller;
import java.util.*;

// la classe mossa � fatta da una lista di pedine toccate e 
// un booleano che � utilizzato in valuta mossa per dire se le pedine di mossa diventano Regine
public class Mossa{
  // Lista delle caselle toccate. 
  public LinkedList<Casella> caselleToccate;
  // Indica se la mossa termina promuovendo una pedina a dama.
  public boolean fattaDama;
  


  public Mossa(Casella partenza){  
    caselleToccate = new LinkedList<Casella>();
    // la prima casella toccata � quella di partenza
    caselleToccate.addLast(partenza);
    fattaDama = false;
  }

  // Costruisce mossa copiandone un'altra.
  public Mossa(Mossa daCopiare){  
    caselleToccate = new LinkedList<Casella>(daCopiare.caselleToccate);
    fattaDama = daCopiare.fattaDama;
  }

}